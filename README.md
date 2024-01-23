# Multithreading tasks
### 1) Heavy processing
change code using different multithreading tools to finish all tasks for about a second and print the results
### 2) Robot movement
It is necessary to modify the code so as to ensure the order of movement of the robot's legs. 
For example: If left is output, then the robot moves its left leg, if right, then the right one. Any part of the code can be modified, but it is necessary to keep running 2 threads
### 3) Payment deadlock
Two accounts are given. Each account has a field responsible for the amount of money. Also, 2 streams are given: stream 1 transfers money from account 1 to account 2, and stream 2 transfers money from account 2 to account 1. Implement money transfer from one account to another if there are enough funds on it. At the same time, there are 2 cases to implement:
 - The situation of the deadline
 - Fix the deadlock situation by ensuring a secure money transfer