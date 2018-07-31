#### 1、线程简介

- **什么是线程？**

  - 操作系统在运行一个程序时，会为其创建一个进程。

    - 线程是操作系统调度的最小单元，也叫轻量级进程。

    - 在一个进程里可以创建多个线程，这些线程拥有各自的计数器、堆栈和局部变量等属性，并且能够访问共享的内存变量。

  - 多个线程能够同时执行（处理器在这些线程上高速切换，让使用者感觉这些线程是在同时执行），显著提升程序的性能，在多核环境中表现更加明显。

  - 使用JMX打印当前程序中的所有线程

    ```java
    public static void main(String[] args) {

    	// 获取线程管理MXBean
    	ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

    	// 不需要同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
    	ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);

    	// 打印线程信息
    	for (ThreadInfo threadInfo : threadInfos) {
    		System.out.println(threadInfo.getThreadId() + "-----" + threadInfo.getThreadName());
    	}
    }
    ```
  - 打印结果如下，可以看出一个java程序不仅仅只是main方法在运行，而是main线程和多个其他线程在同时运行：

    ```
    5-----Attach Listener
    4-----Signal Dispatcher
    3-----Finalizer
    2-----Reference Handler
    1-----main
    ```

- **为什么要使用多线程？**

  - 更多的处理器核心

    - 现代大多数计算机处理器核心数通常不止一个，使用多线程技术可以更好的利用处理器上的多个核心，提高计算机的性能。

    - 提升单个程序的执行效率：一个单线程程序在运行时只能使用一个处理器核心，那么加入再多的处理器核心也无法显著提升该程序的执行效率。使用多线程技术可以将计算逻辑分配到多个处理器核心上，这样将会显著减少程序的处理时间，并且随着更多处理器核心的加入而变得更有效率。

  - 更快的响应时间

    - 对于某些业务，可能整体流程跑下来需要很长的一段时间。如订单的创建，其中的操作可能包括了插入订单数据、生成订单快照、发送邮件通知和记录货品销量等，如果是多线程的情况下，那么用户需要等待这些操作全部执行完毕之后才能看到订购成功的结构。

    - 使用多线程技术，可以将**数据一致性不强的操作**派发给其他线程去处理（如创建订单快照，发送邮件等），这样做可以达到快速响应的效果，提升用户体验。

    - 在单线程的情况下，一个时间段内程序只能响应一个用户的请求，其他用户的请求必须等待其前面用户请求处理完毕之后才能被执行。多线程技术可以在计算机的能力范围内尽可能的压榨处理器的性能，保障了多个用户的请求在同一个时间段内可以被同时执行，提高了计算机的处理性能，缩短了用户的等待时间。

  - 更好的编程模型

    - 多线程编程使开发人员能够更加专注于问题的解决，即为所遇到的问题建立合适的模型，而不是绞尽脑汁地考虑如何将其多线程化。

    - 一旦开发人员建立好了模型，稍作修改总是能够方便地映射到java提供的多线程编程模型上。

- **线程优先级**

  - 处理器采用时分的形式调用运行的线程，即每个线程会被分配到若干时间片，当线程的时间片用完了就会发生线程调度，并等待下次分配。

  - 线程分配到的时间片多少也就决定了线程使用处理器资源的多少，线程优先级就是决定该线程能够被分配到处理器资源多少的一个线程属性，**优先级越高的线程分配时间片的数量将会越多**。

  - Thread类通过一个int型的成员变量priority来控制线程的优先级，优先级的范围是1到10，默认是5。

    - 在线程构建时可通过`setPriority(int)`的方式来修改优先级

    - 优先级高的线程分配时间片的数量要多于优先级低的线程。

  - 对线程优先级的设置应满足以下两个要求，确保CPU不会被独占：

      - 频繁阻塞的线程（休眠或I/O操作）需要设置较高的优先级；

      - 偏计算的线程（需要较多CPU时间或偏运算）则设置较低的优先级。

  - 在不同的JVM及操作系统上，线程规则会存在差异，有些操作系统甚至会忽略对线程优先级的设定，**因此优先级不能作为程序正确性的依赖**。

- **线程的状态**

  - 线程总共有6大状态，如下表所示：

    ![image](/image/THREAD-3-1.png)

  - 线程在自身的生命周期中，并不是固定地处于某个状态，而是随着代码的执行在不同状态之间进行切换，如下图所示：

    ![image](/image/THREAD-3-2.png)

  - JAVA将操作系统中运行中和就绪两个状态合并称为运行状态。

  - 阻塞状态（BLOCKED）是线程在阻塞在加入synchronized关键字修饰的方法或代码块（获取锁）时的状态，**但是阻塞在java.concurrent包中Lock接口的线程状态却是等待状态（WAITING）**，因为Lock接口对于阻塞的实现均使用了LockSupport类中的方法。

- **守护线程**

  - 守护线程是一种支持型线程，因为它主要被用作程序中**后台调度以及支持性工作**。

  - 可通过调用线程类中的setDaemon(true)方法将线程设置为守护线程，需要注意的一点就是该方法需要在线程启动前调用，在线程启动之后，将不能再对Daemon属性进行设置。

    ```java
    Thread t = new Thread();
    t.setDaemon(true);
    t.start();
    ```

  - 当JAVA虚拟机中所有线程均执行完毕或只剩下守护线程的时候，JVM将会退出，因此在JVM退出时，守护线程中的finally代码块并不一定会被执行。

  - 在构建守护线程时，不能依靠finally代码块中的内容来确保执行关闭或清理资源的逻辑。

#### 2、线程的启动和终止

- **构造线程**

  - 线程的构造方法是通过调用内部的init方法来实现的。

  - 一个新构造的线程对象是由其父线程来进行空间分配的（也就是说谁创建了该线程，谁就是该线程的父线程），该线程继承了父线程的daemon、priority、加载资源的contextClassLoader及可继承的ThreadLocal。同时还会分配一个唯一的ID来标识这个线程。

  - init的代码大致如下：

    ```java
    private void init(ThreadGroup g, Runnable target, String name, long stackSize, AccessControlContext acc) {

    	if (name == null) {
    		throw new NullPointerException("name cannot be null");
    	}

    	// 当前线程就是该线程的父线程
    	Thread parent = currentThread();

    	this.name = name;
    	this.group = g;
    	this.target = target;
    	this.stackSize = stackSize;

    	// 将daemon、priority属性设置为跟父线程一样
    	this.daemon = parent.isDaemon();
    	this.priority = parent.getPriority();
    	setPriority(priority);

    	// 将父线程的inheritableThreadLocals复制过来
    	if (parent.inheritableThreadLocals != null) {
    		this.inheritableThreadLocals = ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);
    	}

    	// 分配一个线程ID
    	tid = nextThreadID();
    }
    ```

- **启动线程**

  - 当调用线程的start()方法时，该线程将会被启动。

  - star()方法的含义是：当前线程（父线程）同步告知JAVA虚拟机，只要线程规划器空闲，应立即启动star()被调用的线程。

  - 自定义的线程最好能够起个名字，方便后期使用jstack分析程序进行问题排查。

- **线程中断**

  - 中断可以理解为线程中的一个**标识位**，表示一个运行中的线程是否被其他线程进行了中断操作，该标识位可通过调用线程类中的isInterrupted()方法进行查看。

    - 该标识位初始值为false。

    - 当外界调用该线程类的interrupt()方法之后，该标识位的值将会变为true

    - 如果线程已经处于终结状态，即使该线程被中断过，在调用该线程对象的isInterrupted()方法时，依旧会返回false。

    - 许多声明抛出InterruptedException异常的方法（如Thread.sleep），这些方法在抛出InterruptedException异常之前，JVM会先将该线程的中断标识位清除，此时调用isInterrupted()方法时会返回false。

  - 如下代码在调用线程的中断方法前会输出false，在调用中断方法后会输出true

    ```java
  	public static void main(String[] args) throws Exception {

  		Thread t = new Thread(new Runnable() {

  			@Override
  			public void run() {
  				while(true) {}
  			}
  		});

  		t.start();

  		Thread.sleep(100);

  		System.out.println(t.isInterrupted()); // false
  		t.interrupt();
  		System.out.println(t.isInterrupted()); // true
  	}
    ```

  - 如下代码在中断前后输出的都是false

    ```java
  	public static void main(String[] args) throws Exception {

  		Thread t = new Thread(new Runnable() {

  			@Override
  			public void run() {
  				try {Thread.sleep(1000000);} catch (InterruptedException e) {}
  				while(true) {}
  			}
  		});

  		t.start();

  		Thread.sleep(100);

  		System.out.println(t.isInterrupted()); // false
  		t.interrupt();
  		System.out.println(t.isInterrupted()); // false
  	}
    ```
- **过期的suspent()、resume()、stop()**

  - 对于一个运行中的线程，我们可以调用线程的suspent()方法来让运行中的线程暂停下来，通过resume()方法来恢复暂停中的线程，使用stop()方法来停止线程的运行。

  - 这几个方法都是已过期的方法，如suspent()，在调用后线程不会释放已占有的资源（如锁），而是占有着资源进入睡眠状态，容易引发死锁问题。而stop()方法在终结一个线程时不会保证线程资源的正常释放，通常是没有给予线程完成资源释放工作的机会，因此会导致程序可能工作在不确定状态下。

  - 暂停和恢复操作可以使用等待、通知机制来替代。

- **安全的终止线程**

  - 任务中都会有循环结构，只要控制住循环就可以结束任务。

  - 控制循环通常就用定义标记来完成。该标记可以是自定义的，也可以是使用上述所提到的中断标识位。

    ```java
    public static void main(String[] args) throws InterruptedException {

    	Thread t = new Thread() {

    		private boolean flag = true;

    		public void run() {
    			while (flag) {
    				try {
    					System.out.println("hehe");
    					Thread.sleep(5000000); // wait()
    					System.out.println("sleep over");
    				} catch (InterruptedException e) {
    					flag = false;
    				}
    			}
    			System.out.println("exit");
    		};
    	};

    	t.start();

    	Thread.sleep(200);

    	// 执行中断之后，sleep over不会输出
    	t.interrupt();
    }
    ```

#### 3、线程通信

- **synchronized原理**

  - synchronized关键字可以修饰方法或者以同步代码块的形式来使用，它主要确保多个线程在同一时刻，只能有一个线程处于方法或者同步块中，**它保证了线程对变量访问的可见性和排他性，保证了代码块的原子性**。

  - 我们可以通过如下代码来验证synchronized关键字底层的实现原理

    ```java
    public class Test {

      public static void main(String[] args) {

        // synchronized代码块
        synchronized (Test.class) {
        	fun();
        }
      }

      // synchronized修饰方法
      public static synchronized void fun() {

      }
    }

    ```

  - 通过执行`javap -v Test.class`命令的方式查看改类被编译过后的字节码指令如下：

    ```
    public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=1
    	 0: ldc           #1                  // class com/test/Test
    	 2: dup
    	 3: astore_1
    	 4: monitorenter
    	 5: invokestatic  #16                 // Method fun:()V
    	 8: aload_1
    	 9: monitorexit
    	10: goto          16
    	13: aload_1
    	14: monitorexit
    	15: athrow
    	16: return

    public static synchronized void fun();
    descriptor: ()V
    flags: ACC_PUBLIC, ACC_STATIC, ACC_SYNCHRONIZED
    Code:
      stack=0, locals=0, args_size=0
    	 0: return
    ```

  - 在上述类信息描述中，我们可以看到，synchronized对于同步代码块和同步方法的实现方式不同。

    - 对于同步代码块的实现使用了monitorenter（获取锁）和monitorexit（释放锁）指令，其中monitorexit指令在第4行及第14行都出现了，第4行代表正常的锁释放，而第14行则代表抛异常时的锁释放。

    - 对于同步方法的实现则是依靠方法修饰上的ACC_SYNCHRONIZED来完成的。

    - 无论采用哪种方式，本质都是对一个对象的监视器（monitor）进行获取，而这个获取的过程是排他的，也就是同一时刻只能有一个线程获取到由synchronized所保护对象的监视器。

  - 任何一个对象都拥有自己的监视器，当这个对象由同步块或者这个对象的同步方法调用时，执行方法的线程必须先获取到对象的监视器才能进入同步块或同步方法，而没有获取到监视器的线程将会被阻塞在同步块和同步方法的入口处，进入BLOCKED状态；当访问Object的前驱释放了锁，则该释放操作将唤醒阻塞在同步队列中的线程，使其重新尝试对监视器的获取。

    ![image](/image/THREAD-3-3.jpg)

- **等待、通知机制**

  - 等待通知机制的原理如下图所示：

    ![image](/image/THREAD-3-4.png)

  - 图中线程1首先获取了对象的锁，然后调用了对象的wait方法，从而放弃了锁并进入了对象的等待队列（WaitQueue）中进入WAITING状态。由于线程1释放了对象的锁（Monitor.Exit）,线程2随后便获取了对象的锁，并调用对象的notify方法将线程1从等待队列中移到同步队列（SynchronizedQueue）中去，此时线程1的状态由WAITING变为了BLOCKED状态。线程2释放锁之后，线程1再次获取锁并从wait()方法返回继续执行。

  - 等待、通知机制的经典范式

    - 等待方伪代码如下：

      ```java
      synchronized (对象) {

        while(条件不足) {
          对象.wait();
        }

        对应的处理逻辑
      }
      ```

    - 通知方伪代码如下：

      ```java
      synchronized (对象) {

        改变条件
        对象.notifyAll();
      }
      ```

- **Thread.join()的使用**

  - 在多线程的运行环境中，线程类的join方法可以起到控制不同线程间的执行顺序的作用。

  - 在主线程中执行了线程1的join方法，那么主线程就会释放执行权和执行资格，让线程1先执行，等待线程1执行完毕后，主线程才会重新得到执行资格。

    ```java
    public class Test {

    	public static void main(String[] args) throws InterruptedException {

    		Thread t1 = new Thread();
    		t1.start();

    		// 执行了这一句之后，主线程会释放执行权和执行资格，等待t1线程执行完毕之后才重新获得执行资格
    		t1.join();
    	}
    }
    ```
  - join的实现原理也是利用了等待通知机制，我们可以看到join的代码实现如下，当线程1还是处于激活状态时，则让调用方进入等待状态：

    ```java
    // 加锁当前的线程对象
    public final synchronized void join(long millis) throws InterruptedException {

      // 条件不满足，继续等待
      while (isAlive()) {
          wait(0);
      }

      // 条件符合，方法返回
    }
    ```

  - 当线程1终止时，会调用线程自身的notifyAll()方法，唤醒所有等待在该线程对象上的线程。

  
