import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

void main() {
    // 1. Create a graph
    Graph<String, DefaultWeightedEdge> cityGraph =
            new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

    // 2. Add nodes
    cityGraph.addVertex("Madrid");
    cityGraph.addVertex("Toledo");
    cityGraph.addVertex("Ciudad Real");
    cityGraph.addVertex("Valencia");
    cityGraph.addVertex("Sevilla");

    // 3. Add connections
    cityGraph.setEdgeWeight(
            cityGraph.addEdge("Madrid", "Toledo"),
            75
    );

    cityGraph.setEdgeWeight(
            cityGraph.addEdge("Toledo", "Ciudad Real"),
            120
    );

    cityGraph.setEdgeWeight(
            cityGraph.addEdge("Ciudad Real", "Valencia"),
            220
    );

    cityGraph.setEdgeWeight(
            cityGraph.addEdge("Madrid", "Sevilla"),
            530
    );

    // 4. Path between two cities
    String originCity = "Madrid";
    String destinationCity = "Valencia";

    System.out.println("Finding path from " + originCity + " to " + destinationCity);

    var dijkstra = new DijkstraShortestPath<>(cityGraph);

    GraphPath<String, DefaultWeightedEdge> path =
            dijkstra.getPath(originCity, destinationCity);

    // 5. Show result
    if (path != null) {
        System.out.println("Ruta óptima: " + path.getVertexList());
        System.out.println("Distancia total: " + path.getWeight() + " km");
    }

}