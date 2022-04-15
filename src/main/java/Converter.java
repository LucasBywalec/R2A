import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Scanner;

public class Converter {

    public static void main(String[] args) {
        Converter converter = new Converter();

        System.out.println("Type a number you want to convert, be it either Roman or Arabic");
        Scanner scanner = new Scanner(System.in);
        String numeral = scanner.nextLine();

        numeral = numeral.toUpperCase(Locale.ROOT);

        if(converter.isValidRoman(numeral)){
            System.out.println(converter.romanToArabic(numeral));
        } else if(converter.isValidArabic(numeral)){
            System.out.println(converter.arabicToRoman(numeral));
        } else{
            System.out.println("Wrong input, program is going to shut down");
        }
    }

    private final String[] romanNumerals = {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
    private final int[] romanNumeralsValues = {1,4,5,9,10,40,50,90,100,400,500,900,1000};

    public boolean isRomanNumeral(String c){
        for (String numeral: romanNumerals) {
            if(c.equals(numeral)) return true;
        }
        return false;
    }

    public boolean isValidRoman(@NotNull String numeral){
        char[] arr = numeral.toCharArray();
        char lastChar = '\0';
        int sameCharCount = 0;
        for(char c: arr){
            if(!isRomanNumeral(String.valueOf(c))) return false;
            if(lastChar == '\0') lastChar = c;
            else {
                if (c == lastChar) {
                    sameCharCount++;
                }
                else{
                    lastChar = c;
                    sameCharCount = 0;
                }
            }
            if(sameCharCount == 3) return false;
        }
        return true;
    }

    public boolean isValidArabic(@NotNull String numeral){
        char[] arr = numeral.toCharArray();
        if(arr[0] == '0') return false;
        for(char c: arr){
            if(!Character.isDigit(c)) return false;
        }
        return true;
    }

    public String romanToArabic(@NotNull String numeral){
        char[] arr = numeral.toCharArray();
        int arrLength = arr.length;
        int currentSum = 0;
        int prev = 0, curr;
        for (int i = arrLength-1; i >= 0; i--) {
            if(i == arrLength-1){
                prev = numberValueInArabic(String.valueOf(arr[i]));
                currentSum += prev;
                continue;
            }
            curr = numberValueInArabic(String.valueOf(arr[i]));
            if(curr >= prev){
                currentSum += curr;
            } else{
                currentSum -= curr;
            }
            prev = curr;
        }
        return Integer.toString(currentSum);
    }

    public String arabicToRoman(@NotNull String numeral){
        int intNumeral = Integer.parseInt(numeral);;
        String number = "";
        int i = 12;
        while(intNumeral > 0){
            int divider = intNumeral/romanNumeralsValues[i];
            intNumeral = intNumeral%romanNumeralsValues[i];
            while(divider != 0){
                number = number + romanNumerals[i];
                divider--;
            }
            i--;
        }

        return number;
    }

    public int numberValueInArabic(String c){
        for (int i = 0; i < romanNumerals.length; i++) {
            if(romanNumerals[i].equals(c)) return romanNumeralsValues[i];
        }
        return -1;
    }
}
