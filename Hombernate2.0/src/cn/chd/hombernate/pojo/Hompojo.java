package cn.chd.hombernate.pojo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.chd.hombernate.sql.HomReflect;


/** 
* @author : homels
* @date 创建时间：2018年6月28日 上午11:28:15
* @ClassName: Hompojo
* @Description: TODO 
* @version 
*/
public class Hompojo {
	//pojo类的名字
	public String name;
	//对应数据库表的名字
	public String table;
	//pojo类的类型
	@SuppressWarnings("rawtypes")
	public Class clazz;
	
	//属性内部类
	public static class Property{
		public String name;
		public String type;
	}
	//存一个pojo类的所有属性
	public List<Property> properties = new ArrayList<Property>();
	
	//自增主键的列名
	public String generateKey;
	
	//存放一个pojo类的 方法、方法名 键值对
	public Map<String,Method> getters = new HashMap<String,Method>();
	public Map<String,Method> setters = new HashMap<String,Method>();
	
	//取出这个属性名对应的get方法
	public Method findGetter(String columnName){
		return getters.get(columnName);
	}
	//取出这个属性名对应的set方法
	public Method findSetter(String columnName){
		return setters.get(columnName);
	}
	
	//获得一组方法
	public Method[] findGetters(String[] columns){
		Method[] result = new Method[columns.length];
		for(int i = 0;i<columns.length;i++){
			result[i] = getters.get(columns[i]);
		}
		return result;
	}
	public Method[] findSetters(String[] columns){
		Method[] result = new Method[columns.length];
		System.out.println(setters.get(columns[0]));
		for(int i = 0;i<columns.length;i++){
			result[i] = setters.get(columns[i]);
		}
		return result;
	}
	
	//类的解析操作
	@SuppressWarnings("rawtypes")
	public void parse(Class clazz){
		this.name = clazz.getName();
		this.clazz = clazz;
		
		//获取类clazz里非继承的方法（自己声明的方法）
		Field[] fileds = clazz.getDeclaredFields();
		for(Field filed:fileds){
			Hompojo.Property p = new Hompojo.Property();
			p.name = filed.getName();
			p.type = "varchar";
			this.properties.add(p);
			this.setters.put(p.name,HomReflect.findGetter(clazz, p.name));
			this.getters.put(p.name, HomReflect.findSetter(clazz, p.name));
		}
	}
}
