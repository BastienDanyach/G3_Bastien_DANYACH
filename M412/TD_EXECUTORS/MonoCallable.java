package executor;
// M412 2017-2018: Bastien Danyach S4TG3

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Callable;

public class MonoCallable implements Callable<String>{
	
	private String s;

	public MonoCallable(String s) {
		this.s = s ;
	}
	
	
	
	@Override
	public String call() throws Exception {
		
        final long debut = System.currentTimeMillis();
        String mdp = "";
        try {        	
			MonoCrackPassword mcpU = new MonoCrackPassword(s);
			mdp = mcpU.randomSearch(s.length());
			System.out.println("Mot de passe trouvé : " + mdp);
			long fin = System.currentTimeMillis();
			System.out.println("Temps pour trouver ce mdp : " + (fin - debut) + "ms");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return mdp;
    }	
	
	
}
