/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MailClient
{
    // The server used for sending and receiving.
    private MailServer server;
    // The user running this client.
    private String user;
    
    private boolean sendAutoMsg;
    
    private String autoMsg;
    
    private String subject;

    /**
     * Create a mail client run by user and attached to the given server.
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
        sendAutoMsg = false;
        autoMsg = "Estoy de vacaciones";
        subject = "Vacaciones";        
    }

    /**
     * Return the next mail item (if any) for this user.
     */
    public MailItem getNextMailItem()
    {
        if (sendAutoMsg == true)
        {
            MailItem userFrom = server.getNextMailItem(user);
            sendMailItem(userFrom.getFrom(), subject, autoMsg);
        }
        return server.getNextMailItem(user);
    }

    /**
     * Print the next mail item (if any) for this user to the text 
     * terminal.
     */
    public void printNextMailItem()
    {
        MailItem item = server.getNextMailItem(user);
        if(item == null) {
            System.out.println("No new mail.");
        }
        else {
            item.print();
        }
    }

    /**
     * Send the given message to the given recipient via
     * the attached mail server.
     * @param to The intended recipient.
     * @param message The text of the message to be sent.
     */
    public void sendMailItem(String to, String subject, String message)
    {
        MailItem item = new MailItem(user, to, subject, message);
        server.post(item);
    }
    
    public void numberOfMails()
    {
        System.out.println(server.howManyMailItems(user));
    }
    
    public void vacationMode(boolean setVacationMode)
    {
        sendAutoMsg = setVacationMode;
    }
    
    public void modifyAutoMsg(String newAutoMsg, String newSubject)
    {
        autoMsg = newAutoMsg;
        subject = newSubject;
    }
}
