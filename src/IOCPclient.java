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
	public final static int CONNECTED = 4;
	// Other constants
	public final static String statusMessages[] = { " Error! Could not connect!", " Disconnected", " Disconnecting...",
			" Connecting...", " Connected" };
	public static int connectionStatus = DISCONNECTED;
	public static String statusString = statusMessages[connectionStatus];
	// stuff for connection to IOCP server
	private Socket socket;
	private String ip;
	private Integer port;

	// for writing to and reading from the server
	private PrintWriter out;
	private BufferedReader in;

	public void run() {
		while (true) {
			sleep(10);

			switch (connectionStatus) {
			case BEGIN_CONNECT:
				tryConnect(ip, port);
				break;
			case CONNECTED:
				readMessage();
				sendMessage("HELLO!!");
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
	 * Disconnect the connection to the server
	 */
	public void disconnect() {
		connectionStatus = DISCONNECTING;
	}

	/**
	 * Disconnect the connection to the server
	 */
	public void sendMessage( String message) {
		if (connectionStatus == CONNECTED) {
			try {
				out.write(message+"\n");
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
			}
		} catch (IOException e) {
			System.out.println("readMessage exception: " + e.getMessage());
		}
		return s;
	}

	private void tryConnect(String ip, Integer port) {
		// connect to IOCP server
		try {
			socket = new Socket(ip, port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			// changeStatusTS(CONNECTED, true);
			connectionStatus = CONNECTED;
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
