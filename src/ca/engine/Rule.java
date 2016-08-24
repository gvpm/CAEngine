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
public class Rule {

    Cell ruleCell;
    State nextState;
    int type;

    State currentState;
    StateTuple stateTuple;

    //1 for image 2 for quant
    /**
     *
     * @param ruleCell
     * @param nextState
     */
    public Rule(Cell ruleCell, State nextState) {
        this.ruleCell = ruleCell;
        this.nextState = nextState;
        this.type = 1;
    }
    //still nedd to add state and numbers;

    /**
     *
     * @param currentState
     * @param nextState
     */
    public Rule(State currentState, State nextState) {
        this.type = 2;
        this.currentState = currentState;
        this.nextState = nextState;

    }

    /**
     *
     * @param st
     */
    public void addStateTuple(StateTuple st) {
        if (this.type == 2) {
            stateTuple = st;

        }

    }

    /**
     *
     * @param c
     */
    public void apply(Cell c) {

        if (this.type == 1) {
            if (c.compare(ruleCell)) {
                c.setNextState(nextState);

            }
        } else if (this.type == 2) {

            if (c.getCurrentState().compare(currentState) && checkStateTuple(c)) {
                c.setNextState(nextState);

            }

        }

    }

    /**
     *
     * @param c
     * @return
     */
    public boolean checkStateTuple(Cell c) {

        return stateTuple.validForCell(c);

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
    public Cell getRuleCell() {
        return ruleCell;
    }

    /**
     *
     * @return
     */
    public StateTuple getStateTuple() {
        return stateTuple;
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
     * @return
     */
    public State getCurrentState() {
        return currentState;
    }

}
