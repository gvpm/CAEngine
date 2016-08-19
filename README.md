# CAEngine

The ConsoleTester is set by default, you can run it using:
java -jar "CAEngine.jar" 

##Usage Example

###Initial Setups

CAEngine e = new CAEngine();

####e.setup(1, 1, 1, 1, rows, columns); 
(gridType, cellType, stateType, boundariesType, nOfRows, nOfColumns)

####e.init();

###State Creation

####stateZero = e.createState(1, 0);
(stateType, value of the state)

####stateOne = e.createState(1, 1);
When you add a new state to the machine you can store its reference to use in rules)

###Image Rules
To create a Image rule you need a rule cell, that is just a normal cell but it only stores the states of the neighbours.

####SquareCell rule1Cell = (SquareCell) e.createRuleCell(1, 1);//(cellType, stateType)

You give the state of the cell and its neighbours to be further compared with all the cells on the iteration

####rule1Cell.initRuleCell(stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne, stateOne);

####e.createImageRule(rule1Cell, stateZero);

###Quantity Rules

The Quantity Rule is simpler, you first create the rule stating the current state of the cell, and the next state  

####Rule underPop = e.createQuantityRule(stateOne, stateZero);
(In this case Rule triggers if you have stateone and state tuple  is valid)
####Rule overPop = e.createQuantityRule(stateOne, stateZero);
####Rule reproduction = e.createQuantityRule(stateZero, stateOne);

Then you give the condition, that is a state tuple, containing the state and a number of occurrences of the state

That is the example of the game of life

The last variable allows you to create any type of quantity rule
- -1 less than
- 0 equal to
- +1 greater than

####underPop.addStateTuple(e.createStateTuple(stateOne, 2, -1));
(In this case statetuple is valid if you have less than 2 neighbours in state one)

####overPop.addStateTuple(e.createStateTuple(stateOne, 3, 1));

####reproduction.addStateTuple(e.createStateTuple(stateOne, 3, 0));


###Quantity Rules

####e.iterate();
is used to do an iteration

####e.stateMatrix();
returns a string that prints the current grid on console

####e.changeState(73, stateOne);
you can change the state of a cell using the ID

