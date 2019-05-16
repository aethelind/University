/*
* Aethelind Racic
* 7686783
* Assignment Four
*/

package QuestionOne;

import java.util.ArrayList;
import java.util.List;

// Node class
// Note; this class could include more setters/getters/adders, but I stripped it down to the essentials to avoid confusion
public class Node {
    private Pool data;
    private Node parent;
    private List<Node> children = new ArrayList<>();
    
    // Constructor
    public Node(Pool data){
        this.data = data;
        parent = null;
    }    
    
    // Changes this node's parent, and the parent node's child list.
    public void setParent(Node parent){
        this.parent = parent;
        parent.addChild(this);
    }   
    // Adds a node to the child list. Only accessible through setParent.
    private void addChild(Node child){
        children.add(child);
    }
    
    // Makes this node a root by eliminating the parent node.
    public void setAsRoot(){
        parent = null;
    }
    
    // getters..
    public Pool pool(){
        return data;
    }   
    public List<Node> children(){
        return children;
    }
    
}
