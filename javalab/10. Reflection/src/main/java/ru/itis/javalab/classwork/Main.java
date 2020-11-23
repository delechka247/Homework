package ru.itis.javalab.classwork;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.javalab.homework.EntityManager;
import ru.itis.javalab.homework.User;

import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final String DB_URL = "jdbc:postgresql://localhost:5432/db_for_reflection";
        final String DB_USERNAME = "postgres";
        final String DB_PASSWORD = "poiuyt09";
        final String DB_DRIVER = "org.postgresql.Driver";

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        EntityManager entityManager = new EntityManager(dataSource);

        //entityManager.createTable("users", User.class);

        //User user = new User(2L, "Иван", "Иванов", true);
        //entityManager.save("users", user);

        //User user = new User(3L, "Петр", "Петров", false);
        //entityManager.save("users", user);

        System.out.println(entityManager.findById("users", User.class, Long.class, 2L));





        /*
//        Class aClass0 = Component.class;
//        Class<?> aClass = Component.class;
//        Class<Component> aClass1 = Component.class;
        Class<?> aClass3 = Class.forName("ru.itis.javalab.classwork.Component");

        Field fields[] = aClass3.getDeclaredFields();
        for (Field field : fields) {
            StringBuilder modifiers = new StringBuilder();

            int modifiersCodes = field.getModifiers();
            if (Modifier.isFinal(modifiersCodes)) {
                modifiers.append("final");
                modifiers.append(" ");
            }
            if (Modifier.isPublic(modifiersCodes)) {
                modifiers.append("public");
                modifiers.append(" ");
            }
            if (Modifier.isPrivate(modifiersCodes)) {
                modifiers.append("private");
                modifiers.append(" ");
            }
            if (Modifier.isStatic(modifiersCodes)) {
                modifiers.append("static");
                modifiers.append(" ");
            }

            System.out.println(modifiers.toString() + field.getType().getSimpleName() + " " + field.getName());
        }

        Method methods[] = aClass3.getDeclaredMethods();
        for (Method method : methods) {
            StringBuilder modifiers = new StringBuilder();

            int modifiersCodes = method.getModifiers();
            if (Modifier.isFinal(modifiersCodes)) {
                modifiers.append("final");
                modifiers.append(" ");
            }
            if (Modifier.isPublic(modifiersCodes)) {
                modifiers.append("public");
                modifiers.append(" ");
            }
            if (Modifier.isPrivate(modifiersCodes)) {
                modifiers.append("private");
                modifiers.append(" ");
            }
            if (Modifier.isStatic(modifiersCodes)) {
                modifiers.append("static");
                modifiers.append(" ");
            }

            StringBuilder parametersOfMethod = new StringBuilder();

            Parameter parameters[] = method.getParameters();

            for (Parameter parameter : parameters) {
                parametersOfMethod.append(parameter.getType().getSimpleName())
                        .append(" ").append(parameter.getName()).append(",");
            }
            System.out.println(modifiers.toString() + method.getReturnType().getSimpleName() + " " + method.getName()
                    + "(" + parametersOfMethod.toString() + ")");
        }

//        Constructor constructors[] = aClass3.getConstructors();

        Object object = aClass3.newInstance();

        Constructor<Component> constructor = (Constructor<Component>) aClass3.getConstructor(int.class, int.class);
        Component component = constructor.newInstance(15, 20);
        System.out.println(component.getPublicField());

        Method method = aClass3.getMethod("getSumOfFields", int.class);
        Object result = method.invoke(component, 100);
        System.out.println(result);

        Field field = aClass3.getDeclaredField("privateField");
        field.setAccessible(true);
        field.setInt(component, 155);
        System.out.println(component.getPrivateField());

         */
    }
}
