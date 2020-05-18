public class MainClass {

    public static void main(String[] args) {
//        testGraph();
//        testDfs();
        testBfs();

    }

    private static void testBfs() {
        Graph graph = new Graph(10);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");
        graph.addVertex("J");




        graph.addEdges("A","B");
        graph.addEdges("B","C");
        graph.addEdges("C","D");
        graph.addEdges("D","E");
        graph.addEdges("A","F");
        graph.addEdges("F","G");
        graph.addEdges("G","H");
        graph.addEdges("H","E");
        graph.addEdges("A","I");
        graph.addEdges("I","J");
        graph.addEdges("J","E");
        graph.bfs("A", "E");

    }

    private static void testDfs() {
        Graph graph = new Graph(7);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");

        graph.addEdges("A", "B", "C", "D");
        graph.addEdges("B", "E");
        graph.addEdges("D", "F");
        graph.addEdges("F", "G");

        graph.dfs("A");
        //A B E C D F G
    }

    private static void testGraph() {
        Graph graph = new Graph(4);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdges("A", "B", "C");
        graph.addEdges("B", "A", "C", "D");
        graph.addEdges("C", "A", "B", "D");
        graph.addEdges("D", "B", "C");

        System.out.println("Size of graph is " + graph.getSize());
        graph.display();
    }
}