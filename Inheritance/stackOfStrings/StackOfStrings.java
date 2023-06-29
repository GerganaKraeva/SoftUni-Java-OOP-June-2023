package stackOfStrings;

import java.util.ArrayList;

public class StackOfStrings {
    private ArrayList<String>data;
    public StackOfStrings(){
        this.data=new ArrayList<>();
    }
    public void push(String element){
        this.data.add(element);
    }
    public String pop(){
        String lastElRemoved=this.peek();
        this.data.remove(this.data.size()-1);
       return lastElRemoved;
    }
    public String peek(){
        return this.data.get(this.data.size()-1);
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
}
