# TD Executors

Compte rendu TD2 M415

## Exercice 1

Exercice terminé.
Difficultés de compréhension sur le fonctionnement de randomSearch() en début de séance.
Aide très utile du [cours openclassroom sur le framework Executor](https://openclassrooms.com/courses/le-framework-executor).


## Exercice 2

Exercice terminé.
Exercice qui a pris beaucoup de temps. En effet, j'ai d'abord voulu implémenter Callable à ma classe MonoCrackPassword.java
Finalement j'ai décidé de créer une nouvelle classe.
```
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
```
Cette classe a réutilisé la méthode run de ma précédente classe Search.java mais renvoie en plus le mot de passe. Ainsi je peux récupérer mes résultats dans mon main.


## Exercice 3

Dû à une perte de temps dans les 2 premiers exercices liés à la lecture et la compréhension du cours et des objectifs je n'ai pas pu traiter cet exercice.


## Auteur

**Bastien Danyach S4TG3**
