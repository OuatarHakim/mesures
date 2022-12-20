package mesures;

import org.graphstream.algorithm.Toolkit;
import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.BreadthFirstIterator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceEdge;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Mesures {
    public static Graph readFile(String path, String Graph) {
        Graph graph = new DefaultGraph(Graph);
        FileSource fs = new FileSourceEdge();
        fs.addSink(graph);
        try {
            fs.readAll(path);


        } catch (IOException e) {
            System.out.println("erreur !");
        } finally {
            fs.removeSink(graph);
        }

        return graph;
    }
    public static int DegreMoyenAlea(Graph g){
        return ((2 * g.getEdgeCount()) / g.getNodeCount());
    }

    public static double CoefficientClustering(double dm,double nombreNode ){

        return dm/ nombreNode ;
    }


    public static double[] DistributionDegre(Graph graph){
        int[] dis  = Toolkit.degreeDistribution(graph);
        double[] p = new double[dis.length];
        for(int i = 0 ; i < p.length ; i++){
            if(dis[i] != 0) p[i]= (double)dis[i] / graph.getNodeCount();
        }
        return p;
    }

    public static int[] distance(Graph graph , int nombreNoeud){
        int[] distance = new int[50];
        // n noeuds au hasard
        List<Node> nodes = Toolkit.randomNodeSet(graph,nombreNoeud);
        //pour chaque noeud on fais un parcour en largeur vers tous les autres noeuds
        nodes.forEach(n ->{
            BreadthFirstIterator i = (BreadthFirstIterator) n.getBreadthFirstIterator();
            //on parcour tous les noeuds  a chaque passage sur un noeud

            //les noeuds voisins
            while (i.hasNext()){
                //int depth = i.getDepthOf(i.next());
                //on rajoute 1 (une arrêt) a chaque passage sur un lien
                distance[ i.getDepthOf(i.next())] ++;
            }
        });

        return distance;
    }

    public static  Graph GenrateRandomGraph(int nombreNoeud , double degreMoyen){
        Graph graph  = new SingleGraph("ReseauAleatoire");
        Generator generator =   new RandomGenerator(Math.round(degreMoyen));
        generator.addSink(graph);
        generator.begin();
        for( int   i= 0 ; i < nombreNoeud ; i++){
            generator.nextEvents();
        }
        generator.end();

        return graph;
    }
    public static Graph GenetatePreferencielGraph(int nombreNoeud, double degreMoyen){
        Graph  graph =  new SingleGraph("ReseauPerferentiel");
        Generator generator = new BarabasiAlbertGenerator((int) Math.round(degreMoyen));
        generator.addSink(graph);
        generator.begin();
        for( int   i= 0 ; i < nombreNoeud ; i++){
            generator.nextEvents();
        }
        generator.end();

        return graph;

    }




    public static double distanceMoyenne(Graph graph,int nombreNoeuds){
        int[] distance = distance(graph,nombreNoeuds);
        int sum = Arrays.stream(distance).reduce(0, Integer::sum);
        int valeur= 0;
        //on calcule la somme de touts les distance trouvé
        //i est la distance et distance .get(i) est le nombre de fois
        for (int i = 0 ; i < distance.length;i++) {
            valeur += i * distance[i];
        }

        return valeur /(double)sum;
    }

    public static double[] distanceDestribution(Graph g,int N){
        int[] d = distance(g,N);
        double[] distanceDestribution = new double[d.length];
        int somme = Arrays.stream(d).reduce(0,Integer::sum);
        for(int i = 0; i  < d.length; i++){
            distanceDestribution[i]= (double) d[i]/somme;
        }
        return distanceDestribution;
    }
    public static void writeDataFile(String filename, double[] data) {

        try {
            FileWriter fw = new FileWriter(filename);

            for (int i = 0; i < data.length; i++)
                if (data[i] !=0) fw.write(String.format(Locale.US, "%6d%20.8f%n", i, (double) data[i]));

            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeGnuplotFileLogLog(String path,String nameData ,String GraphPngName ,String title){
        try {
            FileWriter fw = new FileWriter(path);
            fw.write("set terminal png  \n"
                    + "set title \"Degree distribution\"\n"
                    + "set xlabel 'k' \n"
                    + "set ylabel 'p(k)' \n"
                    + "set output '"+GraphPngName+"' \n"
                    + "set logscale xy  \n"
                    + "set yrang [1e-6:1] \n"
                    + "lambda = 6.62208890914917 \n"
                    + "poisson(k) = lambda ** k * exp(-lambda) /gamma(k +1) \n"
                    + "f(x) = lc - gamma * x \n"
                    + " fit f(x) '"+nameData+ "' using (log($1)):(log($2)) via lc , gamma\n"
                    + " c = exp(lc) \n"
                    + "power(k) = c * k ** (-gamma) \n"
                    + "plot '"+nameData+"' title '"+title+"' , poisson(x) title 'Poisson law' , power(x) title 'Power law' "
            );
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

    public static void ExcuteCommande(String commande, String name, String pathDir) {
        try {
            // Execute command
            ProcessBuilder p = new ProcessBuilder(commande, name);
            p.directory(new File(pathDir));
            p.start();
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }


}
