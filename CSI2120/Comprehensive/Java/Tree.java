/*
* Aethelind Racic
* 7686783
* Assignment Four
*/

package QuestionOne;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Tree {
    private File file;       
    private List<Pool> poolList = new ArrayList<>();
    private List<Pool> route = new ArrayList<>();
    private Node[] nodeList;
    
    // Constructor sets file name.
    public Tree(File file){      
        this.file = file;  
    }
        
    public void solve(){       
        // read the .txt file, get pools into a sorted arrayList
        getPools();        
        // create network of linked nodes based on minimizing distance
        createTree();        
        // find and print the route into a file "solution.txt"
        routeOutput();        
    }
    
    /* This method reads the input file to discover the pools, and adds them to an arraylist. */
    private void getPools() {
        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine()) != null){
                //split the line into its three parts
                String[] split = line.split(",");              
                //create a pool object with the relevant info and add it to the list                
                poolList.add((new Pool(split[0],Double.parseDouble(split[1]),Double.parseDouble(split[2]))));
            } 
            // sort the arrayList using built-in sort function. sorts by longitude, lowest longitude = furthest west.
            poolList.sort(Comparator.comparing(Pool::getLn)); 
        } catch (IOException e){
            e.printStackTrace();
        }    
    }
    
    /* This method creates an n-ary tree of Nodes, linked based on the distance between their respective Pools */
    private void createTree(){
        int size = poolList.size();
        double currentDist, minDist;
        Node parent = null;
        nodeList = new Node[size];
        
        // Fill the nodeList with nodes containing pools from poolList
        for(int i=0; i<size; i++){
            nodeList[i] = new Node(poolList.get(i));
        }
        
        // Set the first node as the root
        nodeList[0].setAsRoot();
        
        // for each node in nodeList ...
            // Compare the distance of each pool after the root (i>0) to all previous pools (k=0 to k<i)
            // Keep track of the node containing the closest pool, and the distance.
            // Make a node of closest pool the parent of current pool. 
        for(int i=1; i<size; i++){
            minDist = Double.MAX_VALUE;
            for(int k=0; k<i; k++){
                currentDist = nodeList[i].pool().distance(nodeList[k].pool());
                if(currentDist < minDist){
                    minDist = currentDist;
                    parent = nodeList[k];
                }
            }
            nodeList[i].setParent(parent);
        }
    }
         
    /* Writes a file with the route as required by the assignment. Calculates total distance travelled as it prints. */
    private void routeOutput(){
        // record the order of discovery in a pre-order traversal of the tree
        route.clear();
        traverse(nodeList[0]);
        
        // write route to file..
        try{
            FileWriter fileWriter = new FileWriter ("solution.txt");
            double dist = 0;
            for (int i=0;i<route.size();i++) {
                if(i == 0){
                    dist = 0;
                } else {
                    // calculate distance from last visited to current node, add to distance sum
                    dist += (route.get(i-1)).distance(route.get(i)); 
                }                                  
                fileWriter.write(route.get(i).getName() + "," + dist + "\r\n");                   
            }
            fileWriter.close();            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    /* Recursive preorder traversal of a tree from Node n. Adds nodes to list route as they are discovered. */
    private void traverse(Node n){
        route.add(n.pool());
        if(!n.children().isEmpty()){
            for(Node t : n.children()){
                traverse(t);
            }
        }  
    }
}
