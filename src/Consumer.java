/* Tammy Busche
 * CS 311-001:
 * Operating Systems
 * Fall 2022
 * Assignment 5 */

import java.util.Random;

public class Consumer implements Runnable {
    private final BoundedBuffer takeBuffer;
    private Message consumerMsg;
    private String consumerName;

    /* •Consumer should have a constructor that takes a BoundedBuffer as a parameter. */
    public Consumer (BoundedBuffer bb) {
        takeBuffer = bb;
    }

    /* ◦Consumer should continually read from the BoundedBuffer by calling the take() method. */
    public void run() {
        consumerName = Thread.currentThread().getName();
        //System.out.println("Hello from " + consumerName);
        //System.out.println("Inside " + consumerName);
        try {
            consumerMsg = (Message) takeBuffer.take();
            while (!consumerMsg.isTerminate()) {
                consumerMsg = (Message) takeBuffer.take();
                consumerMessage();
                consumerSleep();
            }
            if (consumerMsg.isTerminate())
                consumerExit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Something else weird has happened");
        }
    }

    /* ◦When Consumer reads something from the buffer, it should print the contents of the
     *  message that it read, and print the thread name as part of that message. */
    void consumerMessage() {
        System.out.println(consumerName + " has taken a message: " + consumerMsg);
    }

    /* ◦sleep for a random time between 5.5 and 10.5 seconds after each time that it reads from
     *  the BoundedBuffer. You must print the value that your thread is going to sleep
     *  (in milliseconds) prior to sleeping. */
    void consumerSleep() {
        Random randomCnsmrSleep = new Random();
        int mSecSleep = randomCnsmrSleep.nextInt(5500) + 5000;
        try {
            System.out.println(consumerName + " will sleep for "
                    + mSecSleep/1000 + " seconds.");
            Thread.sleep(mSecSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* ◦if the Consumer thread reads a message that indicates to "terminate", then that
     *  particular Consumer thread should exit. */
    void consumerExit() {
        System.out.println(consumerName + " is exiting. Later gator.");
        Thread.currentThread().interrupt();
    }
}
