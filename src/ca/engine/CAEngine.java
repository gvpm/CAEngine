/*
 *
 */
package ca.engine;

import java.util.ArrayList;

/**
 * Its the only class you have access from the outside It provides all the
 * interface necessary to interact with the CAEngine
 *
 * @author gvpm
 */
public class CAEngine {

    int gridType;
    int cellType;
    int stateType;
    //1 circular 2 absorbing
    int boundaryType;
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
     * It creates the storing place for the rules and states available
     */
    public CAEngine() {
        states = new ArrayList<>();
        rules = new ArrayList<>();

    }

    /**
     * Once an engine is instantiated it needs to be set up After the setup is
     * done, the grid will be automatically created.
     *
     * @param gridType Type of the grid (1=SquareGrid)
     * @param cellType Type Of the cell (1=SquareCell)
     * @param stateType Type of the state (1=intState, 2=stringState)
     * @param boundaryType Type of the boundary (1=Circular, 2= Fix)
     * @param nOfRows Number of rows
     * @param nOfColumns Number of columns
     */
    public void setup(int gridType, int cellType, int stateType, int boundaryType, int nOfRows, int nOfColumns) {
        this.gridType = gridType;
        this.cellType = cellType;
        this.stateType = stateType;
        this.boundaryType = boundaryType;
        this.nOfRows = nOfRows;
        if (nOfRows <= 0) {
            throw new UnsupportedOperationException("Number of rows not supported yet Try > 2");
        }
        this.nOfColumns = nOfColumns;
        if (nOfColumns <= 0) {
            throw new UnsupportedOperationException("Number of columns not supported yet. Try > 2");
        }

        gridFac = new GridFactory();
        stateFac = new StateFactory();
        cellFac = new CellFactory();
        //creates the proper grid
        createGrid();
    }

    //creates the grid
    /**
     * The grid is initialised, all the cells will receive the first state
     * created The neighbours will be set for all the cells according to the
     * boundary set.
     *
     */
    public void init() {

        grid.initGrid();
        grid.loadDefaultStates();
        grid.setNeighbours();
        //grid.printNeighbours(9);

    }

    /**
     * Is a cell like the others on the grid but it does not store references to
     * neighbours It stores the current state of the cell and the states that
     * the neighbours need to be to match the rule that the ruleCell is applied.
     *
     * You have to initialise the ruleCell create using initRuleCell(...)
     *
     * @param cellType Type of the cell.
     * @param gridType Type of the grid.
     * @return A ruleCell that needs to be initialised
     */
    public Cell createRuleCell(int cellType, int gridType) {

        return cellFac.fabricate(cellType, gridType, true);
    }

    /**
     * It contains a ruleCell and a next State Use apply(Cell c) with another
     * cell of the same type if it matches the rule will tell the it to change
     * its state in the next iteration. Once created the rule is automatically
     * added to the rules from the engine it will automatically be checked on
     * the iterations
     *
     * @param ruleCell The ruleCell that needs to be matched
     * @param nextState The next state the cell should get
     * @return Returns the rule created
     */
    public Rule createImageRule(Cell ruleCell, State nextState) {

        Rule r = new Rule(ruleCell, nextState);
        rules.add(r);
        return r;

    }

    /**
     * State tuple with a state and the quantity to check and if its greater
     * smaller o equal. Its the condition to the Quantity rule.
     *
     * @param s state to be checked
     * @param quantity quantity of that state to compare to the neighbours
     * @param type -1 = total number of neighbours with that state is less than
     * "quantity", 0 = // equal to "quantity", 1= // greater than "quantity"
     * @return returns the state tuple to be added in the quantity rule
     */
    public StateTuple createStateTuple(State s, int quantity, int type) {

        return new StateTuple(s, quantity, type);
    }

    /**
     * Creates quantity rules, it needs a stateTuple to function. A Cell that
     * matched the current state that has the stateTuple condition satisfied
     * will be notified to change its state to the next state in the next
     * iteration
     *
     *
     * Once created the rule is automatically added to the rules from the engine
     * it will automatically be checked on the iterations
     *
     * @param currentState Current state of the cell checked
     * @param nextState Next state for the cell to go if it matches the
     * conditions
     * @return The rule creates
     */
    public Rule createQuantityRule(State currentState, State nextState) {

        Rule r = new Rule(currentState, nextState);
        rules.add(r);

        return r;

    }

    /**
     * Creates and sets up the grid with the values previously received
     */
    public void createGrid() {
        grid = gridFac.fabricate(gridType, this);
        grid.setupGrid(nOfRows, nOfColumns);
    }

    //calls the grid state
    /**
     * Creation of a state from the outside. States created via this function
     * are automatically added to the state list of the engine The first state
     * created is set to be the default state of the machine.
     *
     * The state will be created by the stateFactory and it will be passed
     * outside
     *
     * @param type Type of the state to be created (1= int state, 2= String
     * state)
     * @param value The value of that state (The value passed here should match
     * the type selected)
     * @return The new state that was created
     */
    public State createState(int type, Object value) {
        State newState = stateFac.fabricate(type, value);
        states.add(newState);
        return newState;
    }

    /**
     * The iteration of the engine It checks all the rules in all the cells in
     * the grid After all the cells are notified with their next states (if
     * applicable) a grid update is done and the cells update themselves to the
     * new state
     */
    public void iterate() {
        //throw new UnsupportedOperationException("Not supported yet.");
        for (int i = 0; i < rules.size(); i++) {
            grid.applyRule(rules.get(i));

        }
        grid.updateGrid();

    }

    /**
     * Changes the state of an specific cell to a given state
     *
     * @param id the id of the cell to change
     * @param s the new state of the cell
     */
    public void changeState(int id, State s) {
        Cell c = grid.getCell(id);
        if (c.getCurrentState().getType() == s.getType()) {
            c.setCurrentState(s);

        } else {
            throw new UnsupportedOperationException("State types dont match");

        }

    }

    //////////////////Getters, Setters and toStrings
    /**
     *
     * @return
     */
    public ArrayList<State> getStates() {
        return states;

    }

    /**
     *
     * @param id
     * @return
     */
    public String getStateString(int id) {

        return grid.getCell(id).toString();
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
     * @return a string with all the current states of cells with a space
     * between
     */
    public String stateString() {

        return grid.stateString();
    }

    /**
     *
     * @return a vector with all the states as Strings
     */
    public String[] stateVector() {

        return stateString().split(" ");
    }

    /**
     *
     * @return
     */
    public String idString() {

        return grid.idString();
    }

    /**
     *
     * @return a string with the IDS to be printed in the console in a form of
     * matrix taking in account the number of rows and columns
     */
    public String idMatrix() {

        return grid.idMatrix();
    }

    /**
     * @return a string with the States to be printed in the console in a form
     * of matrix taking in account the number of rows and columns
     */
    public String stateMatrix() {

        return grid.stateMatrix();
    }

    /**
     * Gets an id and prints the neighbours of that cell Currently used for
     * testing
     *
     * @param id the id of the cell to check
     */
    public void printNeighbours(int id) {
        //throw new UnsupportedOperationException("Not Supported yet");

        grid.printNeighbours(id);
    }

    /**
     *
     * @return the current boundary type set in the engine
     */
    public int getBoundaryType() {
        return boundaryType;
    }

    /**
     *
     * @return
     */
    public String getGridConfString() {

        String r = gridType + " ";
        r = r.concat(cellType + " ");
        r = r.concat(stateType + " ");
        r = r.concat(boundaryType + " ");
        r = r.concat(nOfRows + " ");
        r = r.concat(nOfColumns + "");

        return r;
    }

    /**
     *
     * @param fileName
     */
    public void save(String fileName) {

        FileSaver f = new FileSaver(this, fileName);
        f.run();

    }

    /**
     *
     * @return
     */
    public ArrayList<State> getStateList() {

        return states;
    }

    /**
     *
     * @return
     */
    public ArrayList<Rule> getRuleList() {

        return rules;
    }

    /**
     *
     * @param s
     * @return
     */
    public State getStateFromString(String s) {
        State resp = null;
        for (int i = 0; i < states.size(); i++) {
            if (states.get(i).toString().compareTo(s) == 0) {
                resp = states.get(i);
            }

        }

        return resp;
    }

    /**
     *
     * @return
     */
    public int getnOfRows() {
        return nOfRows;
    }

    /**
     *
     * @return
     */
    public int getnOfColumns() {
        return nOfColumns;
    }

}
