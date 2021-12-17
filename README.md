# PG220
# Projet de Programmation Orientée Objet


1/ Exécution du programme:

- La classe principale qui lance le programme est la classe "logic/Main", et regroupe les trois étapes. Cette classe a été implémentée de telle sorte que le programme exécute l’étape passée en ligne de commande (exemple : pour exécuter l’étape 2, l’utilisateur doit entrer en ligne de commande : >>> java Main etape2).

- Pour l’étape2 et 3, des fichiers decoupes.xml (et decoupes_optimisees.xml pour l’étape3) et decoupe’i’.svg (et decoupe_optimisee’i’.svg) avec i le numéro de la découpe, sont créés et ajoutés dans le dossier propre à chaque étape après l'exécution du code.


2/ Étapes complétées:

- Les trois premières étapes ont été entièrement complétées avec tous les algorithmes qui fonctionnent parfaitement.

- En ce qui concerne la quatrième étape, elle n’a pas été implémentée, mais nous estimons que nos algorithmes fonctionnent bien quelle que soit la forme de la planche demandée par le client à une différence près : les coordonnées des découpes ne dépendent plus de la longueur/largeur (cas d’un rectangle), mais du maximum des abscisses de la séquence de points définissant le polygone ( découpe suivant les x) , et du maximum de leurs ordonnées (découpe suivant les y). Les classes qui connaîtront un changement sont donc celles implémentant l’interface Reader dans le package “inputsoutputs”. 


3/ Contraintes : 
 
- Toutes les contraintes sur les prix, dimensions et dates ont été prises en considération. Nous avons implémenté trois classes Tests ( TestPrice, TestDimensions, TestDate), qui permettent de valider nos classes Price, Dimensions et Date.

- Les id de chacun des clients (ou fournisseurs) ainsi que ceux des planches(ou panneaux) d’un même client(ou fournisseur) doivent obligatoirement être différents les uns aux autres dans les fichiers XML, afin qu’il n’y ait pas de confusion lors de la création des fichiers SVG.

- Nous avons réduit la visibilité des maximums de nos classes en implémentant le Factory Design Pattern, pour avoir une sorte d’indépendance entre le package “logic” et le package “inputsoutputs”, Ce dernier a été implémenté pour les classes Client, Supplier, Board, Panel. De même, les classes XMLReader et XMLWriter ne sont pas publiques et donc ne sont pas visibles dans le package “logic”, parce qu’elles implémentent respectivement les interfaces Reader et Writer qui elles sont publiques.

- Comme pour toute contrainte nous avons créé une classe (Price, Dimensions, Date) qui implémente l’interface Validable afin de vérifier cette contrainte, nous sommes dans la mesure d’ajouter n’importe quelle contrainte supplémentaire pour le client/fournisseur (couleur de bois, origine, dureté, etc).


