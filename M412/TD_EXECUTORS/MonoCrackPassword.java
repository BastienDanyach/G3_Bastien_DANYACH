/*
 * M412 2017-2018: Bastien Danyach S4TG3
 */
package executor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MonoCrackPassword{
	private String passEncrypted;
	private String passUncrypted = null;
	private int uncryptedLength;

	public MonoCrackPassword(String passUncrypted)
			throws NoSuchAlgorithmException {
		this.passEncrypted = encryptPassword(passUncrypted);
		this.uncryptedLength = passUncrypted.length();
		this.passUncrypted = passUncrypted;
	}

	public MonoCrackPassword(String passEncrypted, int uncryptedLength) {
		this.passEncrypted = passEncrypted;
		this.uncryptedLength = uncryptedLength;
	}

	/**
	 * compute the 16 bytes md5 digest of a string see
	 * http://docs.oracle.com/javase
	 * /7/docs/technotes/guides/security/crypto/CryptoSpec.html
	 * 
	 * @param pass
	 * @return hex representation of the digest
	 * @throws NoSuchAlgorithmException
	 */
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

	public String getPassEncrypted() {
		return passEncrypted;
	}

	public String getPassUncrypted() {
		return passUncrypted;
	}

	public int getUncryptedLength() {
		return uncryptedLength;
	}

	public String generateRandomWord(int wordLength) {
		Random r = new Random(); // Initialize a Random Number Generator with
									// SysTime as the seed
		StringBuilder sb = new StringBuilder(wordLength);
		for (int i = 0; i < wordLength; i++) { // For each letter in the word
			char tmp = (char) ('a' + r.nextInt(26)); // Generate a letter
														// between a and z
			sb.append(tmp); // Add it to the String
		}
		return sb.toString();
	}

	public String randomSearch(int passLength) throws NoSuchAlgorithmException {
		System.out.println(Thread.currentThread().getName() + " cherche "
				+ this.passUncrypted);
		while (true) {
			String guess = generateRandomWord(passLength);
			MonoCrackPassword crack = new MonoCrackPassword(guess);
			if (this.passEncrypted.equals(crack.passEncrypted)) {
				System.out.println("your password is: " + guess);
				return guess;
			}
		}
	}

	public static void main(String[] args) throws InterruptedException,
			ExecutionException, NoSuchAlgorithmException {

		String[] passList = { "aaa", "bbbb", "ccc", "ddd", "eee", "fff", "ggg",
				"hhh", "iii", "jjjj", "kkk", "lll", "azert"};
		
        List<Future<String>> list = new ArrayList<Future<String>>();
        int nbThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Nombre de Thread: " + nbThreads);

		ExecutorService exec = Executors.newFixedThreadPool(nbThreads);
		CompletionService<String> completion = new ExecutorCompletionService<String>(exec);
		//Search[] searchs = new Search[passList.length];
		 
		for (int i = 0; i < passList.length; i++) {
	        Future<String> f = completion.submit(new MonoCallable(passList[i]));
	        list.add(f);
			//System.out.println(passList[i] + "(" + mcpU.getPassEncrypted()
				//	+ ") <=> " + mcpU.randomSearch(mcpU.getUncryptedLength()));
			//searchs[i] = new Search(passList[i]);
			//searchs[i].start();
			
		}
		
		/*for (Search t : searchs) {
			t.join();
		}*/
		exec.shutdown();

		for (int i = 0; i < passList.length; ++i) {
			String r = completion.take().get();
	        if (r != null)
	           System.out.println("result: " + r);
	    }
		
		

	}
}