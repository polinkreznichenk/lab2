import java.io.PrintStream;
import java.util.Scanner;
import java.util.Arrays;
public class Main {
    // Проверка полного квадрата.
    static boolean isSquare(long n) {
        long x = (long) Math.sqrt(n);
        return x * x == n;
    }
    // Математическая проверка числа Фибоначчи.
    static boolean isFibonacci(int n) {
        if (n <= 0) return false;
        long x = 5L * n * n;
        return isSquare(x + 4) || isSquare(x - 4);
    }
    // Нахождение порядкового номера числа Фибоначчи.
    static int fibonacciIndex(int n) {
        int a = 1, b = 1, index = 2;
        if (n == 1) return 1;
        while (b < n) {
            int c = a + b;
            a = b;
            b = c;
            index++;
        }
        return b == n ? index : -1;
    }
    // Сумма чётных цифр.
    static int evenDigitsSum(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;

            if (digit % 2 == 0)
                sum += digit;
            n /= 10;
        }
        return sum;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Ввод размера массива.
        System.out.print("Введите количество чисел: ");
        int n = sc.nextInt();
        if (n < 0 || n > 100000) {
            System.out.println("Некорректный размер массива.");
            sc.close();
            return;
        }
        // Создание массива.
        int[] a = new int[n];
        // Заполнение массива с клавиатуры.
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            if (a[i] <= 0) {
                System.out.println("Числа должны быть натуральными.");
                sc.close();
                return;
            }
        }
        // Массивы для разделения чисел.
        int[] fib = new int[n];
        int[] other = new int[n];
        int f = 0, o = 0;
        // Разделение чисел.
        for (int x : a) {
            if (isFibonacci(x))
                fib[f++] = x;
            else
                other[o++] = x;
        }
        // Сортировка.
        Arrays.sort(fib, 0, f);
        Arrays.sort(other, 0, o);
        // Объединение массивов.
        for (int i = 0; i < f; i++)
            a[i] = fib[i];
        for (int i = 0; i < o; i++)
            a[f + i] = other[o - 1 - i];
        System.out.println("Отсортированный массив:");
        System.out.println(Arrays.toString(a));
        // Количество чисел Фибоначчи.
        System.out.println("Количество чисел Фибоначчи: " + f);
        // Замена на порядковые номера.
        System.out.println("Преобразованный массив:");
        for (int x : a)
            System.out.print(fibonacciIndex(x) + " ");
        // Замена чисел суммой чётных цифр.
        for (int i = 0; i < n; i++)
            a[i] = evenDigitsSum(a[i]);
        System.out.println("\nСуммы четных цифр:");
        System.out.println(Arrays.toString(a));
        sc.close();
    }
}
