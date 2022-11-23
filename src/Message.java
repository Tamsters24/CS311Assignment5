/* Tammy Busche
 * CS 311-001:
 * Operating Systems
 * Fall 2022
 * Assignment 5 */

/* •Objects of this type are what will be sent through the Bounded Buffer. */
public class Message {
    /* ▪Messages should have the following interface: */
    /* ◦a constructor that accepts a String message */
    public Message(String message) {}

    /* ◦a isTerminate() method, which returns true if the message is a
     *  terminate message, and false if not. */
    boolean isTerminate() {
        /* ▪if a terminate message is being sent, the contents of the
         *  string in Message MUST be blank. */
        return false;
    }

    void setTerminate() {}

    /* ▪a toString() method that returns the following:
     * ◦"quitter: true" if the message is a terminate message
     * ◦the string message if the message is NOT a terminate message. */
    public String toString() {
        return "message";
    }
}
