# JEMPIRE

Bienvenue, Gouverneur.
L'Empire Romain ne s'est pas construit en un jour, et votre village non plus. Votre mission est de faire prospérer une petite colonie jusqu'à la transformer en forteresse imprenable.

## COMMENT JOUER?

Pour jouer, vous devez clone le projet sur votre ordinateur:
```
git clone https://github.com/chris91300/jempire.git
```

Ensuite il faut compiler les fichiers :
```
javac -d build src/main.java
```
Puis lancer le jeu :
```
java -cp build/ Main
```

Maintenant suivez les instructions pour jouer.


## DESCRIPTION DU JEU
Vous disposez au début du jeu de ressources:
1. Bois : 0
2. Pierre : 0
3. Or: 0
4. Nourriture : 100
5. Habitants : 1

A chaque tour de jeu, vous avez le choix entre 6 actions.

1. **Explorer la forêt.** Cette action vous rapporte 5 bois et 3 nourritures.
2. **Créer une mine.** Cette action vous coûte 10 bois. Mais débloque l'accès à la mine.
3. **Travailler à la mine.** Possible si vous avez une mine. Cela coûte 5 nourritures mais vous gagner 5 pierres et 2 or.
4. **Recruter un soldat.** Vous coûte 30 or mais vous gagner 1 habitant.
5. **Commercer.** Vous coûte 5 pierres mais vous gagner 10 or.
6. **Construiser le château.** Le but ultime! Mais attention, vous devez avoir au minimum 100 bois, 100 pierres, 200 or et au moins 40 habitants.

A chaque tour, le jeu vous informe sur les quantités de chaques ressources.

A chaque tour de jeu, chaque habitant mange 1 ration de nourriture.
S'il vous manque de la nourriture, les habitants qui ne pourront pas manger meurent.

Vous **PERDEZ** si vous n'avez plus d'habitant.
Vous **GAGNEZ** si vous construiser un chateau.