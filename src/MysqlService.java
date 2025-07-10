import java.sql.*;
import java.util.*;

public class MysqlService {
    public static Map<String, List<Map<String, Object>>> getDogsAndOwners(String dbname, String username, String pass) {
        Map<String, List<Map<String, Object>>> results = new HashMap<>();
        results.put("dogs", new ArrayList<>());
        results.put("owners", new ArrayList<>());

        String url = "jdbc:mysql://localhost:3306/"+dbname;

        String query = """
            SELECT
              dogs.id AS dog_id,
              dogs.name AS dog_name,
              dogs.age,
              dogs.male,
              dogs.ownerid,
              owners.id AS owner_id,
              owners.name AS owner_name
            FROM
              dogs
            LEFT JOIN
              owners ON dogs.ownerid = owners.id
            
            UNION
            
            SELECT
              dogs.id AS dog_id,
              dogs.name AS dog_name,
              dogs.age,
              dogs.male,
              dogs.ownerid,
              owners.id AS owner_id,
              owners.name AS owner_name
            FROM
              owners
            LEFT JOIN
              dogs ON dogs.ownerid = owners.id
            """;
        // WHERE dogs.id IS NULL;


        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection conn = DriverManager.getConnection(url, username, pass);

            // Create statement and execute query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Process the result set
            while (rs.next()) {
                Map<String, Object> dogMap = new HashMap<>();

                int dog_id = rs.getInt("dog_id");
                dogMap.put("dog_id", dog_id);
                String dog_name = rs.getString("dog_name");
                dogMap.put("dog_name", dog_name);
                float age = rs.getFloat("age");
                dogMap.put("age", age);
                boolean male = rs.getBoolean("male");
                dogMap.put("male", male);
                int ownerid = rs.getInt("ownerid");
                dogMap.put("ownerid", ownerid);

                if (dog_name!=null) results.get("dogs").add(dogMap);

                Map<String, Object> ownerMap = new HashMap<>();

                int owner_id = rs.getInt("owner_id");
                ownerMap.put("owner_id", owner_id);
                String owner_name = rs.getString("owner_name");
                ownerMap.put("owner_name", owner_name);

                if (owner_name!=null) results.get("owners").add(ownerMap);

                //System.out.println("dog_id: " + dog_id + ", dog_name: " + dog_name + ", owner_id: " + owner_id + ", owner_name: " + owner_name);
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return results;
    }

    public static List<Dog> queryDogs(String dbname, String username, String pass, Set<Integer> ids) {
        List<Dog> dogs = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/" + dbname + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        // Build the SQL query
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT d.id AS dog_id, d.name AS dog_name, d.age, d.male, ")
                .append("o.id AS owner_id, o.name AS owner_name ")
                .append("FROM dogs d LEFT JOIN owners o ON d.ownerid = o.id ");

        if (ids != null && !ids.isEmpty()) {
            sb.append("WHERE d.id IN (");
            sb.append(String.join(",", Collections.nCopies(ids.size(), "?")));
            sb.append(")");
        }

        try (
                Connection conn = DriverManager.getConnection(url, username, pass);
                PreparedStatement stmt = conn.prepareStatement(sb.toString())
        ) {
            if (ids != null && !ids.isEmpty()) {
                int idx = 1;
                for (Integer id : ids) {
                    stmt.setInt(idx++, id);
                }
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int dogId = rs.getInt("dog_id");
                String dogName = rs.getString("dog_name");
                float age = rs.getFloat("age");
                boolean male = rs.getBoolean("male");

                int ownerId = rs.getInt("owner_id");
                String ownerName = rs.getString("owner_name");
                Owner owner = null;
                if (!rs.wasNull()) {
                    owner = new Owner(ownerId, ownerName);
                }

                Dog dog = new Dog(dogId, dogName, age, male, owner);
                dogs.add(dog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dogs;
    }

    public static void upsertDogs() {

    }
    public static void upsertOwners() {

    }
    public static void deleteDogs() {

    }
    public static void deleteOwners() {

    }
}
