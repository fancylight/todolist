package org.example.todo.common;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.common.exception.BusinessException;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ck
 *
 */
@Slf4j
public class BeanUtils {


    /**
     * 重写spring工具类,提供类型转换器
     *
     * @param source
     * @param target
     * @param editable
     * @param ignoreProperties
     * @param typeTrans        转换
     * @throws BeansException
     */
    private static<SV,TV> void copyProperties(Object source, Object target, @Nullable Class<?> editable, Function<SV,TV> typeTrans,
                                              @Nullable String... ignoreProperties) throws BeansException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
                        "] not assignable to Editable class [" + editable.getName() + "]");
            }
            actualEditable = editable;
        }
        PropertyDescriptor[] targetPds = org.springframework.beans.BeanUtils.getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = org.springframework.beans.BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            SV value = (SV) readMethod.invoke(source);
                            TV tranValue = typeTrans.apply(value);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, tranValue);
                        } catch (Throwable ex) {
                            log.info("属性转换失败,转换器失效");
                            continue;
                        }

                }
            }
        }
    }

    /**
     * <pre>
     *     List<UserBean> userBeans = userDao.queryUsers();
     *     List<UserDTO> userDTOs = BeanUtil.batchTransform(UserDTO.class, userBeans);
     * </pre>
     */
    public static <T> List<T> batchTransform(final Class<T> clazz, List<? extends Object> srcList) {
        if (CollectionUtils.isEmpty(srcList)) {
            return Collections.emptyList();
        }

        List<T> result = new ArrayList<>(srcList.size());
        for (Object srcObject : srcList) {
            result.add(transform(clazz, srcObject));
        }
        return result;
    }

    public static <T> List<T> batchTransform(final Class<T> clazz, Iterable<? extends Object> src) {
        Iterator<?> iterator = src.iterator();
        if (!iterator.hasNext()) {
            return Collections.emptyList();
        }

        List<T> result = new ArrayList();
        for (Object srcObject : src) {
            result.add(transform(clazz, srcObject));
        }
        return result;
    }

    /**
     * @param clazz
     * @param srcList
     * @param consumer
     * @param <T>
     * @return
     */
    public static <T, U> List<T> batchTransform(final Class<T> clazz, List<U> srcList, BiConsumer<T, U> consumer) {
        if (CollectionUtils.isEmpty(srcList)) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>(srcList.size());
        for (U srcObject : srcList) {
            result.add(transform(clazz, srcObject, consumer));
        }
        return result;
    }

    /**
     * 对象属性值拷贝，支持将Long类型的字段值格式化为Date类型
     * 枚举转Integer
     *
     * @param targetClazz
     * @param source
     * @param <T>
     * @return
     */
    @SneakyThrows
    public static <T> T transform( final Class<T> targetClazz, Object source, Function<Date, Long> fun) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(targetClazz);

        T t = transform(targetClazz, source);

        BeanInfo sourceInfo = Introspector.getBeanInfo(source.getClass());
        BeanInfo targetInfo = Introspector.getBeanInfo(targetClazz);

        PropertyDescriptor[] sourceInfoPropertyDescriptors = sourceInfo.getPropertyDescriptors();
        PropertyDescriptor[] targetInfoPropertyDescriptors = targetInfo.getPropertyDescriptors();

        for (PropertyDescriptor propertyDescriptor : sourceInfoPropertyDescriptors) {
            Method readMethod = propertyDescriptor.getReadMethod();
            for (PropertyDescriptor targetInfoPropertyDescriptor : targetInfoPropertyDescriptors) {
                Method writeMethod = targetInfoPropertyDescriptor.getWriteMethod();
                Method tReadMethod = targetInfoPropertyDescriptor.getReadMethod();

                if (propertyDescriptor.getName().equals(targetInfoPropertyDescriptor.getName())
                        && tReadMethod.getName().equals(readMethod.getName())
                        && readMethod.getReturnType().equals(Date.class)) {

                    if (targetInfoPropertyDescriptor.getPropertyType().equals(Long.class)) {
                        Object invoke = readMethod.invoke(source);
                        writeMethod.invoke(t, fun.apply((Date) invoke));
                    }

                    if (targetInfoPropertyDescriptor.getPropertyType().equals(long.class)) {
                        Object invoke = readMethod.invoke(source);
                        long value = invoke == null ? 0L : fun.apply((Date) invoke);
                        writeMethod.invoke(t, value);
                    }
                }
            }
        }

        return t;
    }


    private static <T> Map<String, PropertyDescriptor> getPdMap(T obj) {
        BeanWrapper bean = new BeanWrapperImpl(obj);
        PropertyDescriptor[] pds = bean.getPropertyDescriptors();
        return Arrays.stream(pds).collect(Collectors.toMap(PropertyDescriptor::getName, p -> p));
    }

    /**
     * 封装{@link org.springframework.beans.BeanUtils}，惯用与直接将转换结果返回
     *
     * <pre>
     *      UserBean userBean = new UserBean("username");
     *      return BeanUtil.transform(UserDTO.class, userBean);
     * </pre>
     */
    public static <T> T transform(Class<T> clazz, Object src) {
        if (src == null) {
            return null;
        }
        T instance;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            throw new BusinessException(510,e.getMessage());
        }
        org.springframework.beans.BeanUtils.copyProperties(src, instance, getNullPropertyNames(src));
        return instance;
    }

    public static <T> T transform(Class<T> clazz, Object src, Consumer<T> consumer) {
        T instance = transform(clazz, src);
        consumer.accept(instance);
        return instance;
    }

    public static <T, U> T transform(Class<T> clazz, U src, BiConsumer<T, U> consumer) {
        T instance = transform(clazz, src);
        consumer.accept(instance, src);
        return instance;
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
