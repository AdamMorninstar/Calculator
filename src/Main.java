import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char[] mathExpression = scanner.nextLine().toUpperCase().replaceAll("\\s", "").toCharArray();
        int position = 0;
        int a = 0;
        int b = 0;
        char mathOp = 0;
        int f = 0;
        int mathOpCounter = 0;

        for (int i = 0; i < mathExpression.length; i++) {
            if (mathExpression[i] == '+') {
                position = i;
                mathOp = '+';
                mathOpCounter++;
            }  if (mathExpression[i] == '-') {
                position = i;
                mathOp = '-';
                mathOpCounter++;
            }  if (mathExpression[i] == '*') {
                position = i;
                mathOp = '*';
                mathOpCounter++;
            }  if (mathExpression[i] == '/') {
                position = i;
                mathOp = '/';
                mathOpCounter++;
            }

        }
        if (mathOp == 0) {
            throw new RuntimeException("Не является математической операцией");
        }
        if (mathOpCounter > 1) throw new RuntimeException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        StringBuilder numberA = new StringBuilder();
        for (int i = 0; i < position; i++) {
            numberA.append(mathExpression[i]);
        }

        StringBuilder numberB = new StringBuilder();
        for (int i = position+1; i < mathExpression.length; i++) {
            numberB.append(mathExpression[i]);
        }
        if (isArab(numberA) && isArab(numberB) ) {
          a = parseInt(numberA.toString());
          b = parseInt(numberB.toString());
        } else if ((isArab(numberA) && !isArab(numberB) || !isArab(numberA) && isArab(numberB) )) {
          throw new RuntimeException("Используются одновременно разные системы счисления");
          
        } else if (!isArab(numberA) && !isArab(numberB)) {
            a = RomanNumbers.romanToArab(numberA.toString());
            b = RomanNumbers.romanToArab(numberB.toString());
        }
        if ((a < 0 || a > 10 || b < 0 || b > 10) && !isArab(numberA) && !isArab(numberB)) throw new RuntimeException("В римской системе нет отрицательных чисел");

        switch (mathOp) {
            case '+' -> f = a + b;
            case '-' -> f = a - b;
            case '*' -> f = a * b;
            case '/' -> f = a / b;
        }
        if (isArab(numberA)) {
            System.out.println(f);
        } else {
            System.out.println(RomanNumbers.arabToRoman(f));
        }

    }
    public static boolean isArab(StringBuilder stringBuilder) {
        try {
          parseInt(stringBuilder.toString());
          return true;
        } catch (Exception e) {
            return false;
        }
    }
}