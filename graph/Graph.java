package graph;

import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.LinkedList;

class Node {
    String val;
    int idx;
    boolean isVisited;

    Node(String val, int idx) {
        this.val = val;
        this.idx = idx;
    }
}

class Node2 {
    String val;
    int idx;
    boolean isVisited;
    List<Node2> neighbors = new ArrayList<>();

    Node2(String val, int idx) {
        this.val = val;
        this.idx = idx;
    }
}

public class Graph {
    List<Node> nodesList = new ArrayList<>();
    int[][] adjacencyMatrix;

    Graph(List<Node> nodesList) {
        this.nodesList = nodesList;
        adjacencyMatrix = new int[nodesList.size()][nodesList.size()];
    }

    void addUndirectedEdge(int i, int j) {
        adjacencyMatrix[i][j] = 1;
        adjacencyMatrix[j][i] = 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        // print rows
        for (int i = 0; i < nodesList.size(); i++) {
            sb.append(nodesList.get(i).val + " ");
        }
        sb.append("\n");
        // print columns
        for (int i = 0; i < nodesList.size(); i++) {
            sb.append(nodesList.get(i).val + " ");
            for (int j : adjacencyMatrix[i]) {
                sb.append(j + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[node.idx][i] == 1) {
                neighbors.add(nodesList.get(i));
            }
        }
        return neighbors;
    }

    private void BFS(Node node) {
        List<Node> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            Node n = q.removeFirst();
            n.isVisited = true;
            System.out.print(n.val + " ");
            List<Node> neighbors = getNeighbors(n);
            for (Node neighbor : neighbors) {
                if (!neighbor.isVisited) {
                    q.add(neighbor);
                    neighbor.isVisited = true;
                }
            }
        }
    }

    void bfs() {
        for (Node node : nodesList) {
            if (!node.isVisited) {
                BFS(node);
            }
        }
    }
}

class Graph2 {
    List<Node2> nodesList = new ArrayList<>();

    Graph2(List<Node2> nodeList) {
        this.nodesList = nodeList;
    }

    void addUndirectedEdge(int i, int j) {
        Node2 first = nodesList.get(i);
        Node2 second = nodesList.get(j);
        first.neighbors.add(second);
        second.neighbors.add(first);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // print columns
        for (int i = 0; i < nodesList.size(); i++) {
            sb.append(nodesList.get(i).val + ": ");
            for (int j = 0; j < nodesList.get(i).neighbors.size(); j++) {
                if (j == nodesList.get(i).neighbors.size() - 1) {
                    sb.append(nodesList.get(i).neighbors.get(j).val);
                } else {
                    sb.append(nodesList.get(i).neighbors.get(j).val + " -> ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void BFS(Node2 node) {
        List<Node2> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            Node2 n = q.remove(0);
            n.isVisited = true;
            System.out.printf("%s ", n.val);
            for (Node2 neighbor : n.neighbors) {
                if (!neighbor.isVisited) {
                    q.add(neighbor);
                    neighbor.isVisited = true;
                }
            }
        }
    }

    void bfs() {
        for (Node2 node : nodesList) {
            if (!node.isVisited) {
                BFS(node);
            }
        }
    }

}

class Main {
    public static void main(String[] args) {
        // List<Node> nodes = new ArrayList<>();
        // nodes.add(new Node("A", 0));
        // nodes.add(new Node("B", 1));
        // nodes.add(new Node("C", 2));
        // nodes.add(new Node("D", 3));
        // nodes.add(new Node("E", 4));
        // Graph graph = new Graph(nodes);
        // graph.addUndirectedEdge(0, 1);
        // graph.addUndirectedEdge(0, 2);
        // graph.addUndirectedEdge(0, 3);
        // graph.addUndirectedEdge(1, 4);
        // graph.addUndirectedEdge(2, 3);
        // graph.addUndirectedEdge(3, 4);
        // System.out.println(graph.toString());
        // graph.bfs();
        // =============================================
        List<Node2> nodes = new ArrayList<>();
        nodes.add(new Node2("A", 0));
        nodes.add(new Node2("B", 1));
        nodes.add(new Node2("C", 2));
        nodes.add(new Node2("D", 3));
        nodes.add(new Node2("E", 4));
        Graph2 graph2 = new Graph2(nodes);
        graph2.addUndirectedEdge(0, 1);
        graph2.addUndirectedEdge(0, 2);
        graph2.addUndirectedEdge(0, 3);
        graph2.addUndirectedEdge(1, 4);
        graph2.addUndirectedEdge(2, 3);
        graph2.addUndirectedEdge(3, 4);
        System.out.println(graph2.toString());
        graph2.bfs();
    }
}
