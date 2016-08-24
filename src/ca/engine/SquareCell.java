/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.engine;

/**
 *
 * @author gvpm
 */
public class SquareCell extends Cell {

    SquareCell northWestNeighbour;
    SquareCell northNeighbour;
    SquareCell northEastNeighbour;
    SquareCell eastNeighbour;
    SquareCell southEastNeighbour;
    SquareCell southNeighbour;
    SquareCell southWestNeighbour;
    SquareCell westNeighbour;

    State nwSt, nSt, neSt, eSt, seSt, sSt, swSt, wSt;

    /**
     *
     * @param id
     * @param rule
     */
    public SquareCell(int id, boolean rule) {
        super(id, rule);

    }

    /**
     *
     * @param current
     * @param nw
     * @param n
     * @param ne
     * @param e
     * @param se
     * @param s
     * @param sw
     * @param w
     */
    public void initRuleCell(State current, State nw, State n, State ne, State e, State se, State s, State sw, State w) {

        if (this.isRuleCell()) {
            this.currentState = current;
            this.nwSt = nw;
            this.nSt = n;
            this.neSt = ne;
            this.eSt = e;
            this.seSt = se;
            this.sSt = s;
            this.swSt = sw;
            this.wSt = w;
        } else {
            throw new UnsupportedOperationException("This is not a rule cell");
        }

    }

    /**
     *
     * @param ruleCell
     * @return
     */
    @Override
    public boolean compare(Cell ruleCell) {
        if (ruleCell.getType() == this.getType()) {
            SquareCell c = (SquareCell) ruleCell;

            if (c.isRuleCell()) {

                boolean c0 = this.currentState.compare(c.currentState);
                boolean c1;
                c1 = false;
                boolean c2;
                c2 = false;
                boolean c3;
                c3 = false;
                boolean c4;
                c4 = false;
                boolean c5;
                c5 = false;
                boolean c6;
                c6 = false;
                boolean c7;
                c7 = false;
                boolean c8;
                c8 = false;
                if (c.getNWSt() == null) {
                    c1 = true;//case when rule asks to ignore that cell
                } else if (this.getNorthWestNeighbour() != null) {
                    c1 = this.getNorthWestNeighbour().getCurrentState().compare(c.getNWSt());

                }

                if (c.getNSt() == null) {
                    c2 = true;
                } else if (this.getNorthNeighbour() != null) {
                    c2 = this.getNorthNeighbour().getCurrentState().compare(c.getNSt());
                }

                if (c.getNESt() == null) {
                    c3 = true;
                } else if (this.getNorthEastNeighbour() != null) {
                    c3 = this.getNorthEastNeighbour().getCurrentState().compare(c.getNESt());
                }

                if (c.getESt() == null) {
                    c4 = true;
                } else if (this.getEastNeighbour() != null) {
                    c4 = this.getEastNeighbour().getCurrentState().compare(c.getESt());
                }

                if (c.getSESt() == null) {
                    c5 = true;
                } else if (this.getSouthEastNeighbour() != null) {
                    c5 = this.getSouthEastNeighbour().getCurrentState().compare(c.getSESt());
                }

                if (c.getSSt() == null) {
                    c6 = true;
                } else if (this.getSouthNeighbour() != null) {
                    c6 = this.getSouthNeighbour().getCurrentState().compare(c.getSSt());
                }

                if (c.getSWSt() == null) {
                    c7 = true;
                } else if (this.getSouthWestNeighbour() != null) {
                    c7 = this.getSouthWestNeighbour().getCurrentState().compare(c.getSWSt());
                }

                if (c.getWSt() == null) {
                    c8 = true;
                } else if (this.getWestNeighbour() != null) {
                    c8 = this.getWestNeighbour().getCurrentState().compare(c.getWSt());
                }

                return c0 && c1 && c2 && c3 && c4 && c5 && c6 && c7 && c8;

            } else {
                throw new UnsupportedOperationException("Cell to compare is not a Rule ");

            }
        }
        throw new UnsupportedOperationException("Cell type wont match rule type ");

    }

    //-----------Getters and Setters
    /**
     *
     * @return
     */
    public SquareCell getNorthWestNeighbour() {
        return northWestNeighbour;
    }

    /**
     *
     * @param northWestNeighbour
     */
    public void setNorthWestNeighbour(SquareCell northWestNeighbour) {
        this.northWestNeighbour = northWestNeighbour;
    }

    /**
     *
     * @return
     */
    public SquareCell getNorthNeighbour() {
        return northNeighbour;
    }

    /**
     *
     * @param northNeighbour
     */
    public void setNorthNeighbour(SquareCell northNeighbour) {
        this.northNeighbour = northNeighbour;
    }

    /**
     *
     * @return
     */
    public SquareCell getNorthEastNeighbour() {
        return northEastNeighbour;
    }

    /**
     *
     * @param northEastNeighbour
     */
    public void setNorthEastNeighbour(SquareCell northEastNeighbour) {
        this.northEastNeighbour = northEastNeighbour;
    }

    /**
     *
     * @return
     */
    public SquareCell getEastNeighbour() {
        return eastNeighbour;
    }

    /**
     *
     * @param eastNeighbour
     */
    public void setEastNeighbour(SquareCell eastNeighbour) {
        this.eastNeighbour = eastNeighbour;
    }

    /**
     *
     * @return
     */
    public SquareCell getSouthEastNeighbour() {
        return southEastNeighbour;
    }

    /**
     *
     * @param southEastNeighbour
     */
    public void setSouthEastNeighbour(SquareCell southEastNeighbour) {
        this.southEastNeighbour = southEastNeighbour;
    }

    /**
     *
     * @return
     */
    public SquareCell getSouthNeighbour() {
        return southNeighbour;
    }

    /**
     *
     * @param southNeighbour
     */
    public void setSouthNeighbour(SquareCell southNeighbour) {
        this.southNeighbour = southNeighbour;
    }

    /**
     *
     * @return
     */
    public SquareCell getSouthWestNeighbour() {
        return southWestNeighbour;
    }

    /**
     *
     * @param southWestNeighbour
     */
    public void setSouthWestNeighbour(SquareCell southWestNeighbour) {
        this.southWestNeighbour = southWestNeighbour;
    }

    /**
     *
     * @return
     */
    public SquareCell getWestNeighbour() {
        return westNeighbour;
    }

    /**
     *
     * @param westNeighbour
     */
    public void setWestNeighbour(SquareCell westNeighbour) {
        this.westNeighbour = westNeighbour;
    }

    /**
     *
     * @return
     */
    public State getNWSt() {
        return nwSt;
    }

    /**
     *
     * @param nwSt
     */
    public void setNWSt(State nwSt) {
        this.nwSt = nwSt;
    }

    /**
     *
     * @return
     */
    public State getNSt() {
        return nSt;
    }

    /**
     *
     * @param nSt
     */
    public void setNSt(State nSt) {
        this.nSt = nSt;
    }

    /**
     *
     * @return
     */
    public State getNESt() {
        return neSt;
    }

    /**
     *
     * @param neSt
     */
    public void setNESt(State neSt) {
        this.neSt = neSt;
    }

    /**
     *
     * @return
     */
    public State getESt() {
        return eSt;
    }

    /**
     *
     * @param eSt
     */
    public void setESt(State eSt) {
        this.eSt = eSt;
    }

    /**
     *
     * @return
     */
    public State getSESt() {
        return seSt;
    }

    /**
     *
     * @param seSt
     */
    public void setSESt(State seSt) {
        this.seSt = seSt;
    }

    /**
     *
     * @return
     */
    public State getSSt() {
        return sSt;
    }

    /**
     *
     * @param sSt
     */
    public void setSSt(State sSt) {
        this.sSt = sSt;
    }

    /**
     *
     * @return
     */
    public State getSWSt() {
        return swSt;
    }

    /**
     *
     * @param swSt
     */
    public void setSWSt(State swSt) {
        this.swSt = swSt;
    }

    /**
     *
     * @return
     */
    public State getWSt() {
        return wSt;
    }

    /**
     *
     * @param wSt
     */
    public void setWSt(State wSt) {
        this.wSt = wSt;
    }

    /**
     *
     * @param st
     * @return
     */
    @Override
    public boolean checkStateTuple(StateTuple st) {
        int cont = 0;
        boolean c1;
        c1 = false;
        boolean c2;
        c2 = false;
        boolean c3;
        c3 = false;
        boolean c4;
        c4 = false;
        boolean c5;
        c5 = false;
        boolean c6;
        c6 = false;
        boolean c7;
        c7 = false;
        boolean c8;
        c8 = false;
        if (this.getNorthWestNeighbour() != null) {
            c1 = this.getNorthWestNeighbour().getCurrentState().compare(st.getState());
        }
        if (this.getNorthNeighbour() != null) {
            c2 = this.getNorthNeighbour().getCurrentState().compare(st.getState());
        }
        if (this.getNorthEastNeighbour() != null) {
            c3 = this.getNorthEastNeighbour().getCurrentState().compare(st.getState());
        }
        if (this.getEastNeighbour() != null) {
            c4 = this.getEastNeighbour().getCurrentState().compare(st.getState());
        }

        if (this.getSouthEastNeighbour() != null) {
            c5 = this.getSouthEastNeighbour().getCurrentState().compare(st.getState());
        }

        if (this.getSouthNeighbour() != null) {
            c6 = this.getSouthNeighbour().getCurrentState().compare(st.getState());
        }

        if (this.getSouthWestNeighbour() != null) {
            c7 = this.getSouthWestNeighbour().getCurrentState().compare(st.getState());
        }

        if (this.getWestNeighbour() != null) {
            c8 = this.getWestNeighbour().getCurrentState().compare(st.getState());
        }

        if (c1) {

            cont++;
        }
        if (c2) {

            cont++;
        }
        if (c3) {

            cont++;
        }
        if (c4) {

            cont++;
        }
        if (c5) {

            cont++;
        }
        if (c6) {

            cont++;
        }
        if (c7) {

            cont++;
        }
        if (c8) {

            cont++;
        }
        //System.out.println("cout:" + cont);
        //System.out.println("quanti:" + st.quantity);
        switch (st.getType()) {
            case -1:

                return cont < st.quantity;
            case 0:

                return cont == st.quantity;

            case 1:
                return cont > st.quantity;

        }

        return false;

    }

    /**
     *
     * @return
     */
    @Override
    public String getConfString() {
        String r = null;
        if (this.isRuleCell()) {

            r = currentState + " ";
            r = r.concat(nwSt + " ");
            r = r.concat(nSt + " ");
            r = r.concat(neSt + " ");
            r = r.concat(eSt + " ");
            r = r.concat(seSt + " ");
            r = r.concat(sSt + " ");
            r = r.concat(swSt + " ");
            r = r.concat(wSt + "");

        }
        return r;
    }

}
