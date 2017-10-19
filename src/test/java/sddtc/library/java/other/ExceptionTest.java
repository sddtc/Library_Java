package sddtc.library.java.other;

public class ExceptionTest {

    public static void main(String[] args) {
        ExceptionTest demo = new ExceptionTest();
        demo.inCatchOrNot();
    }

    public void inCatchOrNot() {
        try {
            ops();
        } catch (Exception ex) {
            System.out.println("catch it.");
        } finally {
            System.out.println("finish it.");
        }
    }

    public void ops() throws Exception{
        try {
            int value = 1/0;

            System.out.println("continue...");
        } finally {
            System.out.println("go finally");
        }
    }
}
