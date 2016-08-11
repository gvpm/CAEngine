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

        State stateOne = e.createState(1, 1);
        State stateZero = e.createState(1, 0);
        e.init();

        SquareCell rule1Cell = (SquareCell) e.createRuleCell(1, 1);
        rule1Cell.initRuleCell(stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne);

        e.createImageRule(rule1Cell, stateZero);

        //System.out.println(e.stateString());
        //System.out.println(e.idString());
        System.out.println("ID Matrix");
        System.out.println(e.idMatrix());

        System.out.println("");
        System.out.println("States Matrix");
        System.out.println("");
        System.out.println(e.stateMatrix());

        System.out.println("");
        System.out.println("Iteration");
        System.out.println("");
        
        e.changeState(35, stateZero);
        
        System.out.println("");
        System.out.println("States Matrix");
        System.out.println("");
        System.out.println(e.stateMatrix());

        e.iterate();

        //System.out.println(e.stateString());
        //System.out.println(e.idString());
        System.out.println("");
        System.out.println("States Matrix");
        System.out.println("");
        System.out.println(e.stateMatrix());
        
       
    }

}
