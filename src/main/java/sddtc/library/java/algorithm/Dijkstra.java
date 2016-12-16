package sddtc.library.java.algorithm;

import sddtc.library.java.object.graph.Edge;
import sddtc.library.java.object.graph.Graph;
import sddtc.library.java.object.graph.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by sddtc on 2016/12/16.
 */
public class Dijkstra {
    /**
     * dijkstra的实现
     * @param start 起点
     * @param end 终点
     * @param graph 一个图
     * @return 最短距离
     */
    public int dijkstra(Vertex start, Vertex end, Graph graph) {
        Set<Vertex> visited = new HashSet<>();
        Set<Vertex> unvisited = new HashSet<>();
        Map<String, Integer> distance = new HashMap<>();

        for(String key: graph.vertexKeys()) {
            distance.put(key, Integer.MAX_VALUE);
        }

        unvisited.add(start);
        distance.put(start.getValue(), 0);

        while (!unvisited.isEmpty()) {
            Vertex next = null;
            int minDistance = Integer.MAX_VALUE;

            for(Vertex v: unvisited) {
                int dist = distance.get(v.getValue());
                if(dist<minDistance) {
                    next = v;
                    minDistance = dist;
                }
            }

            unvisited.remove(next);
            visited.add(next);

            for(Edge edge: next.getNeighbors()) {
                if(!(visited.contains(edge.getEnd()))) {
                    int newDistance = distance.get(next.getValue()) + edge.getWeight();
                    int oldDistance = distance.get(edge.getEnd().getValue());

                    if(newDistance<oldDistance) {
                        distance.put(edge.getEnd().getValue(), newDistance);
                        unvisited.remove(edge.getEnd());
                    }
                }
            }

            if(visited.contains(end)) {
                break;
            }
        }

        return distance.get(end.getValue());
    }
}
