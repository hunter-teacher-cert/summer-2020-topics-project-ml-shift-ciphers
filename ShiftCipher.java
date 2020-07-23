import java.io.*;
import java.util.*;

public class ShiftCipher {
// Attributes - encoded string provided by the user.
private String input;

//                       a,b,c,d,e ,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z
private int[] engFreq = {8,1,3,4,12,2,2,6,7,0,1,4,3,7,8,2,0,6,6,9,3,1,2,0,2,0};

/*
   Gets the input string from the user and returns it as a lowercase string
 */
public static String setInput(){
        Scanner str = new Scanner(System.in);
        System.out.print("Input your encoded message: ");
        String lowercase_str = str.nextLine(); // Convert input to lowercase string
        return lowerCase(lowercase_str); // return lowercase string
}

/*
   Turns a string of various cases to lowercase
 */
public static String lowerCase (String s){
        return s.toLowerCase(); // bye-bye compiler
}

/*
   Calculates frequency of a character in a given string
 */
public static int[] makeFreqArray (String s){
        int [] alpha = new int[26];
        for (int i = 0; i < s.length(); i++) {
                int c = (int)s.charAt(i) - 97;
                if((c >= 0) && (c <=26)) {
                        alpha[c]++;
                }
        }
        return alpha; // bye-bye compiler
}

/*
   Accepts an int [] alpha (of letter distributions) and determines the most frequently seen letter. It returns this letter as char.
 */
public static int mostFreqLetter (int[] alpha){
        int max = -1;
        int inx = 0;
        for(int i=0; i < alpha.length; i++) {
                if (alpha[i]>max) {
                        max = alpha[i];
                        inx = i;
                }
        }
        return inx;
}

/*
   Shifts all letters in a string "i" places to the right.
 */
public String shiftRight (int i){
        return "Hello World"; // bye-bye compiler
}

/*
   Shifts all letters in a string "i" places to the left.
 */
public String shiftLeft (int i){
        return "Hello World"; // bye-bye compiler
}

public static void main (String[] args){
        System.out.println();

        int[] arr;
        String in = setInput();
        arr = makeFreqArray(in);
        System.out.println("Frequency array of input: " + Arrays.toString(arr));
        System.out.println("Most frequent letter of input: " + (char)(mostFreqLetter(arr) + 97));
        System.out.println("Index of most frequent letter: " + mostFreqLetter(arr));

        System.out.println();
}

}



// Used lowercase() in setInput() method
// 'r' that was printing was just wrapped text of my Terminal command
