package mesures;


import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSourceEdge;

import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String filePath = "data/com-dblp.ungraph.txt";
        Graph g = new SingleGraph("graphe");
        FileSourceEdge fs = new FileSourceEdge(true);
        fs.addSink(g);
        try {
            fs.readAll(filePath);
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            fs.removeSink(g);

        }

        // mesures de bases
          afficheMesures(g);



    }

    public static  void afficheMesures (Graph g){
        int N = g.getNodeCount() ;
        int nombreArc   = g.getEdgeCount();
        double degreMoyen =  Toolkit.averageDegree(g);
        double[] C = Toolkit.clusteringCoefficients(g) ;
        double CA = degreMoyen /  N ;
        System.out.println("Nombres de nodes : " + N );
        System.out.println("Nombres de liens  : " + nombreArc);
        System.out.println("Degré Moyen : " + degreMoyen);
        System.out.println("Coefficient de clustering : " + C);
        System.out.println("Coefficient de clustering d'un Réseau aléatoire : " +  CA);
        System.out.println("Le graphe est connexe : " + Toolkit.isConnected(g));
        System.out.println("Un reseau aleatoire de m^ degré moyen  et m^ taile : "  + connecter(degreMoyen,N));
    }

    private static boolean connecter(double d,int N){
        if(d > Math.log(N)){
            return true;
        }else{
            return false;
        }
    }

}


