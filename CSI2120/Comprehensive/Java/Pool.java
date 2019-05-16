/*
* Aethelind Racic
* 7686783
* Assignment Four
*/

package QuestionOne;

public class Pool{
    private String name;
    private double ltD, ltR;
    private double lnD, lnR;
    
    // Constructor for Pool 
    // Assigns values and converts lat/lon to radians.
    public Pool(String name, double ltD, double lnD){
        this.name = name;
        this.ltD = ltD;
        this.lnD = lnD;
        ltR = Math.toRadians(ltD);
        lnR = Math.toRadians(lnD);
    }
    
    // Calculates the distance in kilometres between two Pool locations using the method described
    public double distance(Pool p){
        return 6371 * 2 * Math.asin(Math.sqrt(Math.pow(Math.sin((p.ltR - this.ltR) / 2),2) + Math.pow(Math.sin((p.lnR - this.lnR) / 2),2) * Math.cos(this.ltR) * Math.cos(p.ltR)));        
    }
    
	// Getters and toString 
    public double getLt(){
        return ltD;
    }
    public double getLn(){
        return lnD;
    }
    public String getName(){
        return name;
    }
    public String toString(){
        return ("(" + name + ", [" + ltD + ", " + lnD + "])\n");
        
    }
}
