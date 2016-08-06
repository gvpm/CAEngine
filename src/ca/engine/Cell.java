/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.engine;

import java.util.ArrayList;

/**
 *
 * @author gvpm
 */
public abstract class Cell {
    //1=square cell
    //
    int type;
    //State state;
    //id
    int id;
   
    State currentState;
    State nextState;
    ArrayList<State> stateHistory;
    
    boolean hasNewState;
    
    //The cell with the rule type will only have the states of the neighbours
    boolean rule;
    
    /**
     *
     * @param id
     * @param rule
     */
    public Cell(int id,boolean rule) {
        this.id = id;
        this.rule=rule;
        hasNewState = false;
    }
    
        
    //--------Getters and Setters

    /**
     *
     */
    
    public void flagNewState(){
        hasNewState = true;
        
    }

    /**
     *
     */
    public void unFlagNewState(){
        hasNewState = false;
        
    }
    
    /**
     *
     * @return
     */
    public boolean hasNewState() {
        return hasNewState;
    }
    
    /**
     *
     * @return
     */
    public int getType() {
        return type;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public State getCurrentState() {
        return currentState;
    }

    /**
     *
     * @param currentState
     */
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    /**
     *
     * @return
     */
    public State getNextState() {
        return nextState;
    }

    /**
     *
     * @param nextState
     */
    public void setNextState(State nextState) {
        this.nextState = nextState;
        flagNewState();
    }
    
    //--------Others

    /**
     *
     * @param c
     * @return
     */
    
    public boolean typeMatch (Cell c){
        return c.getId()==this.getId();
        
    }
    
    /**
     *
     * @return
     */
    public boolean isRuleCell(){
        return rule;
        
    }
    //change this to put history
    
    //will update the cell, unflags the changes, current becomes new, new becomes null

    /**
     *
     */
    public void updateCell(){
        if(hasNewState()){
            unFlagNewState();
            currentState = nextState;
            nextState = null;
            
            
        }
        
    }
    
    public String toString(){
        
        return currentState.toString();
         
        
    }

    /**
     *
     * @return
     */
    public String idString(){
        
        return Integer.toString(id);
         
        
    }
    
    
    
    //--------Abstracts

    /**
     *
     * @param c
     * @return
     */
    
    public abstract boolean compare (Cell c);
    
    
    
    
    
    
}
