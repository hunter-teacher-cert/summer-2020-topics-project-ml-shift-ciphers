import java.io.*;
import java.util.*;
import java.lang.*;
import java.net.*;

public class ShiftCipher {
/// Attributes - encoded string provided by the user.
private static String in;
private static final int MAXOPT = 3;
private static int decryptCounter = 0;
private static int[] arr = null;

//                       a,b,c,d,e ,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z
//private static int[] engFreq = {8,1,3,4,12,2,2,6,7,0,1,4,3,7,8,2,0,6,6,9,3,1,2,0,2,0};
private static int[] engFreq = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};


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
   Generate the Frequency for Language
 */
public static void trainFreq(){
        String sampleText = "";
        Scanner str = new Scanner(System.in);
        System.out.print("Input your training text: ");
        String lowercase_str = str.nextLine(); // Convert input to lowercase string
        sampleText = lowerCase(lowercase_str); // lowercase string
        // sampleText = htmlToString("https://www.google.com");
        // sampleText = lowerCase(sampleText); // lowercase string

        engFreq = makeFreqArray(sampleText,engFreq);
        System.out.println();
        System.out.println("Frequency array of training: " + Arrays.toString(engFreq));
}

public static String htmlToString (String html){
        // StringBuilder contentBuilder = new StringBuilder();
        // try {
        //         BufferedReader in = new BufferedReader(new FileReader(html));
        //         String str;
        //         while ((str = in.readLine()) != null) {
        //                 contentBuilder.append(str);
        //         }
        //         in.close();
        // } catch (IOException e) {
        // }
        // String content = contentBuilder.toString();
        //
        // return content;

        String urlToRead = html;
        URL url; // The URL to read
        HttpURLConnection conn; // The actual connection to the web page
        BufferedReader rd; // Used to read results from the web page
        String line; // An individual line of the web page HTML
        String result = ""; // A long string containing all the HTML

        try {
                url = new URL(urlToRead);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = rd.readLine()) != null) {
                        if(line.contains("<p>")) {
                                int turnOn = line.indexOf("<p>");
                                int turnOff = line.indexOf("</p>", turnOn + 1);
                                if(turnOn >= 0){
                                    line = line.substring(turnOn, turnOff);
                                    result += line;
                                }
                        }
                }
                rd.close();
        } catch (Exception e) {
                e.printStackTrace();
        }


        System.out.println("The html string is: " + result);
        return result;
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
        return makeFreqArray(in, alpha);
}

public static int[] makeFreqArray(String text, int[] alpha) {
        // int [] alpha = new int[26];
        for (int i = 0; i < text.length(); i++) {
                int c = (int)text.charAt(i) - 97;
                if((c >= 0) && (c < 26)) {
                        alpha[c]++;
                }
        }
        return alpha; // bye-bye compiler
}


public static int freqLetter(int[] arr, int option) {
        if(arr.length < MAXOPT) {
                throw new IllegalArgumentException("Array size must be 5 or larger.");
        }
        if((option < 1) || (option > MAXOPT)) {
                throw new IllegalArgumentException("Option must be 1, 2, 3, 4 or 5.");
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

        //find fourth largest element and save as fourthLargestValue
        int fourthLargestValue = -1;
        int fourthLargestIndex = -1;

        for(int i = 0; i < arr.length; i++) {
                if(arr[i] > fourthLargestValue && arr[i] <= thirdLargestValue && i != firstLargestIndex && i != secondLargestIndex && i != thirdLargestIndex) {
                        fourthLargestValue = arr[i];
                        fourthLargestIndex = i;
                }
        }

        //find fifth largest element and save as fifthLargestValue
        int fifthLargestValue = -1;
        int fifthLargestIndex = -1;

        for(int i = 0; i < arr.length; i++) {
                if(arr[i] > fifthLargestValue && arr[i] <= fourthLargestValue && i != firstLargestIndex && i != secondLargestIndex && i != thirdLargestIndex && i != fourthLargestIndex) {
                        fifthLargestValue = arr[i];
                        fifthLargestIndex = i;
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
        else if(option == 4) {
                return fourthLargestIndex;
        }
        else if(option == 5) {
                return fifthLargestIndex;
        }
        return -1;
}



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

public static int calcShift(int letter, int charPos) {
        // message   EEEE  ASCII: 101   LETTER: 4
        // encrypted AAAA  ASCII: 97    LETTER 0
        // test      HHHH  ASCII: 104   LETTER: 7

        // (E-B+26)%26 = 4 - 1 + 26 % 26 = 3
        // (E-H+26)%26 = 4 - 7 + 26 % 26 = 23

        //int c = (int) c - 97; // bring ascii character down to 0-25.

        int shift = (charPos - letter + 26) % 26;

        return shift;
}

public static void decrypt (){
        // boolean for answerFound and int for option to run on freqLetter

        int option = 1;
        boolean isCorrect = false;
        int offset = 0;
        Scanner str = new Scanner(System.in);
        int charPos = -1;

        if(decryptCounter == 0) { // will only ask for input the first time decrypt is called
                setInput(); // sets in
                arr = makeFreqArray(); //make frequency array for input array
                decryptCounter++;
        }


        System.out.println();
        System.out.println("Frequency array of encoded message: " + Arrays.toString(arr));
        System.out.println();
        System.out.println("First most frequent letter of input: " + (char)(freqLetter(arr, 1) + 97));
        System.out.println("Index of first most frequent letter: " + freqLetter(arr, 1));
        System.out.println();
        System.out.println("Second most frequent letter of input: " + (char)(freqLetter(arr, 2) + 97));
        System.out.println("Index of second most frequent letter: " + freqLetter(arr, 2));
        System.out.println();
        System.out.println("Third most frequent letter of input: " + (char)(freqLetter(arr, 3) + 97));
        System.out.println("Index of third most frequent letter: " + freqLetter(arr, 3));
        // System.out.println();
        // System.out.println("Fourth most frequent letter of input: " + (char)(freqLetter(arr, 4) + 97));
        // System.out.println("Index of fourth most frequent letter: " + freqLetter(arr, 4));
        // System.out.println();
        // System.out.println("Fifth most frequent letter of input: " + (char)(freqLetter(arr, 5) + 97));
        // System.out.println("Index of fifth most frequent letter: " + freqLetter(arr, 5));
        // System.out.println();

        int charOption = 1;

        while ((charOption <= MAXOPT) && (!isCorrect)) {
                charPos = freqLetter(engFreq, charOption);
                // on this line, we can copy the line above, but change engFreq to generatedFreq
                option = 1;
                while ((option <= MAXOPT) && (!isCorrect)) {
                        offset = calcShift(freqLetter(arr, option), charPos);
                        System.out.printf("Shifted by %d, %s\n", offset, shift(offset));
                        System.out.println("Was this correct? y/n");

                        String answer = str.nextLine(); // Convert input to lowercase string
                        if(answer.equals("Y") || answer.equals("y")) {
                                isCorrect = true;
                        }
                        else{
                                option++;
                        }
                }
                charOption++;
        }
        if(option > MAXOPT) {
                //   System.out.println("Sorry, we couldn't decrypt your message.");

                /*
                    // here is where we can ask if the user wants to trian the computer more
                 */

                System.out.println("Sorry, we couldn't decrypt your message.");
                System.out.println("Do you want to provide additional training text?");
                String YorN = "";
                Scanner inStr = new Scanner(System.in);
                YorN = inStr.nextLine();
                if (YorN.equals("Y") || YorN.equals("y")) {
                        trainFreq();
                        decrypt();
                } else
                        System.out.println("OK, bye!");
        }




} // end decrypt


public static void main (String[] args){
        htmlToString("https://en.wikipedia.org/wiki/Quicksort");
        trainFreq();
        decrypt();

}

}
