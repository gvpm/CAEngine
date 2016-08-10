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
public class CAEngine {

    int gridType;
    int cellType;
    int stateType;
    //1 circular
    int boundariesType;
    int nOfRows;
    int nOfColumns;

    GridFactory gridFac;
    StateFactory stateFac;
    CellFactory cellFac;
    //set up the inicial parameters for the engine
    //receives the parameters for the grid
    Grid grid;

    ArrayList<State> states;
    ArrayList<Rule> rules;

    /**
     *
     */
    public CAEngine() {
        states = new ArrayList<State>();
        rules = new ArrayList<Rule>();

    }

    /**
     *
     * @param gridType
     * @param cellType
     * @param stateType
     * @param boundariesType
     * @param nOfRows
     * @param nOfColumns
     */
    public void setup(int gridType, int cellType, int stateType, int boundariesType, int nOfRows, int nOfColumns) {
        this.gridType = gridType;
        this.cellType = cellType;
        this.stateType = stateType;
        this.boundariesType = boundariesType;
        this.nOfRows = nOfRows;
        this.nOfColumns = nOfColumns;
        gridFac = new GridFactory();
        stateFac = new StateFactory();
        cellFac = new CellFactory();
        //creates the proper grid
        createGrid();
    }

    //creates the grid
    /**
     *
     */
    public void init() {

        grid.initGrid();
        grid.loadDefaultStates();
        grid.setNeighbours();
        //grid.printNeighbours(9);

    }
    //the rule that checks if a cell and its neghjbours are in a determinate state

    /**
     *
     * @param ruleCell
     * @param nextState
     * @return
     */
    public Rule createImageRule(Cell ruleCell, State nextState) {

        Rule r = new Rule(ruleCell, nextState);
        rules.add(r);
        return r;

    }
    //creates a rule cell to be initialized outside the engine and used later in the rule

    /**
     *
     * @param cellType
     * @param gridType
     * @return
     */
    public Cell createRuleCell(int cellType, int gridType) {

        return cellFac.fabricate(cellType, gridType, true);
    }

    //creates and setup the grid;
    /**
     *
     */
    public void createGrid() {
        grid = gridFac.fabricate(gridType, this);
        grid.setupGrid(nOfRows, nOfColumns);
    }

    //calls the grid state
    /**
     *
     * @param type
     * @param value
     * @return
     */
    public State createState(int type, Object value) {
        State newState = stateFac.fabricate(type, value);
        states.add(newState);
        return newState;
    }

    /**
     *
     */
    public void iterate() {
        //throw new UnsupportedOperationException("Not supported yet.");
        for (int i = 0; i < rules.size(); i++) {
            grid.applyRule(rules.get(i));

        }
        grid.updateGrid();

    }

    /**
     *
     * @return
     */
    public ArrayList<State> getStates() {
        return states;

    }

    /**
     *
     * @return
     */
    public int num() {

        return 1;
    }

    /**
     *
     * @return
     */
    public int getGridType() {
        return gridType;
    }

    /**
     *
     * @return
     */
    public int getCellType() {
        return cellType;
    }

    /**
     *
     * @return
     */
    public int getStateType() {
        return stateType;
    }

    /**
     *
     * @return
     */
    public String stateString() {

        return grid.stateString();
    }

    /**
     *
     * @return
     */
    public String idString() {

        return grid.idString();
    }

    public String idMatrix() {

        return grid.idMatrix();
    }

    public String stateMatrix() {

        return grid.stateMatrix();
    }

    public void printNeighbours(int id) {

        grid.printNeighbours(id);
    }

    /**
     *
     * @return
     */
    public int getBoundariesType() {
        return boundariesType;
    }

}
