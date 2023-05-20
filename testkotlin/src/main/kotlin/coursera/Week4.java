package coursera;

import testkotlin.src.main.coursera.Week4Kt;

import java.util.List;

public class Week4 {
    public static void main(String[] args) {
       var integers = List.of(1, 2, 3, 12);

       //calling toplevel lambda fun average in Kotlin file
        int averageOfInt =   Week4Kt.getAverage(integers).invoke();

        //since the function definition in the kotlin file are lambdas, you have to invoke
        var doubles = List.of(1.0, 0.0, 3.4, 5.7, 8.0);
        double averageDouble = Week4Kt.averageOfDouble(doubles).invoke();
        System.out.println("average ints is: " + averageOfInt);
        System.out.println("average doubles is: " + averageDouble);

    }
}
