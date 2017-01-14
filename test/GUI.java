
import ca.test.*;
import ca.engine.CAEngine;
import ca.engine.Rule;
import ca.engine.State;
import ca.engine.FileLoader;
import controlP5.Button;
import controlP5.CheckBox;
import controlP5.ControlP5;
import controlP5.RadioButton;
import controlP5.Textfield;
import java.io.IOException;


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;
import processing.core.PFont;

public class GUI extends PApplet {

    SquareGrid grid;
    CAEngine e = new CAEngine();
    int rows;
    int columns;

    State stateZero;
    State stateOne;
    ControlP5 cp5;
    CheckBox checkbox;
    RadioButton radioButtonBoundary;

    int leftMargin = 610;

    public static void main(String[] args) {

        //This code together with the class windoes make the code run 2 applets  
        //PApplet a = new PApplet();      
        //PApplet b = new PApplet();        
        //a.main(new String[]{"SquareGrid"});
        //b.main(new String[]{"Window2"});
        PApplet.main(new String[]{"GUI"});

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

        textSize(11);
        text("Boundary", 600, 80);

        radioButtonBoundary = cp5.addRadioButton("radioButtonBoundaryType")
                .setPosition(600, 90)
                .setColorForeground(color(120))
                .setColorActive(color(20))
                .setColorLabel(color(255))
                .setColorBackground(color(255))
                .setSize(15, 15)
                .setItemsPerRow(9)
                .setSpacingColumn(45)
                .setSpacingRow(20)
                .addItem("Circular", 1)
                .addItem("Fix", 2);
        Button b6 = cp5.addButton("ResetBoundaryType")
                .setValue(0)
                .setPosition(600, 110)
                .setSize(200, 19);

        textSize(11);
        text("Number of alive cells around to keep alive", 600, 160);
        textSize(11);
        text("Number of alive cells around to get life", 600, 200);
        checkbox = cp5.addCheckBox("checkBoxSurvive")
                .setPosition(600, 170)
                .setColorForeground(color(120))
                .setColorActive(color(20))
                .setColorLabel(color(255))
                .setColorBackground(color(255))
                .setSize(15, 15)
                .setItemsPerRow(9)
                .setSpacingColumn(17)
                .setSpacingRow(25)
                .addItem("S0", 1)
                .addItem("S1", 1)
                .addItem("S2", 1)
                .addItem("S3", 1)
                .addItem("S4", 1)
                .addItem("S5", 1)
                .addItem("S6", 1)
                .addItem("S7", 1)
                .addItem("S8", 1)
                .addItem("R0", 1)
                .addItem("R1", 1)
                .addItem("R2", 1)
                .addItem("R3", 1)
                .addItem("R4", 1)
                .addItem("R5", 1)
                .addItem("R6", 1)
                .addItem("R7", 1)
                .addItem("R8", 1);

        Button b4 = cp5.addButton("CheckAll")
                .setValue(0)
                .setPosition(600, 240)
                .setSize(200, 19);
        Button b5 = cp5.addButton("UncheckAll")
                .setValue(0)
                .setPosition(600, 260)
                .setSize(200, 19);

        Button b3 = cp5.addButton("SetRules")
                .setValue(0)
                .setPosition(600, 280)
                .setSize(200, 19);

        /*
        cp5.addTextfield("FILENAME")
                .setPosition(600, 320)
                .setSize(200, 30)
                .setFont(createFont("arial", 20))
                .setAutoClear(false);

        Button b7 = cp5.addButton("LoadFile")
                .setValue(0)
                .setPosition(600, 370)
                .setSize(200, 19);
        Button b8 = cp5.addButton("SaveFile")
                .setValue(0)
                .setPosition(600, 390)
                .setSize(200, 19);

        */
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
        //Conway's Life
        /*
        Rule underPop = e.createQuantityRule(stateOne, stateZero);
        Rule overPop = e.createQuantityRule(stateOne, stateZero);
        Rule reproduction = e.createQuantityRule(stateZero, stateOne);

        underPop.addStateTuple(e.createStateTuple(stateOne, 2, -1));
        overPop.addStateTuple(e.createStateTuple(stateOne, 3, 1));
        reproduction.addStateTuple(e.createStateTuple(stateOne, 3, 0));
         */
 /*Maze
        Rule underPop = e.createQuantityRule(stateOne, stateZero);
        Rule overPop = e.createQuantityRule(stateOne, stateZero);
        Rule reproduction = e.createQuantityRule(stateZero, stateOne);

        underPop.addStateTuple(e.createStateTuple(stateOne, 1, -1));
        overPop.addStateTuple(e.createStateTuple(stateOne, 5, 1));
        reproduction.addStateTuple(e.createStateTuple(stateOne, 3, 0));
         */
        Rule reproduction1 = e.createQuantityRule(stateZero, stateOne);
        reproduction1.addStateTuple(e.createStateTuple(stateOne, 2, 0));

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

    public void CheckAll(int value) {
        checkbox.activateAll();

    }

    public void UncheckAll(int value) {
        checkbox.deactivateAll();

    }

    public void ResetBoundaryType(int value) {

        e.reSetBoundaryType((int) radioButtonBoundary.getValue());

    }


    public void SetRules(int value) {
        e.clearRules();
        boolean b;
        for (int i = 0; i < 9; i++) {
            b = checkbox.getState(i);
            if (!b) {
                Rule death = e.createQuantityRule(stateOne, stateZero);
                death.addStateTuple(e.createStateTuple(stateOne, i, 0));

            }

        }
        int j = 0;
        for (int i = 9; i < 18; i++) {
            b = checkbox.getState(i);
            if (b) {
                Rule reproduction = e.createQuantityRule(stateZero, stateOne);
                reproduction.addStateTuple(e.createStateTuple(stateOne, j, 0));

            }

            j++;
        }

    }

    public void Rows(float i) {
        rows = (int) i;

    }

    public void Columns(float i) {
        columns = (int) i;

    }

    /*
    public void LoadFile(int value) throws IOException {
        
        FileLoader loader = new FileLoader(cp5.get(Textfield.class, "FILENAME").getText());

        e  = loader.load();

    }
    
    public void SaveFile(int value) throws IOException {

        e.save(cp5.get(Textfield.class, "FILENAME").getText());

    }
*/

   

}
