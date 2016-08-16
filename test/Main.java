
import ca.engine.CAEngine;
import ca.engine.Rule;
import ca.engine.State;
import controlP5.Button;
import controlP5.ControlP5;
import controlP5.Slider;

import java.util.Random;
import processing.core.PApplet;

public class Main extends PApplet {

    SquareGrid grid;
    CAEngine e = new CAEngine();
    int rows;
    int columns;

    State stateZero;
    State stateOne;
    ControlP5 cp5;

    public static void main(String[] args) {

        //This code together with the class windoes make the code run 2 applets  
        //PApplet a = new PApplet();      
        //PApplet b = new PApplet();        
        //a.main(new String[]{"SquareGrid"});
        //b.main(new String[]{"Window2"});
        PApplet.main(new String[]{"Main"});

    }

    @Override
    public void settings() {
        size(900, 600);
        rows = 100;
        columns = 100;

    }

    @Override
    public void setup() {

        cp5 = new ControlP5(this);
        Button b0 = cp5.addButton("Iterate")
                .setValue(0)
                .setPosition(600, 0)
                .setSize(200, 19);
        Button b1 = cp5.addButton("Randomize")
                .setValue(0)
                .setPosition(600, 20)
                .setSize(200, 19);
        Button b2 = cp5.addButton("Clear")
                .setValue(0)
                .setPosition(600, 40)
                .setSize(200, 19);
        /*        
        
        
        Slider sRows = cp5.addSlider("Rows")
                .setPosition(620, 10)
                .setRange(0, 200);
                
        
        Slider sColumns = cp5.addSlider("Columns")
                .setPosition(620, 30)
                .setRange(0, 200);
        
        Button gen = cp5.addButton("Generate")
                .setValue(0)
                .setPosition(600, 50)
                .setSize(200, 19);
         */

        //http://www.sojamo.de/libraries/controlP5/#examples
        e.setup(1, 1, 1, 1, rows, columns);
        stateZero = e.createState(1, 0);
        stateOne = e.createState(1, 1);

        e.init();

        //SquareCell rule1Cell = (SquareCell) e.createRuleCell(1, 1);
        //rule1Cell.initRuleCell(stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne);
        //e.createImageRule(rule1Cell, stateZero);
        ///TEST QUAANTITY
        Rule underPop = e.createQuantityRule(stateOne, stateZero);
        Rule overPop = e.createQuantityRule(stateOne, stateZero);
        Rule reproduction = e.createQuantityRule(stateZero, stateOne);

        underPop.addStateTuple(e.createStateTuple(stateOne, 2, -1));
        overPop.addStateTuple(e.createStateTuple(stateOne, 3, 1));
        reproduction.addStateTuple(e.createStateTuple(stateOne, 3, 0));

        grid = new SquareGrid(this);
        grid.setup(rows, columns, e);

    }

    public void update() {
        if (grid != null) {
            grid.update(e.stateVector());
        }

    }

    @Override
    public void draw() {
        stroke(255);
        if (grid != null) {
            grid.draw();
        }

    }

    @Override
    public void mouseClicked() {
        if (mouseX < 600 && mouseY < 600) {
            int row = grid.getRow(mouseY);
            int column = grid.getColumn(mouseX);
            System.out.println("row: " + row + " columns" + column);
            int id = (row * rows) + column;
            e.changeState(id, stateOne);
        }

    }

    public void keyPressed() {

        if (key == 'i') {

            e.iterate();

        }
        if (key == 'r') {

            randomInitials();

        }
        if (key == 'c') {

            clearGrid();

        }

    }

    public void randomInitials() {
        Random gen = new Random();

        for (int i = 0; i < 100; i++) {
            int randID = gen.nextInt(rows * columns);
            e.changeState(randID, stateOne);

        }

    }

    public void clearGrid() {
        for (int i = 0; i < rows * columns; i++) {
            e.changeState(i, stateZero);

        }
    }

    public void Iterate(int value) {
        e.iterate();

    }

    public void Randomize(int value) {
        randomInitials();

    }

    public void Clear(int value) {
        clearGrid();

    }

    public void Generate(int value) {

    }

    public void Rows(float i) {
        rows = (int) i;

    }

    public void Columns(float i) {
        columns = (int) i;

    }

}
