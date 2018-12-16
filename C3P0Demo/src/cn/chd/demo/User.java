package cn.chd.demo;
/** 
* @author : homels
* @date 创建时间：2018年5月25日 下午4:34:48
* @ClassName: User
* @Description: TODO 
* @version 
*/
public class User {
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return id+"+"+name;
	}
}
