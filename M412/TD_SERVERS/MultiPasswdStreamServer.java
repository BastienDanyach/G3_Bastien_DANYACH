package multiThreads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiPasswdStreamServer {
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("Usage: java KKMultiServer <port number>");
			System.exit(1);
		}
		int portNumber = Integer.parseInt(args[0]);
		boolean listening = true;
		try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
			while (listening) {
				new MultiPasswdStreamServerThread(serverSocket.accept()).start();
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port " + portNumber);
			System.exit(-1);
		}

	}
}