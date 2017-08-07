
package src;

import java.util.*;

public class Node {
    
    private String color;
    private ArrayList<Node> edges;
    private Node parent;
    private int verticeNum;
    private boolean marked;
    
    public Node(int v){
        this.color = "white";
        this.parent = null;
        this.verticeNum = v;
        this.edges = new ArrayList<Node>();
        this.marked = false;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the edges
     */
    public ArrayList<Node> getEdges() {
        return edges;
    }

    /**
     * @param edges the edges to set
     */
    public void addEdge(Node edge) {
        this.edges.add(edge);
    }
    
    public int getVal(){
        return verticeNum;
    }
    
    public Node getParent() {
        return this.parent;
    }

   
    public void setParent(Node Parent) {
        this.parent = Parent;
    }
    
    public boolean getMarked() {
        return this.marked;
    }

   
    public void setMarked(boolean mark) {
        this.marked = mark;
    }
}
