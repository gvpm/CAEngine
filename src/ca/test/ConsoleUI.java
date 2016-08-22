package ca.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ca.engine.*;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author gvpm
 */
public class ConsoleUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner in;
        in = new Scanner(System.in);
        boolean control = true;
        int clearZero = 1;
        CAEngine e = new CAEngine();

        System.out.println("Rows: ");
        int rows = in.nextInt();
        System.out.println("Columns: ");
        int columns = in.nextInt();
        System.out.println("");

        ///*
        e.setup(1, 1, 1, 1, rows, columns);
        State stateZero = e.createState(1, 0);
        State stateOne = e.createState(1, 1);

        e.init();

        //*/
        /*
        e.setup(1, 1, 2, 1, rows, columns);
        State stateZero = e.createState(2, "0");
        State stateOne = e.createState(2, "1");
        e.init();
         */
        int rule = 0;

        while (rule < 1 || rule > 2) {
            System.out.println("1-Rule 185\n2-gameOfLife");
            rule = in.nextInt();

        }

        if (rule == 1) {
            //RULE185
            SquareCell rule1Cell = (SquareCell) e.createRuleCell(1, 1);
            rule1Cell.initRuleCell(stateOne, null, null, null, stateOne, null, null, null, stateOne);
            e.createImageRule(rule1Cell, stateOne);

            SquareCell rule2Cell = (SquareCell) e.createRuleCell(1, 1);
            rule2Cell.initRuleCell(stateOne, null, null, null, stateZero, null, null, null, stateOne);
            e.createImageRule(rule2Cell, stateZero);

            SquareCell rule3Cell = (SquareCell) e.createRuleCell(1, 1);
            rule3Cell.initRuleCell(stateZero, null, null, null, stateOne, null, null, null, stateOne);
            e.createImageRule(rule3Cell, stateOne);

            SquareCell rule4Cell = (SquareCell) e.createRuleCell(1, 1);
            rule4Cell.initRuleCell(stateZero, null, null, null, stateZero, null, null, null, stateOne);
            e.createImageRule(rule4Cell, stateOne);

            SquareCell rule5Cell = (SquareCell) e.createRuleCell(1, 1);
            rule5Cell.initRuleCell(stateOne, null, null, null, stateOne, null, null, null, stateZero);
            e.createImageRule(rule5Cell, stateOne);

            SquareCell rule6Cell = (SquareCell) e.createRuleCell(1, 1);
            rule6Cell.initRuleCell(stateOne, null, null, null, stateZero, null, null, null, stateZero);
            e.createImageRule(rule6Cell, stateZero);

            SquareCell rule7Cell = (SquareCell) e.createRuleCell(1, 1);
            rule7Cell.initRuleCell(stateZero, null, null, null, stateOne, null, null, null, stateZero);
            e.createImageRule(rule7Cell, stateZero);

            SquareCell rule8Cell = (SquareCell) e.createRuleCell(1, 1);
            rule8Cell.initRuleCell(stateZero, null, null, null, stateZero, null, null, null, stateZero);
            e.createImageRule(rule8Cell, stateZero);
        } else if (rule == 2) {
            ///GAME OF LIFE

            Rule underPop = e.createQuantityRule(stateOne, stateZero);
            Rule overPop = e.createQuantityRule(stateOne, stateZero);
            Rule reproduction = e.createQuantityRule(stateZero, stateOne);

            underPop.addStateTuple(e.createStateTuple(stateOne, 2, -1));
            overPop.addStateTuple(e.createStateTuple(stateOne, 3, 1));
            reproduction.addStateTuple(e.createStateTuple(stateOne, 3, 0));
        }

        System.out.println("Neighbours of 0");
        e.printNeighbours(0);

        System.out.println("ID Matrix\n");
        System.out.println(e.idMatrix());

        System.out.println("");
        System.out.println("States Matrix");
        System.out.println("");
        System.out.println(e.stateMatrix());

        while (control) {
            System.out.println("\n1-Iterate\n2-Random Init\n3-Clear Grid\n4-Show Zeros\n5-Exit");
            int input = in.nextInt();

            switch (input) {
                case 1:
                    e.iterate();
                    System.out.println("\nITERATION");
                    System.out.println("");
                    System.out.println("States Matrix");
                    System.out.println("\n");
                    if (clearZero == 1) {
                        System.out.println(e.stateMatrix());
                    } else if (clearZero == -1) {
                        System.out.println(e.stateMatrix().replace("0", " "));
                    }
                    break;
                case 2:
                    Random gen = new Random();

                    for (int i = 0; i < rows * columns; i++) {
                        int rand = gen.nextInt(2);
                        if (rand == 0) {
                            e.changeState(i, stateZero);
                        } else if (rand == 1) {
                            e.changeState(i, stateOne);

                        }

                    }
                    System.out.println("\nRANDOM");
                    System.out.println("");
                    System.out.println("States Matrix");
                    System.out.println("\n");
                    if (clearZero == 1) {
                        System.out.println(e.stateMatrix());
                    } else if (clearZero == -1) {
                        System.out.println(e.stateMatrix().replace("0", " "));
                    }
                    break;
                case 3:
                    for (int i = 0; i < rows * columns; i++) {
                        e.changeState(i, stateZero);

                    }
                    System.out.println("\nCLEAR");
                    System.out.println("");
                    System.out.println("States Matrix");
                    System.out.println("\n");
                    if (clearZero == 1) {
                        System.out.println(e.stateMatrix());
                    } else if (clearZero == -1) {
                        System.out.println(e.stateMatrix().replace("0", " "));
                    }
                    break;
                case 4:
                    clearZero = -clearZero;
                    System.out.println("\nSHOW ZEROS");
                    System.out.println("");
                    System.out.println("States Matrix");
                    System.out.println("\n");
                    if (clearZero == 1) {
                        System.out.println(e.stateMatrix());
                    } else if (clearZero == -1) {
                        System.out.println(e.stateMatrix().replace("0", " "));
                    }
                    break;

                case 5:
                    control = false;
                    break;

            }

        }

    }

}
