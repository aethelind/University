/*
* Aethelind Racic
* 7686783
* Assignment Four
*/

package QuestionOne;

import java.io.File;
import java.io.FileNotFoundException;

public class QuestionOne{
    public static void main(String[] args) {
        try{
            if(args.length == 0){
                // No filename provided..
                throw new ArrayIndexOutOfBoundsException();
            }
            // open the file, should be wading-pools-java.txt
            File file = new File (args[0]);    
            if(!file.exists()){
                // Incorrect filename provided..
                throw new FileNotFoundException();
            }
            Tree t = new Tree(file);
            t.solve();
              
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("No file name provided. Expecting 'wading-pools-java.txt'");
            e.printStackTrace();
        } catch (FileNotFoundException e){
            System.out.println("Incorrect file name. Expecting 'wading-pools-java.txt'");
            e.printStackTrace();
        }
    }    
}
