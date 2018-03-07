package multiThreads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiPasswdStreamServerThread extends Thread {
	private Socket socket = null;
	public MultiPasswdStreamServerThread(Socket socket) {
		super("KKMultiServerThread");
		this.socket = socket;
	}
	public void run() {
		try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
	
		String inputLine, outputLine;
		MultiPasswdStreamProtocol mpsp = new MultiPasswdStreamProtocol();
		outputLine = mpsp.processInput(null);
		out.println(outputLine);
		while ((inputLine = in.readLine()) != null) {
			outputLine = mpsp.processInput(inputLine);
			out.println(outputLine);
			if (outputLine.equals("Bye")) break;
		}
				socket.close();
		} catch (IOException e) {e.printStackTrace();}
	}
}