package mesures;


import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSourceEdge;

import javax.imageio.IIOException;
import java.awt.*;
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
        System.out.println("Nombres de nodes : " + g.getNodeCount());
        System.out.println("Nombres de liens  : " + g.getEdgeCount());
        System.out.println("Degré Moyen : " + Toolkit.averageDegree(g));
        System.out.println("Coefficient de clustering : " + Toolkit.clusteringCoefficients(g));
    }
}


