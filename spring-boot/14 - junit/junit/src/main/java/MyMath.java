public class MyMath {
    public int calculateSum(int[] numbers) {
        int result = 0;
        for (int number: numbers) {
            result += number;
        }
        return result;
    }
}
