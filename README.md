# MesuresRI OUATAR Hakim

# Mesures de réseaux d'interaction

Nous allons analyser un réseau de collaboration scientifique en informatique. Le réseau est extrait de DBLP et disponible sur [SNAP](https://snap.stanford.edu/data/com-DBLP.html).

GraphStream permet de mesurer de nombreuses caractéristiques d'un réseau. La plupart de ces mesures sont implantées comme des méthodes statiques dans la classe [`Toolkit`](https://data.graphstream-project.org/api/gs-algo/current/org/graphstream/algorithm/Toolkit.html). Elles vous seront très utiles par la suite.

1. Commencez par télécharger les données et les lire avec GraphStream. GraphStream sait lire ce format. Voir [`FileSourceEdge`](https://data.graphstream-project.org/api/gs-core/current/org/graphstream/stream/file/FileSourceEdge.html) et ce [tutoriel](http://graphstream-project.org/doc/Tutorials/Reading-files-using-FileSource/). Vous pouvez essayer de visualiser le graphe mais pour cette taille ça sera très lent et très peu parlant.
2. Prenez quelques mesures de base: nombre de nœuds et de liens, degré moyen, coefficient de clustering. Quel sera le coefficient de clustering pour un réseau aléatoire de la même taille et du même degré moyen ?

### Mesures de bases sur ce graphe
|Mesures|Valeur|
|--|--|
*Nombre de noeuds* | *317080*|
*Nombres de liens* | *1049866*|
*Degré Moyen* | *6.62208890914917*|
*C-M-Clustering* | *0.6324308280637396*|


##### Réseau Aléatoire

Le degré moyen  d'un réseau aléatoire est de  (2 *L) / N = 6   
Le coefficient de clustering d'un réseau aléatoire  est Ci = p = <k>/N oû k est le degré ,    
le coeificient de clustring est C = k / N =  0.00002088

3. Le réseau est-il connexe ? Un réseau aléatoire de la même taille et degré moyen sera-t-il connexe ? À partir de quel degré moyen un réseau aléatoire avec cette taille devient connexe ?
   En utilisant la méthode isConnected de la classe Toolkit ,on trouve que le réseau est connexe . Un réseau aléatoire de la même taille et degré , on peut le vérifier en utilisant la  loi de regime connexe d'un réseau :  
   <k> > ln N (p > ln N/N)   
   ln N = ln(317080) = 12,6669    
   k = 6.62208890914917    
   k < ln N  donc ce réseau aléatoire n'est pas connexe     
   Un réseau aléatoire devient connexe à partir de <k> > ln N   
  