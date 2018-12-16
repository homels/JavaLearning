package cn.chd.hombernate.sql;

import java.lang.reflect.Method;
import java.sql.Types;
import java.text.SimpleDateFormat;

/**
 * @author : homels
 * @date 创建时间：2018年6月28日 下午3:08:54
 * @ClassName: HomReflect
 * @Description: TODO
 * @version
 */
public class HomReflect {
	static SimpleDateFormat sdf_dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat sdf_d = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat sdf_t = new SimpleDateFormat("HH:mm:ss");

	// 获取属性对应的get方法对应的名字字符串
	public static String getter(String fieldName) {
		char firstChar = Character.toUpperCase(fieldName.charAt(0));
		StringBuffer strbuf = new StringBuffer("get" + fieldName);
		strbuf.setCharAt(3, firstChar);
		//System.out.println(strbuf.toString());
		return strbuf.toString();
	}

	public static String setter(String fieldName) {
		char firstChar = Character.toUpperCase(fieldName.charAt(0));
		StringBuffer strbuf = new StringBuffer("set" + fieldName);
		strbuf.setCharAt(3, firstChar);
		return strbuf.toString();
	}

	@SuppressWarnings("rawtypes")
	public static Method findSetter(Class cls, String fieldName) {
		String methodName = setter(fieldName);
		// ？？不是declaredMethods
		Method[] methods = cls.getMethods();
		//methods = cls.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				return method;
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static Method[] findSetter(Class cls, String[] labels) {
		Method[] methods = new Method[labels.length];
		for (int i = 0; i < labels.length; i++) {
			methods[i] = findSetter(cls, labels[i]);
		}
		return methods;
	}

	@SuppressWarnings("rawtypes")
	public static Method findGetter(Class cls, String fieldName) {
		String methodName = getter(fieldName);
		Method[] methods = cls.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				return method;
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static void map(Object pojo, Method method, int columnType, String columnValue) throws Exception {
		//System.out.println("map调用");
		if (method == null){
			System.out.println("method为空");
			return;
		}
		if (columnValue == null){
			System.out.println("columnVlaue为空");
			return;
		}
		Object arg0 = null;
		//System.out.println("第一步");
		if (columnType == Types.CHAR || columnType == Types.VARCHAR) {
			arg0 = columnValue;
		} else if (columnType == Types.BIT) {
			// boolean 型号
			arg0 = (columnValue.equals("1"));
		} else if (columnType == Types.DATE) // date
		{
			arg0 = sdf_d.parse(columnValue);
		} else if (columnType == Types.TIME) // time
		{
			arg0 = sdf_t.parse(columnValue);
		} else if (columnType == Types.TIMESTAMP) // datetime timestamp
		{
			arg0 = sdf_dt.parse(columnValue);
		} else if (columnType == Types.TINYINT || columnType == Types.SMALLINT || columnType == Types.INTEGER
				|| columnType == Types.BIGINT || columnType == Types.DOUBLE || columnType == Types.FLOAT) {
			// 整数类型的处理
			Class[] parameterTypes = method.getParameterTypes();
			Class c = parameterTypes[0];
			if (c.equals(int.class) || c.equals(Integer.class)) {
				arg0 = Integer.valueOf(columnValue);
			} else if (c.equals(long.class) || c.equals(Long.class)) {
				arg0 = Long.valueOf(columnValue);
			} else if (c.equals(short.class) || c.equals(Short.class)) {
				arg0 = Short.valueOf(columnValue);
			} else if (c.equals(byte.class) || c.equals(Byte.class)) {
				arg0 = Byte.valueOf(columnValue);
			} else if (c.equals(float.class) || c.equals(Float.class)) {
				arg0 = Float.valueOf(columnValue);
			} else if (c.equals(double.class) || c.equals(Double.class)) {
				arg0 = Double.valueOf(columnValue);
			}
		}
		System.out.println(arg0);
	//	System.out.println("第二步");
		// 调用setter方法
		Object args[] = { arg0 };
		try {
			System.out.println("yes");
			// 掉setter
			method.invoke(pojo, args);
			System.out.println("调用setter");
		} catch (Exception e) {
			Class[] parameterTypes = method.getParameterTypes();
			Class c = parameterTypes[0];
			System.out.println("期望类型:" + c.getCanonicalName() + "，实际类型:" + arg0.getClass().getCanonicalName());
		}
	//	System.out.println("map结束s");
	}
}
//class cn.chd.test.User public void cn.chd.test.User.setAge(int) 4 1
//class test.HomUser public int test.HomUser.getAge() 4 1

