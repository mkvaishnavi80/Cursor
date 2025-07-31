public class FizzBuzz {
    
    /**
     * Prints numbers from 1 to n with FizzBuzz rules:
     * - Multiples of 4: print "Fizz"
     * - Multiples of 7: print "Buzz" 
     * - Multiples of both 4 and 7 (i.e., multiples of 28): print "FizzBuzz"
     * - All other numbers: print the number itself
     * 
     * @param n the upper limit (inclusive)
     */
    public static void printFizzBuzz(int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 4 == 0 && i % 7 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 4 == 0) {
                System.out.println("Fizz");
            } else if (i % 7 == 0) {
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
        System.out.println("FizzBuzz from 1 to 30:");
        printFizzBuzz(30);
    }
}