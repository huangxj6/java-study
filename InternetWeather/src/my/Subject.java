package my;

/**
 * 被观察者
 * 
 * @author huangxj
 *
 * @date 2018年6月27日 
 * 
 * @version v1.0
 */
public interface Subject {
	
	/**
	 * 注册一个观察者
	 * 
	 * @author huangxj
	 *
	 * @date 2018年6月27日 
	 * 
	 * @version v1.0
	 */
	public void registerObserver(Observer observer);
	
	/**
	 * 移除一个观察者
	 * 
	 * @author huangxj
	 *
	 * @date 2018年6月27日
	 * 
	 * @version v1.0
	 */
	public void removeObserver(Observer observer);
	
	/**
	 * 通知观察者
	 * 
	 * @author huangxj
	 *
	 * @date 2018年6月27日 下午4:46:13
	 * 
	 * @version v1.0
	 */
	public void notifyObservers();
	
	/**
	 * 业务方法，规定被观察者工作时告知所有观察者
	 * 
	 * @author huangxj
	 *
	 * @date 2018年6月27日 
	 * 
	 * @version v1.0
	 */
	public void work();
}
