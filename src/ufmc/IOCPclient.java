package ufmc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class IOCPclient implements Runnable {

	// Connect status constants
	public final static int NULL = 0;
	public final static int DISCONNECTED = 1;
	public final static int DISCONNECTING = 2;
	public final static int BEGIN_CONNECT = 3;
	public final static int INITIATING = 4;
	public final static int CONNECTED = 5;
	// Other constants
	public static int connectionStatus = DISCONNECTED;
	// stuff for connection to IOCP server
	private Socket socket;
	private String ip;
	private Integer port;

	private CDUFrame textFrame;

	// for writing to and reading from the server
	private PrintWriter out;
	private BufferedReader in;

	public void run() {
		while (true) {
			sleep(10);

			switch (connectionStatus) {
			case BEGIN_CONNECT:
				tryConnect(ip, port);
				connectionStatus = INITIATING;
				sendMessage("Arn.Vivo:\r");
				break;
			case INITIATING:
				sendMessage("Arn.Inicio:1:2:3:4:5:6:7:8:9:10:11:12:13:14:");
				connectionStatus = CONNECTED;
				break;
			case CONNECTED:
				readMessage();
				// update CDUFrame here
				break;
			case DISCONNECTING:
				cleanUp();
				connectionStatus = DISCONNECTED;
				break;
			case DISCONNECTED:
				sleep(1000);
				connectionStatus = BEGIN_CONNECT;
				break;
			default:
				break; // do nothing
			}
		}
	}

	/**
	 * sleep
	 */
	public void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // wait milliseconds
	}

	/**
	 * Connect to server on IP:Port
	 */
	public void connect(String newIp, Integer newPort) {
		ip = newIp;
		port = newPort;
		connectionStatus = BEGIN_CONNECT;
	}

	/**
	 * window to write results in
	 */
	public void textFrame(CDUFrame frame) {
		textFrame = frame;
	}

	/**
	 * Disconnect the connection to the server
	 */
	public void disconnect() {
		connectionStatus = DISCONNECTING;
	}

	/**
	 * Disconnect the connection to the server
	 */
	public void sendMessage(String message) {
		if ((connectionStatus == CONNECTED) || (connectionStatus == INITIATING)) {
			try {
				System.out.println("< " + message);
				out.write(message + "\n");
				out.flush();
				if (out.checkError())
					disconnect();
			} catch (Exception ex) {
				System.out.println("General ?? exception: " + ex.getMessage());
				disconnect();
			}
		}
	}

	/**
	 * Read data from server the server
	 */
	public String readMessage() {
		String s = "";
		// Receive data
		try {
			if (in.ready()) {
				s = in.readLine();
				System.out.println("> " + s);
				// textFrame.line[1].setText(s);
				if (s.contentEquals("Arn.Fin:"))
					disconnect();
				if (s.contentEquals("Arn.Vivo:"))
					sendMessage("Arn.Vivo:\r");
				if (s.contains("Arn.Resp"))
					printMessage(s);
			}
		} catch (IOException e) {
			System.out.println("readMessage exception: " + e.getMessage());
		}
		return s;
	}

	/**
	 * Print data from FMC on screen. 
	 * Data is in format: "Arn.Resp:140="ABCD
	 * EFG":141="NEXT LINE":"
	 */
	private void printMessage(String message) {
		String[] lines= message.split("\\:");
		for (String line : lines){
			String[] property= line.split("\\=");
			int lineNr=0;
			try{
				lineNr = Integer.parseInt(property[0]);
			}
			catch (NumberFormatException e){
				lineNr=0;
			}
			if (lineNr>0 && lineNr<15){
				if (property.length==1){
					textFrame.line[lineNr-1].setText("                          ");
				}
				else{
					textFrame.line[lineNr-1].setText(" " + property[1]);
					
				}
			}	
		}
	}

	private void tryConnect(String ip, Integer port) {
		// connect to IOCP server
		try {
			socket = new Socket(ip, port);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException ex) {
			System.out.println("General I/O exception: " + ex.getMessage());
			disconnect();
		} catch (Exception ex) {
			System.out.println("tryConnect exception: " + ex.getMessage());
			ex.printStackTrace();
		}

	}

	// Cleanup for disconnect
	private void cleanUp() {
		try {
			if (socket != null) {
				socket.close();
				socket = null;
			}
		} catch (IOException e) {
			socket = null;
		}

		try {
			if (in != null) {
				in.close();
				in = null;
			}
		} catch (IOException e) {
			in = null;
		}

		try {
			if (out != null) {
				out.close();
				out = null;
			}
		} catch (Exception e) {
			in = null;
		}

	}
}
