package utils;

import java.util.*;

public class Graph {
    private final Set<String> nodes = new HashSet<>();
    private final Set<Edge> edges = new HashSet<>();
    private final Map<String, Set<Edge>> outgoing = new HashMap<>();
    private final Map<String, Set<Edge>> incoming = new HashMap<>();
    private final Map<String, Set<String>> outgoingNodes = new HashMap<>();
    private final Map<String, Set<String>> incomingNodes = new HashMap<>();
    public Graph() {

    }
    public Graph(int nodeCount) {
        for (int i = 0; i < nodeCount; i++) {
            nodes.add(String.valueOf(i));
        }
    }
    public Graph(int[][] edgeMatrix) {
        for (int i = 0; i < edgeMatrix.length; i++) {
            nodes.add(String.valueOf(i));
        }

        for (int i = 0; i < edgeMatrix.length; i++) {
            for (int j = 0; j < edgeMatrix[i].length; j++) {
                if (edgeMatrix[i][j] >= 0) {
                    addEdge(String.valueOf(i), String.valueOf(j), edgeMatrix[i][j]);
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
    public Graph subgraph(Set<String> nodes) {
        Graph newGraph = new Graph();
        for (String node : nodes) {
            newGraph.addNode(node);
        }
        for (String node : nodes) {
            for (Edge outgoingEdge : this.outgoingEdges(node)) {
                if (!nodes.contains(outgoingEdge.to)) continue;
                newGraph.addEdge(node, outgoingEdge.to, outgoingEdge.weight);
            }
        }
        return newGraph;
    }
    public boolean addNode(String node) {
        return nodes.add(node);
    }
    public boolean addEdge(String from, String to, int weight) {
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
    Set<Edge> outgoingEdges(String node) {
        return outgoing.getOrDefault(node, Collections.emptySet());
    }
    Set<Edge> incomingEdges(String node) {
        return incoming.getOrDefault(node, Collections.emptySet());
    }
    Set<String> outgoingNodes(String node) {
        return outgoingNodes.getOrDefault(node, Collections.emptySet());
    }
    Set<String> incomingNodes(String node) {
        return incomingNodes.getOrDefault(node, Collections.emptySet());
    }
    Set<String> bothWayNodes(String node) {
        Set<String> bothWayNodes = this.incomingNodes(node);
        bothWayNodes.retainAll(this.outgoingNodes(node));
        return bothWayNodes;
    }
    public int djikstra(String origin, String destination) {
        Map<String, Integer> distances = new HashMap<>();
        for (String node : nodes) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(origin, 0);

        record NodeDist(String node, int distance) {}
        PriorityQueue<NodeDist> pq = new PriorityQueue<>(Comparator.comparingInt(NodeDist::distance));
        pq.add(new NodeDist(origin, 0));

        Set<String> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            NodeDist current = pq.poll();
            visited.add(current.node());

            if (current.node().equals(destination)) {
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
    public Set<String> clique(int size, String origin) {
        if (size < 2) throw new IllegalArgumentException("Size must be at least 2");
        Set<String> neighbours = this.bothWayNodes(origin);
        if (neighbours.size() < size-1) return new HashSet<>();
        if (size == 2) return Set.of(origin, neighbours.iterator().next());
        Set<String> clique = subgraph(neighbours).clique(size-1);
        if (!clique.isEmpty()) clique.add(origin);
        return clique;
    }
    public Set<String> clique(int size) {
        if (size < 2) throw new IllegalArgumentException("Size must be at least 2");
        return new HashSet<>(clique(new ArrayList<>(nodes), size, new ArrayList<>()));
    }
    private List<String> clique(List<String> candidates, int size, List<String> current) {
        if (current.size() == size) {
            return current;
        }

        for (int i = 0; i < candidates.size(); i++) {
            String node = candidates.get(i);

            if (current.size() + candidates.size() - i < size) return new ArrayList<>();

            boolean connected = true;
            for (String n : current) {
                if (!this.bothWayNodes(node).contains(n)) {
                    connected = false;
                    break;
                }
            }
            if (!connected) continue;

            current.add(node);
            List<String> next = clique(candidates.subList(i + 1, candidates.size()), size, current);
            if (!next.isEmpty()) {
                return next;
            }
            current.removeLast();
        }
        return new ArrayList<>();
    }
    public Graph mst() {
        if (nodes.isEmpty()) return new Graph();
        return mst("0");
    }
    public Graph mst(String startNode) {
        Graph mst = new Graph();
        Set<String> visited = new HashSet<>();
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
        String from;
        String to;
        int weight;
        public Edge(String  from, String  to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        public String getFrom() {
            return this.from;
        }
        public String getTo() {
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
            return from.equals(edge.from) && to.equals(edge.to) && weight == edge.weight;
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
