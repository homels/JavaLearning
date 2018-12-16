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
* @date ����ʱ�䣺2018��6��28�� ����11:28:15
* @ClassName: Hompojo
* @Description: TODO 
* @version 
*/
public class Hompojo {
	//pojo�������
	public String name;
	//��Ӧ���ݿ�������
	public String table;
	//pojo�������
	@SuppressWarnings("rawtypes")
	public Class clazz;
	
	//�����ڲ���
	public static class Property{
		public String name;
		public String type;
	}
	//��һ��pojo�����������
	public List<Property> properties = new ArrayList<Property>();
	
	//��������������
	public String generateKey;
	
	//���һ��pojo��� ������������ ��ֵ��
	public Map<String,Method> getters = new HashMap<String,Method>();
	public Map<String,Method> setters = new HashMap<String,Method>();
	
	//ȡ�������������Ӧ��get����
	public Method findGetter(String columnName){
		return getters.get(columnName);
	}
	//ȡ�������������Ӧ��set����
	public Method findSetter(String columnName){
		return setters.get(columnName);
	}
	
	//���һ�鷽��
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
	
	//��Ľ�������
	@SuppressWarnings("rawtypes")
	public void parse(Class clazz){
		this.name = clazz.getName();
		this.clazz = clazz;
		
		//��ȡ��clazz��Ǽ̳еķ������Լ������ķ�����
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
