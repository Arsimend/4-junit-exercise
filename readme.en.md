# Unit testing with JUnit

![Unit testing: a magnifying glass finds a bug in the code, and the test results are shown as passed and failed](img/chapters/testing.svg)

This repository contains Java exercises for practicing unit testing with [JUnit](https://junit.org). Unlike in the earlier exercises, there are no ready-made tests: you write the tests yourself. The Java classes to be tested are ready in the template.

## Starting the exercise

First fork this repository into your own GitHub repository with GitHub's **Fork** button, and only then clone your fork to your computer:

```sh
git clone https://github.com/<your-github-username>/4-junit-exercise.git
```

Open the cloned project in your code editor.

## Testing

You can run your tests with your code editor's testing tool ([VS Code](https://code.visualstudio.com/docs/java/java-testing), [Eclipse](https://www.vogella.com/tutorials/JUnitEclipse/article.html)) or with [Gradle](https://docs.gradle.org/current/userguide/java_testing.html), for example:

```
./gradlew test --tests PriceFormatterTest       # unix
.\gradlew.bat test --tests PriceFormatterTest   # windows
```

Add `--info` to the end of the command for more details about failing tests. Do not change the names, parameters or return types of the methods under test. You get the original version of a file back with `git restore <path to file>` or with the **Palauta lähtökoodi** (restore starter code) function of the course system.

## Submitting and points

Each part of the exercise has its own page in the course system (https://jtjuslin.kapsi.fi/java), and its code fields contain the part's starter code. Copy your solution into the code fields or write it there directly. **Testaa ilman palautusta** (test without submitting) runs the part's tests without saving points, and **Lähetä** (submit) submits your solution. Each test class is accepted only if all of its tests pass, and the points are divided according to the accepted test classes.

**Points in two stages**

1. When your solution passes in the course system, you get **half of the points** for the part.
2. Finally, push everything to your public GitHub repository. Then press the **Lähetä tarkistettavaksi** (submit for checking) button on the part's page. When the tests pass, you get **full points** for the part. The repository may remain public.

There is no grading YAML workflow (GitHub Actions) on GitHub, and `git push` does not start any grading. When you press **Lähetä tarkistettavaksi**, the course system's bot fetches your code from your repository and checks it with the same tests. Both stages must be completed before the chapter deadline. You can submit as many times as you need, and your best result is kept.


## Exercise 1: PriceFormatterTest *(5 * 10 %)*

Write unit tests for the `formatPrice` method of the ready-made [PriceFormatter](./src/main/java/price/formatter/PriceFormatter.java) class. The method takes a floating-point number and returns it formatted as a string:

```java
PriceFormatter formatter = new PriceFormatter();
String price = formatter.formatPrice(3.141592653589793); // returns "3,14 €"
```

In the basic case, the method formats a number as `"123 456,79 €"` according to the following rules:

* The thousands separator is a space.
* The decimal separator is a comma.
* The decimal part has two digits and is rounded to the nearest hundredth, i.e. cent ([extra tip](./rounding.md)).
* If the decimal part is zero after rounding, it is left out completely.
* The price ends with the euro sign `€`.

**You do not need to implement the method yourself**: you practice *testing* a ready-made method. ⛔ *Do not change the `PriceFormatter` class.*

Write your tests in the [PriceFormatterTest](./src/test/java/price/formatter/PriceFormatterTest.java) class of the template and remove its ready-made `fail` test. Write a separate test method for each rule that calls `formatPrice` with different parameters and checks the returned values. You do not need to study the method's source code: the most important thing is to know the **requirements** above. Your tests should pass, because the given method *should* not have any bugs.

💡 *If your tests use numbers that are exactly halfway between hundredths, you may run into the limited precision of floating-point numbers. The problem is not caused by Java or the code but by how computers handle decimals. A more detailed explanation is in [rounding.md](./rounding.md) and in the article ["What Every Computer Scientist Should Know About Floating-Point Arithmetic" (oracle.com)](https://docs.oracle.com/cd/E19957-01/806-3568/ncg_goldberg.html).*

### Grading: mutation testing

Passing tests do not yet guarantee that the tests are thorough. That is why the course system first runs your tests against the working `PriceFormatter` class, where all of them must pass. Then your tests are run **against five differently buggy versions** 😈. You get a point for each buggy version if at least one of your tests fails against it, i.e. your tests find the bug. If your tests do not find all bugs, make sure they check all of the rules above with different inputs.

🚀 *This method is called mutation testing: "Mutation testing is used to design new software tests and evaluate the quality of existing software tests. Mutation testing involves modifying a program in small ways." [(Wikipedia)](https://en.wikipedia.org/wiki/Mutation_testing)*


## Exercise 2: DayOfYear *(applying, 25 % + 25 %)*

The static method `dayOfYear` of the ready-made [DayOfYear](./src/main/java/refactoring/DayOfYear.java) class takes a date as three integers and returns the day's ordinal number in that year (1–366). The first day of the year is 1 and the last one 365 or 366, depending on the year. **The method has bugs** that you need to find with your own JUnit tests and fix.

> *"Some data sets specify dates using the year and day of year rather than the year, month, and day of month. The day of year (DOY) is the sequential day number starting with day 1 on January 1st."*
>
> [Day of the Year (DOY) calendar. National Snow and Ice Data Center](https://nsidc.org/data/user-resources/help-center/day-year-doy-calendar)

You can look up the day numbers of different dates in the [DOY calendar](https://nsidc.org/data/user-resources/help-center/day-year-doy-calendar). Note that from March onwards, the numbers differ between leap years and regular years.

### Part 2.1: tests for the `dayOfYear` method

Write the test class `DayOfYearTest` in the package `refactoring` in the [src/test/java](./src/test/java/) directory (start the file with `package refactoring;`). In the course system, a template of the class is ready on the exercise page. Design the test cases based on how the method **should** work, not on how it works now. Test different times of the year and leap years, and write each test case in its own `@Test` method.

💡 *At this point, your tests hopefully find the bugs in the method, so not all tests pass yet. Remember to add the new test file to version control with `git add`.*

### Part 2.2: fixing and refactoring the `dayOfYear` method

In professional software development, code is written in teams where developers change and existing code is modified all the time. That is why code must be understandable and modifiable without unexpected breakage. The `dayOfYear` method consists of a long `if-else` structure and repeated additions, and the same, possibly wrong, numbers repeat in the code.

Fix the bugs and refactor the method to be more understandable and maintainable so that it works the same way from the outside. 📣 **Do not change the method signature or the order of the parameters.** Java's [Year](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/Year.html) class with its `isLeap` method and the [Month](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/Month.html) and [YearMonth](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/YearMonth.html) classes, which know the lengths of the months, may help.

> *"Code refactoring is the process of restructuring existing source code—changing the factoring—without changing its external behavior."*
>
> Wikipedia. [Code refactoring](https://en.wikipedia.org/wiki/Code_refactoring).

⛔ *Java's standard library has ready-made implementations for calculating the day of the year, and a real project would use them. The goal of this exercise is to practice testing and refactoring, though, so modify the given code.*

**"Code smells"**: study the following code smells and improve the code with them. The list is covered with the same example code in the [original source](https://web.mit.edu/6.005/www/fa16/classes/04-code-review/) of the exercise.

* [Don't Repeat Yourself](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself)
* [Comments Where Needed](https://stackoverflow.blog/2021/12/23/best-practices-for-writing-code-comments/)
* [Fail Fast](https://en.wikipedia.org/wiki/Fail-fast)
* [Avoid Magic Numbers](https://en.wikipedia.org/wiki/Magic_number_(programming))
* [One Purpose For Each Variable](https://en.wikipedia.org/wiki/Single-responsibility_principle)

🤔 *Invalid parameters, a bug or a feature? According to the fail fast principle, problems are easier to fix the earlier they are detected. The method now returns a number even if the month or day is out of range. You may throw an `IllegalArgumentException`, for example, but it is not required, and invalid inputs are not counted as bugs of this method.*

### Grading

The course system first runs your own `DayOfYearTest` class, whose tests must all pass against your fixed code (25 %). Then it runs a separate grading test `DayOfYearGradingTest` that checks that the fixed method works correctly (25 %).


## License and authors

This exercise was developed by Teemu Havulinna and Jukka Juslin, and it is licensed under the [Creative Commons BY-NC-SA license](https://creativecommons.org/licenses/by-nc-sa/4.0/). ChatGPT 3.5 and GitHub Copilot were used in creating the assignment, the source code and the tests.

**DayOfYear example class (Smelly Example #1).** The [DayOfYear](./src/main/java/refactoring/DayOfYear.java) class is borrowed from the [course materials of MIT's Software Construction course](https://web.mit.edu/6.005/www/fa16/classes/04-code-review/):

> *Collaboratively authored with contributions from: Saman Amarasinghe, Adam Chlipala, Srini Devadas, Michael Ernst, Max Goldman, John Guttag, Daniel Jackson, Rob Miller, Martin Rinard, and Armando Solar-Lezama.*
>
> *Licensed under [CC BY-SA 4.0](https://creativecommons.org/licenses/by-sa/4.0/).*
