package cn.chd.hombernate.sql;

import java.lang.reflect.Method;
import java.sql.Types;
import java.text.SimpleDateFormat;

/**
 * @author : homels
 * @date ����ʱ�䣺2018��6��28�� ����3:08:54
 * @ClassName: HomReflect
 * @Description: TODO
 * @version
 */
public class HomReflect {
	static SimpleDateFormat sdf_dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat sdf_d = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat sdf_t = new SimpleDateFormat("HH:mm:ss");

	// ��ȡ���Զ�Ӧ��get������Ӧ�������ַ���
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
		// ��������declaredMethods
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
		//System.out.println("map����");
		if (method == null){
			System.out.println("methodΪ��");
			return;
		}
		if (columnValue == null){
			System.out.println("columnVlaueΪ��");
			return;
		}
		Object arg0 = null;
		//System.out.println("��һ��");
		if (columnType == Types.CHAR || columnType == Types.VARCHAR) {
			arg0 = columnValue;
		} else if (columnType == Types.BIT) {
			// boolean �ͺ�
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
			// �������͵Ĵ���
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
	//	System.out.println("�ڶ���");
		// ����setter����
		Object args[] = { arg0 };
		try {
			System.out.println("yes");
			// ��setter
			method.invoke(pojo, args);
			System.out.println("����setter");
		} catch (Exception e) {
			Class[] parameterTypes = method.getParameterTypes();
			Class c = parameterTypes[0];
			System.out.println("��������:" + c.getCanonicalName() + "��ʵ������:" + arg0.getClass().getCanonicalName());
		}
	//	System.out.println("map����s");
	}
}
//class cn.chd.test.User public void cn.chd.test.User.setAge(int) 4 1
//class test.HomUser public int test.HomUser.getAge() 4 1

