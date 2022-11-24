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

import java.util.concurrent.locks.*;

public class BoundedBuffer {
    final Lock lock = new ReentrantLock();          // From reference
    final Condition notFull  = lock.newCondition(); // From reference
    final Condition notEmpty = lock.newCondition(); // From reference
    Object[] items;                                 // From reference
    int count, putptr, takeptr;                     // From reference

    /* ◦Your BoundedBuffer class should have a constructor
     *  that takes the size of the buffer as a parameter. */
    public BoundedBuffer(int bufferSize) {
        items = new Object[bufferSize];
        // e.g. new BoundedBuffer(5)
        // -->[][][][][]-->
    }

    /* ◦BoundedBuffer should print a message containing the
     *  thread name prior to waiting on an empty buffer. */
    void emptyBufferWaitMsg() {
        System.out.println(Thread.currentThread().getName()
                + " waiting, buffer empty");
    }

    /* ◦BoundedBuffer should print a message containing the
     *  thread name prior to waiting on a full buffer. */
    void fullBufferWaitMsg() {
        System.out.println("The Bounded Buffer is FULL. "
                + Thread.currentThread().getName() + " will begin waiting.");
    }

    /* ◦Note that the sample code above has put() and get() methods
     *  that deal with class Object. Your BoundedBuffer class needs
     *  to also do this. This means that you may need to cast objects
     *  that you read from the BoundedBuffer. */

    /* ◦put() needs to accept an Object */
    // From reference.                                  // Compare with Day 12 & 13 Slides.
    void put(Object obj) throws InterruptedException {  //put(item) {
        lock.lock();                                    //  lock.acquire();
        try {
            while (count == items.length) {             //  while ((tail-front)==MAX) {
                fullBufferWaitMsg();
                notFull.await();                        //    full.wait(lock);
            }                                           //  }
            items[putptr] = obj;                        //  buf[tail % MAX] = item;
            if (++putptr == items.length)
                putptr = 0;
            count++;                                    //  tail++;
            notEmpty.signal();                          //  empty.signal(lock);
        } finally {
            lock.unlock();                              //  lock.release();
        }
    }                                                   //}

    /* ◦take() needs to return a variable of type Object */
    // From reference.                                  // Compare with Day 12 & 13 Slides.
    Object take() throws InterruptedException {         //get() {
        lock.lock();                                    //  lock.acquire();
        try {
            while (count == 0) {                        //  while (front == tail) {
                emptyBufferWaitMsg();
                notEmpty.await();                       //    empty.wait(lock);
            }                                           //  }
            Object o = items[takeptr];                  //  item = buf[front % MAX]
            if (++takeptr == items.length)
                takeptr = 0;
            count--;                                    //  front++;
            notFull.signal();                           //  full.signal(lock);
            return o;                                   //  return item;
        } finally {
            lock.unlock();                              //  lock.release();
        }
    }                                                   //}
}