import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static boolean isDivisibleBy3(int n){
        int total = 0;

        while(n > 0) {
            total += n % 10;
            n /= 10;
        }

        while (total >= 10) {
            int tempTotal = 0;
            while (total > 0) {
                tempTotal += total % 10;
                total /= 10;
            }
            total = tempTotal;
        }

        return total == 0 || total == 3 || total == 6 || total == 9;
    }

    public static void checkDivisibility(Set<Integer> numbers, Set<Integer> numbers2) {
        for (int number : numbers) {
            boolean result = isDivisibleBy3(number);
            if(result) {
                numbers2.add(number);
            }
        }
        System.out.println(numbers2);
    }

    public static void main(String[] args) {

        Set<Integer> numbers = new HashSet<>(Arrays.asList(123, 124, 125, 126, 127, 128, 129, 130));
        Set<Integer> numbers2 = new HashSet<>();
        checkDivisibility(numbers, numbers2);
    }
}