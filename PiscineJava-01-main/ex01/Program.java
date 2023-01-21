public class Program {


    public static void main(String[] args) {
        UserIdsGenerator userIdsGenerator = UserIdsGenerator.getInstance();
        UserIdsGenerator userIdsGenerator1 = UserIdsGenerator.getInstance();

        System.out.println(userIdsGenerator.generateId());
        System.out.println(userIdsGenerator1.generateId());
        System.out.println(userIdsGenerator.generateId());

        User oleg = new User("Oleg", 50);
        System.out.println(oleg.getIdentifier());
    }
}
