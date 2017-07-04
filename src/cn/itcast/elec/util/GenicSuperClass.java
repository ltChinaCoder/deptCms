package cn.itcast.elec.util;

import java.lang.reflect.ParameterizedType;

public class GenicSuperClass {

	public static Class getClass(Class  class1) {
		ParameterizedType pt=(ParameterizedType) class1.getGenericSuperclass();
		Class entity=(Class) pt.getActualTypeArguments()[0];
		return entity;
	}

}
