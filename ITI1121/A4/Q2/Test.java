public class Test {


    public static void main( String[] args ) {
      LinkedList<String> animals = new LinkedList();
      LinkedList<String> letters = new LinkedList();
      
      /* Will produce errors:
       letters.insertAfter("x", animals);
       animals.insertAfter("monkey", letters);
       */
      
      animals.addLast("cat");
      animals.addLast("dog");
      animals.addLast("hamster");
      animals.addLast("snake");
      animals.addLast("bear");
      animals.addLast("cow");
      animals.addLast("chimp");
      
      letters.addLast("A");
      letters.addLast("B");
      letters.addLast("C");
      
      /* Will produce errors:
       animals.insertAfter("kitten", letters);
       letters.insertAfter("Z", animals);
       */
      
      
      System.out.println("Before switch...");
      System.out.println("Animals: \n"+animals);
      System.out.println("Letters: \n"+letters);
      
      animals.insertAfter("dog",letters);
      
      System.out.println("After switch...");
      System.out.println("Animals: \n"+animals);
      System.out.println("Letters: \n"+letters);
      
      StudentInfo.display();
     
    }
}
