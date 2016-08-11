
import ca.engine.CAEngine;
import ca.engine.SquareCell;
import ca.engine.State;
import processing.core.PApplet;


public class Main extends PApplet {
    
    SquareGrid  grid;
    CAEngine e = new CAEngine();
    
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
        size(800,800);
    }

    @Override
    public void setup() {
        
        e.setup(1, 1, 1, 1, 10, 10);
        

        State stateOne = e.createState(1, 1);
        State stateZero = e.createState(1, 0);
        e.init();

        SquareCell rule1Cell = (SquareCell) e.createRuleCell(1, 1);
        rule1Cell.initRuleCell(stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne);

        e.createImageRule(rule1Cell, stateZero);
        
        grid=new SquareGrid(this);
        grid.setup(10, 10, 50,e.stateVector());
         
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
      
      
}
