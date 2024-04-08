package Module3;

import java.util.Scanner;

public class DecimalToFraction {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Prompt the user to enter a decimal number
        System.out.print("Enter a decimal number: ");
        double decimal = input.nextDouble();
        
        // Split the decimal number into integer and decimal parts
        String[] parts = Double.toString(decimal).split("\\.");
        int integerPart = Integer.parseInt(parts[0]);
        int decimalPart = Integer.parseInt(parts[1]);
        
        // Extract the numerator and denominator from the decimal part
        int numerator = (int) (decimal * Math.pow(10, parts[1].length()) - integerPart * Math.pow(10, parts[1].length()));
        int denominator = (int) Math.pow(10, parts[1].length());
        
        // Create a Rational object representing the fraction
        Rational fraction = new Rational(integerPart * denominator + numerator, denominator);
        
        // Print the fraction representation of the decimal number
        System.out.println("The fraction number is " + fraction);
    }
}

class Rational {
    private long numerator;
    private long denominator;

    // Construct a Rational object with default values
    public Rational() {
        this(0, 1);
    }

    // Construct a Rational object with given numerator and denominator
    public Rational(long numerator, long denominator) {
        // Simplify the fraction by dividing both numerator and denominator by their greatest common divisor
        long gcd = gcd(numerator, denominator);
        this.numerator = ((denominator > 0) ? 1 : -1) * numerator / gcd;
        this.denominator = Math.abs(denominator) / gcd;
    }

    // Find the greatest common divisor of two numbers
    private static long gcd(long n, long d) {
        long n1 = Math.abs(n);
        long n2 = Math.abs(d);
        int gcd = 1;
        for (int k = 1; k <= n1 && k <= n2; k++) {
            if (n1 % k == 0 && n2 % k == 0)
                gcd = k;
        }
        return gcd;
    }

    // Get method for numerator
    public long getNumerator() {
        return numerator;
    }

    // Get method for denominator
    public long getDenominator() {
        return denominator;
    }

    // Method to add two Rational objects
    public Rational add(Rational secondRational) {
        long n = numerator * secondRational.getDenominator() + denominator * secondRational.getNumerator();
        long d = denominator * secondRational.getDenominator();
        return new Rational(n, d);
    }

    // Method to subtract two Rational objects
    public Rational subtract(Rational secondRational) {
        long n = numerator * secondRational.getDenominator() - denominator * secondRational.getNumerator();
        long d = denominator * secondRational.getDenominator();
        return new Rational(n, d);
    }

    // Method to multiply two Rational objects
    public Rational multiply(Rational secondRational) {
        long n = numerator * secondRational.getNumerator();
        long d = denominator * secondRational.getDenominator();
        return new Rational(n, d);
    }

    // Method to divide two Rational objects
    public Rational divide(Rational secondRational) {
        long n = numerator * secondRational.getDenominator();
        long d = denominator * secondRational.numerator;
        return new Rational(n, d);
    }

    // Override toString() method to represent Rational object as a string
    @Override
    public String toString() {
        if (denominator == 1)
            return numerator + "";
        else
            return numerator + "/" + denominator;
    }
}