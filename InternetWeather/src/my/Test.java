package my;

public class Test {

	
	public static void main(String[] args) {
		
		Subject subject = new Subject1();
		
		subject.registerObserver(new Observer1());
		subject.registerObserver(new Observer2());
		
		subject.registerObserver(new Observer() {
			
			@Override
			public void receive(String msg) {
				
				System.out.println("匿名内部类！-------" + msg);
			}
		});
		
		
		
	}
	
	
}
