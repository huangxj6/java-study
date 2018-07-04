package daili;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pool implements InvocationHandler {
	
	private List<IAccess> accessList = new ArrayList<>();
	
	public Pool() {
		init();
	}
	
	private void init() {
		accessList.add(new LocalAccess(1)); 
		accessList.add(new LocalAccess(2)); 
		accessList.add(new LocalAccess(3)); 
		accessList.add(new LocalAccess(4)); 
	}

	public IAccess getAccess() {
		return (IAccess) Proxy.newProxyInstance(IAccess.class.getClassLoader(), 
				new Class[] { IAccess.class }, this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Random random = new Random();
		int index = random.nextInt(4);

		return method.invoke(accessList.get(index), args);
	}

	public static void main(String[] args) throws Exception {

		Pool pool = new Pool();
		IAccess access = pool.getAccess();
		System.out.println(access.getUrl("filename"));
		System.out.println(access.getUrl("filename"));
		System.out.println(access.getUrl("filename"));
		System.out.println(access.getUrl("filename"));
		System.out.println(access.getUrl("filename"));
	}
}
