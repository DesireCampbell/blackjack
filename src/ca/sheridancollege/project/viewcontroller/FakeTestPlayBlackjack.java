package ca.sheridancollege.project.viewcontroller;

import static junit.framework.Assert.assertEquals;


public class FakeTestPlayBlackjack {

    /**
     * fake JUnit testing
     * @param args
     */
    public static void main(String args[]) {
        try {
            testGoodReadUserLine();
            testBoundaryReadUserLine();
            testGoodReadUserString();
            testBadBoundaryReadUserString();
            testGoodReadUserInt();
            testBadBoundaryReadUserInt();
            testGoodReadUserDouble();
            testBadBoundaryReadUserDouble();
            testGoodReadUserBoolean();
            testBoundaryReadUserBoolean();
            System.out.println("All passed!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
    /**
     * readUserLine
     */
    
    public static void testGoodReadUserLine() {
        System.out.println("readUserLine");
        String prompt = "TESTER, input 'John George Paul Ringo': ";
        String expected = "John George Paul Ringo";
        String actual = PlayBlackjack.readUserLine(prompt);
        assertEquals(expected,actual);
    }
    

    public static void testBoundaryReadUserLine() {
        System.out.println("readUserLine");
        String prompt = "TESTER, input '  99.99  ' (extra spaces before and after): ";
        String expected = "99.99";
        String actual = PlayBlackjack.readUserLine(prompt);
        assertEquals(expected,actual);
    }
    
    /**
     * readUserString
     */

    public static void testGoodReadUserString() {
        System.out.println("readUserString");
        String prompt = "TESTER, input 'John George Paul Ringo': ";
        String expected = "John";
        String actual = PlayBlackjack.readUserString(prompt);
        assertEquals(expected,actual);
    }
    

    public static void testBadBoundaryReadUserString() {
        System.out.println("readUserString");
        String prompt = "TESTER, input '  John Paul !': ";
        String expected = "John";
        String actual = PlayBlackjack.readUserString(prompt);
        assertEquals(expected,actual);
    }
    
    /**
     * readUserInt
     */

    public static void testGoodReadUserInt() {
        System.out.println("readUserInt");
        String prompt = "TESTER, input '10': ";
        int expected = 10;
        int actual = PlayBlackjack.readUserInt(prompt);
        assertEquals(expected,actual);
    }
    

    public static void testBadBoundaryReadUserInt() {
        System.out.println("readUserInt");
        String prompt = "TESTER, input 'FF', then '9.9', then '-10': ";
        int expected = -10;
        int actual = PlayBlackjack.readUserInt(prompt);
        assertEquals(expected,actual);
    }
    
    /**
     * readUserDouble
     */
    public static void testGoodReadUserDouble() {
        System.out.println("readUserDouble");
        String prompt = "TESTER, input '99.9': ";
        double expected = 99.9;
        double actual = PlayBlackjack.readUserDouble(prompt);
        int compResult = Double.compare(expected,actual);
        //assertEquals won't work with doubles, so compare to output of Double.compare()
        assertEquals(0,compResult);
    }
    

    public static void testBadBoundaryReadUserDouble() {
        System.out.println("readUserDouble");
        String prompt = "TESTER, input 'FF', then ' -99 ': ";
        double expected = -99.0;
        double actual = PlayBlackjack.readUserDouble(prompt);
        int compResult = Double.compare(expected,actual);
        //assertEquals won't work with doubles, so compare to output of Double.compare()
        assertEquals(0,compResult);
    }
    
    /**
     * readUserBoolean
*/
    public static void testGoodReadUserBoolean() {
        System.out.println("readUserBoolean");
        String prompt = "TESTER, input 'y': ";
        boolean expected = true;
        boolean actual = PlayBlackjack.readUserBoolean(prompt);
        assertEquals(expected,actual);
    }
    

    public static void testBoundaryReadUserBoolean() {
        System.out.println("readUserBoolean");
        String prompt = "TESTER, input '  Yes please  ' (extra spaces): ";
        boolean expected = true;
        boolean actual = PlayBlackjack.readUserBoolean(prompt);
        assertEquals(expected,actual);
    }

    
    
}//end of class FakeTestPlayBlackjack()
