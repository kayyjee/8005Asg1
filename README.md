# 8005Asg1
Comparing processes vs. threads in Java
Objective
Using multiple processes and threads in the Windows environment, to measure the performance and efficiency of each mechanism.  


Constraints
•	The implementation must have the same number of workers. In my case, each program will use 5.
•	The design must be the same for both the process and thread implementation. In my case, both
Are collecting the prime factors for large numbers and outputting the results to log files.


APPLICATION DESIGN
•	Language used: Java
•	Environment used: Windows
•	Computation done by workers: Factoring the primes of large numbers

I chose to get the prime factors of prime numbers because it relates to network security in that large prime numbers are used for encryption. The fact that prime factorization takes a long time is the foundation for many cryptographic algorithms, in particular, the RSA encryption algorithm. 

•	Each of my workers is given a set of large integers. It is given the task of getting the prime factors for each of the numbers in its set. By accessing the system time I will be able to get the amount of seconds it took for the workers to execute. From there I will calculate the average worker time and compare the average of threads vs. the average of processes. 

•	I split it up into 5 different performance tests, with each test having 3 runs. The tests were as follows.

1.	Each worker 5 numbers (1000000000 to 1000000024)
2.	Each worker 10 numbers (1000000000 to 1000000049)
3.	Each worker 20 numbers (1000000000 to 1000000099)
4.	Each worker 50 numbers (1000000000 to 1000000249)
5.	Each worker 100 numbers (1000000000 to 1000000499)

Program File Structure

Threads 
 
ThreadsDriver:  This class acts as a driver initializing the threads and then using ThreadsPrimes methods to get the prime factors and  coming up with an average time for the threads. The average time as well as the results of the prime factors are all written to a log file.
ThreadsPrimes: This class contains the methods used by ThreadsDriver to get the prime factors. Using system time we get the time taken for 5 worker threads to execute a getPrimes method which requires a large number of calculations and processor power.

Processes
 
ProcessesDriver: This class will create our 5 worker processes. Each worker will have already been created in the form of a jar file. This class will use the location of the jar file and create a command to enter into the command line containing that location. It will then execute that command at the command line and have initialized our worker processes.
WriteToLog: This program will read from the 5 worker log files and write the contents into a neat master log file. It stores the contents of the worker log files in arrays. It then reads the time taken for each worker to execute and compares the values to come up with an average time. All of this is then written to the master log file.
Worker*: This class will be converted into a runnable Jar file by the command line that will act as an individual process. When it is run, it will take the system time before calculating the prime factors for its set of numbers.  When it is finished, it will take the system time again and with both, find the time it took in seconds. All of the calculations will be written to a log file.

All of the .jar files were created with the following command:
 


jar -cvfe worker1.jar Worker1 Worker1.class











EXECUTION INSTRUCTIONS
THREADS
Compile Code

1.	From Terminal / Command Line, navigate to the Asg1/src folder.
2.	Enter the command: java ThreadsDriver

Feel free to change the values within ThreadsDriver or ThreadsPrimes. Comments will aid in changing data values.

PROCESSES
Compile Code and put in appropriate folder

1.	Navigate to Asg1/src folder.
2.	Enter the command: java ProcessesDriver
Worker log files will be made upon completion of workers. To create a master log file:

3.	Enter the command: java WriteToLog 

To play around with the values the worker calculates, edit the Worker*.java files. 
You must then compile them with:   javac –Xlint Worker*.java
Finally, to create the jar files, enter:  jar –cvfe worker*.jar Worker* Worker*.class
Where * is the worker #. 

Once completed, just enter: java ProcessesDriver 









