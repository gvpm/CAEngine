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
    //1 for absorbing
    int boundariesType;
    int nOfRows;
    int nOfColumns;
    
    GridFactory gridFac;
    StateFactory stateFac;
    //set up the inicial parameters for the engine
    //receives the parameters for the grid
    Grid grid;
    
    ArrayList<State> states;

    public CAEngine() {
        states = new ArrayList<State>();
    }
    
    

    public void setup(int gridType, int cellType,int stateType, int boundariesType, int nOfRows, int nOfColumns) {
        this.gridType = gridType;
        this.cellType = cellType;
        this.stateType = stateType;
        this.nOfRows = nOfRows;
        this.nOfColumns = nOfColumns;
        gridFac = new GridFactory();
        stateFac = new StateFactory();
        //creates the proper grid
        createGrid();
    }
    

    //creates the grid
    public void init(){
        
        grid.initGrid();
        grid.loadDefaultStates();
        grid.setNeighbours();
        grid.printNeighbours(4);
      
      
    }
    
    //creates and setup the grid;
    public void createGrid (){
         grid = gridFac.fabricate(gridType,this);
         grid.setupGrid(nOfRows, nOfColumns);
    }
    
   
    //calls the grid state
    public State createState(int type, Object value) {
        State newState= stateFac.fabricate(type, value);
        states.add(newState);
        return newState;
    }
    
    
    
    
    
    public void iterate(){
        throw new UnsupportedOperationException("Not supported yet."); 

        
    }
    
    public ArrayList<State> getStates(){
        return states;
        
    }
    
    
    
    public int num(){
        
        return 1;
    }

    public int getGridType() {
        return gridType;
    }

    public int getCellType() {
        return cellType;
    }

    public int getStateType() {
        return stateType;
    }
    
    public String lineString(){
        
        return grid.lineString();
    }
    public String idString(){
        
        return grid.idString();
    }
    
    

 
    
}
