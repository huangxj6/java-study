package my;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者1
 * 
 * @author huangxj
 *
 * @date 2018年6月27日
 * 
 * @version v1.0
 */
public class Subject1 implements Subject{
	
	/**
	 * 被观察者需要维护一个观察者列表
	 */
	private List<Observer> observerList = new ArrayList<>();

	@Override
	public void registerObserver(Observer observer) {
		observerList.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observerList.remove(observer);
	}

	@Override
	public void notifyObservers() {
		
		for (Observer observer : observerList) {
			observer.receive("hi！");
		}
	}

	@Override
	public void work() {
		
		System.out.println("I am  working ~ ！");
		notifyObservers();
	}
}
