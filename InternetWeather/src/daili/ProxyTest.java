package daili;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;


/**
 * 场景：房东找代理，委托中介将自己的房子出租出去
 * 
 * 在代理模式中，中介起到的作用是帮助房东过滤租客，找到合适的租客之后，会把租客的请求转发给房东，中介不对租房方法作额外的功能扩展
 * 
 * 在装饰器模式中，代码实现起来可能和代理模式有点像。装饰器要求的是对方法作额外的扩展。
 * 
 * 租房者租房，有两个场景
 * 	1、租房者从房东1找到房东10，直到找到合适的为止
 * 	2、每一个租房者来找房东的时候，房东都必须判断这个租房者是否符合我的要求（租金能否给够，只租给女生等等）
 * 
 * 但我们想想，租房者的目的就只是租房，房东的目的就只是想把房子租出去，租房者找房东以及房东匹配租房者这些其实都是一些额外的事情
 * 为了增加代码的内聚性，我们可以加一个代理（也就是中介），租房者负责给钱及住房，房东负责收钱及提供房子，中介负责给两者牵线搭桥（匹配）。
 * 
 * 对于租房者来说，中介就是一个能满足我任何要求的房东，我提什么样的要求，中介就能给什么样的房子。
 * 对于房东来说，房东不需要做匹配的这个动作，租房者有很多个，中介只有一个，租房者无法直接接触到房东，他必须经过中介，当中介来找房东的时候，就说明中介已经帮房东匹配过了。
 * 
 * @author huangxj
 *
 * @date 2018年7月3日 下午10:57:34
 * 
 * @version v1.0
 */
public class ProxyTest {
	
	public static void main(String[] args) {
		
		// 有一个中介
		Mediation mediation = new Mediation();
		
		// 房东1和房东2都让他帮忙租房
		mediation.addIRental(new Landlord1());
		mediation.addIRental(new Landlord2());
		
		// 租房者把中介就认为是房东（具备房屋出租能力的人）
		IRental rental = mediation.getInstance();
		
		// 传入租金，直接执行租房操作
		rental.houseRental(20000);
	}
}

/**
 * 出租接口，提供房屋出租的功能
 * 
 * @author huangxj
 *
 * @date 2018年7月3日 下午9:08:59
 * 
 * @version v1.0
 */
interface IRental {

	/**
	 * 出租
	 * 
	 * @author huangxj
	 *
	 * @date 2018年7月3日 下午9:08:09
	 * 
	 * @version v1.0
	 */
	public void houseRental(Integer money);
	
	/**
	 * 获取最少的房屋租金
	 * 
	 * @author huangxj
	 *
	 * @date 2018年7月3日 下午11:03:39
	 * 
	 * @version v1.0
	 */
	public Integer getAtLeastMoney();
}

/**
 * 房东1，具备出租房屋的能力
 * 
 * @author huangxj
 *
 * @date 2018年7月3日 下午9:08:55
 * 
 * @version v1.0
 */
class Landlord1 implements IRental {
	
	@Override
	public void houseRental(Integer money) {
		
		System.out.println("匹配成功，我是房东1，要求租金至少" + getAtLeastMoney());
		System.out.println("收取租金" + money);
		System.out.println("签合同");
	}

	@Override
	public Integer getAtLeastMoney() {
		return 1500;
	}
}

/**
 * 房东2，具备出租房屋的能力
 * 
 * @author huangxj
 *
 * @date 2018年7月3日 下午9:08:55
 * 
 * @version v1.0
 */
class Landlord2 implements IRental {


	@Override
	public void houseRental(Integer money) {
		
		System.out.println("匹配成功，我是房东2，要求租金至少" + getAtLeastMoney());
		System.out.println("收取租金" + money);
		System.out.println("签合同");
	}

	@Override
	public Integer getAtLeastMoney() {
		return 800;
	}
}

/**
 * 中介
 * 
 * @author huangxj
 *
 * @date 2018年7月3日 下午9:12:33
 * 
 * @version v1.0
 */
class Mediation implements InvocationHandler {

	private List<IRental> rentalList = new ArrayList<>();

	/**
	 * 房东可以把自己的房子交给中介来出租
	 * 
	 * @author huangxj
	 *
	 * @date 2018年7月4日 上午8:16:13
	 * 
	 * @version v1.0
	 */
	public void addIRental(IRental rental) {
		rentalList.add(rental);
	}

	/**
	 * 获取一个中介
	 * 
	 * @author huangxj
	 *
	 * @date 2018年7月3日 下午9:26:30
	 * 
	 * @version v1.0
	 */
	public IRental getInstance() {

		Class<IRental> clazz = IRental.class;
		return (IRental) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		// 拿到租房者能给的租金
		Integer money = (Integer) args[0];
		
		// 遍历所有房东，判断哪个房东合适
		for (IRental rental : rentalList) {
			
			if(money >= rental.getAtLeastMoney()) {
				return method.invoke(rental, args);
			}
		}
		
		System.out.println("没有找到合适的，租房失败！");
		
		return null;
	}
}
