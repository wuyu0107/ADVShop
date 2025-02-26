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

### Module 2
Deployment: https://oral-raynell-wuyu0107-16c10ca6.koyeb.app
<details>
<summary>Reflection</summary>

#### List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
Empty Test Methods
   - SonarCloud identified that test methods ```contextLoads()``` and ```setup()``` were empty, suggesting that the methods need to be either implemented or commented with the purpose
   - I fixed this code quality issue by adding a nested comment explaining why the methods are empty. 

Variable Name Hiding a Field
  - The local variable ```product``` in the test was hiding a field declared in the same class. So SonarCloud suggested renaming the variable again to avoid confusion and potential errors
    - I fixed this issue by renaming the local variable from ```product``` to ```newProduct```, so that there will be no more confusion with the variable names

#### Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!
I think the current implementation meets some part of the definition of continuous integration and continuous deployment (CI/CD). The workflows that I have implemented in this exercise include automated builds, security checks via Scorecard, and code analysis via SonarCloud. This indicates that the code is integrated and verified every push to the branches. However, since there is no automated testing of the code, it misses complete definition of continuous integration. For continuous deployment, this was done by integrating Koyeb to my project. It would allow for automated deployments to cloud environment after successful builds and checks. This meets the definition of continuous deployment as changes would be automatically deployed to the live environment. 

</details>

### Module 3
<details>
<summary>Reflection</summary>

#### Explain what principles you apply to your project!

Principles that were applied to my project are:

```Single Responsibility Principle (SRP)```: It means that each class has a single responsibility. For example, controllers are broken into two parts, car and product. The ```CarController``` will handle only for the cars, while ```ProductController``` will only handle for the products. 

```Open/Closed Principle (OCP)```: The principle allows implementations to be extended without existing classes. These are illustrated in the ```CarServiceImpl``` and ```ProductServiceImpl``` where if new features have to be added, new methods/functions can be created rather than changing the existing code. 

```Interface Segregation Principle (ISP)```: The principle focuses on making multiple smaller interfaces, rather than having big ones. This principle is shown in ```CarService``` and ```ProductService```, allowing only the required behaviors to be implemented. 

#### Explain the advantages of applying SOLID principles to your project with examples.

1. Improved maintainability of the code: since each class has single responsibility, making changes in one part of the code won't affect the other areas. For example, if there is a logic change in ```CarService```, the repository of it will not get affected by the change.
2. Easier implementation of new features: OCP ensures new features to be implemented without making changes to existing classes. If new type of product is added, new service implementation can be done without changing ```ProductService```.
3. Reusable components of code: the interfaces and abstraction allows parts of code to be reused. For example, the ```CarService``` interface allows multiple implementations to be made, without affecting other existing code. 

#### Explain the disadvantages of not applying SOLID principles to your project with examples.

1. Difficulty in maintaining code: if single class has multiple responsibilities, making a change in one part may affect the other part - possibly making it to not work. If ```CarService``` handled database operations, a change in business logic may affect the database queries too. 
2. Difficult implementation of new features: Without OCP, making modifications or implementations of features require modification of existing classes, which may result in potential bugs. 
3. Code duplication: having large interfaces may allow unrelated classes to be implemented with unnecessary methods. 

</details>

