package model; /**
 * Created by gwenli on 2017-09-30.
 */

import java.util.*;
import java.util.regex.*;

public class Solution {
    String input;
    public static void main(String args[] ) throws Exception {
        Solution sol = new Solution();
        sol.start();

    }
    public void start() throws Exception {
         /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner sn = new Scanner(System.in);
        this.input = sn.nextLine();
        if (this.isEntry(this.input)){
            System.out.println("1");
            System.out.println(this.maskEntries(this.input));

        }
        else if (this.isEmail(this.input)){
            System.out.println("2");
            System.out.println(this.maskEmail(this.input));

        }
        else if(this.isPhone(this.input))
        {
            System.out.println("3");
            System.out.println(this.maskPhone(this.input));
        }
        else {
            System.out.println(input);
            throw new Exception("not valid input");
        }


    }
    public Boolean isEmail(String input){
        //set regex for email address
        // String eP = "[\\u0000-\\u007F]+.?[\\u0000-\\u007F]+@[\\u0000-\\u007F]+.[\\u0000-\\u007F]+";
        String eP = "^[\\w\\!\\#\\$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\}\\|\\~]+(.?[\\w\\!\\#\\$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\}\\|\\~]+)+@\\w+.[a-z]+$";
        //create email pattern object
        Pattern ePattern = Pattern.compile(eP);
        //create email matcher object
        Matcher eM = ePattern.matcher(input);
        return eM.find();
    }

    public Boolean isPhone(String input){
        //set regex for phone number
        //String pP = "\\+?\\d{0,3}([\\-\\s]*\\(?\\d{3}\\)?){2}[\\-\\s]*\\(?\\d{4}";
        String pP = "\\+?\\d{0,3}[-\\s(]*(\\d{3}[\\-\\s\\(\\)]*){2}\\d{4}\\)?";
        //String pP = "\\d\\h\\d";
        //create phone pattern object
        Pattern pPattern = Pattern.compile(pP);
        //create email matcher object
        Matcher pM = pPattern.matcher(input);
        return pM.find();
    }

    public Boolean isEntry(String input){

        String first2 = input.substring(0,2);
        //set regex for entries
        String entryP = "((E:)|(P:))";
        //String pP = "\\d\\h\\d";
        //create entry pattern object
        Pattern entryPattern = Pattern.compile(entryP);
        //create email matcher object
        Matcher entryM = entryPattern.matcher(first2);
        return entryM.find();
    }


    public String maskEmail(String input) {
        //set index of the last letter before '@'
        int ll = input.indexOf('@')-1;
        StringBuilder ans = new StringBuilder();
        ans.append(input.substring(0,1));
        for (int i=0; i < 5; i++){
            ans.append("*");
        }
        ans.append(input.substring(ll));
        return ans.toString();
    }


    public String maskPhone(String input) {
       /* StringBuilder ans = new StringBuilder();
        for (int i=0; i<this.input.length(); i++){
            if (!Character.isDigit(this.input.charAt(i)))
                ans.append(this.input.charAt(i));

            else
                ans.append("*");
        }
        return ans.toString();*/
        String ans;
        int numberofDigitinRegionCode;
        if (input.substring(0,1).equals("+")){
            StringBuilder answer = new StringBuilder();
            answer.append("+");
            numberofDigitinRegionCode=input.replaceAll("\\D","").length()-10;
            for (int i=0; i<numberofDigitinRegionCode;i++){
                answer.append("*");
            }
            //System.out.println(answer);
           // System.out.println(numberofDigitinRegionCode);
            //String temp=input.replaceAll("[-\\s(]*(\\d{3}[\\-\\s\\(\\)]*){2}","-***-***-");
            String temp= input.substring(numberofDigitinRegionCode+1);
            String temp2=temp.replaceAll("[-\\s(]*(\\d{3}[\\-\\s\\(\\)]*){2}","-***-***-");


            answer.append(temp2);

            ans=answer.toString();



       }
       else
         ans = input.replaceAll("[\\s(]*(\\d{3}[\\-\\s\\(\\)]*){2}","***-***-");
       // System.out.println(pre);
    //    String ans = pre.replaceAll("[-\\s()]+","-");

        return ans;
    }


    public String maskEntries(String input) {
        StringBuilder ans = new StringBuilder();
        String[] entries=input.split("\\n");
        //System.out.println(entries.length);

        for (int i=0; i<=entries.length-1; i++){
            String currentEntry = entries[i];
            String currentSub = currentEntry.substring(2);
            if(this.isEmail(currentSub)){
                ans.append("E:");
                String toadd = maskEmail(currentSub);
                ans.append(toadd);
                ans.append("\n");
            }
            else{
                ans.append("P:");
                ans.append(maskPhone(currentSub));
                ans.append("\n");
            }
        }
        return ans.toString();
        //String ans = this.input;

    }
}



