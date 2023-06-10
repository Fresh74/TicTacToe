import java.util.*;
public class Board{
    int rows;
    int columns;
    int wincond;
    LinkedList l = new LinkedList();
    public Board(int rows, int columns, int wincond){
        this.rows = rows;
        this.columns = columns; 
        this.wincond = wincond;
        for(int i = 0; i<columns; i++){
            Set<Integer> s = new TreeSet<Integer>();
            l.add(s);
        }
    }
    public boolean contains(int x, int y){
        return l.get(x).contains(y);
    }
    public void add(int x, int y){
        l.get(x).add(y);
    }
    public class LinkedList{
        private Node front;
        public LinkedList(){
            front = null;
        }
        public int size() {
            int count = 0;
            Node current = front;
            while (current != null) {
                current = current.next;
                count++;
            }
            return count;
        }
        public Set get(int index) {
            return nodeAt(index).myValue; 
        }
        public String toString() {
            if (front == null) {
                return "[]";
            } else {
                String result = "[" + front.myValue;
                Node current = front.next;
                while (current != null) {
                    result += ", " + current.myValue;
                    current = current.next;
                }
                result += "]";
                return result;
            }
        }
        public int indexOf(Object value) {
            int index = 0;
            Node current = front;
            while (current !=  null) {
                if (current.myValue == value) {
                    return index;
                }
                index++;
                current = current.next;
            }
            return -1;
        }
        public void add(Set value) {
            if (front == null) {
                front = new Node(value);
            } else {
                Node current = front;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = new Node(value);
            }
        }
        public void add(int index, Set value) {
            if (index == 0) {
                front = new Node(value, front);
            } else {
                Node current = nodeAt(index - 1);
                current.next = new Node(value, current.next);
            }
        }
        public void remove(int index) {
            if (index == 0) {
                front = front.next;
            } else {
                Node current = nodeAt(index - 1);
                current.next = current.next.next;
            }
        }
        public Node nodeAt(int index) {
            Node current = front;
            for (int i = 0; i < index; i++) {
                current = current.next; 
                
            }
            return current;
        }
    }
    public class Node
    {
        public Set myValue; // this is a ptr to an Object
        public Node next;
    
        /*** Constructor for objects of class Node */
        public Node(){
            this(null, null);
        }
        public Node(Set data){
            this(data, null);
        }
        public Node(Set data, Node next){
            this.myValue = data;
            this.next = next;
        }
        public String toString(){
            return "" + myValue;
        }
        void setNext(Node n){        
            next = n;
        }
        void setMyValue(Set x)
        {
            myValue = x;
        }
        public void setMyValueObject(Set x)
        {
            myValue = x;
        }
        Node getNext()
        {    
            return next;
        }
        String getMyValue()
        {
            return myValue.toString();
        }
        Object getMyValueObject()
        {
            return myValue;
        } 
    }
}