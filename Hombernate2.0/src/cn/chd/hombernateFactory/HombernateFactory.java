package cn.chd.hombernateFactory;
/** 
* @author : homels
* @date ����ʱ�䣺2018��6��26�� ����8:39:02
* @ClassName: HombernateFactory
* @Description: �����Ĺ����࣬���ڲ���session��HomSession�Ĺ��캯����protected�� 
* @version 
*/
public class HombernateFactory {
	private static HombernateFactory hombernateFactory = null;
	private HombernateFactory(){
		
	}
	public synchronized static HombernateFactory getInstance(){
		if(hombernateFactory==null){
			hombernateFactory = new HombernateFactory();
		}
		return hombernateFactory;
	}
	
	public HomSession getNewSession(){
		return new HomSession();
	}
}
