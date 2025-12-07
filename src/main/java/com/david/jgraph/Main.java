import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

void main() {
    // 1. Create a graph
    Graph<String, DefaultEdge> cityGraph = new SimpleDirectedGraph<>(DefaultEdge.class);

    // 2. Add nodes
    cityGraph.addVertex("Madrid");
    cityGraph.addVertex("Toledo");
    cityGraph.addVertex("Ciudad Real");
    cityGraph.addVertex("Valencia");
    cityGraph.addVertex("Sevilla");

    // 3. Add connections
    cityGraph.addEdge("Madrid", "Toledo");
    cityGraph.addEdge("Toledo", "Ciudad Real");
    cityGraph.addEdge("Ciudad Real", "Valencia");
    cityGraph.addEdge("Madrid", "Sevilla");

    // 4. Path between two cities
    String originCity = "Madrid";
    String destinationCity = "Valencia";

    System.out.println("Finding path from " + originCity + " to " + destinationCity);

    GraphPath<String, DefaultEdge> path =
            DijkstraShortestPath.findPathBetween(cityGraph, originCity, destinationCity);

    // 5. Show result
    if (path != null) {
        System.out.println("Path found: " + path.getVertexList());
    } else {
        System.out.println("There is no possible path between those cities.");
    }
}