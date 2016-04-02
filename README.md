## Multi Threading Examples In Java
Several examples which explain and implement the features in Java that enable multi-threading.

### Basics
1. Shows how to declare threads i.e. by extending the thread class or by implementing the runnable interface.
2. Use of volatile keyword where a common long variable is read and incremented by multiple threads whose number is defined by constant TOTAL_THREADS. In the comments throughtout code, toggle the comments to explore the use of volatile keyword in action.
3. Use of synchronized keyword which helps in solving the accessibility concurrency problem i.e. An access problem can occur if several thread access and change the same shared data at the same time.  Toggle between the synchronized incrementCounter method i.e. syncIncrementCounter and incrementCounter to see the use. The synchronized keyword in Java ensures:
  - that only a single thread can execute a block of code at the same time.
  - that each thread entering a synchronized block of code sees the effects of all previous modifications that were guarded by the same lock.
4. Use of multiple locks in synchornized code blocks to improve the speed of execution vs. using a single intrinsic lock of the object when used with synchronized methods.
5. Executor framework