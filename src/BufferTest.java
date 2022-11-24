/* Tammy Busche
 * CS 311-001:
 * Operating Systems
 * Fall 2022
 * Assignment 5

◦Hint: BufferTest may need to keep track of how many threads have been sent the "terminate" message.
◦BufferTest should print out the thread name of any threads that have exited. */

import java.io.*;

public class BufferTest implements Runnable{
    static BoundedBuffer userBuffer;
    static Message msg;

    /* BufferTest should contain your main method, and should do the following: */
    /* ◦main() should accept two command line arguments */
    public static void main (String[]args) {
        System.out.println("hello from BufferTest:main()");
        try {
            /* the first [args] of which is the size of the BoundedBuffer */
            int size = Integer.parseInt(args[0]);

            /* and the second [args] of which is the number of Consumers to create. */
            int numConsumers = Integer.parseInt(args[1]);

            /* ◦Create the BoundedBuffer, with the size being set from command line. */
            userBuffer = new BoundedBuffer(size);

            // Create an array of threads:
            // - A thread for the Producer.
            // - A thread for each Consumer (numConsumers).
            Thread[] t = new Thread[1 + numConsumers];

            /* ◦Create and start X Consumers, with X being set from the command line */
            /* ◦Set the thread name of the Consumer threads to be "Consumer" + X */
            for (int i = 0; i < numConsumers; i++) {
                t[i] = new Thread(new Consumer(userBuffer));
                t[i].setName("Consumer " + i);
            }

            /* ◦Set the thread name of the main thread to be "Producer" */
            t[numConsumers] = new Thread(new BufferTest());
            t[numConsumers].setName("Producer");

            // Start threads
            for (int j = 0; j < (numConsumers + 1); j++)
                t[j].start();

            try {   //step through the array of threads and wait for each of them to finish
                for (int k = 0; k < (numConsumers + 1); k++) {
                    t[k].join();
                }
                System.out.println("=== All threads completed ===");
            } catch (Exception e) {
                System.err.println("join failed");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
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

    /* •Your BufferTest class will act as the producer. */
    /* •All user I/O must be from the terminal. */
    public void run() {
        //System.out.println("Inside Producer");
        try {
            Console cmdInput = System.console();    // Fire up the ol' console
            System.out.println("Producer: Enter a message: ");
            msg = new Message(cmdInput.readLine());
            userBuffer.put(msg);
            /* ◦Continually accept input strings from the user, creating a Message with the input string,
                and then calling the put() method on the BoundedBuffer, each time sending it the Message. */
            while (!msg.isTerminate()) {
                System.out.println("Enter a string: ");
                msg = new Message(cmdInput.readLine());
                userBuffer.put(msg);
            }
            userBuffer.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
