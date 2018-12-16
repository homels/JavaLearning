package cn.chd.hombernate.pojo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.chd.hombernate.sql.HomReflect;

/** 
* @author : homels
* @date ����ʱ�䣺2018��6��28�� ����4:16:49
* @ClassName: HomMapping
* @Description: ��xml�ļ����ȡһЩ��pojoӳ��
* @version 
*/
public class HomMapping {
	//һ��xml�ļ����һЩ��pojo
	public List<Hompojo> classes = new ArrayList<Hompojo>();
	public Map<String,Hompojo> classes_map1 = new HashMap<String,Hompojo>();
	public Map<String,Hompojo> classes_map2 = new HashMap<String,Hompojo>();
	private HomMapping(){
		
	}
	public static HomMapping i;
	static{
		i = new HomMapping();
		try{
			i.load();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	private void load() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("/hombernate.xml");
		if(inputStream==null)return;
		SAXReader xmlReader = new SAXReader();
		Document x_doc = xmlReader.read(inputStream);
		Element x_root = x_doc.getRootElement();
		inputStream.close();
		//��xml�ļ��ĸ������Զ�ȡ����
		@SuppressWarnings("unchecked")
		List<Element> x_classes = x_root.elements("class");
		for(Element x_class:x_classes){
			Hompojo hp = new Hompojo();
			hp.name = x_class.attributeValue("name");
			hp.table = x_class.attributeValue("table");
			hp.clazz = Class.forName(hp.name);
			@SuppressWarnings("unchecked")
			List<Element> x_props = x_class.elements("property");
			for(Element x_prop:x_props){
				Hompojo.Property p =new Hompojo.Property();
				hp.properties.add(p);
				p.name = x_prop.attributeValue("name");
				p.type = x_prop.attributeValue("type");
				//��method�Ž�pojo��map����
				hp.getters.put(p.name, HomReflect.findGetter(hp.clazz, p.name));
				hp.setters.put(p.name, HomReflect.findSetter(hp.clazz, p.name));
			}
			//��ÿ������������pojo���뵽list�У��Ա����
			addClass(hp);
		}
	}

	private void addClass(Hompojo hp) {
		classes.add(hp);
		classes_map1.put(hp.name, hp);
		classes_map2.put(hp.table, hp);
	}
	
	public Hompojo findClass(String className){
		Hompojo r = classes_map1.get(className);
		if(r!=null) return r;
		return null;
	}
	
}
