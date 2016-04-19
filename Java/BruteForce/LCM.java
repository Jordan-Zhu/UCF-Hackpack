public class LCM{

    // Reduction by the GCD.
    public static int lcm(int a, int b){
        return a * (b / gcd(a,b));
    }
    
    public static int gcd(int a, int b) {
        while(a != 0 && b != 0){ // until either one of them is 0 
            int c = b;
            b = a % b;
            a = c;
        }
        return a+b; // either one is 0, so return the non-zero value
    }
    public static void main(String[] args){
        System.out.println(lcm(3,5)); // 15
    }
}
