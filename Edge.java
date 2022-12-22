/**
 * Vlad Koval
 * cs2210
 * December 7, 2022
 */

public class Edge {
    private Node firstEndpoint, secondEndpoint; // making two object references for the node class
    private String type; // making a string

    /**
     * Constructor for the edge class
     * @param u 
     * @param v
     * @param edgeType
     */
    public Edge(Node u, Node v, String edgeType) {
        //intialzing all local variables with the parameter variables
        type = edgeType;
        firstEndpoint = u;
        secondEndpoint = v;
    }

    /**
     * getter method for first node
     * @return firstEndPoint variable
     */
    public Node firstNode(){ 
        return firstEndpoint; 
    }
    /**
     * getter method for second node
     * @return secondEndPoint variable
     */
    public Node secondNode(){ 
        return secondEndpoint; 
    }
    /**
     * getter method for the string type
     * @return type variable
     */
    public String getType(){ 
        return type; 
    }
    
}
