import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * The client class is responsible for taking a string input from the user and
 * will then send the string to the server. It will then recieve a Message
 * object from the server, and will display it's content to the user.
 * 
 * @author Aidan O'Grady
 * @version 2.2
 * @since 1.1
 *
 */
public class Client {
	
	/** String that the client is to send to the server for the message. */
	private String input;
	
	/** The message to be received from the server. */
	private Message message;
	
	/**
	 * Constructor for the Client class. Input and Message are set to dummy
	 * values initially.
	 */
	public Client(){
		// General set up.
		input = "";
		message = null;
		run();
	}
	
	/**
	 * Runs the client, handling the obtaining of the user's input and also
	 * manipulating the objects received from the server. This has been broken
	 * down into further methods below for clarity.
	 */
	private void run(){
		
		Socket socket = null;
		PrintWriter pw = null;
		ObjectInputStream ois = null;
		
		try{
			socket = new Socket("127.0.0.1", 6100);
			
			input = getInput(); // Getting the input string.
			
			//Sending the input to the server.
			pw = new PrintWriter(socket.getOutputStream(), true);
			pw.println(input);
			
			// Receiving the object from the server.
			ois = new ObjectInputStream(socket.getInputStream());
			displayObject(ois.readObject());
						
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		} catch(IOException ioe) {
			System.err.println(ioe);
		} finally {
			pw.close();
		}
	}
	
	/**
	 * Obtains a string input from the user through the use of a scanner.
	 * This will determine what is sent to the server.
	 * 
	 * In this implementation, getInput is executed after the connection is
	 * established, rather than beforehand. This was done to allow for testing
	 * to be easier to do, since when the client is waiting for input, it is
	 * maintaining it's connection to the server, which gives the opportunity
	 * to test other clients' connections during this idling time. 
	 * 
	 * @return the string to be sent to the server.
	 */
	private String getInput(){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter a string: ");
		String input = scanner.nextLine();
		
		scanner.close();
		return input;
	}
	
	/**
	 * After the object has been sent to the client from the server, this
	 * method is used to display its information to the client, after 
	 * assuring that it is of an appropriate type.
	 * 
	 * @param The object sent to the client.
	 */
	private void displayObject(Object object){
		// We must check the object received from server is a Message.
		if (object instanceof Message) {
			message = (MessageImpl) object; // It is, horray!
			
			// Displaying it's information to the client.
			System.out.println("Original String: " + message.getMessage());
			System.out.println("Char Count: " + message.getCharacterCount());
			System.out.println("Digit Count: " + message.getDigitCount());
			
		} else { // Oh dear.
			System.out.println("The recieved object is not of Message type.");
		}
	}
	
	/**
	 * The main method of this class will start the client side execution
	 * of the program.
	 * 
	 * @param Any arguments added during execution in terminal.
	 */
	public static void main(String[] args){
		System.out.println("Hello and welcome to CS313-ACE-2 Client.");
		System.out.println("Author:\tAidan O'Grady");
		System.out.println("Reg No:\t201218150");
		Client client = new Client();
	}

}