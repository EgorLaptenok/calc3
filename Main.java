
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String input = scanner.nextLine();
        String[] tokens = input.split(" ");

        if (tokens.length != 3) {
            System.out.println("Неверный Ввод. Пожалуйста, введите допустимое выражение");
            return;
        }

        String first = tokens[0];
        String operator = tokens[1];
        String second = tokens[2];

        Pattern arabicPattern = Pattern.compile("[1-9]|10");
        Pattern romanPattern = Pattern.compile("[IVX]{1,3}");

        Matcher firstMatcher = arabicPattern.matcher(first);
        boolean isFirstArabic = firstMatcher.matches();

        Matcher secondMatcher = isFirstArabic ? arabicPattern.matcher(second) : romanPattern.matcher(second);
        boolean isSecondArabic = secondMatcher.matches();

        if (!isFirstArabic && !isSecondArabic) {
            System.out.println("Неверный Ввод, введите арабские или римские цифры");
            return;
        }

        int a = Integer.parseInt(first);
        int b = Integer.parseInt(second);

        if (isFirstArabic && !isSecondArabic || !isFirstArabic && isFirstArabic) {
            System.out.println("Неверный Ввод, введите арабские или римские цифры");
            return;
        }

        if (a < 1 || a > 10 || b < 1 || b > 10) {
            System.out.println("Неверный Ввод, введите цифры от 1 до 10 включительно");
            return;
        }

        int result = 0;

        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                System.out.println("Неверный оператор, введите либо +, -, * или /.");
                return;
        }

        if (isFirstArabic) {
            System.out.println(result);
        } else {
            System.out.println(convertToRoman(result));
        }
    }

    private static String convertToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
}
