public class Employee {

    private String name;
    private int hours;
    private double rate;
    private Address[] address;
    
    private int index;
    
    public Employee(String n, int h, double r, Address a){
      address = new Address[6];
      index = 0;
      
      address[index] = a;
      index++;
      
      name = n;
      hours = h;
      rate = r;
      
      
    }
    
    public void addAddress(Address a){
      // This method adds secondary addresses to Employee's information.
      if(index > 5){
        throw new IndexOutOfBoundsException("Address list is full.");
      } else {
        address[index] = a;
        index++;
      }
    }
    
    public String toString(){
      String s = "";
      s = "Name: " + name + "\n"
        + "Hours: " + hours + "\n"
        + "Rate: " + rate + "\n";
      
      for(int i=0; i<index; i++){
        s = s+ "Address " + (i+1) + ": " + address[i];
      }

      return s;
    }

}
