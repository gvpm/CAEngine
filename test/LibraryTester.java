/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ca.engine.*;

/**
 *
 * @author gvpm
 */
public class LibraryTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CAEngine e = new CAEngine();
        e.setup(1, 1, 1, 1, 3, 3);

        State stateOne = e.createState(1, 1);
        State stateZero = e.createState(1, 0);
        e.init();

        SquareCell rule1Cell = (SquareCell) e.createRuleCell(1, 1);
        rule1Cell.initRuleCell(stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne);

        e.createImageRule(rule1Cell, stateZero);

        System.out.println(e.lineString());
        System.out.println(e.idString());

        e.iterate();

        System.out.println(e.lineString());
        System.out.println(e.idString());

    }

}
