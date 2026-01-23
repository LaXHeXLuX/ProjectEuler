package utils;

import java.util.*;

public class Graph {
    private final Set<Integer> nodes = new HashSet<>();
    private final Set<Edge> edges = new HashSet<>();
    private final Map<Integer, Set<Edge>> outgoing = new HashMap<>();
    private final Map<Integer, Set<Edge>> incoming = new HashMap<>();
    private final Map<Integer, Set<Integer>> outgoingNodes = new HashMap<>();
    private final Map<Integer, Set<Integer>> incomingNodes = new HashMap<>();
    public Graph() {

    }
    public Graph(int nodeCount) {
        for (int i = 0; i < nodeCount; i++) {
            nodes.add(i);
        }
    }
    public Graph(int[][] edgeMatrix) {
        for (int i = 0; i < edgeMatrix.length; i++) {
            nodes.add(i);
        }

        for (int i = 0; i < edgeMatrix.length; i++) {
            for (int j = 0; j < edgeMatrix[i].length; j++) {
                if (edgeMatrix[i][j] >= 0) {
                    addEdge(i, j, edgeMatrix[i][j]);
                }
            }
        }
    }
    public Graph(Graph graph) {
        nodes.addAll(graph.nodes);
        for (Edge edge : graph.edges) {
            this.addEdge(edge.from, edge.to, edge.weight);
        }
    }
    public Set<Edge> getEdges() {
        return this.edges;
    }
    public Graph subgraph(Set<Integer> nodes) {
        Graph newGraph = new Graph();
        for (Integer node : nodes) {
            newGraph.addNode(node);
        }
        for (Integer node : nodes) {
            for (Edge outgoingEdge : this.outgoingEdges(node)) {
                if (!nodes.contains(outgoingEdge.to)) continue;
                newGraph.addEdge(node, outgoingEdge.to, outgoingEdge.weight);
            }
        }
        return newGraph;
    }
    public boolean addNode(int node) {
        return nodes.add(node);
    }
    public boolean addEdge(int from, int to, int weight) {
        if (weight < 0) throw new IllegalArgumentException("Weight can't be negative");
        if (!nodes.contains(from) || !nodes.contains(to)) return false;

        Edge e = new Edge(from, to, weight);
        edges.add(e);

        outgoing.computeIfAbsent(from, _ -> new HashSet<>()).add(e);
        incoming.computeIfAbsent(to, _ -> new HashSet<>()).add(e);
        outgoingNodes.computeIfAbsent(from, _ -> new HashSet<>()).add(to);
        incomingNodes.computeIfAbsent(to, _ -> new HashSet<>()).add(from);

        return true;
    }
    public int nodeCount() {
        return nodes.size();
    }
    Set<Edge> outgoingEdges(int node) {
        return outgoing.getOrDefault(node, Collections.emptySet());
    }
    Set<Edge> incomingEdges(int node) {
        return incoming.getOrDefault(node, Collections.emptySet());
    }
    Set<Integer> outgoingNodes(int node) {
        return outgoingNodes.getOrDefault(node, Collections.emptySet());
    }
    Set<Integer> incomingNodes(int node) {
        return incomingNodes.getOrDefault(node, Collections.emptySet());
    }
    Set<Integer> bothWayNodes(int node) {
        Set<Integer> bothWayNodes = this.incomingNodes(node);
        bothWayNodes.retainAll(this.outgoingNodes(node));
        return bothWayNodes;
    }
    public int djikstra(int origin, int destination) {
        Map<Integer, Integer> distances = new HashMap<>();
        for (int node : nodes) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(origin, 0);

        record NodeDist(int node, int distance) {}
        PriorityQueue<NodeDist> pq = new PriorityQueue<>(Comparator.comparingInt(NodeDist::distance));
        pq.add(new NodeDist(origin, 0));

        Set<Integer> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            NodeDist current = pq.poll();
            visited.add(current.node());

            if (current.node() == destination) {
                return current.distance();
            }

            for (Edge edge : outgoingEdges(current.node())) {
                if (visited.contains(edge.to)) continue;
                int newDist = current.distance() + edge.weight;
                if (newDist < distances.get(edge.to)) {
                    distances.put(edge.to, newDist);
                    pq.add(new NodeDist(edge.to, newDist));
                }
            }
        }

        return distances.get(destination);
    }
    public Set<Integer> clique(int size, int origin) {
        if (size < 2) throw new IllegalArgumentException("Size must be at least 2");
        Set<Integer> neighbours = this.bothWayNodes(origin);
        if (neighbours.size() < size-1) return new HashSet<>();
        if (size == 2) return Set.of(origin, neighbours.iterator().next());
        Set<Integer> clique = subgraph(neighbours).clique(size-1);
        if (!clique.isEmpty()) clique.add(origin);
        return clique;
    }
    public Set<Integer> clique(int size) {
        if (size < 2) throw new IllegalArgumentException("Size must be at least 2");
        return new HashSet<>(clique(new ArrayList<>(nodes), size, new ArrayList<>()));
    }
    private List<Integer> clique(List<Integer> candidates, int size, List<Integer> current) {
        if (current.size() == size) {
            return current;
        }

        for (int i = 0; i < candidates.size(); i++) {
            int node = candidates.get(i);

            if (current.size() + candidates.size() - i < size) return new ArrayList<>();

            boolean connected = true;
            for (int n : current) {
                if (!this.bothWayNodes(node).contains(n)) {
                    connected = false;
                    break;
                }
            }
            if (!connected) continue;

            current.add(node);
            List<Integer> next = clique(candidates.subList(i + 1, candidates.size()), size, current);
            if (!next.isEmpty()) {
                return next;
            }
            current.removeLast();
        }
        return new ArrayList<>();
    }
    public Graph mst() {
        if (nodes.isEmpty()) return new Graph();
        return mst(0);
    }
    public Graph mst(int startNode) {
        Graph mst = new Graph();
        Set<Integer> visited = new HashSet<>();
        mst.addNode(startNode);
        visited.add(startNode);
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        pq.addAll(this.outgoingEdges(startNode));
        while (!pq.isEmpty()) {
            Edge next = pq.poll();
            if (visited.contains(next.to)) continue;
            mst.addNode(next.to);
            mst.addEdge(next.from, next.to, next.weight);
            mst.addEdge(next.to, next.from, next.weight);
            visited.add(next.to);
            pq.addAll(this.outgoingEdges(next.to));
        }
        return mst;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Graph)) return false;
        return this.equals((Graph) o);
    }
    private boolean equals(Graph graph) {
        return nodes.equals(graph.nodes) && edges.equals(graph.edges);
    }
    @Override
    public String toString() {
        return "nodes=" + nodes + ", edges=" + edges;
    }

    public static class Edge {
        int from;
        int to;
        int weight;
        public Edge(int  from, int  to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        public int getFrom() {
            return this.from;
        }
        public int getTo() {
            return this.to;
        }
        public int getWeight() {
            return this.weight;
        }
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Edge)) return false;
            return this.equals((Edge) o);
        }
        public boolean equals(Edge edge) {
            return from == edge.from && to == edge.to && weight == edge.weight;
        }
        @Override
        public int hashCode() {
            return Objects.hash(from, to, weight);
        }
        @Override
        public String toString() {
            return from + "->" + to + " (" + weight + ")";
        }
    }
}
