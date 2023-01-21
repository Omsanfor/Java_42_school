public class Program {
    public static void main(String[] args){
        UsersArrayList arrayList = new UsersArrayList();

        System.out.println(arrayList.getSize());

        arrayList.addUser(new User("Oleg1", 42));

        System.out.println(arrayList.getSize());

        arrayList.addUser(new User("Oleg2", 42));
        arrayList.addUser(new User("Oleg3", 42));
        arrayList.addUser(new User("Oleg4", 42));
        arrayList.addUser(new User("Oleg5", 42));
        arrayList.addUser(new User("Oleg6", 42));
        arrayList.addUser(new User("Oleg7", 42));
        arrayList.addUser(new User("Oleg8", 42));
        arrayList.addUser(new User("Oleg9", 42));
        arrayList.addUser(new User("Oleg10", 42));
        arrayList.addUser(new User("Oleg11", 42));
        arrayList.addUser(new User("Oleg12", 42));
        arrayList.addUser(new User("Oleg13", 42));
        arrayList.addUser(new User("Oleg14", 42));
        arrayList.addUser(new User("Oleg15", 42));
        arrayList.addUser(new User("Oleg16", 42));
        arrayList.addUser(new User("Oleg17", 42));
        arrayList.addUser(new User("Oleg18", 42));
        arrayList.addUser(new User("Oleg19", 42));
        arrayList.addUser(new User("Oleg20", 42));
        arrayList.addUser(new User("Oleg21", 42));
        arrayList.addUser(new User("Oleg22", 42));

        System.out.println(arrayList.getSize());

        System.out.println(arrayList.getUserByIndex(12).getName());
        System.out.println(arrayList.getUserById(17).getName());
    }
}
