
package pojo;   

/**  
* 创建时间：2018年12月15日 下午3:47:09  

* 项目名称：Daodemo  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* 文件名称：ICCard.java  
* 类说明：  
*/

public class ICCard {
	int id;
	String number;
	String password;
	int userId;
	double balance;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
  


