import java.util.Scanner;

public class Calc {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа: ");
        String expression = scanner.nextLine();
        System.out.println(calc(expression));

    }

    public static String calc(String input) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String[] operator = input.split("[+\\-*/]");
        if (operator.length != 2) throw new Exception("Должно быть два числа");
        oper = detectOperation(input);
        if (oper == null) throw new Exception("Неподдерживаемая математическая операция");
        if (Roman.isRoman(operator[0]) && Roman.isRoman(operator[1])) {
            num1 = Roman.convertToArabian(operator[0]);
            num2 = Roman.convertToArabian(operator[1]);
            isRoman = true;
        } else if (!Roman.isRoman(operator[0]) && !Roman.isRoman(operator[1])) {
            num1 = Integer.parseInt(operator[0]);
            num2 = Integer.parseInt(operator[1]);
            isRoman = false;
        } else {
            throw new Exception("Числа должны быть в одном формате");
        }
        if (num1 > 10 || num1 < 0 || num2 > 10 || num2 < 0) {
            throw new Exception("Число должно быть положительным и меньше десяти");
        }
        int arabian = calculator(num1, num2, oper);
        if (isRoman) {
            result = Roman.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }

        return result;
    }

    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calculator(int a, int b, String oper) {

        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }

    class Roman {
        static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};


        public static boolean isRoman(String val) {
            for (int i = 0; i < romanArray.length; i++) {
                if (val.equals(romanArray[i])) {
                    return true;
                }
            }
            return false;
        }

        public static int convertToArabian(String roman) {
            for (int i = 0; i < romanArray.length; i++) {
                if (roman.equals(romanArray[i])) {
                    return i;
                }
            }
            return -1;
        }

        public static String convertToRoman(int arabian) {
            return romanArray[arabian];
        }
    }
}