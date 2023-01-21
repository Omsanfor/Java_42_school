package app;

import classes.Car;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public class Program {
    private static Scanner scanner = new Scanner(System.in);
    private static List<? extends Class<?>> allClassesFrom;
    private static Class<?> selectClass;
    private static Object obj;
    private static List<String> methodNames = new ArrayList<>();



    public static void main(String[] args){
        System.out.println("Classes:");
        allClassesFrom = getAllClassesFrom("classes");
        for (Class <?> a : allClassesFrom) {
            System.out.println(a.getSimpleName());
        }
        System.out.println("---------------------");
        try { selectClass = selectClass(); } catch (Exception e) { System.out.println(e.getMessage()); System.exit(-1); }
        System.out.println("---------------------");
        getFieldsAndMethods();
        System.out.println("---------------------");
        try { createObj(); } catch (Exception e) { System.out.println("Ошибка конструктора"); System.exit(-1); }
        System.out.println("---------------------");
        try { changeField(); } catch (IllegalAccessException | InputMismatchException e) { System.out.println("Не удалось изменить поле"); System.exit(-1); }
            catch (NoSuchElementException e) {System.out.println("Не удалось изменить поле"); System.exit(-1);}
        System.out.println("---------------------");
        try { callMethod(); } catch (InvocationTargetException | IllegalAccessException | ArrayIndexOutOfBoundsException | NoSuchElementException e) { System.out.println("Не удалось выполнить метод"); System.exit(-1); }

    }

    private static void callMethod() throws InvocationTargetException, IllegalAccessException, InputMismatchException {
        System.out.println("Enter name of the method for call:");
        String methodName;
        int index = -1;
        int i = 0;
        while (scanner.hasNext()) {
            methodName = scanner.nextLine();
            if (methodName.length() == 0)
                continue;
            for(String name : methodNames) {
                if (name.equals(methodName)) {
                    index = i;
                    break;
                }
                i++;
            }
            if (index > 0)
                break;
        }
        Method[] declaredMethods = selectClass.getDeclaredMethods();
        Class<?>[] parameterTypes = declaredMethods[index].getParameterTypes();
        List<Object> paramMethod = new ArrayList<>();
        for(int j = 0; j < parameterTypes.length; j++) {
            System.out.println("Enter " + parameterTypes[j].getSimpleName() + " value:");
            paramMethod.add(getParamConstructor(parameterTypes[j].getSimpleName()));
        }
        Object invoke = declaredMethods[index].invoke(obj, paramMethod.toArray());
        if (invoke != null)
            System.out.println("Method returned: " + invoke);
    }

    private static void changeField() throws IllegalAccessException, NoSuchElementException {
        String fieldName = null;
        System.out.println("Enter name of the field for changing:");
        while(scanner.hasNext()) {
            fieldName = scanner.nextLine();
            for(Field a : selectClass.getDeclaredFields()) {
                if (fieldName.equals(a.getName())) {
                    a.setAccessible(true);
                    System.out.println("Enter " + a.getType().getSimpleName() + " value: ");
                    a.set(obj, getParamConstructor(a.getType().getSimpleName()));
                    System.out.println("Object updated: " + obj);
                    return;
                }

            }
        }
        if (fieldName == null) {
            throw new IllegalAccessException();
        }
    }

    private static void createObj() throws Exception {
        System.out.println("Let's create an object.");
        List<Object> paramConstructor = new ArrayList<>();
        for (Constructor<?> c : selectClass.getDeclaredConstructors()) {
            if (c.getParameterCount() > 0) {
                for (Parameter param : c.getParameters()) {
                    System.out.println(param.getName() + ":");
                    paramConstructor.add(getParamConstructor(param.getType().getSimpleName()));
                }
                obj = c.newInstance(paramConstructor.toArray());
                System.out.println("Object created: " + obj);
                break;
            }
        }
    }

    private static Object getParamConstructor( String type){
        switch (type.toLowerCase(Locale.ROOT)) {
            case "string":
                return scanner.nextLine();
            case "int":
            case "integer":
                return scanner.nextInt();
            case "long":
                return scanner.nextLong();
            case "double":
                return scanner.nextDouble();
            case "float":
                return scanner.nextFloat();
            case "char":
            case "character":
                return scanner.next();
            default:
                throw new RuntimeException("Unrecognized type");
        }
    }

    private static void getFieldsAndMethods() {
        System.out.println("fields :");
        StringBuilder stringBuilder = new StringBuilder();
        for(Field a : selectClass.getDeclaredFields()) {
            System.out.print("\t" + a.getType().getSimpleName() + " ");
            System.out.println(a.getName());
        }
        System.out.println("methods:");
        for(Method a : selectClass.getDeclaredMethods()) {
            System.out.print("\t" + a.getReturnType().getSimpleName() + " ");
            stringBuilder.append(a.getName() + "(");
            Class<?>[] parameterTypes = a.getParameterTypes();
            for(int i = 0; i < parameterTypes.length; i++) {
                stringBuilder.append(parameterTypes[i].getSimpleName());
                if (i < parameterTypes.length - 1)
                    stringBuilder.append(", ");
            }
            stringBuilder.append(")");
            System.out.println(stringBuilder.toString());
            methodNames.add(stringBuilder.toString());
            stringBuilder = new StringBuilder();
        }
        System.out.println();
    }


    private static Class<?> selectClass() throws Exception {
        System.out.println("Enter class name:");
        String nameClass;
        while(scanner.hasNext()) {
            nameClass = scanner.nextLine();
            for (Class <?> a : allClassesFrom) {
                if (nameClass.equals(a.getSimpleName()))
                    return a;
            }
        }
        throw new Exception("Поле не может быть null");
    }


    private static List<? extends Class<?>> getAllClassesFrom(String packageName) {
        List<? extends Class<?>> classes = new Reflections(packageName, new SubTypesScanner(false)).getAllTypes().stream().map(a -> {
            try {
                return Class.forName(a);
            } catch (ClassNotFoundException e) {
                return null;
            }
        }).filter(a -> Objects.nonNull(a) && !(a.getSimpleName().equals("Object")))
                .collect(Collectors.toList());
        return classes;
    }
}
