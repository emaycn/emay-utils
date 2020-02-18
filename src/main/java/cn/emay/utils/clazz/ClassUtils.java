package cn.emay.utils.clazz;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类工具
 * @author frank
 *
 */
public class ClassUtils {
	
	/**
	 * 获取对象所有的字段
	 */
	public static Field[] getAllFields(Class<?> inclass) {
		Class<?> clazz = inclass;
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

}
