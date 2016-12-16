package sddtc.library.java.object.graph;

import java.util.List;

/**
 * Created by sddtc on 2016/12/16.
 */
public class Vertex {
    private String value;
    private List<Edge> neighbors;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Edge> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Edge> neighbors) {
        this.neighbors = neighbors;
    }
}
