import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

class MyTests {

    @Run
    public void testAddition() {
        System.out.println("  [PASS] testAddition executed successfully");
    }

    @Run
    public void testStringEquality() {
        System.out.println("  [PASS] testStringEquality executed successfully");
    }

    public void helperMethod() {
        System.out.println("  [SKIP] helperMethod should not be executed");
    }

    @Run
    public void testArrayBounds() {
        System.out.println("  [PASS] testArrayBounds executed successfully");
    }
}

public class MiniTestRunner {

    public static void main(String[] args) {
        MyTests testInstance = new MyTests();
        int testsRun = 0;

        System.out.println("--- Running Mini Test Runner ---");

        for (Method method : MyTests.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Run.class) && method.getParameterCount() == 0) {
                try {
                    method.setAccessible(true);
                    method.invoke(testInstance);
                    testsRun++;
                } catch (Exception e) {
                    System.out.println("  [FAIL] Test failed: " + method.getName() + " -> " + e.getMessage());
                }
            }
        }

        System.out.println("--------------------------------");
        System.out.println("Total @Run tests executed: " + testsRun);
    }
}