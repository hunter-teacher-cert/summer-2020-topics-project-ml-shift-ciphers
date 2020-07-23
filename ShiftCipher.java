import java.io.*;
import java.util.*;

public class ShiftCipher {
/// Attributes - encoded string provided by the user.
private static String in;

//                       a,b,c,d,e ,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z
private int[] engFreq = {8,1,3,4,12,2,2,6,7,0,1,4,3,7,8,2,0,6,6,9,3,1,2,0,2,0};

/*
   Gets the input string from the user and returns it as a lowercase string
 */
public static void setInput(){
        Scanner str = new Scanner(System.in);
        System.out.print("Input your encoded message: ");
        String lowercase_str = str.nextLine();   // Convert input to lowercase string
        in = lowerCase(lowercase_str);   // lowercase string
}

/*
   Turns a string of various cases to lowercase
 */
public static String lowerCase (String s){
        return s.toLowerCase();   // bye-bye compiler
}

/*
   Calculates frequency of a character in a given string
 */
public static int[] makeFreqArray (){
        int [] alpha = new int[26];
        for (int i = 0; i < in.length(); i++) {
                int c = (int)in.charAt(i) - 97;
                if((c >= 0) && (c <=26)) {
                        alpha[c]++;
                }
        }
        return alpha;   // bye-bye compiler
}


public static int freqLetter(int[] arr, int option) {
        if(arr.length < 3) {
                throw new IllegalArgumentException("Array size must be 3 or larger.");
        }
        if(option != 1 && option != 2 && option != 3) {
                throw new IllegalArgumentException("Option must be 1, 2, or 3.");
        }
        // find the first largest element and save as firstLargestValue
        int firstLargestValue = arr[0];
        int firstLargestIndex = 0;

        for(int i = 1; i < arr.length; i++) {
                if(arr[i] > firstLargestValue) {
                        firstLargestValue = arr[i];
                        firstLargestIndex = i;
                }
        }
        // find the second largest element and save as secondLargestValue
        int secondLargestValue = -1;
        int secondLargestIndex = -1;

        for(int i = 0; i < arr.length; i++) {
                if(arr[i] > secondLargestValue && arr[i] <= firstLargestValue && i != firstLargestIndex) {
                        secondLargestValue = arr[i];
                        secondLargestIndex = i;
                }
        }
        //find third largest element and save as thirdLargestValue
        int thirdLargestValue = -1;
        int thirdLargestIndex = -1;

        for(int i = 0; i < arr.length; i++) {
                if(arr[i] > thirdLargestValue && arr[i] <= secondLargestValue && i != firstLargestIndex && i != secondLargestIndex) {
                        thirdLargestValue = arr[i];
                        thirdLargestIndex = i;
                }
        }

        if(option == 1) {
                return firstLargestIndex;
        }
        else if(option == 2) {
                return secondLargestIndex;
        }
        else if(option == 3) {
                return thirdLargestIndex;
        }
        return -1;
}



/*
   Accepts an int [] alpha (of letter distributions) and determines the most frequently seen letter. It returns this letter as char.

   public static int freqLetter (int[] alpha, int option){
        //option 1 = most frequent
        //option 2 = second most frequent
        //option 3 = third most frequent
        int max = -1;
        int second = -1;
        int third = -1;
        int inx = 0;
        for(int i=0; i < alpha.length; i++) {
                if (alpha[i]>max) {
                        max = alpha[i];
                        inx = i;
                }
        }
        return inx;
   }
 */

public static int mostFreqLetter(int[] alpha){
        return freqLetter(alpha,1);
}

/*
   Shifts all letters in a string "i" places to the right.
 */
private static String shift(int shift){
        // ABC --> 97, 98, 99
        String out = "";
        for (int i = 0; i < in.length(); i++) {
                int c = (int)in.charAt(i) - 97;
                if((c < 0) || (c > 26)) {
                        out += in.charAt(i);
                } else {
                        int ch = ((c + shift) % 26 ) + 97; // take index of A-Z letter, add shift value, mod 26 to keep between A-Z, then add 97 to convert to ASCII
                        out += (char)ch;
                }
        }
        return out;
}

public static int calcShift(int letter) {
        // message   EEEE  ASCII: 101   LETTER: 4
        // encrypted AAAA  ASCII: 97    LETTER 0
        // test      HHHH  ASCII: 104   LETTER: 7

        // (E-B+26)%26 = 4 - 1 + 26 % 26 = 3
        // (E-H+26)%26 = 4 - 7 + 26 % 26 = 23
        int shift = (4 - letter + 26) % 26;

        return shift;
}

public static void main (String[] args){
        System.out.println();

        int[] arr;
        setInput();
        arr = makeFreqArray();
        System.out.println();
        System.out.println("Frequency array of input: " + Arrays.toString(arr));
        System.out.println();
        System.out.println("First most frequent letter of input: " + (char)(freqLetter(arr, 1) + 97));
        System.out.println("Index of first most frequent letter: " + freqLetter(arr, 1));
        System.out.println();
        System.out.println("Second most frequent letter of input: " + (char)(freqLetter(arr, 2) + 97));
        System.out.println("Index of second most frequent letter: " + freqLetter(arr, 2));
        System.out.println();
        System.out.println("Third most frequent letter of input: " + (char)(freqLetter(arr, 3) + 97));
        System.out.println("Index of third most frequent letter: " + freqLetter(arr, 3));
        System.out.println();
        int offset = calcShift(mostFreqLetter(arr));
        System.out.printf("Shifted by %d, %s\n", offset, shift(offset));
        System.out.println();


}

}
