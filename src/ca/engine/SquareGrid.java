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
public class SquareGrid extends Grid {

    /**
     *
     * @param engine
     */
    public SquareGrid(CAEngine engine) {
        super(engine);
        this.type = 1;

    }

    /**
     *
     * @param rows
     * @param columns
     */
    @Override
    public void setupGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.nOfCells = rows * columns;
    }

    /**
     *
     */
    @Override
    //creates all the cells
    public void initGrid() {
        for (int i = 0; i < nOfCells; i++) {
            cells.add(cellFac.fabricate(engine.getCellType(), i, false));

        }

    }

    /**
     *
     */
    @Override
    public void setNeighbours() {

        //Case of circular 
        if (engine.getBoundariesType() == 1) {

            setNeighboursNull();
            SquareCell c;

            int nw, n, ne, e, se, s, sw, w;

            for (int i = 0; i < cells.size(); i++) {
                c = (SquareCell) cells.get(i);
                nw = i - columns - 1;
                n = i - columns;
                ne = i - columns + 1;
                e = i + 1;
                se = i + columns + 1;
                s = i + columns;
                sw = i + columns - 1;
                w = i - 1;

                //id on the middle
                if (nOfEdges(i) == 0) {
                    nw = i - columns - 1;
                    n = i - columns;
                    ne = i - columns + 1;
                    e = i + 1;
                    se = i + columns + 1;
                    s = i + columns;
                    sw = i + columns - 1;
                    w = i - 1;

                    c.setNorthWestNeighbour((SquareCell) cells.get(nw));
                    c.setNorthNeighbour((SquareCell) cells.get(n));
                    c.setNorthEastNeighbour((SquareCell) cells.get(ne));
                    c.setEastNeighbour((SquareCell) cells.get(e));
                    c.setSouthEastNeighbour((SquareCell) cells.get(se));
                    c.setSouthNeighbour((SquareCell) cells.get(s));
                    c.setSouthWestNeighbour((SquareCell) cells.get(sw));
                    c.setWestNeighbour((SquareCell) cells.get(w));

                    //case where is in edge but not corner    
                } else if (nOfEdges(i) == 1) {
                    //set
                    if (isOnNorthEdge(i)) {

                        n = i + ((rows - 1) * columns);//
                        nw = n - 1;//
                        ne = n + 1;//
                        
                        e = i + 1;
                        se = i + columns + 1;
                        s = i + columns;
                        sw = i + columns - 1;
                        w = i - 1;

                    } else if (isOnEastEdge(i)) {
                        nw = i - columns - 1;
                        n = i - columns;
                        s = i + columns;
                        sw = i + columns - 1;
                        w = i - 1;
                        e = i - (columns - 1);//
                        ne = e - columns;//
                        //ne=i;
                        se = e + columns;//

                    } else if (isOnSouthEdge(i)) {
                        nw = i - columns - 1;
                        n = i - columns;
                        ne = i - columns + 1;
                        e = i + 1;
                        s = i - ((rows - 1) * columns);//
                        se = s + 1;//
                        sw = s - 1;//
                        w = i - 1;
                        //set
                    } else if (isOnWestEdge(i)) {

                        n = i - columns;
                        ne = i - columns + 1;
                        e = i + 1;
                        se = i + columns + 1;
                        s = i + columns;
                        w = i + (columns - 1);//
                        //nw = i -1;// 
                        nw = w - columns;//                        
                        //sw = i + ((2*columns)-1);//
                        sw = w + columns;//

                    }

                    c.setNorthWestNeighbour((SquareCell) cells.get(nw));
                    c.setNorthNeighbour((SquareCell) cells.get(n));
                    c.setNorthEastNeighbour((SquareCell) cells.get(ne));
                    c.setEastNeighbour((SquareCell) cells.get(e));
                    c.setSouthEastNeighbour((SquareCell) cells.get(se));
                    c.setSouthNeighbour((SquareCell) cells.get(s));
                    c.setSouthWestNeighbour((SquareCell) cells.get(sw));
                    c.setWestNeighbour((SquareCell) cells.get(w));
                    
                    //case where is in one of the 4     
                } else if (nOfEdges(i) > 1) {

                    if (isNorthWestCorner(i)) {
                        nw = i ;
                        n = i;
                        ne = i ;                        
                        sw = i;
                        w = i;
                        
                        e = i + 1;
                        se = i + columns + 1;
                        s = i + columns;

                    } else if (isNorthEastCorner(i)) {
                        nw = i ;
                        n = i ;
                        ne = i ;
                        e = i ;
                        se = i ;
                        
                        s = i + columns;
                        sw = i + columns - 1;
                        w = i - 1;

                    } else if (isSouthEastCorner(i)) {
                        nw = i - columns - 1;
                        n = i - columns;
                        w = i - 1;
                        
                        ne = i;
                        e = i ;
                        se = i;
                        s = i ;
                        sw = i;
                        

                    } else if (isSouthWestCorner(i)) {
                        
                        n = i - columns;
                        ne = i - columns + 1;
                        e = i + 1;
                        
                        se = i;
                        s = i ;
                        sw = i;
                        w = i ;
                        nw = i;

                    }

                }

            }
            //case for other types of boundaries
        } else {

            throw new UnsupportedOperationException("Boundarie Not supported"); //To change body of generated methods, choose Tools | Templates.

        }

    }

    /**
     *
     * @param id
     * @return
     */
    public int nOfEdges(int id) {
        int r;
        r = 0;
        //up border
        if (id <= columns - 1) {
            r++;
        }
        //down border
        if (id >= cells.size() - columns) {
            r++;
        }
        //left border
        if (id % columns == 0) {
            r++;
        }
        //roght border
        if ((id + 1) % columns == 0) {
            r++;
        }

        return r;
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean isOnNorthEdge(int id) {

        return id <= columns - 1;
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean isOnEastEdge(int id) {

        return (id + 1) % columns == 0;
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean isOnSouthEdge(int id) {

        return id >= cells.size() - columns;
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean isOnWestEdge(int id) {

        return id % columns == 0;
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean isNorthWestCorner(int id) {

        return isOnNorthEdge(id) && isOnWestEdge(id);
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean isNorthEastCorner(int id) {

        return isOnNorthEdge(id) && isOnEastEdge(id);
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean isSouthEastCorner(int id) {

        return isOnSouthEdge(id) && isOnEastEdge(id);
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean isSouthWestCorner(int id) {

        return isOnSouthEdge(id) && isOnWestEdge(id);
    }

    /**
     *
     */
    public void setNeighboursNull() {
        SquareCell c;

        for (int i = 0; i < cells.size(); i++) {
            c = (SquareCell) cells.get(i);
            c.setNorthWestNeighbour(null);
            c.setNorthNeighbour(null);
            c.setNorthEastNeighbour(null);
            c.setEastNeighbour(null);
            c.setSouthEastNeighbour(null);
            c.setSouthNeighbour(null);
            c.setSouthWestNeighbour(null);
            c.setWestNeighbour(null);

        }
    }

    /**
     *
     * @param id
     */
    @Override
    public void printNeighbours(int id) {
        SquareCell c = (SquareCell) cells.get(id);

        //System.out.println(c.getNorthWestNeighbour().idString());
        System.out.println(c.getNorthWestNeighbour().idString() + " " + c.getNorthNeighbour().idString() + " " + c.getNorthEastNeighbour().idString());
        System.out.println("");
        System.out.println(c.getWestNeighbour().idString() + " " + c.idString() + " " + c.getEastNeighbour().idString());
        System.out.println("");
        System.out.println(c.getSouthWestNeighbour().idString() + " " + c.getSouthNeighbour().idString() + " " + c.getSouthEastNeighbour().idString());

    }

}
