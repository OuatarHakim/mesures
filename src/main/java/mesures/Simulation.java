package mesures;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Simulation {

    public static void main(String[] args) {
        int nombreNoeud = 317080;
        double degreMoyen = 6.62208890914917;
        /* Graph g = Mesures.readFile("./data/com-dblp.ungraph.txt", "dblp");*/
       // Graph g = Mesures.GenrateRandomGraph(nombreNoeud, degreMoyen);
        Graph g = Mesures.GenetatePreferencielGraph(nombreNoeud, degreMoyen);
        Propagation propagation = new Propagation(g);
       // propagation.Scenario1();
        // writeDataFile("./Result/Propagation/propagationS1.dat",propagation.getPercent());
      //  writeDataFile("./Result/Propagation/Aleatoire/propagationS1.dat", propagation.getPercent());
      //  writeDataFile("./Result/Propagation/Preferentiel/propagationS1.dat", propagation.getPercent());

      //  propagation.Scenario2();
       // writeDataFile("./Result/Propagation/propagationS2.dat",propagation.getPercent());
     //   writeDataFile("./Result/Propagation/Aleatoire/propagationS2.dat", propagation.getPercent());
     //   writeDataFile("./Result/Propagation/Preferentiel/propagationS2.dat", propagation.getPercent());

       propagation.Scenario3();
      //  writeDataFile("./Result/Propagation/propagationS3.dat",propagation.getPercent());
     //   writeDataFile("./Result/Propagation/Aleatoire/propagationS3.dat", propagation.getPercent());
        writeDataFile("./Result/Propagation/Preferentiel/propagationS3.dat", propagation.getPercent());


     //   System.out.printf("<k> groupe 0 = %.2f%n", averageDegree(propagation.getGroupe0()));
       // System.out.printf("<k> groupe 1 = %.2f%n", averageDegree(propagation.getGroupe1()));


        double seuilEpidemie = seuilEpidemie(propagation.getNodes());

        System.out.printf("λc  = %f%n", seuilEpidemie);


    }

    public static void writeDataFile(String filename, double[] data) {


        try {
            FileWriter fw = new FileWriter(filename);

            for (int i = 0; i < data.length; i++)
                if (data[i] > 0) fw.write(String.format(Locale.US, "%6d%20.8f%n", i, data[i]));

            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeGnuplotFile(String path, String nameData, String GraphPngName, String title) {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write("set terminal png  \n"
                    + "set title \"" + title + "\"\n"
                    + "set xlabel 'Days' \n"
                    + "set ylabel '%' \n"
                    + "set output '" + GraphPngName + "' \n"
                    + "plot '" + nameData + "' title '" + title + "' with lines ls 1 "
            );
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double averageDegree(ArrayList<Node> groupe) {
        int sum = 0;
        for (Node n : groupe)
            sum += n.getDegree();
        return (double) sum / groupe.size();
    }


    public static double degreeVariance(Graph g) {
        ArrayList<Node> list = new ArrayList<>();
        g.nodes().forEach(n -> {
            list.add(n);
        });

        return degreeVariance(list);
    }

    public static double degreeVariance(ArrayList<Node> nodes) {
        int sum = 0;
        for (Node n : nodes)
            sum += Math.pow(n.getDegree(), 2);
        return sum / (double) nodes.size();
    }

    public static double seuilEpidemie(ArrayList<Node> listNodes) {
        return SeuilEpidemie(averageDegree(listNodes), degreeVariance(listNodes));
    }

    public static double SeuilEpidemie(double averageDegree, double degreeVariance) {
        return averageDegree / degreeVariance;
    }


}
