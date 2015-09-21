import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class IOCPclient {
	// socket for connection to IOCP server
	private Socket socket;
	// for writing to and reading from the server
	private OutputStreamWriter out;
	private InputStreamReader in;

	public void connect(String ip, Integer port) {
		// connect to IOCP server
		try {
			socket = new Socket(ip, port);
			out = new OutputStreamWriter(socket.getOutputStream());
			in = new InputStreamReader(socket.getInputStream());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			out.write("Hello World!");
			out.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void disconnect() {
		cleanUp();
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
