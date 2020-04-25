package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
	    System.out.println("Podaj ile punktów");
	    int max = in.nextInt();

	    Vec xx = new Vec(max);
	    Vec yy = new Vec(max);
	    for(int i = 0; i < max; i++){
	        System.out.print("x: ");
	        xx.set(i, in.nextDouble());
            System.out.print("y: ");
            yy.set(i, in.nextDouble());
        }

	    System.out.println("xx: " + xx);
	    System.out.println("yy: " + yy);

        Double a;
        Polynomial result = new Polynomial(0), temp, toMultiply;
	    Vec currX = new Vec(max-1);
	    Vec rest;

	    String polString = "";

	    for(int i = 0; i < max; i++){
	        currX.setAll(xx.get(i));
	        rest = xx.eliminate(i);

	        a = yy.get(i)/ (currX.subtract(rest).product());
	        temp = new Polynomial(a);

	        polString += a + "*";
            for(int j = 0; j < max -1; j++){
                toMultiply = Polynomial.getPolynomialWithRoots(rest.get(j));
                polString += "(" + toMultiply.toString() + ")";
                temp.multiply(toMultiply);
            }
            polString += "\n";
            result.add(temp);
        }

	    System.out.println("\nresult:");
	    result.show();
        System.out.println(polString);


    }


}
