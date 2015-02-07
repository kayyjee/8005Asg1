

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
/**
 *
 * @author Kyle
 * 
 * This class will create our 5 worker processes. Each worker will have already been created in the form
 * of a jar file. This class will use the location of the jar file and create a command to enter 
 * into the command line containing that location. It will then execute that command at the command line
 * and have initialized our worker processes. 
 * 
 * 
 */


public class ProcessesDriver {

    public static void main(String[] args) throws IOException, InterruptedException {

        //The location of the Jar files which contain their respective set of numbers and tasks
        String worker1Location = "C:\\Asg1\\src\\worker1.jar";
        String worker2Location = "C:\\Asg1\\src\\worker2.jar";
        String worker3Location = "C:\\Asg1\\src\\worker3.jar";
        String worker4Location = "C:\\Asg1\\src\\worker4.jar";
        String worker5Location = "C:\\Asg1\\src\\worker5.jar";
        
        //The commands below create processes with the parameters being cmd line arguements
        ProcessBuilder worker1Process = new ProcessBuilder("java", "-jar", worker1Location);
        ProcessBuilder worker2Process = new ProcessBuilder("java", "-jar", worker2Location);
        ProcessBuilder worker3Process = new ProcessBuilder("java", "-jar", worker3Location);
        ProcessBuilder worker4Process = new ProcessBuilder("java", "-jar", worker4Location);
        ProcessBuilder worker5Process = new ProcessBuilder("java", "-jar", worker5Location);
        
        //Entering our processes at the cmd line
        try {
            worker1Process.start();
            worker2Process.start();
            worker3Process.start();
            worker4Process.start();
            worker5Process.start();

        } catch (IOException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
        
        //Output that we have started our workers, close program
        System.out.println("Please Wait, calculations in progress");
        
        
        System.exit(0);
        WriteToLog.main(args);
    }
}
