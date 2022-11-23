/* Tammy Busche
 * CS 311-001:
 * Operating Systems
 * Fall 2022
 * Assignment 5 */

public class Consumer implements Runnable {
    private final String consumerName;
    private final BoundedBuffer takeBuffer;
    private Message consumerMsg;

    /* •Consumer should have a constructor that takes a BoundedBuffer as a parameter. */
    public Consumer (BoundedBuffer bb, String cName) {
        takeBuffer = bb;
        consumerName = cName;
        System.out.println("Hello from " + consumerName);
    }

    public void run() {
        System.out.println("Inside " + consumerName);
        while (takeBuffer.count != 0) {
            try {
                takeBuffer.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* ◦Consumer should continually read from the BoundedBuffer by calling the take() method. */
    void takeCaller() {
    }

    /* ◦When Consumer reads something from the buffer, it should print the contents of the
     *  message that it read, and print the thread name as part of that message. */
    void consumerMessage() {}

    /* ◦sleep for a random time between 5.5 and 10.5 seconds after each time that it reads from
     *  the BoundedBuffer. You must print the value that your thread is going to sleep
     *  (in milliseconds) prior to sleeping. */
    void consumerSleep() {}

    /* ◦if the Consumer thread reads a message that indicates to "terminate", then that
     *  particular Consumer thread should exit. */
    void consumerExit() {}
}
