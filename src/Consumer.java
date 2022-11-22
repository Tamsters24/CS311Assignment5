/* Tammy Busche
 * CS 311-001:
 * Operating Systems
 * Fall 2022
 * Assignment 5 */

public class Consumer implements Runnable {
    private String consumerName;

    /* •Consumer should have a constructor that takes a BoundedBuffer as a parameter. */
    public Consumer (BoundedBuffer bb, String cnsmrNm) {
        consumerName = cnsmrNm;
    }

    public void run() {}

    /* ◦Consumer should continually read from the BoundedBuffer by calling the take() method. */
    void takeCaller() {

    }

    /* ◦When Consumer reads something from the buffer, it should print the contents of the
     *  message that it read, and print the thread name as part of that message. */
    void consumerMessage() {

    }

    /* ◦sleep for a random time between 5.5 and 10.5 seconds after each time that it reads from
     *  the BoundedBuffer. You must print the value that your thread is going to sleep
     *  (in milliseconds) prior to sleeping. */
    void consumerSleep() {

    }

    /* ◦if the Consumer thread reads a message that indicates to "terminate", then that
     *  particular Consumer thread should exit. */
    void consumerExit() {

    }
}
