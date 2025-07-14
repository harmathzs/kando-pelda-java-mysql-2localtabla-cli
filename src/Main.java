import java.util.*;

public class Main {
    static boolean isRunningTest = false;

    static int choice = 7;
    static List<Dog> dogs = new ArrayList<>();
    static List<Owner> owners = new ArrayList<>();

    public static void refreshData() {
        dogs = MysqlService.queryDogs("localhost", "dogs_and_owners", "root", "", Collections.emptySet());
        owners = MysqlService.queryOwners("localhost", "dogs_and_owners", "root", "", Collections.emptySet());
    }

    public static void showData() {
        // dogs
        System.out.println("Dogs:");
        System.out.println("------");
        for (Dog dog: dogs) {
            System.out.println("id: "+dog.getId()
                    +"; name: "+dog.getName()
                    +"; age: "+dog.getAge()
                    +"; male: "+dog.isMale()
                    +"; owner: "+ (dog.getOwner()==null ? "-" : dog.getOwner().getName())
            );
        }
        System.out.println("------");

        // dog_owners
        System.out.println("Owners:");
        System.out.println("------");
        for (Owner owner: owners) {
            System.out.println("id: "+owner.getId()+"; name: "+owner.getName());
        }
        System.out.println("------");
    }

    public static void showMenu() {
        System.out.println("Menu");
        System.out.println("-----");
        System.out.println("1. Insert new Owner");
        System.out.println("2. Insert new Dog");
        System.out.println("3. Update existing Owner");
        System.out.println("4. Update existing Dog");
        System.out.println("5. Delete existing Owner");
        System.out.println("6. Delete existing Dog");
        System.out.println("7. Exit program");
        System.out.println("-----");
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        boolean quit = false;
        do {
            refreshData();
            showData();
            showMenu();
            System.out.print("Make your choice: ");
            if (!isRunningTest) choice = Integer.parseInt(stdin.nextLine());
            switch (choice) {
                case 1: {
                    System.out.print("New owner name: ");
                    String ownerName = stdin.nextLine();
                    Owner[] owners = new Owner[1];
                    owners[0] = new Owner(null, ownerName);
                    MysqlService.upsertOwners("localhost", "dogs_and_owners", "root", "", owners);
                    break;
                }
                case 2: {
                    System.out.print("New dog name: ");
                    String dogName = stdin.nextLine();
                    System.out.print("Age: ");
                    float dogAge = Float.parseFloat(stdin.nextLine());
                    System.out.print("Male (y/n): ");
                    boolean dogMale = stdin.nextLine().equalsIgnoreCase("y");
                    System.out.print("Owner id: ");
                    Integer ownerId = Integer.parseInt(stdin.nextLine());
                    Owner dogOwner = null;
                    for (Owner owner: owners) {
                        if (Objects.equals(owner.getId(), ownerId)) {
                            dogOwner = owner;
                        }
                    }
                    Dog[] dogs = new Dog[1];
                    dogs[0] = new Dog(null, dogName, dogAge, dogMale, dogOwner);
                    MysqlService.upsertDogs("localhost", "dogs_and_owners", "root", "", dogs);
                    break;
                }
                case 3: {
                    System.out.print("Update owner. Id? ");
                    int ownerId = Integer.parseInt(stdin.nextLine());
                    System.out.print("New owner name: ");
                    String ownerName = stdin.nextLine();
                    Owner[] owners = new Owner[1];
                    owners[0] = new Owner(ownerId, ownerName);
                    MysqlService.upsertOwners("localhost", "dogs_and_owners", "root", "", owners);
                    break;
                }
                case 4: {

                    break;
                }
                case 5: {
                    System.out.print("Delete owner. Id? ");
                    int ownerId = Integer.parseInt(stdin.nextLine());
                    MysqlService.deleteOwners("localhost", "dogs_and_owners", "root", "", new HashSet<>(List.of(ownerId)));
                    break;
                }
                case 6: {
                    System.out.print("Delete dog. Id? ");
                    int dogId = Integer.parseInt(stdin.nextLine());
                    MysqlService.deleteDogs("localhost", "dogs_and_owners", "root", "", new HashSet<>(List.of(dogId)));
                    break;
                }
                case 7: {

                    break;
                }
            }
            quit = choice == 7;
        } while (!quit);
    }
}