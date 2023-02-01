package mesures;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Simulation {

    public static void main(String[] args) {
        Graph g = Mesures.readFile("./data/com-dblp.ungraph.txt", "dblp");
        Propagation propagation = new Propagation(g);
     /*   propagation.Scenario1();
        writeDataFile("./Result/Propagation/propagationS1.dat",propagation.getPercent());

        propagation.Scenario3();
        writeDataFile("./Result/Propagation/propagationS3.dat",propagation.getPercent());
*/


    }

    public static void writeDataFile(String filename, double[] data) {


        try {
            FileWriter fw = new FileWriter(filename);

            for (int i = 0; i < data.length; i++)
               if(data[i] >  0 )fw.write(String.format(Locale.US, "%6d%20.8f%n", i, data[i]));

            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeGnuplotFile(String path,String nameData ,String GraphPngName ,String title){
        try {
            FileWriter fw = new FileWriter(path);
            fw.write("set terminal png  \n"
                    + "set title \""+title+"\"\n"
                    + "set xlabel 'Days' \n"
                    + "set ylabel '%' \n"
                    + "set output '"+GraphPngName+"' \n"
                    + "plot '"+nameData+"' title '"+title+"' with lines ls 1 "
            );
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
