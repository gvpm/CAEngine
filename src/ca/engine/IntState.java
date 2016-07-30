/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.engine;


public class IntState extends State {
    int value;

    public IntState(int value) {
        this.type=1;
        this.value=value;
        
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    
    
    
    @Override
    public boolean compare(State s) {
        
        if(s.getType()==this.type){
            IntState iS=(IntState) s;
            return iS.getValue()==this.value;
            
            
        }
        throw new UnsupportedOperationException("Wrong type"); 
    }
    
    @Override
    public String toString (){
        
        return Integer.toString(value);
    }
    
}
