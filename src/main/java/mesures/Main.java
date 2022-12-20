package mesures;


import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSourceEdge;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException {
        Graph g = Mesures.readFile("./data/com-dblp.ungraph.txt", "dblp");
        int nombresNoeud = g.getNodeCount();
        double degreMoyen = Toolkit.averageDegree(g);


        // mesures de bases
        afficheMesures(g);
        //4)
        //Mesures.writeDataFile("./Result/DBLP/dd_dblp.dat",Mesures.DistributionDegre(g) );
      /*  Mesures.writeGnuplotFileLineaire("./Result/DBLP/plot_dd_dblp_lin.gnuplot","dd_dblp.dat","dd_dblp_lineaire.png","DBLP lineaire");
        Mesures.ExcuteCommande("gnuplot", "plot_dd_dblp_lin.gnuplot", "./Result/DBLP/");
        Mesures.writeGnuplotFileLogLog("./Result/DBLP/plot_dd_dblp_log.gnuplot","dd_dblp.dat","dd_dblp_log.png","DBLP loglog");
        Mesures.ExcuteCommande("gnuplot", "plot_dd_dblp_log.gnuplot", "./Result/DBLP/");
*/

        //5) distance moyenne
      //  System.out.println("La distance moyenne : " + Mesures.distanceMoyenne(g, 1000));
     //   Mesures.writeDataFile("./Result/DBLP/dd.dat", Mesures.distanceDestribution(g, 1000));
  //      Mesures.writeGnuplotFileLineaire("./Result/DBLP/distance.gnuplot", "dd.dat", "distance.png", "Distance");
        //  Mesures.ExcuteCommande("gnuplot", "distance.gnuplot", "./Result/DBLP/");


        //6)
    //    Graph graph1 = Mesures.GenrateRandomGraph(nombresNoeud, degreMoyen);
     //   afficheMesures(graph1);

    //    Mesures.writeDataFile("./Result/Random/dd_random.dat", Mesures.DistributionDegre(graph1));
    //    Mesures.writeGnuplotFileLogLog("./Result/Random/plot_random_lin.gnuplot", "dd_random.dat", "dd_random_lin.png", "Random");
      //  Mesures.ExcuteCommande("gnuplot", "plot_random_lin.gnuplot", "./Result/Random/");

     //   Mesures.writeGnuplotFileLogLog("./Result/Random/plot_random_log.gnuplot", "dd_random.dat", "dd_random_log.png", "Random");
      //  Mesures.ExcuteCommande("gnuplot", "plot_random_log.gnuplot", "./Result/Random/");
    //    System.out.println("La distance moyenne : " + Mesures.distanceMoyenne(graph1,1000));
    //    Mesures.writeDataFile("./Result/Random/dd_distance_random.dat",Mesures.distanceDestribution(graph1,1000));
   //     Mesures.writeGnuplotFileLineaire("./Result/Random/distance.gnuplot", "dd_distance_random.dat", "distance.png", "Distance");
      //  Mesures.ExcuteCommande("gnuplot", "distance.gnuplot", "./Result/Random/");

        Graph graph2 = Mesures.GenetatePreferencielGraph(nombresNoeud,degreMoyen);
         afficheMesures(graph2);
         Mesures.writeDataFile("./Result/Perferenciel/dd_perferenciel.dat",Mesures.DistributionDegre(graph2));
        Mesures.writeGnuplotFileLineaire("./Result/Perferenciel/plot_perferenciel_lin.gnuplot","dd_perferenciel.dat","dd_perferenciel_lin.png","Perferenciel");
      //  Mesures.ExcuteCommande("gnuplot", "plot_perferenciel_lin.gnuplot", "./Result/Perferenciel/");

        Mesures.writeGnuplotFileLogLog("./Result/Perferenciel/plot_perferenciel_log.gnuplot","dd_perferenciel.dat","dd_perferenciel_log.png","Perferenciel");
    //    Mesures.ExcuteCommande("gnuplot", "plot_perferenciel_log.gnuplot", "./Result/Perferenciel/");
           Mesures.writeDataFile("./Result/Perferenciel/dd_distance_perferenciel.dat",Mesures.distanceDestribution(graph2,1000));
        Mesures.writeGnuplotFileLineaire("./Result/Perferenciel/distance.gnuplot","dd_distance_perferenciel.dat","distance_perferenciel.png","distance_perferenciel");
      //  Mesures.ExcuteCommande("gnuplot", "distance.gnuplot", "./Result/Perferenciel/");


    }

    private static void afficheMesures(Graph graph) {
        System.out.println("Affichage des mesures Graphe : " + graph.getId());
        int NombreNoeuds = graph.getNodeCount();
        System.out.println("Nombres de noeuds: " + NombreNoeuds);
        int NombreLiens = graph.getEdgeCount();
        System.out.println("Nombres de liens: " + NombreLiens);
        System.out.println("Degré moyen d'un graphe aléatoire: " + Mesures.DegreMoyenAlea(graph));
        double degreMoyen = Toolkit.averageDegree(graph);
        System.out.println("Degré moyen en utilisant Toolkit : " + degreMoyen);

        System.out.println("coefficient de clustering moyen en utilisant Toolkit : " + Toolkit.averageClusteringCoefficient(graph));
        System.out.println("coefficient de clustering d'un réseau aléatoire : ");
        System.out.printf(Locale.US, "%6d%20.8f%n", 1, Mesures.CoefficientClustering(degreMoyen, NombreNoeuds));
        //1) est-il  un réseau connexe : Oui
        System.out.println("Le graphe est-il connexe : " + Toolkit.isConnected(graph));
        System.out.println("------------------------------\n");

    }


}


