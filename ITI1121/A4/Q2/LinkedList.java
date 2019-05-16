public class LinkedList<E> {
  
    private static class Node<T> {
    
        private T value;
    
        private Node<T> previous;
        private Node<T> next;
    
        private Node(T value, Node<T> previous, Node<T> next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }
  
    private Node<E> head;
    private int size;
  
    public LinkedList() {
        head = new Node<E>(null, null, null);
        head.next = head.previous = head;
        size = 0;
    }
  
    /** Returns the size of this list.
     *
     * @return the size of this list
     */
  
    public int size() {
        return size;
    }
  
    /** 
     * Adds an element at the end of the list. THIS IS A SAMPLE
     * METHOD THAT CANNOT BE USED BY InsertAfter !
     *
     * @param element the element to be added.
     * @return true since duplicated values are allowed.
     * @throws NullPointerException if elem is null.
     */
    public boolean addLast(E element) {
    
        if (element == null) {
            throw new NullPointerException();
        }
    
        Node<E> before, after;

        before = head.previous;
        after = head;
    
        before.next = new Node<E>(element, before, after);
        after.previous = before.next;
    
        size++;
    
        return true;
    }
    
    public void insertAfter( E obj, LinkedList <E> other ){
      
      if(obj == null || head == null || head.next == null || other.head == null || other.head.next == null){
        throw new NullPointerException("List(s) or object is null!");
      }

      boolean flag = false;
      int i = 0;
      Node<E> x;
      x = this.head;
      
      while(true){
        x=x.next;
        if(i>=size){
          flag = true;
          break;
        }
        
        if(x.value.equals(obj)){
          break;
        }
        
        i++;
      }
      
      if(flag){
        throw new IllegalArgumentException("Object not found in list!");
      }
      
      Node<E> n = x.next;
      Node<E> p = x.previous;
      
      x.next=other.head.next;
      n.previous=other.head.previous;
      other.head.previous.next=n;
      other.head = null;
    }
    
    
    public String toString(){
      if(head == null || head.next == null){
        return "[]";
      }
      
      String s = "[";
      Node<E> tmp = head;
      
      for(int j=0; j<size; j++){
        tmp = tmp.next;
        s = s+(tmp.value.toString());
        
        if(j != size-1){
          s=s+", ";
        }
      }
      
      s=s+"]";
      
      
      return s;
    }
    
    
  
}
