import java.sql.*;

public class MysqlService {
    public static void getDogsAndOwners(String dbname, String username, String pass) {
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
                int dog_id = rs.getInt("dog_id");
                String dog_name = rs.getString("dog_name");
                int owner_id = rs.getInt("owner_id");
                String owner_name = rs.getString("owner_name");
                System.out.println("dog_id: " + dog_id + ", dog_name: " + dog_name + ", owner_id: " + owner_id + ", owner_name: " + owner_name);
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
