
package cn.chd.Export;   

/**  
* ����ʱ�䣺2018��10��28�� ����10:28:56  

* ��Ŀ���ƣ�Depot  
* @author homrls  
* @version 1.0   
* @since JDK 1.8.0_21  
* �ļ����ƣ�Record.java  
* ��˵����  
*/

public class Record {
	private String carNumber;
	private String startTime;
	private String endTime;
	public double fee;
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
}
  
