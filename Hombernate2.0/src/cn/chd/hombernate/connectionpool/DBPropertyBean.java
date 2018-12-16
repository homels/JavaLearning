package cn.chd.hombernate.connectionpool;
/** 
* @author : homels
* @date 创建时间：2018年6月26日 下午9:22:14
* @ClassName: DBPropertyBean
* @Description: 数据库连接池的属性bean类
* @version 
*/
public class DBPropertyBean {
	//节点名（数据库名）
	private String nodeName;
	//驱动名 例子：com.mysql.jdbc.Driver
	private String dirverName;
	//url 例子：jdbc:mysql://localhost:3306/learning
	private String url;
	//用户名
	private String userName;
	//密码
	private String password;
	//连接池最大链接数
	private int maxConnections;
	//最小链接数
	private int minConnrctions;
	//初始connection数
	private int initConnection;
	//重连间隔
	private int conninterval;
	//链接多久连接不上就算超时
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
