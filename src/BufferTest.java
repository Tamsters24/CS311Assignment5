/* Tammy Busche
 * CS 311-001:
 * Operating Systems
 * Fall 2022
 * Assignment 5

 * •Your BufferTest class will act as the producer.

◦Hint: BufferTest may need to keep track of how many threads have been sent the "terminate" message.
◦BufferTest should print out the thread name of any threads that have exited. */

import java.io.*;

public class BufferTest implements Runnable{
    private String mainThreadName;

    public BufferTest(String nameParam) {
        mainThreadName = nameParam;
    }

    public void run() {
        System.out.println("Inside producer");
    }

    /* BufferTest should contain your main method, and should do the following: */
    /* ◦main() should accept two command line arguments */
    /* •All user I/O must be from the terminal. */
    public static void main (String[]args) {
        System.out.println("hello from BufferTest:main()");
        try {
            /* the first of which is the size of the BoundedBuffer */
            int size = Integer.parseInt(args[0]);

            /* and the second of which is the number of Consumers to create. */
            int numConsumers = Integer.parseInt(args[1]);

            /* ◦Create the BoundedBuffer, with the size being set from command line. */
            BoundedBuffer userBuffer = new BoundedBuffer(size);

            /* ◦Create and start X Consumers, with X being set from the command line */
            /* ◦Set the thread name of the Consumer threads to be "Consumer" + X */
            Thread[] t = new Thread[numConsumers + 1]; // An array of threads for the consumers and main.
            Consumer[] userConsumers = new Consumer[numConsumers];          // An array of consumers.
            for (int i = 0; i < numConsumers; i++) {
                t[i] = new Thread(userConsumers[i]);
                t[i].setName("Consumer " + i);
                userConsumers[i] = new Consumer(userBuffer,t[i].getName());
                t[i].start();
            }

            /* ◦Set the thread name of the main thread to be "Producer" */
            t[numConsumers] = new Thread(new BufferTest("Producer"));
            t[numConsumers].start();

            /* ◦Continually accept input strings from the user, creating a Message with the input string,
                and then calling the put() method on the BoundedBuffer, each time sending it the Message. */
            Console cmdInput = System.console();
            System.out.println("Enter a string: ");
            Message toPut = new Message(cmdInput.readLine());
            userBuffer.put(toPut);

            while (toPut.isTerminate()!=true) {
                System.out.println("Enter a string: ");
                toPut = new Message(cmdInput.readLine());
                userBuffer.put(toPut);
            }
            System.out.println("Terminate here");

        } catch (Exception e) {
            /* ▪A "usage" statement should be printed if the parameters are not
             *  present, followed by a program exit. The "usage" statement should
             *  tell the user how to use the command line and give an example. */
            System.out.println("\t---**************** Error ****************---");
            System.out.println("\t---******* Parameter/s not received ******---");
            System.out.println("A Bounded Buffer will be used to communicate between threads.");
            System.out.println("This program will run a thread acting as a \"Producer\" which will \"write\" ");
            System.out.println("into one end of the buffer. Meanwhile, another thread/s, the \"Consumer/s\",");
            System.out.println("will \"read\" from the other as such:\n");
            System.out.println("                       Bounded Buffer");
            System.out.println("Producer Writes to --> [][][][][][][] --> Consumer/s Read from");
            System.out.println("This example shows a Bounded Buffer with a length of \"7\".\n");
            System.out.println("\t---*** Example Execution from cmd line ***---");
            System.out.println("cmdline> java BufferTest 5 3");
            System.out.println("5: The size of the buffer");
            System.out.println("3: The number of consumers");
            System.out.println("\t---***************************************---\n");
        }
    }
}
