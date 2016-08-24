/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Is loads the Engine config from a file with a given name
 *
 * It loads all the grid configs and inits the grid Loads all the states
 * supported Loads all the rules
 *
 * Currently not supporting the initial condition, still loading default state.
 *
 * @author gvpm
 */
public class FileLoader {

    CAEngine e;
    String fileName;
    int gridType;
    int cellType;
    int stateType;
    int boundaryType;
    int nOfRows;
    int nOfColumns;

    /**
     *
     * @param fileName
     */
    public FileLoader(String fileName) {
        this.fileName = fileName;
    }

    /**
     *
     * @return returns the CAEngine fully initialised with grid, states and
     * rules
     * @throws IOException
     */
    public CAEngine load() throws IOException {

        FileReader f;
        try {
            f = new FileReader(fileName);

            BufferedReader b;
            b = new BufferedReader(f);
            boolean eof = false;
            String initLine = b.readLine();
            String[] initSv = initLine.split(" ");

            e = new CAEngine();
            gridType = Integer.parseInt(initSv[0]);
            cellType = Integer.parseInt(initSv[1]);
            stateType = Integer.parseInt(initSv[2]);
            boundaryType = Integer.parseInt(initSv[3]);
            nOfRows = Integer.parseInt(initSv[4]);
            nOfColumns = Integer.parseInt(initSv[5]);
            e.setup(gridType, cellType, stateType, boundaryType, nOfRows, nOfColumns);
            int gridInit = 0;

            while (!eof) {
                String line = b.readLine();
                if (line == null) {//Case where line is empty, end of file
                    eof = true;
                } else if (line.startsWith("STATE")) {
                    String[] sv = line.split(":");
                    System.out.println(sv[1]);
                    if (cellType == 1) {
                        e.createState(stateType, Integer.parseInt(sv[1]));
                    } else if (cellType == 2) {
                        e.createState(stateType, sv[1]);
                    }

                } else if (line.startsWith("ENDOFSTATE")) {
                    if (gridInit == 0) {
                        e.init();
                        gridInit = 1;
                    }

                } else if (line.startsWith("IMAGERULE")) {

                    String[] sv = line.split(":");
                    String[] parameters = sv[1].split(" ");
                    SquareCell rule1Cell = (SquareCell) e.createRuleCell(cellType, gridType);
                    rule1Cell.initRuleCell(e.getStateFromString(parameters[0]), e.getStateFromString(parameters[1]), e.getStateFromString(parameters[2]), e.getStateFromString(parameters[3]), e.getStateFromString(parameters[4]), e.getStateFromString(parameters[5]), e.getStateFromString(parameters[6]), e.getStateFromString(parameters[7]), e.getStateFromString(parameters[8]));
                    e.createImageRule(rule1Cell, e.getStateFromString(parameters[9]));

                } else if (line.startsWith("QUANTITYRULE")) {
                    String[] sv = line.split(":");
                    String[] parameters = sv[1].split(" ");
                    Rule r = e.createQuantityRule(e.getStateFromString(parameters[0]), e.getStateFromString(parameters[1]));

                    r.addStateTuple(e.createStateTuple(e.getStateFromString(parameters[2]), Integer.parseInt(parameters[3]), Integer.parseInt(parameters[4])));

                }

            }
            b.close();

        } catch (FileNotFoundException ex) {

        }
        return e;

    }
}
