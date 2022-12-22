/**
 * Vlad Koval
 * cs2210
 * December 7, 2022
 */

public class Node {
    private int id; // making an id variable
    private boolean marked = false; // setting a boolean variable false

    /**
     * constructor
     * @param id accepting the id to intialize
     */
    public Node(int id){
        this.id = id; //this id is equal to param id
    }
    /**
     * MarkNode
     * setter method to intialize marked with new variable
     * @param mark new value for marked
     */
    public void markNode(boolean mark){
        this.marked = mark; //setting marked to mark
    } 
    /**
     * getter method for marked
     * @return
     */
    public boolean getMark(){
        return marked; 
    }
    /**
     * getter method for id
     * @return
     */
    public int getId(){ 
        return id; 
    } 
}
