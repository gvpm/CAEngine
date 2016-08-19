


import ca.engine.CAEngine;
import java.util.ArrayList;
import processing.core.*;

//Specific type of grid
public class SquareGrid {

    float totalWidth, totalHeight;
    int rows, columns;
    float cellSize;
    int nCells;
    PApplet p;

    CAEngine e;

    String[] states;

    public SquareGrid(PApplet p) {
        this.p = p;
    }

    public void setup(int rows, int columns, CAEngine e) {
        /*
        this.rows = rows;
        this.columns = columns;
        this.cellSize = cellSize;

        totalHeight = rows * cellSize;
        totalWidth = columns * cellSize;
        nCells = rows * columns;
        this.e=e;
         */
        totalHeight = 600;
        totalWidth = 600;
        this.rows = rows;
        this.columns = columns;

        this.cellSize = totalWidth / columns;

        nCells = rows * columns;
        this.e = e;

        //System.out.println(totalWidth + " " + totalHeight + " " + nCells);
    }

    public void init() {

    }

    public void update(String[] states) {
        this.states = states;

    }

    public void draw() {
        p.stroke(150);
        //p.fill(150);
        //p.size((int)totalWidth,(int)totalHeight);
        int cont = 0;
        String zero = "0";
        String one = "1";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //System.out.println(e.getStateString(cont));
                if (e.getStateString(cont).compareTo(zero) == 0) {
                    p.fill(255);

                } else if (e.getStateString(cont).compareTo(one) == 0) {

                    p.fill(0);

                }
                p.rect(cellSize * j, cellSize * i, cellSize, cellSize);

                cont++;
            }

        }

    }

    public int getColumn(int x) {

        return (int) (x / cellSize);
    }

    public int getRow(int y) {

        return (int) (y / cellSize);
    }

}
