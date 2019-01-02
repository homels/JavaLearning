package Commonality;

@SuppressWarnings("serial")
public class TransportObject implements java.io.Serializable{
	private Object object;
	private int servicetype;
	public TransportObject(int servicetype,Object object)
	{
		this.object=object;
		this.servicetype=servicetype;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public int getServicetype() {
		return servicetype;
	}
	public void setServicetype(int servicetype) {
		this.servicetype = servicetype;
	}
}
