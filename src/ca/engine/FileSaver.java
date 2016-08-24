/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.engine;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Is saves the current Engine config in a file with a given name
 *
 * It stores all the grid configs All the states supported All the rules
 *
 * Currently not supporting the initial condition, still loading default state.
 *
 * @author gvpm
 */
public class FileSaver {

    CAEngine e;
    String fileName;
    ArrayList<State> states;
    ArrayList<Rule> rules;

    /**
     *
     * @param e
     * @param fileName
     */
    public FileSaver(CAEngine e, String fileName) {
        this.fileName = fileName;
        this.e = e;
        states = e.getStateList();
        rules = e.getRuleList();
    }

    /**
     *
     */
    public void run() {

        FileWriter arq = null;
        PrintWriter gravarArq;
        try {
            arq = new FileWriter(fileName);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error while creating output file.");

        }
        gravarArq = new PrintWriter(arq);

        gravarArq.println(e.getGridConfString());
        gravarArq.flush();

        for (int i = 0; i < states.size(); i++) {

            gravarArq.println("STATE:" + states.get(i));
            gravarArq.flush();

        }
        gravarArq.println("ENDOFSTATES");
        gravarArq.flush();

        for (int i = 0; i < rules.size(); i++) {

            if (rules.get(i).getType() == 1) {

                Rule ruleToPrint = rules.get(i);
                gravarArq.println("IMAGERULE:" + ruleToPrint.getRuleCell().getConfString() + " " + ruleToPrint.getNextState());
                gravarArq.flush();

            }

            if (rules.get(i).getType() == 2) {

                Rule ruleToPrint = rules.get(i);
                gravarArq.println("QUANTITYRULE:" + ruleToPrint.getCurrentState() + " " + ruleToPrint.getNextState() + " " + ruleToPrint.getStateTuple().getConfString());
                gravarArq.flush();

            }

        }

        gravarArq.close();
    }

}
