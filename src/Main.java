import java.util.Collections;
import java.util.List;

public class Main {
    public static void showData() {
        // dogs
        List<Dog> dogs = MysqlService.queryDogs("localhost", "dogs_and_owners", "root", "", Collections.emptySet());
        System.out.println("Dogs:");
        System.out.println("------");
        for (Dog dog: dogs) {
            System.out.println("id: "+dog.getId());
        }
        System.out.println("------");
        // dog_owners
        List<Owner> owners = MysqlService.queryOwners("localhost", "dogs_and_owners", "root", "", Collections.emptySet());
        System.out.println("Owners:");
        System.out.println("------");
        for (Owner owner: owners) {
            System.out.println("id: "+owner.getId());
        }
        System.out.println("------");
    }

    public static void main(String[] args) {
        showData();
    }
}