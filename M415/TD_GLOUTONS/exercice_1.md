#Rendu TD_GLOUTON B.Danyach G.Boyer


**Tableau taille/temps**


| **Nombres d'articles** | **Valeurs** | **Temps de résolution(en secondes)** |
| ---------------------- | ----------- | ------------------------------------ |
| 40                     | 40          | 0.8                     	      |
| 1000                   | 50          | 43                                   |
| 6000                   | 200         | 149                                  |
| 6000                   | 100         | 117                                  |
| 10000                  | 200         | 138                                  |


**Tableau comparaison méthodes biggestValueFirst/bestRatioValueWeightFirst**

A faire

# Code biggestValueFirst à finir :
def biggestValueFirst(listItems, maxWeight):
    if not listItems:
        return (0, [])

    value,weight,_ = listItems[-1]

    list = []
    valeur = value
    range = -1
    poids =0

    for i in range(0,len(listItems)-1):
        v,w,_= listItems[i]
        if(v<valeur & w<maxWeight):
            variable = v
            range = i
            poids = w

    list.__add__(listItems[range])
    solutionWithoutItem = biggestValueFirst(listItems.remove(i), maxWeight - poids)
    opt = solutionWithoutItem  # current best solution found

    return opt, list
