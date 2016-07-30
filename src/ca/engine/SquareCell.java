/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.engine;


public class SquareCell extends Cell {
    SquareCell northWestNeighbour;
    SquareCell northNeighbour;
    SquareCell northEastNeighbour;
    SquareCell eastNeighbour;
    SquareCell southEastNeighbour;
    SquareCell southNeighbour;
    SquareCell southWestNeighbour;
    SquareCell westNeighbour;
    
    State nwSt,nSt,neSt,eSt,seSt,sSt,swSt,wSt;

    public SquareCell(int id,boolean rule) {
        super(id,rule);
        
    }
    public void initRuleCell(State nw, State n,State ne,State e,State se,State s,State sw,State w ) {
        
        if(this.isRuleCell()){
        this.nwSt=nw;
        this.nSt=n;
        this.neSt=ne;
        this.eSt=e;
        this.seSt=se;
        this.sSt=s;
        this.swSt=sw;
        this.wSt=w;
        }else{
            throw new UnsupportedOperationException("This is not a rule cell");
        }
            
    }
    

    @Override
       public boolean compare(Cell ruleCell) {
        if(ruleCell.getType()==this.getType()){
        SquareCell c = (SquareCell) ruleCell;
        
        if(c.isRuleCell()){
        
        boolean c0= this.currentState.compare(c.currentState);
        boolean c1 = this.getNorthWestNeighbour().getCurrentState().compare(c.getNWSt());
        boolean c2 = this.getNorthNeighbour().getCurrentState().compare(c.getNSt());
        boolean c3 = this.getNorthEastNeighbour().getCurrentState().compare(c.getNESt());
        boolean c4= this.getEastNeighbour().getCurrentState().compare(c.getESt());
        boolean c5 = this.getSouthEastNeighbour().getCurrentState().compare(c.getSESt());
        boolean c6 = this.getSouthNeighbour().getCurrentState().compare(c.getSSt());
        boolean c7 = this.getSouthWestNeighbour().getCurrentState().compare(c.getSWSt());
        boolean c8 = this.getWestNeighbour().getCurrentState().compare(c.getWSt());
        
        return  c0&&c1&&c2&&c3&&c4&&c5&&c6&&c7&&c8;
        
        }else{
            throw new UnsupportedOperationException("Cell to compare is not a Rule ");
            
        }
        }
         throw new UnsupportedOperationException("Cell type wont match rule type ");
        
        
    }
    
    //-----------Getters and Setters

    public SquareCell getNorthWestNeighbour() {
        return northWestNeighbour;
    }

    public void setNorthWestNeighbour(SquareCell northWestNeighbour) {
        this.northWestNeighbour = northWestNeighbour;
    }

    public SquareCell getNorthNeighbour() {
        return northNeighbour;
    }

    public void setNorthNeighbour(SquareCell northNeighbour) {
        this.northNeighbour = northNeighbour;
    }

    public SquareCell getNorthEastNeighbour() {
        return northEastNeighbour;
    }

    public void setNorthEastNeighbour(SquareCell northEastNeighbour) {
        this.northEastNeighbour = northEastNeighbour;
    }

    public SquareCell getEastNeighbour() {
        return eastNeighbour;
    }

    public void setEastNeighbour(SquareCell eastNeighbour) {
        this.eastNeighbour = eastNeighbour;
    }

    public SquareCell getSouthEastNeighbour() {
        return southEastNeighbour;
    }

    public void setSouthEastNeighbour(SquareCell southEastNeighbour) {
        this.southEastNeighbour = southEastNeighbour;
    }

    public SquareCell getSouthNeighbour() {
        return southNeighbour;
    }

    public void setSouthNeighbour(SquareCell southNeighbour) {
        this.southNeighbour = southNeighbour;
    }

    public SquareCell getSouthWestNeighbour() {
        return southWestNeighbour;
    }

    public void setSouthWestNeighbour(SquareCell southWestNeighbour) {
        this.southWestNeighbour = southWestNeighbour;
    }

    public SquareCell getWestNeighbour() {
        return westNeighbour;
    }

    public void setWestNeighbour(SquareCell westNeighbour) {
        this.westNeighbour = westNeighbour;
    }

    public State getNWSt() {
        return nwSt;
    }

    public void setNWSt(State nwSt) {
        this.nwSt = nwSt;
    }

    public State getNSt() {
        return nSt;
    }

    public void setNSt(State nSt) {
        this.nSt = nSt;
    }

    public State getNESt() {
        return neSt;
    }

    public void setNESt(State neSt) {
        this.neSt = neSt;
    }

    public State getESt() {
        return eSt;
    }

    public void setESt(State eSt) {
        this.eSt = eSt;
    }

    public State getSESt() {
        return seSt;
    }

    public void setSESt(State seSt) {
        this.seSt = seSt;
    }

    public State getSSt() {
        return sSt;
    }

    public void setSSt(State sSt) {
        this.sSt = sSt;
    }

    public State getSWSt() {
        return swSt;
    }

    public void setSWSt(State swSt) {
        this.swSt = swSt;
    }

    public State getWSt() {
        return wSt;
    }

    public void setWSt(State wSt) {
        this.wSt = wSt;
    }

    
}
