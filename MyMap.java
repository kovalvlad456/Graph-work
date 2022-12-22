/**
 * Vlad Koval
 * cs2210
 * December 7, 2022
 */


//importing neccessary packages for this class
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Stack;

public class MyMap {
    private Graph g; // reference object for graph class
    //int variables used to keep track of certain factors
    private int startingNode;
    private int destinationNode;
    private int  maxPrivateRoads;
    private int maxConstructionRoads;
    private Stack<Node> path = new Stack<>(); // implementation of stack for the findPath
    
  /**
   * Constructor
   * @param inputFile // file given to read into
   * @throws MapException
   */  
  public MyMap (String inputFile) throws MapException {
      // local variables within the constructor
      int first = 0;
      boolean horizontal = true;
      int c;
      int i;

      // try catch block to catch any exceptions
      try {

        BufferedReader input = new BufferedReader(new FileReader(inputFile)); // using buffered reader to obtain the data from the file given
        
        //reading the data line by line and storing it into the line variable
        String line = input.readLine();
        line = input.readLine();
        startingNode = Integer.parseInt(line);
        line = input.readLine();
        destinationNode = Integer.parseInt(line);
        line = input.readLine();
        int width = Integer.parseInt(line);
        line = input.readLine();
        int length = Integer.parseInt(line);
        line = input.readLine();
        maxPrivateRoads = Integer.parseInt(line);
        line = input.readLine();
        maxConstructionRoads = Integer.parseInt(line);
        //intializing the size of the actual graph
        int graphSize = width * length;
        g = new Graph(graphSize);
        
        //intializing variables
        first = startingNode;
        horizontal = true;
        c = first;
        
        while ((line = input.readLine()) != null) { // while line does not equal null loop
          	if (horizontal == true) { // if horizontal is equal toi true
				      for(i = 1; i < line.length(); i = i + 2) { // looping through the line char by char
					    String str = ""; 
					    str = str + line.charAt(i); 
				
				      // checking to see if the charAt(i) is equal to P, B,V,C,OR +
					    c = checkTrueLetter(c, i, line, str);
				}
            
            // RE-INTIALIZING variables
            c = first;
            horizontal = false;
            
          }else if (horizontal == false) { // if horizontal is false
            for(i = 0; i < line.length(); i = i + 2) { // loop through the line again
              String str = ""; 
              str = str + line.charAt(i);
              
              //checking to see if the charAt is equal to P,B,V,C,+
              c = letterCheck(c, i, line, width, str);
              
            }
            
            //RE-INTIALIZING VARIABLES
            first += width;
            c = first;
            horizontal = true;
            
          }
          
        }
        //catch block
      } catch (Exception e) {	
        throw new MapException("Problem with input file"); // throwing a map exception if the try block doesn't iterate
      }
      
  }

  /**
   * check the letter to see if it equals P, B, V, +, C
   * @param c
   * @param i
   * @param line
   * @param str
   * @return
   * @throws GraphException
   */
  private int checkTrueLetter(int c, int i, String line, String str) throws GraphException {
    if (line.charAt(i) == 'P') {
      g.addEdge(g.getNode(c), g.getNode(c+1), str); // adding the first and second nodes to edges
      c++;
    }else if (line.charAt(i) == 'B' || line.charAt(i) == '+') {
      c++;
    }else if (line.charAt(i) == 'V') {
      g.addEdge(g.getNode(c), g.getNode(c+1), str);
      c++;
    }else if (line.charAt(i) == 'C') {
      g.addEdge(g.getNode(c), g.getNode(c+1), str);
      c++;
    }
    return c;
  }

  /**
   * checking false horizontal letters
   * @param c
   * @param i
   * @param line
   * @param width
   * @param str
   * @return
   * @throws GraphException
   */
  private int letterCheck(int c, int i, String line, int width, String str) throws GraphException {
    if (line.charAt(i) == 'P') {
      g.addEdge(g.getNode(c), g.getNode(c+width), str);
      c++;
    }else if (line.charAt(i) == 'B' || line.charAt(i) == '+') {
      c++;
    }else if (line.charAt(i) == 'V') {
      g.addEdge(g.getNode(c), g.getNode(c+width), str);
      c++;
    }else if (line.charAt(i) == 'C') {
      g.addEdge(g.getNode(c), g.getNode(c+width), str);
      c++;
    }
    return c; // return the c
  }
    
  /**
   * Getting the graph reference
   * @return a graph object
   */
  public Graph getGraph() {
  	return this.g; // this object of graph
  }
    
  /**
   * getter method for the starting node
   * @return int variable
   */
  public int getStartingNode() {
  	return this.startingNode;
  }
    
    /**
     * getter method for the destination node
     * @return
     */
    public int getDestinationNode() {
    	return this.destinationNode;
    }
    
    /**
     * getter method for max private 
     * @return int variable
     */
    public int maxPrivateRoads() {
    	return this.maxPrivateRoads;
    }
    
  /**
  * getter method for construction roads max
  * @return int variable of max construction roads
  */
  public int maxConstructionRoads() {
  	return this.maxConstructionRoads;
  }

  /**
   * Finds the path the car needs to take to get to the destination
   * @param start where the car starts 
   * @param destination where the car needs to end
   * @param maxPrivate max private roads
   * @param maxConstruction max construction roads
   * @return
   */
  public Iterator findPath(int start, int destination, int maxPrivate, int maxConstruction) {
		
	  Stack<Node> path = new Stack<Node>(); // creating a stack with the node type
		
    //try-catch block
		try {
			Node newNode = g.getNode(start); //get the node of the start
			Node destinationNode = g.getNode(destination); //get the node of your
			
      
      
			if (findPathHelper(newNode, destinationNode, maxPrivate, maxConstruction, path)) { // if findPathHelper comes back true
				return path.iterator(); // return the iterator of the stack
			}else {
				return null; // return null if not true
			}
			
		} catch(GraphException e) {
			e.printStackTrace(); // throw a graph exception
		}
		
		return null;
	}
	
  /**
   * Helper method to find the actual path
   * @param current
   * @param destination
   * @param privateVal
   * @param construct
   * @param nodeStack
   * @return
   * @throws GraphException
   */
	private boolean findPathHelper(Node current, Node destination, int privateCounter, int constructionCounter, Stack<Node> nodeStack) throws GraphException {
		current.markNode(true);
		nodeStack.push(current);
		
		if (current == destination) {
			return true; // base case if current equals to destination return true
		}
			
		Iterator<Edge> edges = g.incidentEdges(current); // add the edges into g
		while (edges.hasNext()) {// while edges has next loop
			Edge currentEdge = edges.next(); //current edge equal to the next edge
			Node currentNode; // reference to the current node
				
			if (current != currentEdge.firstNode()) { // if it does not equal the first Node
				currentNode = currentEdge.firstNode();
			}
			else {
				currentNode = currentEdge.secondNode(); // currentNode equals second node
			}
				
			if (currentNode.getMark() == false) { // if getMark returns false
				if (currentEdge.getType().equals("V") && privateCounter > 0) { // if it equals V denote private roads counter
          privateCounter--;
					if (findPathHelper(currentNode, destination, privateCounter, constructionCounter, nodeStack)) {//recursivly call the findPathHelper method with the updated private roads counter
						return true;
					}
				}
					
				else if (currentEdge.getType().equals("C") && constructionCounter > 0) { // if it equals C denote construct roads counter
          constructionCounter--;
					if (findPathHelper(currentNode, destination, privateCounter, constructionCounter, nodeStack)) {
						return true;
					}
				}
					
				else if (currentEdge.getType().equals("P")){ // if it equals P
					if (findPathHelper(currentNode, destination, privateCounter, constructionCounter, nodeStack)) {
						return true;
					}
				}
					
			}

		}
		nodeStack.pop(); // pop the top of the stack
		current.markNode(false); // mark current as false
		return false; // return false
		
	}
}