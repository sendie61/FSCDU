import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class IOCPclient implements  Runnable {


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
	// socket for connection to IOCP server
	private Socket socket;
	// for writing to and reading from the server
	private OutputStreamWriter out;
	private InputStreamReader in;
	
	public void run() {
		
	}

	/**
	 * Connect to server on IP:Port
	 */
	public void connect(String ip, Integer port) {
		tryConnect(ip, port);
	}

	/**
	 * Disconnect the connection to the server
	 */
	public void disconnect() {
		cleanUp();
	}

	/**
	 * Disconnect the connection to the server
	 */
	public void sendMessage() {
		if (connectionStatus == CONNECTED) {
			try {
				out.write("Hello World!\n");
				out.flush();
			} catch (IOException ex) {
				System.out.println("General I/O exception: " + ex.getMessage());
				disconnect();
			}
		}
	}

	/**
	 * Read data from server the server
	 */
	public String readMessage() {
		// Receive data
		return "";
	}

	private void tryConnect(String ip, Integer port) {
		// connect to IOCP server
		try {
			socket = new Socket(ip, port);
	//		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	//		out = new PrintWriter(socket.getOutputStream(), true);
			// changeStatusTS(CONNECTED, true);

		} catch (IOException ex) {
			System.out.println("General I/O exception: " + ex.getMessage());
			ex.printStackTrace();
			disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			out.write("Hello World!\n");
			out.flush();
		} catch (Exception ex) {
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
		} catch (IOException e) {
			in = null;
		}

	}
}
