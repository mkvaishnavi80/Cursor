public class FizzBuzz {
    
    /**
     * Prints numbers from 1 to n with FizzBuzz rules:
     * - Multiples of 3: print "Fizz"
     * - Multiples of 5: print "Buzz" 
     * - Multiples of both 3 and 5: print "FizzBuzz"
     * - All other numbers: print the number itself
     * 
     * @param n the upper limit (inclusive)
     */
    public static void fizzBuzz(int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }
    
    /**
     * Main method to demonstrate the FizzBuzz functionality
     */
    public static void main(String[] args) {
        System.out.println("FizzBuzz for numbers 1 to 15:");
        fizzBuzz(15);
        
        System.out.println("\nFizzBuzz for numbers 1 to 30:");
        fizzBuzz(30);
    }
}