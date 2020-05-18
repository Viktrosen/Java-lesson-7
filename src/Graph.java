import java.util.*;

public class Graph {

    private final List<Vertex> vertexList;
    private final boolean[][] adjMat;

    public Graph(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMat = new boolean[maxVertexCount][maxVertexCount];
    }

    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    public void addEdges(String start, String second, String... other) {
        addEdge(start, second);
        for (String another : other) {
            addEdge(start, another);
        }
    }

    private void addEdge(String start, String finish) {
        int startIndex = indexOf(start);
        int finishIndex = indexOf(finish);

        if (startIndex == -1 || finishIndex == -1) {
            throw new IllegalArgumentException("Invalid label for edge");
        }

        adjMat[startIndex][finishIndex] = true;
        adjMat[finishIndex][startIndex] = true;
    }

    private int indexOf(String vertexLabel) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexLabel.equals(vertexList.get(i).getLabel())) {
                return i;
            }
        }

        return -1;
    }

    public int getSize() {
        return vertexList.size();
    }

    private void displayVertex(Vertex vertex) {
        System.out.println(vertex);
    }

    public void display() {
        for (int i = 0; i < getSize(); i++) {
            System.out.print(vertexList.get(i));
            for (int j = 0; j < getSize(); j++) {
                if (adjMat[i][j]) {
                    System.out.print(" -> " + vertexList.get(j));
                }
            }
            System.out.println();
        }
    }

    /**
     * англ. Depth-first search, DFS
     *
     * @param startLabel
     */
    public void dfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }

        Stack<Vertex> stack = new Stack<>();

        Vertex vertex = vertexList.get(startIndex);
        visitVertex(vertex, stack);

        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null) {
                visitVertex(vertex, stack);
            }
            else {
                stack.pop();
            }
        }

        resetVertexState();

    }

    private int min(int first, int second, int third){
        int temp;
        if (first<second){
            temp = first;
        }
        else {temp = second;}
        if (temp<third){return temp;}
        else {return third;}
    }

    /**
     * англ. breadth-first search, BFS
     *
     * @param startLabel
     */
    public void bfs(String startLabel, String finishLabel) {
        int startIndex = indexOf(startLabel);
        int finishIndex = indexOf(finishLabel);
        List[] ways= new ArrayList[3];
        for (int i = 0; i < ways.length ; i++) {
            ways[i] = new ArrayList();}
        if (startLabel!=null){
        ways[0].add(startLabel);
        ways[1].add(startLabel);
        ways[2].add(startLabel);}
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }
        Queue<Vertex> queue = new LinkedList<>();
        Vertex vertex = vertexList.get(startIndex);
        visitVertex(vertex, queue);
        int i = 0;
        while (!queue.isEmpty()) {

            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(vertex, queue);
                ways[i].add(vertex.getLabel());

            }
            else {
                queue.remove();
            }
            i++;
            if (i==3){i=0;}
        }
        for (int j = 0; j < ways.length ; j++) {
            if (!ways[j].contains(finishLabel)){ways[j].add(finishLabel);}
        }

        for (int j = 0; j < ways.length; j++) {
            if (ways[j].size()==min(ways[0].size(),ways[1].size(),ways[2].size())){
                System.out.println("Кратчайший путь: "+ways[j]);
            }
        }
        resetVertexState();}

    private void resetVertexState() {
        for (Vertex vertex : vertexList) {
            vertex.setVisited(false);
        }
    }

    private Vertex getNearUnvisitedVertex(Vertex peek) {
        int peekIndex = vertexList.indexOf(peek);
        for (int i = 0; i < getSize(); i++) {
            if (adjMat[peekIndex][i] && !vertexList.get(i).isVisited()) {
                return vertexList.get(i);
            }
        }

        return null;
    }



    private void visitVertex(Vertex vertex, Stack<Vertex> stack) {
        displayVertex(vertex);
        stack.push(vertex);
        vertex.setVisited(true);
    }

    private void visitVertex(Vertex vertex, Queue<Vertex> queue) {
        displayVertex(vertex);
        queue.add(vertex);
        vertex.setVisited(true);
    }


}