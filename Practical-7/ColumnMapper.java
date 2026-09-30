import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

class Student {
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "course")
    private String course;

    @Column(name = "gpa")
    private double gpa;

    @Override
    public String toString() {
        return "Student { id=" + id + ", name='" + name + "', course='" + course + "', gpa=" + gpa + " }";
    }
}

public class ColumnMapper {

    public static <T> T mapRow(Class<T> clazz, String[] headers, String[] rowData) throws Exception {
        T instance = clazz.getDeclaredConstructor().newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }

            String colName = field.getAnnotation(Column.class).name();
            int colIndex = -1;

            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equalsIgnoreCase(colName)) {
                    colIndex = i;
                    break;
                }
            }

            if (colIndex == -1 || colIndex >= rowData.length) {
                System.out.println("[Warning] Missing column data for field '" + field.getName() + "' (mapped to column '" + colName + "'). Setting default.");
                continue;
            }

            field.setAccessible(true);
            String val = rowData[colIndex];
            Class<?> type = field.getType();

            if (type == int.class || type == Integer.class) {
                field.setInt(instance, Integer.parseInt(val));
            } else if (type == double.class || type == Double.class) {
                field.setDouble(instance, Double.parseDouble(val));
            } else if (type == long.class || type == Long.class) {
                field.setLong(instance, Long.parseLong(val));
            } else if (type == boolean.class || type == Boolean.class) {
                field.setBoolean(instance, Boolean.parseBoolean(val));
            } else {
                field.set(instance, val);
            }
        }

        return instance;
    }

    public static void main(String[] args) {
        String[] headers = {"id", "name", "course"};
        String[] rowData = {"101", "Riya Patel", "Computer Engineering"};

        System.out.println("--- Mapping Row to Student Object ---");
        try {
            Student student = mapRow(Student.class, headers, rowData);
            System.out.println("Mapped Object: " + student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}