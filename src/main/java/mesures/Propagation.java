package mesures;

import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Propagation {
    private ArrayList<Node> infected = new ArrayList<>();
    private Graph graph;
    private ArrayList<Node> lesSusceptibles = new ArrayList<>();
    private double beta = 1 / 7D;
    private double mu = 1 / 14D;
    private int days = 84;

    private double[] percent = new double[84];
    private ArrayList<Node> groupe0 = new ArrayList<>();
    private ArrayList<Node> groupe1 = new ArrayList<>();

    private ArrayList<Node> nodes = new ArrayList<>();

    public Propagation(Graph graph) {
        this.graph = graph;
    }

    public void initStat() {
        this.graph.nodes().forEach(n -> {
            n.setAttribute("state", "");
        });
    }

    public Node firstPatient() {
        Node node = Toolkit.randomNode(this.graph);
        node.setAttribute("state", "Infected");
        return node;
    }


    /*
         public void scenario1(){

             this.graph.nodes().forEach(n -> {
                 this.lesSusceptibles.add(n);
             });


             Node p0 = firstPatient();
             this.infected.add(p0);
             int day = 1;

             while(day <= this.days){

                 this.graph.nodes().forEach(node -> {
                     Iterator<Node> it  = node.neighborNodes().iterator();
                    while(it.hasNext()) {
                        Node voisin = it.next();
                        if (this.lesSusceptibles.contains(voisin)) {
                            //infection
                            if (Math.random() < this.beta) {
                                this.infected.add(voisin);

                            }
                        }
                    }
                       // mise à jour
                        if(this.infected.contains(node)){
                            if(Math.random() < this.mu){
                                this.infected.remove(node);
                                this.lesSusceptibles.add(node);
                         }
                     }

                 });
                this.percent.add(day-1, (double) (this.infected.size()  / this.graph.getNodeCount()));
             }





         }*/
    public void Scenario1() {

        this.graph.nodes().forEach(n -> {
            this.lesSusceptibles.add(n);
        });
        this.nodes = new ArrayList<>(this.lesSusceptibles);

        Node p0 = firstPatient();
        this.infected.add(p0);
        int day = 0;
        int count = this.graph.getNodeCount();
        while (day < this.days) {
            //creation d'une liste tmp afin d'éviter l'erreur  ConcurrentModificationException

            ArrayList<Node> tmp = new ArrayList<>(this.infected);
            tmp.forEach(this::infecter);

            this.percent[day] = (double) this.infected.size() / count;
            //this.percent.add(day, (double) (tmp.size()+1 / count));
            day++;
            tmp.clear();
        }
        this.infected.clear();
        this.lesSusceptibles.clear();


    }

    public void Scenario2() {
        int count = this.graph.getNodeCount();

        this.lesSusceptibles = (ArrayList<Node>) Toolkit.randomNodeSet(this.graph, (count / 2));
        this.nodes = new ArrayList<>(this.lesSusceptibles);

        Random rand = new Random();
        Node p0 = this.lesSusceptibles.get(rand.nextInt(this.lesSusceptibles.size()));
        this.infected.add(p0);

        int day = 0;
        while (day < this.days) {
            //creation d'une liste tmp afin d'éviter l'erreur  ConcurrentModificationException
            ArrayList<Node> tmp = new ArrayList<>(this.infected);
            tmp.forEach(this::infecter);
            this.percent[day] = (double) this.infected.size() / count;
            //this.percent.add(day, (double) (tmp.size()+1 / count));
            day++;
            tmp.clear();

        }
        this.infected.clear();
        this.lesSusceptibles.clear();


    }


    public void Scenario3() {
        int count = this.graph.getNodeCount();

        this.groupe0 = (ArrayList<Node>) Toolkit.randomNodeSet(this.graph, (count / 2));


        for (Node node : groupe0) {
            Edge e = Toolkit.randomEdge(node);
            if (e != null) {
                Node opposite = e.getOpposite(node);
                this.groupe1.add(opposite);
            }
        }
        this.graph.nodes().forEach(n -> {

            this.lesSusceptibles.add(n);
        });

        this.lesSusceptibles.removeAll(this.groupe1);
        this.nodes = new ArrayList<>(this.lesSusceptibles);


        Random rand = new Random();
        Node p0 = this.lesSusceptibles.get(rand.nextInt(this.lesSusceptibles.size()));
        this.infected.add(p0);
        int day = 0;
        while (day < this.days) {
            //creation d'une liste tmp afin d'éviter l'erreur  ConcurrentModificationException

            ArrayList<Node> tmp = new ArrayList<>(this.infected);
            tmp.forEach(this::infecter);

            this.percent[day] = (double) this.infected.size() / count;
            //this.percent.add(day, (double) (tmp.size()+1 / count));
            day++;
            tmp.clear();
        }
        this.infected.clear();
        this.lesSusceptibles.clear();


    }

    public void infecter(Node node) {
        Iterator<Node> it = node.neighborNodes().iterator();
        while (it.hasNext()) {
            Node next = it.next();
            if (this.lesSusceptibles.contains(next)) {
                if (Math.random() < this.beta) {
                    this.infected.add(next);
                    this.lesSusceptibles.remove(next);
                }
            }
        }
        if (this.infected.contains(node)) {
            if (Math.random() < this.mu) {
                this.infected.remove(node);
                this.lesSusceptibles.add(node);
            }
        }
    }

    public double[] getPercent() {
        return this.percent;

    }

    public ArrayList<Node> getGroupe0() {
        return this.groupe0;
    }

    public ArrayList<Node> getGroupe1() {
        return this.groupe1;
    }

    public ArrayList<Node> getNodes() {
        return this.nodes;
    }
}
