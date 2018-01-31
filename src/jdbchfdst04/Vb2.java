package jdbchfdst04;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Vb2 {
    private static final String URL = "jdbc:mysql://localhost/tuincentrum?useSSL=false";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    // Geeft de berekening is alias 'gemiddelde'
    private static final String SELECT_GEMIDDELDE_VERKOOPPRIJS = "select avg(verkoopprijs) as gemiddelde from planten";
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_GEMIDDELDE_VERKOOPPRIJS)) {
            /*
            Deze ResultSet bevat maximaal één record.
            Je geeft dit duidelijk aan door een if te gebruiken in de plaats van een while bij het verwerken van de ResultSet.
            */
            if (resultSet.next()) {
                // Leest de inhoud van de kolom met de alias gemiddelde
                System.out.println(resultSet.getBigDecimal("gemiddelde"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
