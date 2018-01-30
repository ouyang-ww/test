/**
 * Migration by weouyang on 09/05/2017.
 */
public class testOuyang {
    public testOuyang() {
        System.out.println("class name :" + this.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        new testOuyang.tst2();
        System.out.println("tst2 class name:" + testOuyang.tst2.class.getSimpleName());
        try {
            System.out.println(Class.forName("tst2").getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static final class tst2 extends testOuyang {
        public tst2() {
            super();
        }
    }
}
