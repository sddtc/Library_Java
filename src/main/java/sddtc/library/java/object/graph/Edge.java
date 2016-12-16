package sddtc.library.java.object.graph;

/**
 * Created by sddtc on 2016/12/16.
 */
public class Edge implements Comparable<Edge> {
    private Vertex start, end;
    private int weight;

    public Edge(Vertex start, Vertex end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Vertex getStart() {
        return start;
    }

    public void setStart(Vertex start) {
        this.start = start;
    }

    public Vertex getEnd() {
        return end;
    }

    public void setEnd(Vertex end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }

    @Override
    public int hashCode() {
        return (this.start.getValue() + this.end.getValue()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Edge)) {
            return false;
        }
        Edge e = (Edge) obj;
        return e.start.equals(this.start) && e.end.equals(this.end);
    }
}
