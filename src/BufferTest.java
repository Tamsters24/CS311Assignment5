/* Tammy Busche
 * CS 311-001:
 * Operating Systems
 * Fall 2022
 * Assignment 5

 * •Your BufferTest class will act as the producer.

◦Hint: BufferTest may need to keep track of how many threads have been sent the "terminate" message.
◦BufferTest should print out the thread name of any threads that have exited. */

import java.util.InputMismatchException;
import java.io.*;

public class BufferTest implements Runnable{
    private String mainThreadName;

    public BufferTest(String nameParam) {
        mainThreadName = nameParam;
    }

    public void run() {}

    /* BufferTest should contain your main method, and should do the following: */
    /* ◦main() should accept two command line arguments */
    /* •All user I/O must be from the terminal. */
    public static void main (String[]args) {
        try {
            /* the first of which is the size of the BoundedBuffer */
            int size = Integer.valueOf(args[0]);

            /* and the second of which is the number of Consumers to create. */
            int numConsumers = Integer.valueOf(args[0]);

            Console cmmdInput = System.console();

            /* ◦Create the BoundedBuffer, with the size being set from command line. */
            BoundedBuffer userBuffer = new BoundedBuffer(size);

            /* ◦Create and start X Consumers, with X being set from the command line */
            /* ◦Set the thread name of the Consumer threads to be "Consumer" + X */
            Thread[] t = new Thread[numConsumers + 1]; // An array of threads for the consumers and main.
            Consumer[] userConsumers = new Consumer[numConsumers];          // An array of consumers.
            for (int i = 0; i < numConsumers; i++) {
                String consumerName = "Consumer " + String.valueOf(i);
                userConsumers[i] = new Consumer(userBuffer,consumerName);
                t[i+1] = new Thread(userConsumers[i]);
            }

            /* ◦Set the thread name of the main thread to be "Producer" */
            t[numConsumers] = new Thread(new BufferTest("Producer"));

            /* ◦Continually accept input strings from the user, creating a Message with the input string,
             and then calling the put() method on the BoundedBuffer, each time sending it the Message. */
            String inputStr = "";
            /*while (inputStr != "terminate") {
                System.out.println("Enter a string: ");
                inputStr = cmmdInput.nextLine();
            }*/
            System.out.println("inputStr = " + inputStr);
            //cmmdInput.close();

        } catch (InputMismatchException e) {
            /* ▪A "usage" statement should be printed if the parameters are not
             *  present, followed by a program exit. The "usage" statement should
             *  tell the user how to use the command line and give an example. */
            System.out.println("---*** Parameter/s not received ***---");
            System.out.println("A Bounded Buffer is used to communicate between threads.");
            System.out.println("This program will run a thread acting as a \"Producer\" which will \"write\" ");
            System.out.println("into one end of the buffer. Meanwhile, another thread, the \"Consumer\",");
            System.out.println("will \"read\" from the other as such:\n");
            System.out.println("\t\t\t\t\t\t  Bounded Buffer");
            System.out.println("\tProgram Writes to --> [][][][][][][] --> Comsumer/s Read from\n");
            System.out.println("This example shows a Bounded Buffer with a length of \"7\". The user should ");
            System.out.println("enter a numerical value/s corresponding to the requested input displayed ");
            System.out.println("on the screen.");
            System.out.println("Example:");
            System.out.println("On-Screen - \"Please enter the size of the Bounded Buffer: 7\"");
            System.out.println("User enters the number \"7\".");
            System.out.println("On-Screen - \"Please enter the number of Consumers to create: 2\"");
            System.out.println("User enters the number \"2\", meaning that two (2) Consumers will read ");
            System.out.println("output from the Bounded Buffer.");
        }
    }
}
