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
public class StateTuple {

    int quantity;
    State state;
    int type;
    //-1 = fewer 0 equal 1 = greater;

    /**
     *
     * @param state
     * @param quantity
     * @param type
     */
    public StateTuple(State state, int quantity, int type) {
        this.quantity = quantity;
        this.state = state;
        this.type = type;
    }

    /**
     *
     * @param c
     * @return
     */
    public boolean validForCell(Cell c) {

        return c.checkStateTuple(this);
    }

    /**
     *
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     */
    public State getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(State state) {
        this.state = state;
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
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

}
