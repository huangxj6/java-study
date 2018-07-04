package my;

/**
 * 观察者
 * 
 * @author huangxj
 *
 * @date 2018年6月27日
 * 
 * @version v1.0
 */
public interface Observer {
	
	/**
	 * 定义相关事件供被观察者调用
	 * 
	 * @author huangxj
	 *
	 * @date 2018年6月27日
	 * 
	 * @version v1.0
	 */
	public void receive(String msg);
}
