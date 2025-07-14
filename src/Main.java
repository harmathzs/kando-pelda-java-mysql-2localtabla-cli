import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static boolean isRunningTest = false;
    public static int choice = 7;

    public static void showData() {
        // dogs
        List<Dog> dogs = MysqlService.queryDogs("localhost", "dogs_and_owners", "root", "", Collections.emptySet());
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
        List<Owner> owners = MysqlService.queryOwners("localhost", "dogs_and_owners", "root", "", Collections.emptySet());
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
            showData();
            showMenu();
            System.out.print("Make your choice: ");
            if (!isRunningTest) choice = Integer.parseInt(stdin.nextLine());
            switch (choice) {
                case 1: {
                    System.out.print("New owner name: ");
                    String ownerName = stdin.nextLine();
                    Owner newOwner = new Owner(null, ownerName);
                    break;
                }
                case 2: {

                    break;
                }
                case 3: {

                    break;
                }
                case 4: {

                    break;
                }
                case 5: {

                    break;
                }
                case 6: {

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