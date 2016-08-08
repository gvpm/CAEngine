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
public class Rule {

    Cell ruleCell;
    State nextState;

    /**
     *
     * @param ruleCell
     * @param nextState
     */
    public Rule(Cell ruleCell, State nextState) {
        this.ruleCell = ruleCell;
        this.nextState = nextState;
    }

    /**
     *
     * @param c
     */
    public void apply(Cell c) {

        if (c.compare(ruleCell)) {
            c.setNextState(nextState);

        }

    }

}
