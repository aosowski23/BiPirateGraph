
package src;

import java.io.*;
import java.util.*;
import src.Node;

public class Graph {
    
    /*
    This method uses Depth First Search to color each vertice in the graph 
    (either red or blue, and determine if the graph is two-colorable
    */
    public static ArrayList<Node> DFS(Node[] G){
        int count = 0;
        boolean cycle = false;
        boolean first = false;
        ArrayList<Node> stack = new ArrayList<Node>();
        ArrayList<Node> cycles = new ArrayList<Node>();
       
        for(int i = 1; i < G.length; i++){
            Node curr = G[i];
            
            if(curr != null){
                if(curr.getColor().equals("white")){
                    stack.add(curr);
                    curr.setColor("red");
               
                    while(stack.isEmpty() == false){
                        for(int j = 0; j < stack.size(); j++){
                           // System.out.print(stack.get(j).getVal() + ", ");
                        }
                        Node pop = stack.remove(stack.size()-1);
                        
                        //System.out.println();
                        if(pop.getMarked() == false){
                         
                            pop.setMarked(true);
                            if(pop.getEdges() != null){
                                for(int j = 0; j < pop.getEdges().size();j++){
                                    count ++;
                                    if(pop.getEdges().get(j).getColor().equals("white")){
                                        stack.add(pop.getEdges().get(j));
                                        pop.getEdges().get(j).setParent(pop);
                                        
                                        if(pop.getColor().equals("red")){
                                            pop.getEdges().get(j).setColor("blue");
                                        }
                                        else{
                                            pop.getEdges().get(j).setColor("red");
                                        }   
                                    }

                                    try{
                                        if(pop.getColor().equals(pop.getEdges().get(j).getColor()) ){

                                            cycle = true;
                                            if(cycle == true && first == false){

                                            first = true;

                                            Node start = pop;
                                            Node temp = pop.getEdges().get(j);
                                            Node stop = pop.getEdges().get(j).getParent();

                                            while( start != null && stop!=start){
                                                cycles.add(start);
                                                start = start.getParent();

                                            }
                                            cycles.add(start);
                                            cycles.add(temp);
                                            }
                                        }
                                    }
                                    catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                } 
            }    
        }
        System.out.println(count);
        return cycles;
        
    }
    
    /*
    This method is used to determine the adjacent vertices of each vertice in the graph
    */
    public static void fillArray(String line, Node[] vertices){
        
        //Parsing the line to determine which vertices are included in the edge
        String[] vals = line.split(" ");
        int v = Integer.parseInt(vals[0]);
        int e = Integer.parseInt(vals[1]);

        if(vertices[v] == null){
        
            vertices[v] = new Node(v);

            if(vertices[e] == null){ 
       
                vertices[e] = new Node(e);
            }
        }
  
        else{
            if(vertices[e] == null){ 
          
                vertices[e] = new Node(e);
            }
        }
        vertices[v].addEdge(vertices[e]);
        vertices[e].addEdge(vertices[v]);            
    }
    
    public static void main(String[] args) {
        
        BufferedReader in;
        
    
        ArrayList<Node> cycle = new ArrayList<Node>();
        
        Node[] vertices = null;
        int amount = 0;
        
        try{
        //System.out.println(new FileReader(args[0]).);
        in = new BufferedReader(new FileReader("/Users/Anthony/Documents/Spring_2017/CS311/programming2/pa2/src/src/largegraph1.txt"));
        
        amount =  Integer.parseInt(in.readLine())+1;
     
        vertices = new Node[amount];
        
        String line;
        
        while((line = in.readLine()) != null){
            fillArray(line, vertices);                   
        }
        
        cycle = DFS(vertices);
        

        }
        catch (Exception e) {
            e.printStackTrace();
        }   
        
        try{

            String nF = "smallgraphoutput.txt";
            //System.out.println(nF.substring(0, nF.length()-4) + ".txt");
            FileWriter writer = new FileWriter(nF);
            //System.out.println(vertices[200000].getVal());
            if(cycle.size() == 0){
              writer.write("yes");
              writer.write("\n");
              for(int i =1; i < vertices.length; i++){
                if(vertices[i] != null){
                    writer.write(i + " " + vertices[i].getColor());
                    writer.write("\n");
                  System.out.println(i + " " + vertices[i].getColor());
                }
              }
               writer.close();
            }

            else{
              writer.write("no");
              writer.write("\n");
              
              for(int i =0; i < cycle.size(); i++){
                if(cycle.get(i) != null){
                    writer.write("" + cycle.get(i).getVal() + "");
                    writer.write("\n");
                  System.out.println(cycle.get(i).getVal());
                }

               }
              writer.close();
        }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }    
}
