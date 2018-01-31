package jdbchfdst04;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
/*
1° fout: cursist had geen select rechten voor soorten.
Dus eerst in workbench: "grant select on soorten to cursist"
*/
public class Vb2B {
    private static final String URL = "jdbc:mysql://localhost/tuincentrum?useSSL=false";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String SELECT_PLANTENNAMEN_SOORTNAMEN = 
            "select planten.naam as plantnaam, soorten.naam as soortnaam from planten inner join soorten on planten.soortid = soorten.id";
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_PLANTENNAMEN_SOORTNAMEN)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("plantnaam") + " " + resultSet.getString("soortnaam"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
