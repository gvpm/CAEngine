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
public abstract class Grid {

    CAEngine engine;

    ArrayList<Cell> cells;

    int type;
    int rows, columns;
    int nOfCells;

    CellFactory cellFac = new CellFactory();

    /**
     *
     * @param engine
     */
    public Grid(CAEngine engine) {
        this.engine = engine;
        cells = new ArrayList<Cell>();
    }

    /**
     *
     * @param rows
     * @param columns
     */
    public abstract void setupGrid(int rows, int columns);

    /**
     *
     */
    public abstract void initGrid();

    /**
     *
     * @param c
     */
    public void addCell(Cell c) {
        cells.add(c);
    }

    //loads the first state into all the cells
    /**
     *
     */
    public void loadDefaultStates() {
        if (!engine.getStates().isEmpty()) {
            if (!cells.isEmpty()) {

                for (int i = 0; i < cells.size(); i++) {
                    cells.get(i).setCurrentState(engine.getStates().get(0));

                }

            } else {
                throw new UnsupportedOperationException("Init grid fisrt");
            }

        } else {
            throw new UnsupportedOperationException("No  states created");

        }

    }
    //SET NAIGHBOURS ACORDING TO BAUNDARIES

    /**
     *
     */
    public abstract void setNeighbours();

    /**
     *
     * @param id
     */
    public abstract void printNeighbours(int id);

    /**
     *
     * @return
     */
    public String lineString() {
        String r;
        r = null;
        for (int i = 0; i < cells.size(); i++) {
            if (i == 0) {

                r = cells.get(0).toString();
            } else {
                r = r + " " + cells.get(i).toString();
            }

        }
        //r=cells.get(0).toString();
        return r;

    }

    /**
     *
     * @return
     */
    public String idString() {
        String r;
        r = null;
        for (int i = 0; i < cells.size(); i++) {
            if (i == 0) {

                r = cells.get(0).idString();
            } else {
                r = r + " " + cells.get(i).idString();
            }

        }
        //r=cells.get(0).toString();
        return r;

    }

    /**
     *
     * @param r
     */
    public void applyRule(Rule r) {
        for (int i = 0; i < cells.size(); i++) {
            r.apply(cells.get(i));

        }

    }

    /**
     *
     */
    public void updateGrid() {
        for (int i = 0; i < cells.size(); i++) {
            cells.get(i).updateCell();
        }
    }

}
