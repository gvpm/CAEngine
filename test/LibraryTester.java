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
        e.setup(1, 1, 1, 1, 10, 10);

        State stateZero = e.createState(1, 0);
        State stateOne = e.createState(1, 1);
        e.init();

        SquareCell rule1Cell = (SquareCell) e.createRuleCell(1, 1);
        rule1Cell.initRuleCell(stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne);

        //e.createImageRule(rule1Cell, stateZero);
        ///TEST QUAANTITY
        Rule underPop = e.createQuantityRule(stateOne, stateZero);
        Rule overPop = e.createQuantityRule(stateOne, stateZero);
        Rule reproduction = e.createQuantityRule(stateZero, stateOne);

        underPop.addStateTuple(e.createStateTuple(stateOne, 2, -1));
        overPop.addStateTuple(e.createStateTuple(stateOne, 3, 1));
        reproduction.addStateTuple(e.createStateTuple(stateOne, 3, 0));

        System.out.println("ID Matrix");
        System.out.println(e.idMatrix());

        System.out.println("");
        System.out.println("States Matrix");
        System.out.println("");
        System.out.println(e.stateMatrix());

        e.changeState(35, stateOne);
        e.changeState(36, stateOne);
        e.changeState(34, stateOne);
        //e.changeState(24, stateOne);
        // e.changeState(25, stateOne);
        //e.changeState(26, stateOne);
        e.changeState(80, stateOne);
        e.changeState(81, stateOne);
        e.changeState(90, stateOne);
        e.changeState(91, stateOne);
        e.changeState(62, stateOne);
        e.changeState(63, stateOne);
        e.changeState(72, stateOne);
        e.changeState(73, stateOne);

        System.out.println("");
        System.out.println("States Matrix");
        System.out.println("");
        System.out.println(e.stateMatrix());

        for (int i = 0; i < 13; i++) {
            System.out.println("");
            System.out.println("Iteration");
            System.out.println("");

            e.iterate();

            //System.out.println(e.stateString());
            //System.out.println(e.idString());
            System.out.println("");
            System.out.println("States Matrix");
            System.out.println("");
            System.out.println(e.stateMatrix());

        }

    }

}
