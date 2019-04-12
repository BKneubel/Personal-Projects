package com.classwork.main;

import java.util.*;
import javax.script.*;
 
public class Integration {
    static ScriptEngineManager mgr = new ScriptEngineManager();
    static ScriptEngine engine = mgr.getEngineByName("JavaScript");
    public static void main(String args[]){
        @SuppressWarnings("resource")
        Scanner kbReader = new Scanner(System.in);
        System.out.print("What rule? (s/m/t) ");
        String rule = kbReader.next();
        System.out.print("n: ");
        int n = kbReader.nextInt();
        System.out.print("Starting: ");
        int start = kbReader.nextInt();
        System.out.print("Ending: ");
        int end = kbReader.nextInt();
        System.out.print("Function: ");
        String function = kbReader.next();
        if(rule.equals("s") || rule.equals("S"))
            System.out.println("S(" + n + ") = " + Simpson(n, start, end, function));
        else if (rule.equals("t") || rule.equals("T"))
            System.out.println("T(" + n + ") = " + Trapezoid(n, start, end, function));
        else if (rule.equals("m") || rule.equals("M"))
            System.out.println("M(" + n + ") = " + Midpoint(n, start, end, function));
        else System.out.println("Error: You must use Simpson's, Midpoint, or Trapezoid (s/m/t)");
    }
   
    public static double Midpoint(int n, int start, int finish, String function){
        double deltaX = (double)(finish - start) / (double)n, area = 0;
        double[] xValues = new double[n];
        double[] yValues = new double[n];
        for(int i = 0; i < xValues.length; i++)
            xValues[i] = (deltaX / 2) + start + deltaX * (double)i;
        for(int i = 0; i < yValues.length; i++){
            String tempEquation = function.replaceAll("x", "(" + xValues[i] + ")");
            try{
                yValues[i] = (double) engine.eval(tempEquation);
            }
            catch(ScriptException e){
                System.out.println("Error parsing the equation. You must use appropriate syntax for *JavaScript*, which evaluates the expression. The error is as follows: ");
                System.out.println(e);
                break;
            }
        }
        for(double d : yValues)
            area = area + d;
        area = area * deltaX;
    return area;   
    }
   
    public static double Trapezoid(int n, int start, int finish, String function){
        double deltaX = (double)(finish - start) / (double)n, area = 0;
        double[] xValues = new double[n + 1];
        double[] yValues = new double[n + 1];
        double[] finalValues = new double[n + 1];
        for(int i = 0; i < xValues.length; i++)
            xValues[i] = start + deltaX * (double)i;
        for(int i = 0; i < yValues.length; i++){
            String tempEquation = function.replaceAll("x", "(" + xValues[i] + ")");
            try{
                yValues[i] = (double) engine.eval(tempEquation);
            }
            catch(ScriptException e){
                System.out.println("Error parsing the equation. You must use appropriate syntax for *JavaScript*, which evaluates the expression. The error is as follows: ");
                System.out.println(e);
                break;
            }
        }
        for(int i = 0; i < finalValues.length; i++){
            if(i == 0 || i == (n))
                finalValues[i] = yValues[i];
            else finalValues[i] = yValues[i] * 2;
        }
        for(double d : finalValues)
            area = area + d;
        area = ( deltaX / 2) * area;
    return area;   
    }
   
    public static double Simpson(int n, int start, int finish, String function){
        double deltaX = (double)(finish - start) / (double)n, area = 0;
        double[] xValues = new double[n + 1];
        double[] yValues = new double[n + 1];
        double[] finalValues = new double[n + 1];
        if(n % 2 == 0){
            for(int i = 0; i < xValues.length; i++)
                xValues[i] = start + deltaX * (double)i;
            for(int i = 0; i < yValues.length; i++){
                String tempEquation = function.replaceAll("x", "(" + xValues[i] + ")");
                try{
                    yValues[i] = (double) engine.eval(tempEquation);
                }
                catch(ScriptException e){
                    System.out.println("Error parsing the equation. You must use appropriate syntax for *JavaScript*, which evaluates the expression. The error is as follows: ");
                    System.out.println(e);
                    break;
                }
            }
            for(int i = 0; i < finalValues.length; i++){
                if(i == 0 || i == (n))
                    finalValues[i] = yValues[i];
                else if(i % 2 == 1)
                    finalValues[i] = yValues[i] * 4;
                else finalValues[i] = yValues[i] * 2;
            }
            for(double d : finalValues)
                area = area + d;
            area = (area / 3) * deltaX;
        }
        else System.out.println("Error: the n for Simpson's Rule must be even.");
        return area;   
    }
}