public class Run4Ex {
    public static void main(String[] args) {
        int a = 5;
        int b = 128;
        int sum = 0;
        while (a < 9) {
            a += 1;
            if (b < 17) break;
            b /= 2;
            sum += 1;
        }
        System.out.print(sum + 1);
    }
}
