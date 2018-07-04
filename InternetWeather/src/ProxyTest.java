


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

//出租接口，提供房屋出租的功能
interface IRental {

	// 出租
	public void houseRental(Integer money);

	// 房屋金额
	public Integer getAtLeastMoney();
}

//房东1，具备出租房屋的能力
class Landlord1 implements IRental {

	@Override
	public void houseRental(Integer money) {
		
		if(money < getAtLeastMoney()) {
			System.out.println("给这么少的钱就想租我的房子，没门！");
			return;
		}

		System.out.println("匹配成功，我是房东1，要求租金至少" + getAtLeastMoney());
		System.out.println("收取租金" + money);
		System.out.println("签合同");
	}

	@Override
	public Integer getAtLeastMoney() {
		return 1500;
	}
}

//房东2，具备出租房屋的能力
class Landlord2 implements IRental {

	@Override
	public void houseRental(Integer money) {
		
		if(money < getAtLeastMoney()) {
			System.out.println("给这么少的钱就想租我的房子，没门！");
			return;
		}

		System.out.println("匹配成功，我是房东2，要求租金至少" + getAtLeastMoney());
		System.out.println("收取租金" + money);
		System.out.println("签合同");
	}

	@Override
	public Integer getAtLeastMoney() {
		return 800;
	}
}

//网站
class WebSite {

	private static List<IRental> rentalList = new ArrayList<>();
	
	// 房东可以用该方法来发布房屋信息
	public static void publicRentalInfo(IRental rental) {
		rentalList.add(rental);
	}
	
	// 客户可以用该方法获取所有的待租房屋
	public List<IRental> getRentalList(){
		return rentalList;
	}
}



public class ProxyTest {

	public static void main(String[] args) {

		// 房东把房屋信息发布到网站上
		WebSite.publicRentalInfo(new Landlord1());
		WebSite.publicRentalInfo(new Landlord2());
		
		// 
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

			if (money >= rental.getAtLeastMoney()) {
				return method.invoke(rental, args);
			}
		}

		System.out.println("没有找到合适的，租房失败！");

		return null;
	}
}


