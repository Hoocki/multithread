# Multithreading tasks
- [x] Heavy processing
- [x] Race condition
- [x] Robot movement
- [ ] Payment deadlock
 ### Heavy processing
**Challenge:**
There is a method that does the job in 1 second and is called N times. Change the code to finish all N tasks for about a second by using multithreading tools in parallel.\

**Solution:**\
This task is solved by implementing 2 approaches:
 - executor service by using such methods: submit() and get() 
 - CompletableFuture class by using such methods: supplyAsync() and join()

### Race condition
**Challenge:**
The **Wallet** class is given with the money field and the incr() and get() methods. 
There is also a **ConcurrentTransactor** class that performs a transaction with our wallet.  
The code performs N money transfer transactions in our wallet. 
It is necessary to modify the Wallet class so that it is able to process N transactions. 
We cannot change the return type for the incr() and get() methods.\

**Solution:**\
This task is solved by implementing 3 approaches:
- using keyword ***synchronized*** for method incr()
- using ***AtomicInteger*** object with atomic methods: incrementAndGet() and get()
- using ***ReentrantLock*** object in incr() method

### Robot movement
**Challenge:**
There is a **Leg** class with a constructor that takes the value ***Right*** or ***Left***.  
It is necessary to modify the code so as to ensure the order of movement of the robot's legs and at the same time the launch of 2 threads is preserved

**Solution:**\
This task is solved by implementing 2 approaches:
- using shared object ***lock*** and value ***currentLeg*** to check which thread can execute method
- using ***ScheduledExecutorService*** object to execute each thread periodically with delay

### Payment deadlock
**Challenge:**
Two accounts are given and two streams.
Stream 1 transfers money from account 1 to account 2,
and stream 2 transfers money from account 2 to account 1. 
Implement money transfer from one account to another 
if there are enough funds on it. At the same time, there are 2 cases to implement:
 - The situation of the deadline
 - Fix the deadlock situation by ensuring a secure money transfer
 
**Solution:**