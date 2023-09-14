package cs250.hw1;
/*Author: Tatiana Gabel
 * Class: CS250
 * Homework 1 numbering systems and representations
 */

public class Operations{
    public static void main (String[] args){
        
        //Task 1: Check correct number of args
        System.out.println("Task 1");
        if (args.length == 3){
            System.out.println("Correct number of arguments given.");
        } else {
            System.out.println("Incorrect number of arguments given");
            System.exit(0);
        }

        //Create number objects for each argument
        Num num1 = new Num(args[0]);
        Num num2 = new Num(args[1]);
        Num num3 = new Num(args[2]);

        //Task 2: Identify numbering system
        System.out.println("\nTask 2");
        System.out.println(num1.start + "=" + num1.type);
        System.out.println(num2.start + "=" + num2.type);
        System.out.println(num3.start + "=" + num3.type);
        
        //Task 3: Error checking numbering format
        System.out.println("\nTask 3");
        System.out.println(num1.start + "=" + num1.isValid);
        System.out.println(num2.start + "=" + num2.isValid);
        System.out.println(num3.start + "=" + num3.isValid);
        if (!(num1.isValid && num2.isValid && num3.isValid)){
            System.exit(0); //if any of the numbers are invalid the program terminates
        }

        //Task 4: Conversions
        System.out.println("\nTask 4");
        System.out.println("Start=" + num1.start + ",Binary=0b" + num1.bin + ",Decimal=" +
                num1.dec + ",Hexadecimal=0x" + num1.hex);
        System.out.println("Start=" + num2.start + ",Binary=0b" + num2.bin + ",Decimal=" +
                num2.dec + ",Hexadecimal=0x" + num2.hex);
        System.out.println("Start=" + num3.start + ",Binary=0b" + num3.bin + ",Decimal=" +
                num3.dec + ",Hexadecimal=0x" + num3.hex);

        //Task 5:Ones compliment
        System.out.println("\nTask 5");
        System.out.println(num1.start + "=" + num1.bin + "=>" + num1.getOnesComp());
        System.out.println(num2.start + "=" + num2.bin + "=>" + num2.getOnesComp());
        System.out.println(num3.start + "=" + num3.bin + "=>" + num3.getOnesComp());

        //Task 6:Twos compliment
        System.out.println("\nTask 6");
        System.out.println(num1.start + "=" + num1.bin + "=>" + num1.getTwosComp());
        System.out.println(num2.start + "=" + num2.bin + "=>" + num2.getTwosComp());
        System.out.println(num3.start + "=" + num3.bin + "=>" + num3.getTwosComp());


        //Task 7: Or and xor
        System.out.println("\nTask 7");
        System.out.println(num1.bin + "|" + num2.bin + "|" + num3.bin + "=" +
        num1.or(num2.or(num3.bin)));
        System.out.println(num1.bin + "&" + num2.bin + "&" + num3.bin + "=" +
        num1.and(num2.and(num3.bin)));
        System.out.println(num1.bin + "^" + num2.bin + "^" + num3.bin + "=" +
        num1.xor(num2.xor(num3.bin)));

        //Task 8: Left and Right shift
        System.out.println("\nTask 8");
        System.out.println(num1.bin + "<<2=" + num1.getLeftShift() + "," + num1.bin + ">>2=" + num1.getRightShift());
        System.out.println(num2.bin + "<<2=" + num2.getLeftShift() + "," + num2.bin + ">>2=" + num2.getRightShift());
        System.out.println(num3.bin + "<<2=" + num3.getLeftShift() + "," + num3.bin + ">>2=" + num3.getRightShift());

    }
}