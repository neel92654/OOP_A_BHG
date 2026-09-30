import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {
    @NotBlank
    private String name;

    @NotBlank
    @MaxLength(10)
    private String username;

    @NotBlank
    @MaxLength(20)
    private String password;

    public SignupForm(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}

public class FormValidator {

    public static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object val = field.get(obj);
                String value = (val == null) ? null : val.toString();

                if (field.isAnnotationPresent(NotBlank.class)) {
                    if (value == null || value.trim().isEmpty()) {
                        errors.add(field.getName() + " must not be blank");
                    }
                }

                if (field.isAnnotationPresent(MaxLength.class)) {
                    int max = field.getAnnotation(MaxLength.class).value();
                    if (value != null && value.length() > max) {
                        errors.add(field.getName() + " must be at most " + max + " characters (current length: " + value.length() + ")");
                    }
                }
            } catch (IllegalAccessException e) {
                errors.add("Error accessing field " + field.getName());
            }
        }

        return errors;
    }

    public static void main(String[] args) {
        System.out.println("--- Validating Invalid SignupForm ---");
        SignupForm invalidForm = new SignupForm("", "superlongusername12345", "secretPass");
        List<String> errors = validate(invalidForm);

        for (String err : errors) {
            System.out.println("Validation Error: " + err);
        }

        System.out.println("\n--- Validating Valid SignupForm ---");
        SignupForm validForm = new SignupForm("Riya", "riya_07", "myPassword123");
        List<String> validErrors = validate(validForm);
        if (validErrors.isEmpty()) {
            System.out.println("Form is valid! No errors found.");
        } else {
            for (String err : validErrors) {
                System.out.println("Validation Error: " + err);
            }
        }
    }
}