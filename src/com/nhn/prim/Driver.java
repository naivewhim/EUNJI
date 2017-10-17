package com.nhn.prim;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Driver {
    public static void main(String[] args) {

        // key: startNode, value: Edge
        List<Edge> allEdgeList = new ArrayList<>();
        allEdgeList.add(new Edge(0, 1, 2));
        allEdgeList.add(new Edge(0, 3, 8));
        allEdgeList.add(new Edge(0, 4, 4));
        allEdgeList.add(new Edge(1, 0, 2));
        allEdgeList.add(new Edge(1, 2, 3));
        allEdgeList.add(new Edge(2, 1, 3));
        allEdgeList.add(new Edge(2, 3, 5));
        allEdgeList.add(new Edge(2, 4, 1));
        allEdgeList.add(new Edge(3, 0, 8));
        allEdgeList.add(new Edge(3, 2, 5));
        allEdgeList.add(new Edge(3, 4, 7));
        allEdgeList.add(new Edge(4, 0, 4));
        allEdgeList.add(new Edge(4, 2, 1));

        // MST 초기화
        List<Edge> minimumEdgeList = new ArrayList<>();

        // 임의의 정점 선택 : 0
        Set<Integer> allVertex = new HashSet<>();
        IntStream.range(0, 5).forEach(i -> allVertex.add(i));
        allVertex.remove(0);

        // minimum vertex
        Set<Integer> minimumVertex = new HashSet<>();
        minimumVertex.add(0);

        while(!allVertex.isEmpty()) {
            // minimumVertex 에 있는 정점과 연결된 엣지 중 비용이 가장 작은 엣지 선택
            // 엣지는 최소힙 형태로 저장된다. (키 = 가중치)
            PriorityQueue<Edge> currentEdgeList = allEdgeList.stream()
                    .filter(edge -> minimumVertex.contains(edge.getStartNode()) && allVertex.contains(edge.getEndNode()))
                    .collect(Collectors.toCollection(() -> new PriorityQueue<>()));

            Edge minimumEdge = currentEdgeList.peek();

            // MST 에 추가
            minimumEdgeList.add(minimumEdge);

            // allVertex 에서 제거
            allVertex.remove(minimumEdge.getEndNode());

            // minimumVertex 에 추가
            minimumVertex.add(minimumEdge.getEndNode());
        }

        minimumEdgeList.stream()
                .forEach(edge -> {
                    System.out.println(edge.getStartNode() + "->" + edge.getEndNode()
                    + ": " + edge.getWeight());
                });
    }

}
