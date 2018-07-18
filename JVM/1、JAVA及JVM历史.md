#### 1、JAVA技术体系所包含的内容

- 下图展示了JAVA技术体系所包含的内容，以及JDK和JRE所涵盖的范围

  ![image](/image/JVM-1-1.png)

#### 2、JAVA的发展史

- **1991年4月**

  - 由詹姆斯·高林斯博士领导的绿色计划（Green Project）开始启动。

  - 计划目的：开发一种能在各种消费性电子产品上运行的程序架构。

  - 计划的产品就是JAVA语言的前身：OAK

  - OAK在消费品市场上不算成功，后期随着互联网浪潮兴起，成功蜕变为JAVA语言。

- **1995年5月23日**

  - OAK改名为JAVA

  - 在SunWorld大会上正式发布JAVA 1.0版。

  - 第一次提出了"Write Once, Run Anywhere"（一次编译，到处运行）的口号。

- **1996年1月23日**

  - JDK1.0发布，JAVA语言有了第一个正式版本的运行环境。

  - JDK1.0提供了一个纯解释执行的JVM实现（Sun Classic JVM）。

  - JDK1.0包括JVM、APPLET、AWT等

- **1996年4月**

  - 10个最主要的操作系统供应商申明将其产品中嵌入JAVA技术。

  - 5月底，在美国旧金山举行了首届JavaOne大会。

  - 同年9月，已有大约8.3万个网页应用了JAVA技术来制作。

- **1997年2月19日**

  - JDK1.1 发布

  - JDK1.1 技术代表有：JAR格式、JDBC、JavaBeans、RMI等，在语法上也有了一定的发展，如内部类和反射等

  - 直到1999年4月8日，JDK1.1 一共发布了1.1.0 ~ 1.1.8共9个版本。自1.1.4之后，每个版本都会有一个自己的名字，如JDK 1.1.4 - Sparkler（宝石）

- **1998年12月4日**

  - JDK1.2 发布，工程代号Playground（竞技场）

  - 在JDK1.2 中，JAVA技术体系被分为J2SE、J2EE、J2ME三大方向

  - JDK1.2 技术代表有：EJB、Java Plug-in、Java IDL、Swing等。在语言和API上，添加了strictfp关键字与一系列Collections集合类。

  - JDK1.2 第一次在JVM中内置了JIT编译器。

  - 在1999年3月和7月，分别有JDK1.2.1和JDK1.2.2两个版本发布。

- **1999年4月27日**

  - HotSpot虚拟机发布，最初由一家小公司开发，后被SUN公司收购

  - HotSpot虚拟机发布时是作为JDK1.2 的附加程序提供的，后来成为了JDK1.3 及之后所有版本的Sun JDK的默认虚拟机。

- **2000年5月8日**

  - JDK1.3 发布，工程代号Kestrel（美洲红隼）

  - 在一些类库上做了改进（如数学运算和新的Timer API等）

  - JNDI服务开始被作为一项平台级服务提供（以前仅仅是一项扩展）

  - 使用CORBA IIOP来实现RMI的通讯协议

  - 提供了大量新的JAVA 2D API，新添加了JavaSound等类库

  - 2001年5月17日发布JDK1.3修正版JDK1.3.1，代号Ladybird（瓢虫）

  - 自JDK1.3 后，SUN大约每隔两年发布一个JDK的主版本，以动物命名。期间的各个修正版则以昆虫命名。

- **2002年1月13日**

  - JDK1.4 发布，工程代号Merlin（灰背隼）

  - JDK1.4 是JAVA真正走向成熟的版本，IBM等著名公司都有参与甚至实现自己独立的JDK1.4

  - JDK1.4 技术代表有：正则表达式、异常链、NIO、日志类、XML解析器和XSLT转换器等。

  - JDK1.4 有两个修正版，分别为1.4.1的蚱蜢和1.4.2的螳螂

- **2002年前后**

  - 微软公司的.Net Framework发布。

  - 该平台在技术实现或目标用户上都与JAVA有很多相近之处。

- **2004年9月30日**

   - JDK1.5 发布，工程代号Tiger（老虎）

   - JDK1.5 在语法易用性上做了较大的改进，如自动装箱、泛型、动态注解、枚举、可变长参数、foreach循环等

   - 改进了JAVA的内存模型，提供了java.util.concurrent并发包等

- **2006年12月11日**

   - JDK1.6 发布，工程代号Mustang（野马）

   - 在该版本中，开始启用JAVA SE6、JAVA EE6、JAVA ME6的命名方式。

   - 该版本提供了动态语言支持、提供编译API、微型HTTP服务器和API等

   - 该版本对JAVA虚拟机内部做了大量改进，如锁与同步、垃圾收集、类加载等。

- **2006年11月13日**

  - SUN公司宣布JAVA开源

  - 在随后一年多的时间内，陆续将JDK的各个部分在GPL V2下公开了源码。

- **2009年2月9日**

   - JDK1.7 完成了第一个里程碑版本，工程代号Dolphin（海豚）

   - JDK1.7主要改进包括：提供新的G1收集器、加强对非JAVA语言的调用支持、升级类加载架构等。

- **2009年4月20日**

  - Oracle公司以74亿美元的价格收购SUN公司

#### 3、JVM的发展史

- **Sun Classic VM**

  - 世界上第一款商用虚拟机。

  - 这款虚拟机只能使用纯解释器方式来执行JAVA代码，如果要使用JIT编译器，就必须进行外挂，但此时JIT将会完全接管虚拟机的执行系统，解释器将不再工作。

- **Exact VM**

  - 只在JDK1.2、Solaris平台发布，由于后期HotSpot VM的出现，Exact VM很快就被取代了。

  - 支持编译器和解析器混合工作及两级即时编译器等工作模式。

  - Exact Memory Management准确试内存管理（可以知道内存中某个位置的数据具体是什么类型的）

- **JRockit**

  - 最初由BEA公司在2002年的时候从Appeal Virtual Machines公司收购，后被Oracle公司收购。

  - 号称世界上速度最快的虚拟机，专注服务器应用，不太关注程序的启动速度，内部不包含解析器的实现，全部代码都靠即时编译后执行。

  - JRockit的垃圾收集器和MissionControl服务套件（诊断内存泄露）一直处于领先水平。

- **HotSpot VM**

  - 最初由"Longview Technologies"公司设计，来源于Strongtalk VM，1997年被SUN公司收购。

  - HotSpot虚拟机发布时是作为JDK1.2 的附加程序提供的，后来成为了JDK1.3 及之后所有版本的Sun JDK的默认虚拟机。

  - 在Oracle公司收购了BEA公司级SUN公司之后，Oracle公司宣布在不久的将来会将JRockit和HotSpot的优势进行互补。

- **KVM**

  - Kilobyte，简单，轻量，高度可移植，

  - 运行速度慢。

  - 在手机平台上广泛应用。

- **J9**

  - IBM公司开发。

  - IBM Technology for java virtual Machine IT4J

- **Dalvik**

  - 谷歌开发的安卓虚拟机

  - Dex dalvik Executalbe

  - 基于寄存器架构，不是java虚拟机的栈架构，不能直接执行class文件

- **Microsoft JVM**

  - 只能运行在windows平台下

  - 当时是windows平台下运行最快的虚拟机

  - 被sun公司指控，赔偿了sun公司十亿美元左右
