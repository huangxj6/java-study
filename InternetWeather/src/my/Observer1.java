package my;

/**
 * 观察者1
 * 
 * @author huangxj
 *
 * @date 2018年6月27日
 * 
 * @version v1.0
 */
public class Observer1 implements Observer{

	@Override
	public void receive(String msg) {
		
		System.out.println("Observer1！-------" + msg);
	}
}
