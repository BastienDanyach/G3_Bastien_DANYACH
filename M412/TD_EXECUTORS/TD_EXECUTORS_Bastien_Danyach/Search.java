package executor;
// M412 2017-2018: Bastien Danyach S4TG3

import java.security.NoSuchAlgorithmException;

public class Search extends Thread{
	private String s;
	
	public Search(String s) {
		this.s = s;
	}
	
	public void run() {
		try {
			long debut = System.currentTimeMillis();
			MonoCrackPassword mcpU = new MonoCrackPassword(s);
			System.out.println("Mot de passe trouvé : " +mcpU.randomSearch(s.length()));
			long fin = System.currentTimeMillis();
			System.out.println("Temps pour trouver ce mdp : " + (fin - debut) + "ms");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
