import java.sql.*;

public class MysqlService {
    public static void getDogsAndOwners(String dbname, String username, String pass) {
        String url = "jdbc:mysql://localhost:3306/"+dbname;

        String query = "SELECT id, name FROM owners";

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
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
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
