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
        defaultCell = cellFac.fabricate(engine.getCellType(), -1, false);
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
        switch (engine.getBoundaryType()) {
            case 1:
                setNeighboursCircularBoundary();

                //case for other types of boundaries
                break;
            case 2:
                setNeighboursAbsorbingBoundary();
                break;
            default:
                throw new UnsupportedOperationException("Boundarie Not supported"); //To change body of generated methods, choose Tools | Templates.
        }

    }

    /**
     *
     */
    public void setNeighboursCircularBoundary() {
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
            //case where its only one element
            if (rows == 1 && columns == 1) {
                nw = i;
                n = i;
                ne = i;
                e = i;
                se = i;
                s = i;
                sw = i;
                w = i;

            }//case where 1 row many columns
            else if (rows == 1 && columns > 1) {
                //first
                if (i == 0) {
                    nw = columns - 1;
                    n = i;//
                    ne = i + 1;
                    e = i + 1;
                    se = i + 1;
                    s = i;//
                    sw = columns - 1;
                    w = columns - 1;
                } //middle
                else if (i > 0 && i < columns - 1) {
                    nw = i - 1;
                    n = i;
                    ne = i + 1;
                    e = i + 1;
                    se = i + 1;
                    s = i;
                    sw = i - 1;
                    w = i - 1;
                }//last
                else if (i == columns - 1) {
                    nw = i - 1;
                    n = i;
                    ne = 0;
                    e = 0;
                    se = 0;
                    s = i;
                    sw = i - 1;
                    w = i - 1;
                }

            } //case there are 1 column andmany rows
            else if (rows > 1 && columns == 1) {
                //first
                if (i == 0) {
                    nw = rows - 1;
                    n = rows - 1;//
                    ne = rows - 1;
                    e = i;
                    se = i + 1;
                    s = i + 1;//
                    sw = i + 1;
                    w = i;
                } //middle
                else if (i > 0 && i < rows - 1) {
                    nw = i - 1;
                    n = i - 1;//
                    ne = i - 1;
                    e = i;
                    se = i + 1;
                    s = i + 1;//
                    sw = i + 1;
                    w = i;
                }//last
                else if (i == rows - 1) {
                    nw = i - 1;
                    n = i - 1;//
                    ne = i - 1;
                    e = i;
                    se = 0;
                    s = 0;//
                    sw = 0;
                    w = i;
                }

            } //id on the middle
            else if (nOfEdges(i) == 0) {
                nw = i - columns - 1;
                n = i - columns;
                ne = i - columns + 1;
                e = i + 1;
                se = i + columns + 1;
                s = i + columns;
                sw = i + columns - 1;
                w = i - 1;

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
                    nw = w - columns;//                        
                    sw = w + columns;//

                }

                //case where is in one of the 4     
            } else if (nOfEdges(i) > 1) {
                //set
                if (isNorthWestCorner(i)) {
                    nw = i + (rows * columns) - 1;//
                    n = i + ((rows - 1) * columns);//
                    ne = n + 1;//  
                    w = i + (columns - 1);//
                    sw = w + columns;//

                    e = i + 1;
                    se = i + columns + 1;
                    s = i + columns;
                    //set
                } else if (isNorthEastCorner(i)) {
                    ne = (i - (columns - 1)) + (((rows * columns) - 1) - (columns - 1));//
                    n = i + ((rows - 1) * columns);//
                    nw = n - 1;               //                                
                    e = i - (columns - 1);//                        
                    se = e + columns;//

                    s = i + columns;
                    sw = i + columns - 1;
                    w = i - 1;

                } else if (isSouthEastCorner(i)) {
                    nw = i - columns - 1;
                    n = i - columns;
                    w = i - 1;

                    se = i - ((rows * columns) - 1);//
                    e = i - (columns - 1);//
                    ne = e - columns;//                                               
                    s = i - ((rows - 1) * columns);//                       
                    sw = s - 1;//

                } else if (isSouthWestCorner(i)) {

                    n = i - columns;
                    ne = i - columns + 1;
                    e = i + 1;
                    //jumps all the way to the end of the line and comes back everything   
                    sw = (i + (columns - 1)) - (((rows * columns) - 1) - (columns - 1));
                    s = i - ((rows - 1) * columns);//
                    se = s + 1;// 
                    //se=i;
                    w = i + (columns - 1);//
                    nw = w - columns;//

                }

            }

            c.setNorthWestNeighbour((SquareCell) cells.get(nw));
            c.setNorthNeighbour((SquareCell) cells.get(n));
            c.setNorthEastNeighbour((SquareCell) cells.get(ne));
            c.setEastNeighbour((SquareCell) cells.get(e));
            c.setSouthEastNeighbour((SquareCell) cells.get(se));
            c.setSouthNeighbour((SquareCell) cells.get(s));
            c.setSouthWestNeighbour((SquareCell) cells.get(sw));
            c.setWestNeighbour((SquareCell) cells.get(w));

        }

    }

    private void setNeighboursAbsorbingBoundary() {
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
            //case where its only one element
            if (rows == 1 && columns == 1) {
                nw = -1;
                n = -1;
                ne = -1;
                e = -1;
                se = -1;
                s = -1;
                sw = -1;
                w = -1;

            }//case where 1 row many columns
            else if (rows == 1 && columns > 1) {
                //first
                if (i == 0) {
                    nw = -1;
                    n = -1;//
                    ne = -1;
                    e = i + 1;
                    se = -1;
                    s = -1;//
                    sw = -1;
                    w = -1;
                } //middle
                else if (i > 0 && i < columns - 1) {
                    nw = -1;
                    n = -1;
                    ne = -1;
                    e = i + 1;
                    se = -1;
                    s = -1;
                    sw = -1;
                    w = i - 1;
                }//last
                else if (i == columns - 1) {
                    nw = -1;
                    n = -1;
                    ne = -1;
                    e = -1;
                    se = -1;
                    s = -1;
                    sw = -1;
                    w = i - 1;
                }

            } //case there are 1 column andmany rows
            else if (rows > 1 && columns == 1) {
                //first
                if (i == 0) {
                    nw = -1;
                    n = -1;//
                    ne = -1;
                    e = -1;
                    se = -1;
                    s = i + 1;//
                    sw = -1;
                    w = -1;
                } //middle
                else if (i > 0 && i < rows - 1) {
                    nw = -1;
                    n = i - 1;//
                    ne = -1;
                    e = -1;
                    se = -1;
                    s = i + 1;//
                    sw = -1;
                    w = -1;
                }//last
                else if (i == rows - 1) {
                    nw = -1;
                    n = i - 1;//
                    ne = -1;
                    e = -1;
                    se = -1;
                    s = -1;//
                    sw = -1;
                    w = -1;
                }

            } //id on the middle
            else if (nOfEdges(i) == 0) {
                nw = i - columns - 1;
                n = i - columns;
                ne = i - columns + 1;
                e = i + 1;
                se = i + columns + 1;
                s = i + columns;
                sw = i + columns - 1;
                w = i - 1;

                //case where is in edge but not corner    
            } else if (nOfEdges(i) == 1) {
                //set
                if (isOnNorthEdge(i)) {

                    n = -1;//
                    nw = -1;//
                    ne = -1;//

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
                    e = -1;//
                    ne = -1;//
                    se = -1;//

                } else if (isOnSouthEdge(i)) {
                    nw = i - columns - 1;
                    n = i - columns;
                    ne = i - columns + 1;
                    e = i + 1;
                    s = -1;//
                    se = -1;//
                    sw = -1;//
                    w = i - 1;
                    //set
                } else if (isOnWestEdge(i)) {

                    n = i - columns;
                    ne = i - columns + 1;
                    e = i + 1;
                    se = i + columns + 1;
                    s = i + columns;
                    w = -1;//
                    nw = -1;//                        
                    sw = -1;//

                }

                //case where is in one of the 4     
            } else if (nOfEdges(i) > 1) {
                //set
                if (isNorthWestCorner(i)) {
                    nw = -1;//
                    n = -1;//
                    ne = n + 1;//  
                    w = -1;//
                    sw = -1;//

                    e = i + 1;
                    se = i + columns + 1;
                    s = i + columns;
                    //set
                } else if (isNorthEastCorner(i)) {
                    ne = -1;//
                    n = -1;//
                    nw = -1;               //                                
                    e = -1;//                        
                    se = -1;//

                    s = i + columns;
                    sw = i + columns - 1;
                    w = i - 1;

                } else if (isSouthEastCorner(i)) {
                    nw = i - columns - 1;
                    n = i - columns;
                    w = i - 1;

                    se = -1;//
                    e = -1;//
                    ne = -1;//                                               
                    s = -1;//                       
                    sw = -1;//

                } else if (isSouthWestCorner(i)) {

                    n = i - columns;
                    ne = i - columns + 1;
                    e = i + 1;
                    //jumps all the way to the end of the line and comes back everything   
                    sw = -1;
                    s = -1;//
                    se = -1;// 
                    //se=i;
                    w = -1;//
                    nw = -1;//

                }

            }

            if (nw != -1) {
                c.setNorthWestNeighbour((SquareCell) cells.get(nw));
            } else {
                c.setNorthWestNeighbour((SquareCell) defaultCell);
            }

            if (n != -1) {
                c.setNorthNeighbour((SquareCell) cells.get(n));
            } else {
                c.setNorthNeighbour((SquareCell) defaultCell);
            }

            if (ne != -1) {
                c.setNorthEastNeighbour((SquareCell) cells.get(ne));
            } else {
                c.setNorthEastNeighbour((SquareCell) defaultCell);
            }

            if (e != -1) {
                c.setEastNeighbour((SquareCell) cells.get(e));
            } else {
                c.setEastNeighbour((SquareCell) defaultCell);
            }

            if (se != -1) {
                c.setSouthEastNeighbour((SquareCell) cells.get(se));
            } else {
                c.setSouthEastNeighbour((SquareCell) defaultCell);
            }

            if (s != -1) {
                c.setSouthNeighbour((SquareCell) cells.get(s));
            } else {
                c.setSouthNeighbour((SquareCell) defaultCell);
            }

            if (sw != -1) {
                c.setSouthWestNeighbour((SquareCell) cells.get(sw));
            } else {
                c.setSouthWestNeighbour((SquareCell) defaultCell);
            }

            if (w != -1) {
                c.setWestNeighbour((SquareCell) cells.get(w));
            } else {
                c.setWestNeighbour((SquareCell) defaultCell);
            }

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
        //throw new UnsupportedOperationException("Not Supported yed");
        ///*
        SquareCell c = (SquareCell) cells.get(id);

        //System.out.println(c.getNorthWestNeighbour().idString());
        System.out.println(c.getNorthWestNeighbour().idString() + " " + c.getNorthNeighbour().idString() + " " + c.getNorthEastNeighbour().idString());
        System.out.println("");
        System.out.println(c.getWestNeighbour().idString() + " " + c.idString() + " " + c.getEastNeighbour().idString());
        System.out.println("");
        System.out.println(c.getSouthWestNeighbour().idString() + " " + c.getSouthNeighbour().idString() + " " + c.getSouthEastNeighbour().idString());
        //*/
    }

}
