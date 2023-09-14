package cs250.hw1;

public class Num {
    String start;
    String type;
    Boolean isValid = true;

    String hex;
    String bin;
    String dec;
    private String onesComp;

    public Num(String num){
        start = num;
        if ((num.length()> 2) && num.substring(0,2).equalsIgnoreCase("0x")){
            type = "Hexadecimal";
            for (int i = 2; i <= num.length()-1; i++){ //Checks each char after the 0x
                char c = num.toLowerCase().charAt(i);
                Boolean isAToF = ((97 <= c) && (c <= 102)); //Must be 0-9 or a-f
                if (!(Character.isDigit(c)) && !(isAToF)){
                    isValid = false;
                    return; //if this is false then we do not need to calculate the values following it
                } 
            }
            this.hex = num.substring(2).toLowerCase();
            dec = hexToDec(hex);
            bin = decToBin(dec);

        } else if ((num.length()> 2) && num.substring(0,2).equalsIgnoreCase("0b")){
            type = "Binary";
            for (int i = 2; i <= num.length()-1; i++){ //Checks each char after the 0b
                char c = num.toLowerCase().charAt(i);
                if ((c != '1') && (c != '0')){ // must be 0 or 1
                    isValid = false;
                    return; //if this is false then we do not need to calculate the values following it
                } 
            }
            bin = num.substring(2);
            hex = binToHex(bin);
            dec = hexToDec(hex);


        } else {
            type = "Decimal";
            for (int i = 0; i <= num.length()-1; i++){ //Checks all digits
                char c = num.toLowerCase().charAt(i);
                if (!Character.isDigit(c)){ // must be a digit
                    isValid = false;
                    return; //if this is false then we do not need to calculate the values following it
                } 
            }
            dec = num;
            bin = decToBin(dec);
            hex = binToHex(bin);


        }
    }

    public String calculateOnesComp(String num){
        String numOnes = "";
        for (int i = 0; i <= num.length()-1; i++){ //flips and records the bits
            if (num.charAt(i) == '0'){
                numOnes += "1";
            } else {
                numOnes += "0";
            }
        }
        return numOnes;
    }

    public String getOnesComp(){
        onesComp = calculateOnesComp(bin);
        return onesComp;
    }

    public String getTwosComp(){
        Boolean carry = false;
        int i = onesComp.length()-1;
        String result = "";
        do{
            //current bit add 1
            if (onesComp.charAt(i) == '0'){
                //0 +1 = 1
                result = "1" + result; 
                carry = false;
            } else {
                //1 + 1 = 0 with carry
                result = "0" + result;
                carry = true;
            }
            i--;
            
        }while((carry == true)&&(i >= 0));
        if (carry == true){ //if a carry digit carries over to the end we add it
            result = "1" + result;
        }
        while(i>=0){ //Otherwise we just add the remaining digits
            result = onesComp.charAt(i) + result;
            i--;
        }
        return result;
    }

    public String getLeftShift(){
        return bin + "00";
    }

    public String getRightShift(){
        if(bin.length() < 2)
            return "";
        else
        return bin.substring(0,bin.length()-2);
    }

    public String or(String num1){
        String num2 = bin;
        if (this.bin.length() > num1.length()){ //we switch the values so that the number with more digits is first
            num2 = num1;
            num1 = bin;
        }

        String result = "";
        //We compare the digits within the shorter number
        for (int i = num2.length()-1;i >= 0; i--){
            if((num1.charAt(i) == '0') && (num2.charAt(i) == '0')){ // only false when both inputs are false
                result = "0" + result;
            } else {
                result = "1" + result;
            }
        }
        //Then we compare the remaining digits with 0
        int index2 = num1.length()-num2.length()-1;
        for (int i = index2; i >=0; i--){
            if(num1.charAt(i) == '0'){
                result = "0" + result;
            } else {
                result = "1" + result;
            }
        }
        
        return result;
    }

    public String and(String num1){
        String num2 = bin;
        if (this.bin.length() > num1.length()){ //We switch the values so that the number with more digits is first
            num2 = num1;
            num1 = bin;
        }

        String result = "";
        //We compare the digits within the shorter number
        for (int i = 0; i < num2.length();i++){
            if((num1.charAt(num1.length()-i-1) == '1') && (num2.charAt(num2.length()-i-1) == '1')){ //only true when both are true
                result = "1" + result;
            } else {
                result = "0" + result;
            }
        }
        //Then we compare the remaining digits with 0, hence they are all 0
        int index2 = num1.length()-num2.length()-1;
        for (int i = index2; i >=0; i--){
                result = "0" + result;
        }
        
        return result;
    }

    public String xor(String num1){
         String num2 = bin;
        if (this.bin.length() > num1.length()){ //we switch the values so that the number with more digits is first
            num2 = num1;
            num1 = bin;
        }

        String result = "";
        //We compare the digits within the shorter number
        for (int i = 0; i < num2.length();i++){
            if(num1.charAt(num1.length()-i-1) == num2.charAt(num2.length()-i-1)){ //xor is false when the bits are the same
                result = "0" + result;
            } else {
                result = "1" + result;
            }
        }
        //Then we compare the remaining digits with 0
        int index2 = num1.length()-num2.length()-1;
        for (int i = index2; i >=0; i--){
            if(num1.charAt(i) == '0'){
                result = "0" + result;
            } else {
                result = "1" + result;
            }
        }
        
        return result;
    }


    private String binToHex(String bin){
        String result = "";
        //for each 4 characters
        for(int i = bin.length();i>0;i-= 4){
            //convert to decimal
            int currDigit = 0;
            int p = 0;
            if(i>=4){//There are 4 digits
                for(int j = bin.substring(i-4, i).length()-1;j>=0;j--){
                    currDigit += Math.pow(2,p)*(bin.substring(0,i).charAt(j)-48); 
                    p++;
                }
            } else {//There are not 4 digits
                for(int j = bin.substring(0, i).length()-1;j>=0;j--){
                    currDigit += Math.pow(2,p)*(bin.substring(0,i).charAt(j)-48); 
                    p++;
                }
            }
            //That corresponds to a hex values that get added to result
            if(currDigit < 10){
                //It's the same
                result = "" + currDigit + result;
            } else{
                char c = (char)(currDigit + 87);
                result = "" + c + result;
            }
        }

        return result;
    }

    private String hexToDec(String hex){
        String result = "";
        int currDigit;
        int p = 0;
        int total = 0;
        //for each character
        for(int i= hex.length()-1; i>=0;i--){
            //convert to a value
            char c = hex.charAt(i);
            if((97 <= c) && (c <= 102)){ //Case if is A to F
                currDigit = c - 87; // lower case a is 97 from 0, and a in hexadecimal is 10 in decimal, so we add -97 + 10
            } else {
                currDigit = c - 48; //Ascii numbers are 48 digits from their respective number
            }
            //mulitply by increasing powers of 16 and add to total
            total += currDigit*Math.pow(16, p);
            p++;
        }
        result = "" + total;

        return result;
    }

    private String decToBin(String dec){
        String result = "";
        //Convert dec to an int
        int decNum = 0;
        int p = 0;
        
        for(int i = dec.length()-1; i>=0;i--){
            decNum += Math.pow(10,p)*(dec.charAt(i)-48);
            p++;
        }
        //We can divide by 2 and keep track of the remainder amount, these written in reverse order is the number in binary
        int currDigit;
        do{
            currDigit = decNum % 2;
            result = "" + currDigit + result;
            decNum = decNum / 2;
        }while(decNum > 0);

        return result;
    }
}



