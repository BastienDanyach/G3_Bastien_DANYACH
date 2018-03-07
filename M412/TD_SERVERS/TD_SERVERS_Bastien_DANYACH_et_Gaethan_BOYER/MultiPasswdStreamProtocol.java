package multiThreads;

import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.*;

public class MultiPasswdStreamProtocol {


	public String processInput(String theInput) {
		String theOutput = null;

		if (theInput == "exit") {
			return "exit";

		} else {
			theOutput = "Bye.";
		}
		return theOutput;
	}

	public String encryptPassword(String pass) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");

		byte[] passBytes = pass.getBytes();

		md.update(passBytes);
		byte[] digest = md.digest(passBytes);

		StringBuilder sb = new StringBuilder();
		for (byte b : digest) { // convert to hex
			sb.append("0123456789ABCDEF".charAt((b & 0xF0) >> 4));
			sb.append("0123456789ABCDEF".charAt((b & 0x0F)));
		}
		return sb.toString();
	}



}
