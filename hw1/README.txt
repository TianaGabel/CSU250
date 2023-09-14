Operations.java is just a main method
- The output formatting is done there
- My num class is called
- and the number of arguments is checked

Num.java is a class I made to make the process of converting the numbers easier to debug
-Only one "number" is stored in an instance of num
-The constructor determines the type, and wether it is valid or not
-and the Hexidecimal, Decimal and Binary values are found for the number (named Hex, Dec and Binary)

Other methods include
-getOnesComp() which gets the one's compliment
-similarly for getTwosComp()
-The bitwise operators were done so that I could call them like num1.or(num2.bin)
-The parameter is a string because I wanted the functionality to translate for multiple or calls

*48 is the character shift from numbers in java's ascii, it's a magic number that I did not have time to fix sorry*