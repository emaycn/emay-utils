package cn.emay.utils.clazz;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类工具
 *
 * @author frank
 */
public class ClassUtils {

    /**
     * 新建一个实例
     */
    public static <D> D newInstance(Class<D> dataClass) {
        try {
            return dataClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(dataClass.getName() + " can't be new Instance", e);
        }
    }

    /**
     * 获取对象所有的字段
     */
    public static Field[] getAllFields(Class<?> targetClazz) {
        Class<?> clazz = targetClazz;
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            if (clazz.getName().equals(Object.class.getName())) {
                break;
            }
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        fieldList.forEach(fie -> fie.setAccessible(true));
        return fields;
    }

    /**
     * 类型强制转换
     */
    public static <T> T cast(Object data, Class<T> clazz) {
        return clazz.cast(data);
    }

    /**
     * 类型强制转换
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object data) {
        return (T) data;
    }

}
