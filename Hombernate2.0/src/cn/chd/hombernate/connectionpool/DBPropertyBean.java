package cn.chd.hombernate.connectionpool;
/** 
* @author : homels
* @date ����ʱ�䣺2018��6��26�� ����9:22:14
* @ClassName: DBPropertyBean
* @Description: ���ݿ����ӳص�����bean��
* @version 
*/
public class DBPropertyBean {
	//�ڵ��������ݿ�����
	private String nodeName;
	//������ ���ӣ�com.mysql.jdbc.Driver
	private String dirverName;
	//url ���ӣ�jdbc:mysql://localhost:3306/learning
	private String url;
	//�û���
	private String userName;
	//����
	private String password;
	//���ӳ����������
	private int maxConnections;
	//��С������
	private int minConnrctions;
	//��ʼconnection��
	private int initConnection;
	//�������
	private int conninterval;
	//���Ӷ�����Ӳ��Ͼ��㳬ʱ
	private int timeout;
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getDirverName() {
		return dirverName;
	}
	public void setDirverName(String dirverName) {
		this.dirverName = dirverName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMaxConnections() {
		return maxConnections;
	}
	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}
	public int getMinConnrctions() {
		return minConnrctions;
	}
	public void setMinConnrctions(int minConnrctions) {
		this.minConnrctions = minConnrctions;
	}
	public int getInitConnection() {
		return initConnection;
	}
	public void setInitConnection(int initConnection) {
		this.initConnection = initConnection;
	}
	public int getConninterval() {
		return conninterval;
	}
	public void setConninterval(int conninterval) {
		this.conninterval = conninterval;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
