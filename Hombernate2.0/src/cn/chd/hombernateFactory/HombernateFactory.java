package cn.chd.hombernateFactory;
/** 
* @author : homels
* @date 创建时间：2018年6月26日 下午8:39:02
* @ClassName: HombernateFactory
* @Description: 单例的工厂类，用于产生session，HomSession的构造函数是protected的 
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
