public class memoryDiagram{
  public static void main( String[] args ) {
    Employee o1; 
    Address a1, a2;
    
    a1 = new Address ("Queen", "K1P1N2", 48);
    a2 = new Address ("King Edward", "K1N6N5", 800);

    o1 = new Employee("Falcao", 40, 15.50, a1);
    o1.addAddress(a2);
    
    System.out.println(o1);
    
    StudentInfo.display();
  }
}