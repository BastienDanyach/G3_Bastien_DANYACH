# Rendu Exercice 2 TD_GLOUTON 2 B.Danyach G.Boyer

# Stratégies d'ordonnancement :
## Haut
On affecte aux 3 machines les 3 premières tâches, ensuite on attribue à la machine qui à fini le plus tôt la tâche la plus lourde. Le reste est ensuite partagé entre les 2 machines restantes.
## Milieu
On affecte toujours les tâches les plus petites possibles aux machines disponibles/ qui ont fini le plus tôt.
## Bas
On affecte d'abord les tâches les plus lourdes aux 3 machines. Les 2 machines qui ont fini le plus tôt se voient attribué 2 tâches chacunes pendant que celle qui aura mis le plus de temps ne fera qu'une seule tâche ensuite, la plus longue.
### Cet ordonnancement est-il optimal ?
Il est effectivement optimal. En effet, la somme de nos tâches donne 24 unités de temps. Or nous avons seulement 3 machines disponibles. 24/3 = 8. Or cet ordonnancement nous permet de finir l'ensemble de nos tâches en 8 unités de temps, soit la durée la plus optimale possible.

### Pourquoi cet algorithme peut être qualifié de "glouton" ?
Un algorithme glouton est un algorithme qui va s'occuper de faire des choix optimaux à l'échelle locale pour arriver à faire des choix qui mèneront à une optimisation globale. Ici c'est ce qu'on essaye de faire en comparant les makeSpan et en essayant à chaque fois que l'on va attribuer une tâche, d'attribuer celle-ci de manière optimale.

### Que faire si les durées des tâches étaient uniquement de 2 et 4 unités de temps ?
Pour cela, nous aurions juste à alterner sur chaque machine les tâches de 2 et les tâches de 4 afin d'obtenir un makeSpan optimal.
