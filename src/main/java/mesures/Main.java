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
        writeDataFile("./data/lineaire/dd_lineaire.dat",g);
        writeGnuplotFileLineaire("./data/lineaire/dd_lineaire.gnuplot","dd_lineaire.dat", "dd_lineaire.png" ,"dd_lineaire");
        ExcuteCommande("gnuplot", "dd_lineaire.gnuplot", "./data/lineaire/");

        writeDataFile("./data/LogLog/dd_loglog.dat",g);
        writeGnuplotFileLogLog("./data/LogLog/dd_loglog.gnuplot","dd_loglog.dat", "dd_loglog.png" ,"dd_loglog");
        ExcuteCommande("gnuplot", "dd_loglog.gnuplot", "./data/LogLog/");

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



    private static void writeDataFile(String filename , Graph g ){
        int[] dd =  Toolkit.degreeDistribution(g);
        try {
            FileWriter fw = new FileWriter(filename);

            for (int i = 0; i < dd.length; i++)
                if (dd[i] !=0) fw.write(String.format(Locale.US, "%6d%20.8f%n", i, (double) dd[i] /g.getNodeCount()));
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void writeGnuplotFileLineaire(String path,String nameData ,String GraphPngName ,String title){
        try {
            FileWriter fw = new FileWriter(path);
            fw.write("set terminal png  \n"
                    + "set title \""+title+"\"\n"
                    + "set xlabel 'k' \n"
                    + "set ylabel 'p(k)' \n"
                    + "set output '"+GraphPngName+"' \n"
                    + "plot '"+nameData+"' title '"+title+"' with lines ls 1 "
            );
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeGnuplotFileLogLog(String path,String nameData ,String GraphPngName ,String title){
        try {
            FileWriter fw = new FileWriter(path);
            fw.write("set terminal png  \n"
                    + "set title \""+title+"\"\n"
                    + "set xlabel 'k' \n"
                    + "set ylabel 'p(k)' \n"
                    + "set output '"+GraphPngName+"' \n"
                    + "set logscale xy\n"
                    + "set yrange [1e-6:1]\n"

                    +  "# Poisson\n"
                    + "lambda = 6.62208890914917\n"
                    + "poisson(k) = lambda ** k * exp(-lambda) / gamma(k + 1)\n"
                    + "f(x) = lc - gamma * x  \n"
                    + "fit f(x) '" + nameData + "' using (log($1)):(log($2)) via lc, gamma \n"
                    + "c = exp(lc) \n"
                    +"power(k) = c *k ** (-gamma) \n"
                    + "plot '"+nameData+"' title '"+title+"', poisson(x) title 'Poisson law' , power(x) title 'Power law'"
            );
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void ExcuteCommande(String commande, String name, String pathDir) {
        try {
            // Execute command
            ProcessBuilder p = new ProcessBuilder(commande, name);
            p.directory(new File(pathDir));
            p.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


