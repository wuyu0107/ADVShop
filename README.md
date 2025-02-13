## Min Kim - 2306199743

### Module 1
<details>
<summary>Reflection 1</summary>

#### You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code. 

I focused on coding standards that emphasize readability and maintainability in this exercise. I ensured that each function does only one thing, and operates as intended. The code follows a consistent formattting, such as proper indentation and keeping lines short as possible to maintain clarity and focus. I also used descriptive names for variables and functions like ```findProductByID``` which conveys its purpose clearly, retrieving a product by its product ID. 
Additional function names used in this exercise are ```edit``` and ```delete``` and these functions are also predictable by their names. 

Areas I would like to improve is adding comments to improve the readability as the current code lacks the documentation (ex. why the function works like this and that) and have better error handling so that the program can handle unexpected scenarios when running.

</details>

<details>
  <summary>Reflection 2</summary>

  #### After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors? 

  Seeing the results of the written unit tests gave me confidence that the code works as expected. But, since there is no answer to which or what kind of unit test should be written, there still might be parts that are not working properly. I think at least one unit test per method is required so that it checks that the function works properly for its purpose. To make sure that our unit tests are enough to verify, it's important to follow the principle of testing both positive and negative scenarios. Code coverage of 100% implies that all code is executed, but this doesn't necessarily mean that the code has no bugs or errors. 
  
  #### Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner! 

  There would be issues with the cleanliness of the code since there would be repetition of instance variables and command methods from the ```CreateProductFunctionTest.java``` file if we follow the same setup procedures. This will violate the DRY (Don't Repeat Youself) rule of clean coding principles. Instead of repeating the same setup process and instance variables, we can create a base test class that contains the shared setup procedures and instance variables, then extend all functional test classes from this base class. 

</details>
