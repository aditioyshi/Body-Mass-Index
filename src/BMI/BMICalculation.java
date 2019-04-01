/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BMI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author eyakubsorkar@gmail.com
 */
public class BMICalculation {

    static List underWeight = new ArrayList();
    static List healthy = new ArrayList();
    static List overWeight = new ArrayList();
    static List obese = new ArrayList();

    private static void foodSuggestion() {

        underWeight.add("Meat");
        underWeight.add("Fish");
        underWeight.add("Egg");
        underWeight.add("More Protain Food");
        underWeight.add("Fruits");
        underWeight.add("Vegetables");

        healthy.add("Apples");
        healthy.add("Avocados");
        healthy.add("Bananas");
        healthy.add("Egg");
        healthy.add("Meat");
        healthy.add("Chicken Breasts");

        overWeight.add("Egg");
        overWeight.add("Leafy Greens");
        overWeight.add("Salmon Fish");
        overWeight.add("Boiled Potatoes");
        overWeight.add("Soups");

        obese.add("Vegetables");
        obese.add("Wheat Sandwitch");
        obese.add("Dal");
        obese.add("Roti");
        obese.add("Avoid Junk Foods");

    }

    private static void displayResult(float bmi) {

        String result = "";
        if (bmi < 18.5) {
            result = "Underweight";
            System.out.println("Your BMI is " + bmi + " and you're " + result);
            System.out.println("You should maintain this type of foods: ");
            for (int i = 0; i < underWeight.size(); i++) {
                System.out.println(underWeight.get(i));
            }
        } else if (bmi < 25) {
            result = "Healthy";
            System.out.println("Your BMI is " + bmi + " and you're " + result);
            System.out.println("You should maintain this type of foods: ");
            for (int i = 0; i < healthy.size(); i++) {
                System.out.println(healthy.get(i));
            }
        } else if (bmi < 30) {
            result = "Overweight";
            System.out.println("Your BMI is " + bmi + " and you're " + result);
            System.out.println("You should maintain this type of foods: ");
            for (int i = 0; i < overWeight.size(); i++) {
                System.out.println(overWeight.get(i));
            }
        } else {
            result = "Obese";
            System.out.println("Your BMI is " + bmi + " and you're " + result);
            System.out.println("You should maintain this type of foods: ");
            for (int i = 0; i < obese.size(); i++) {
                System.out.println(obese.get(i));
            }
        }

    }

    private static void calculateBMI() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your weight in KG : ");
        float weight = scanner.nextFloat();
        //System.out.println("Weight: " + weight);
        System.out.println("Enter your Height in Meter : ");
        float height = scanner.nextFloat();
        //System.out.println("Height: " + height);

        float bmi = weight / (height * height);
        //System.out.println("BMI: "+ bmi);

        //print the result
        displayResult(bmi);
    }

    public static void main(String[] args) {
        foodSuggestion();
        System.out.println("Calculate BMI by providing your Weights in KG & Height in Meters");

        while (true) {
            System.out.println();
            calculateBMI();
        }
    }

}
