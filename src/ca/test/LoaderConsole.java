package ca.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ca.engine.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author gvpm
 */
public class LoaderConsole {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        Scanner in;
        in = new Scanner(System.in);
        boolean control = true;
        int clearZero = 1;
        
        CAEngine e;
        
        System.out.println("File to load: ");
        String fileName = in.nextLine();
        
        FileLoader loader = new FileLoader(fileName);
        
        e = loader.load();
        int rows = e.getnOfRows();
        int columns = e.getnOfColumns();
        ArrayList<State> states = e.getStateList();

        //*/
        /*
        e.setup(1, 1, 2, 1, rows, columns);
        State stateZero = e.createState(2, "0");
        State stateOne = e.createState(2, "1");
        e.init();
         */
        System.out.println("Neighbours of 0");
        e.printNeighbours(0);
        
        System.out.println("ID Matrix\n");
        System.out.println(e.idMatrix());
        
        System.out.println("");
        System.out.println("States Matrix");
        System.out.println("");
        System.out.println(e.stateMatrix());
        
        while (control) {
            System.out.println("\n1-Iterate\n2-Random Init\n3-Clear Grid\n4-Show Zeros\n5-Save\n6-Exit");
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
                            e.changeState(i, states.get(0));
                        } else if (rand == 1) {
                            e.changeState(i, states.get(1));
                            
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
                        e.changeState(i, states.get(0));
                        
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
                    e.save("save.txt");
                    break;
                case 6:
                    control = false;
                    break;
                
            }
            
        }
        
    }
    
}
