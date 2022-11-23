/* Tammy Busche
 * CS 311-001:
 * Operating Systems
 * Fall 2022
 * Assignment 5 */

/* •Objects of this type are what will be sent through the Bounded Buffer. */
public class Message {
    String messageContent;
    boolean terminate;

    /* ▪Messages should have the following interface: */
    /* ◦a constructor that accepts a String message */
    public Message(String message) {
        messageContent = message;
        setTerminate(messageContent);
    }

    /* ◦a isTerminate() method, which returns true if the message is a
     *  terminate message, and false if not. */
    boolean isTerminate() {
        System.out.println("Terminate: " + terminate);
        return terminate;
    }

    /* ▪if a terminate message is being sent, the contents of the
     *  string in Message MUST be blank. */
    void setTerminate(String msgContent) {
        if (msgContent.equals(""))
            terminate = true;
        else
            terminate = false;
    }

    /* ▪a toString() method that returns the following:
     * ◦"quitter: true" if the message is a terminate message
     * ◦the string message if the message is NOT a terminate message. */
    public String toString() {
        if (isTerminate())
            messageContent = "quitter: true";
        return messageContent;
    }
}
