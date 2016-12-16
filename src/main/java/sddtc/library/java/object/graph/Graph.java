package sddtc.library.java.object.graph;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by sddtc on 2016/12/16.
 */
public class Graph {
    private HashMap<String, Vertex> vertexes;
    private HashMap<Integer, Edge> edges;

    public Graph() {
        this.vertexes = new HashMap<>();
        this.edges = new HashMap<>();
    }

    public void build(String param) {
        //build a graph
    }

    public Set<String> vertexKeys() {
        return vertexes.keySet();
    }
}
