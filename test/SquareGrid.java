
import java.util.ArrayList;
import processing.core.*;

//Specific type of grid
public class SquareGrid {

    float totalWidth, totalHeight;
    int rows, columns;
    float cellSize;
    int nCells;
    PApplet p;

    String[] states;

    public SquareGrid(PApplet p) {
        this.p = p;
    }

    public void setup(int rows, int columns, float cellSize, String[] states) {

        this.rows = rows;
        this.columns = columns;
        this.cellSize = cellSize;

        totalHeight = rows * cellSize;
        totalWidth = columns * cellSize;
        nCells = rows * columns;
        this.states = states;

        System.out.println(totalWidth + " " + totalHeight + " " + nCells);
    }

    public void init() {

    }

    public void update(String[] states) {
        this.states = states;

    }

    public void draw() {
        p.stroke(0);
        //p.fill(150);
        //p.size((int)totalWidth,(int)totalHeight);
        int cont = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (states[cont] == "0") {
                    p.fill(255);

                } else if (states[cont] == "1") {
                    p.fill(0);
                }
                p.rect(cellSize * i, cellSize * j, cellSize, cellSize);
                cont++;
            }

        }

    }

}
