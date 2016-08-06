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
            //in on the middle
            if (nOfEdges(i) == 0) {
                

                c.setNorthWestNeighbour((SquareCell)cells.get(nw));
                c.setNorthNeighbour((SquareCell)cells.get(n));
                c.setNorthEastNeighbour((SquareCell)cells.get(ne));
                c.setEastNeighbour((SquareCell)cells.get(e));
                c.setSouthEastNeighbour((SquareCell)cells.get(se));
                c.setSouthNeighbour((SquareCell)cells.get(s));
                c.setSouthWestNeighbour((SquareCell)cells.get(sw));
                c.setWestNeighbour((SquareCell)cells.get(w));

            } 
            else {

            }

        }

       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        SquareCell c = (SquareCell)cells.get(id);
        
        //System.out.println(c.getNorthWestNeighbour().idString());

        
        System.out.println(c.getNorthWestNeighbour().idString()+" "+c.getNorthNeighbour().idString()+" "+c.getNorthEastNeighbour().idString());
        System.out.println("");
        System.out.println(c.getWestNeighbour().idString()+" "+c.idString()+" "+c.getEastNeighbour().idString());
        System.out.println("");
        System.out.println(c.getSouthWestNeighbour().idString()+" "+c.getSouthNeighbour().idString()+" "+c.getSouthEastNeighbour().idString());


        
    }

}
