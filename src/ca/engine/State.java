/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.engine;

/**
 *
 * @author gvpm
 */
public abstract class State {
    int type;
    //1= int state
    

    public State() {
        
    }
    
   

    public int getType() {
        return type;
    }
    
    
    
    public abstract boolean compare (State s);
    
    public abstract String toString();
    
    
    
   
    
}
