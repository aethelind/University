public class Address {
  
  private String street;
  private int number;
  private String postal;
  
  public Address(String s, String p, int n){
    street = s;
    postal = p;
    number = n;
  }
  
  public String toString(){
    return Integer.toString(number) + " " + street + " street, " + postal + ".\n";
  }
    
}
