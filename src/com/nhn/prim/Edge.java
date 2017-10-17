package com.nhn.prim;

public class Edge implements Comparable<Edge> {
    private int startNode, endNode, weight;

    public Edge(int startNode, int endNode, int weight) {
        super();
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getStartNode() {
        return startNode;
    }

    public int getEndNode() {
        return endNode;
    }

    // ascending (min heap)
    @Override
    public int compareTo(Edge edge) {
        if (this.weight > edge.getWeight()) {
            return 1;
        } else if(this.weight < edge.getWeight()) {
            return -1;
        }
        return 0;
    }
}
