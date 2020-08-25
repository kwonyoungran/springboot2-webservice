package com.apress.springrecipes.calculator;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(com.apress.springrecipes.calculator.CalculatorConfiguration.class);

        com.apress.springrecipes.calculator.ArithmeticCalculator arithmeticCalculator =
                context.getBean("arithmeticCalculator", com.apress.springrecipes.calculator.ArithmeticCalculator.class);
        arithmeticCalculator.add(1, 2);
        arithmeticCalculator.sub(4, 3);
        arithmeticCalculator.mul(2, 3);
        arithmeticCalculator.div(4, 2);

        com.apress.springrecipes.calculator.UnitCalculator unitCalculator = context.getBean("unitCalculator", com.apress.springrecipes.calculator.UnitCalculator.class);
        unitCalculator.kilogramToPound(10);
        unitCalculator.kilometerToMile(5);
    }
}
