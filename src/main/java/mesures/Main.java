package mesures;


import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSourceEdge;

import javax.imageio.IIOException;
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



    }
}


