Problem Statement
-------------------------------------------------------------------
Json Expression Evaluator

1. Write a full expression parser and evaluator, that takes in an Expression and a Json
2. Expression will contain:
      1. Variable (which will start with $), could also be nested (dot separated)
      2. Constant:
            1) String within quotes
            2) Boolean -  true or false
            3) Decimal 
            4) Number
      3. Logical Operator:
            1) AND
            2) OR
            3) NOT
            4) (  )   (Brackets)
     4. Comparison Operators
            1) ==
            2) EXISTS
3. Evaluation will be done on a json string input. Use variable values from this json, to evaluate it against the json.  
4. Returns true if expression is true on evaluation against a  json

Sample Examples
------------------------------------------------
1.
      Expression: "$color == 'red'" 

      Json: {"color":"red","size":10,"cost":100.0,"mattress":{"name":"king"},"big":true,"legs":[{"length":4}]}

      Output: true
      
2. 
      Exp: "$mattress.name == 'king' AND $cost == 100.0"
      
      Json: {"color":"red","size":10,"cost":100.0,"mattress":{"name":"king"},"big":true,"legs":[{"length":4}]}
      
      Output: true
        
3.
      Exp: "NOT EXISTS $color"
      
      Json: {"color":"red","size":10,"cost":100.0,"mattress":{"name":"king"},"big":true,"legs":[{"length":4}]} 
      
      output: false
        
4.
      Exp: "( $cost == 100.0 AND ( $mattress.big == false ) ) OR $size == 100"   
      
      Json: {"color":"red","size":10,"cost":100.0,"mattress":{"name":"king"},"big":true,"legs":[{"length":4}]} 
      
      Output: false

Modules
-------------------------------------------------------------------
1) InputData.java: This module reads the input & returns the data

2) Init.java: This module is the entry point of the application. Application starts its execution from here.
It reads the input data and give it to Parser to process

3) Parser.java: This module will take the input & process it.

4) JSONParser.java: This module will take the string representation of json &
    converts it into json object.

5) JSONObject.java: This module will take the json object created by JSONParser.java &
    json specific operations have been defined here.

6) ExpressionParser.java: This module will take the string representation of expression & split it into tokens

7) Condition.java: This module takes a condition in string format & converts it into a condition object,
    which will be used in ExpressionEvaluator.java

8) ExpressionEvaluator.java: This module will take care of the evaluation of the expression based on the json object.

-------------------------------------------------------------------
Sub-modules
-------------------------------------------------------------------
1) Stack.java: This sub-module will help in evaluation of expression condition.
Example: to get the topmost token etc
2) JSONParserHelper.java: This sub-module contains helper functions
-------------------------------------------------------------------
Assumptions
-------------------------------------------------------------------
1) I have assumed the given input is valid. (Both expression & json).
2) I have assumed the input format is as same as shown below.
     Valid expressions formats
           1. "$color == 'red'"
           2. "$mattress.name == 'king' AND $cost == 100.0"
           3. "NOT EXISTS $color"
           4. "( $cost == 100.0 AND ( $mattress.big == false ) ) OR $size == 100" etc

          Valid JSON Strings formats
          1. {"color":"red","size":10,"cost":100.0,"mattress":{"name":"king"},"big":true,"legs":[{"length":4}]}
3) If the expression contains any keys whose value is Array, then I am considering it as null.
    Example:

    expression: "$legs.length == 4"
    Json: {"color":"red","size":10,"cost":100.0,"mattress":{"name":"king"},"big":true,"legs":[{"length":4}]}

    Here in the json 'legs' is a key , whose 'value' is array. When one says 'legs.length', we don't know which element in the array to check.
    So these kind of expressions will be yield to "false" result.

-------------------------------------------------------------------
Cons
-------------------------------------------------------------------
1) Circuit break pattern is not implemented.
2) Restrictions in the inputs.
3) No test cases

-------------------------------------------------------------------
How To Execute?
-------------------------------------------------------------------
1) Install java & set up the env variables
2) Import the project into IntelliJ or Eclipse IDE and start executing from Init.java

-------------------------------------------------------------------
Read Author
-------------------------------------------------------------------
Name: Sachin Kalsi

Ph: +91 9945746470

email: sachinkalsi15@gmail.com

website: https://sachinkalsi.github.io/

Github: https://github.com/sachinkalsi

-------------------------------------------------------------------
NOTE:
1) PLEASE LET ME KNOW AT ANY TIME IF YOU FIND ANY DIFFICULTY/TROUBLE IN EXECUTING THE APPLICATION.
