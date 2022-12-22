/**
 * Vlad Koval
 * cs2210
 * December 7, 2022
 */

import java.util.Iterator; // importing the iterator class
import java.util.ArrayList; // importing the Array list class

public class Graph implements GraphADT{
    // creating object references
    private Node[] nodes; 
    private Edge[][] edges;

    /**
     * constructor
     * @param n the size of the graph
     */
    public Graph(int n){
        nodes = new Node[n];
        for (int i = 0; i != n; i++){
            nodes[i] = new Node(i);
        } // populate the node graph with n nodes
        edges = new Edge[n][n];
    }

    @Override
    /**
     * add the edges
     * @param nodeu
     * @param nodev
     * @param edgetype
     */
    public void addEdge(Node nodeu, Node nodev, String edgeType) throws GraphException {
        if (nodes.length == 0){
            throw new GraphException("There are no nodes in the graph"); // throw exception if length is 0
        }
        //get node both node ids
        // create a new edge object with new nodes
        getNode(nodeu.getId());
        getNode(nodev.getId());
        edges[nodeu.getId()][nodev.getId()] = new Edge(nodeu, nodev, edgeType);
        edges[nodev.getId()][nodeu.getId()] = new Edge(nodev, nodeu, edgeType);
    }

    @Override
    /**
     * Getter method for nodes
     * @param id
     */
    public Node getNode(int id) throws GraphException {
        if (nodes.length ==0){
            throw new GraphException("No nodes in this graph"); // throw exception if node length equals 0
        }
        //try catch block
        try {
            return nodes[id]; // return nodes at id
        } catch (Exception e) {
            throw new GraphException(id + "does not exist"); // throw graph exception
        }
    }

    @Override
    /**
     * incidentEdges
     * @param u
     */
    public Iterator incidentEdges(Node u) throws GraphException {
        if (nodes.length == 0){
            throw new GraphException("There are no nodes in the graph");
        }
        getNode(u.getId()); 

        ArrayList<Edge> list = new ArrayList(); // making a new Array list
        for (int y = 0; y != edges.length; y++){ 
            if (edges[u.getId()][y] != null){
                list.add(edges[u.getId()][y]); 
            }
        }
        if (list.size() == 0){
            throw new GraphException("There are no incident edges for node " + u.getId());
        }else{
            return list.iterator();
        }
    }

    @Override
    /**
     * getter method for edges
     */
    public Edge getEdge(Node u, Node v) throws GraphException {
        if (nodes.length == 0){ // length of nodes equal to 0
            throw new GraphException("There are no nodes in the graph"); //throw graph exception
        }
        getNode(u.getId()); // try getting nodes u and v if they exist
        getNode(v.getId()); // get node of v id

        if (edges[u.getId()][v.getId()] != null){ // if edges does not equal null
            return edges[u.getId()][v.getId()]; // returns the edges object
        }else{
            throw new GraphException("There is no edge connecting nodes " + u.getId() + " and " + v.getId()); // throw a graph exception
        }
    }

    @Override
    /**
     * areAdjacent
     * @param u 
     * @param v
     */
    public boolean areAdjacent(Node u, Node v) throws GraphException {
        if (nodes.length == 0){
            throw new GraphException("There are no nodes in the graph");// throw a graph exception
        }
        getNode(u.getId()); 
        getNode(v.getId());

        if (edges[u.getId()][v.getId()] == null){ // if equals null
            return false; //return false
        }else{
            return true;
        }
    }
    
}
