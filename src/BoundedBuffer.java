/* Tammy Busche
 * CS 311-001:
 * Operating Systems
 * Fall 2022
 * Assignment 5
 *
 * Reference Code:
 * https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/Condition.html
 * see BoundedBufferSampleCode.java
*/

public class BoundedBuffer {
    /* ◦Your BoundedBuffer class should have a constructor
     *  that takes the size of the buffer as a parameter. */
    public BoundedBuffer(int bufferSize) {

    }

    /* ◦BoundedBuffer should print a message containing the
     *  thread name prior to waiting on an empty buffer. */
    void emptyBufferPrint() {

    }

    /* ◦BoundedBuffer should print a message containing the
     *  thread name prior to waiting on a full buffer. */
    void fullBufferPrint() {

    }

    /* ◦Note that the sample code above has put() and get() methods
     *  that deal with class Object. Your BoundedBuffer class needs
     *  to also do this. This means that you may need to cast objects
     *  that you read from the BoundedBuffer. */

    /* ◦take() needs to return a variable of type Object */
    Object take() {
        Object o = new Object();
        return o;
    }

    /* ◦put() needs to accept an Object */
    void put(Object obj) {

    }
}