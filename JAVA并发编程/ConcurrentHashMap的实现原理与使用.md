#### 1、为什么要使用ConcurrentHashMap？

- ConcurrentHashMap在HashMap的基础上保障了线程的安全性，HashMap在多线程的环境下可能会导致程序出现死循环。

- ConcurrentHashMap效率比HashTable高很多。

  - HashTable通过synchronized来保障线程安全性，在线程竞争较激烈的情况下，会造成线程阻塞或轮询状态。

  - HashTable没有实现读写锁的机制。

- ConcurrentHashMap的锁分段技术可以有效的提升并发访问率

  - HashTable效率低的原因是因为所有访问的线程都必须竞争同一把锁。

  - ConcurrentHashMap则是将数据分成一段一段的存储，然后给每一段数据配一把锁，当一个线程占用锁访问其中一个数据段的时候，其他段的数据也能被其他线程访问。

#### 2、ConcurrentHashMap的结构

- ConcurrentHashMap是由Segment数组结构所组成，Segment是由HashEntry数组结构所组成。具体如下图所示：

  ![image](/image/THREAD-1-1.png)

- Segment在ConcurrentHashMap里扮演锁的角色，是一种可重入锁，结构和HashMap类似，是一种数组和链表结构。

- HashEntry用于存储键值数据，当有线程要对HashEntry数组中的数据进行修改时，必须先获得它所对应的Segment锁才行。

#### 3、ConcurrentHashMap的初始化

- 初始化Segment数组

  - 和HashMap一样，为了能够通过按位与的散列算法来定位Segment数组的索引，必须保证Segment数组的长度的2的N次方。

  - Segment数组的长度ssize是通过从concurrencyLevel计算得出的。concurrencyLevel是用户在创建ConcurrentHashMap时所传入的参数，用于指定并发数量的一个估值，ssize通过位移的方式来保障它的值是2的N此方

    ```java
    if (concurrencyLevel > MAX_SEGMENTS)
        concurrencyLevel = MAX_SEGMENTS;

    int sshift = 0;
    int ssize = 1;
    while (ssize < concurrencyLevel) {
        ++sshift;
        ssize <<= 1;
    }

    this.segmentShift = 32 - sshift;
    this.segmentMask = ssize - 1;
    ```

  - 不传concurrencyLevel的话它的默认值就为16，同时ConcurrentHashMap限制concurrencyLevel的最大值为65535，这意味着Segment数组的长度最大为65536，对应的二进制是16位。

- 初始化每个Segment

#### 4、定位Segment



#### 5、ConcurrentHashMap的操作
