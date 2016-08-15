
import ca.engine.CAEngine;
import ca.engine.Rule;
import ca.engine.SquareCell;
import ca.engine.State;
import java.util.Random;
import processing.core.PApplet;

public class Main extends PApplet {

    SquareGrid grid;
    CAEngine e = new CAEngine();
    int rows;
    int columns;

    State stateZero;
    State stateOne;

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
        size(800, 800);
        rows = 20;
        columns = 20;

    }

    @Override
    public void setup() {

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

        Random gen = new Random();

        for (int i = 0; i < 100; i++) {
            int randID = gen.nextInt(rows * columns);
            e.changeState(randID, stateOne);

        }

        grid = new SquareGrid(this);
        grid.setup(rows, columns, e);

        //grid = new SquareGrid(this);
        //grid.setup(10, 10, 10);
    }

    public void update() {

        grid.update(e.stateVector());

    }

    @Override
    public void draw() {
        stroke(255);
        grid.draw();

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
    }

}
