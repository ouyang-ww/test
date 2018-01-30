/**
 * Migration by weouyang on 09/05/2017.
 */
public class testOuyang2 {
    public testOuyang2() {
        System.out.println("class name :" + this.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        new testOuyang2.tst3();
        System.out.println("tst2 class name:" + testOuyang2.tst3.class.getSimpleName());
        try {
            System.out.println(Class.forName("testOuyang.tst2").getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static final class tst3 extends testOuyang2 {
        public tst3() {
            super();
        }
    }
}
