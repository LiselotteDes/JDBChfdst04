package jdbchfdst04;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Taak_AantalBierenPerBrouwer {
    private static final String URL = "jdbc:mysql://localhost/bieren?useSSL=false";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String LIJST_BIEREN_PER_BROUWER = 
            "select brouwers.naam, count(*) as aantalbieren"
            + " from brouwers inner join bieren on brouwers.id = bieren.brouwerid"
            + " group by brouwers.id, brouwers.naam"
            + " order by brouwers.naam";
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(LIJST_BIEREN_PER_BROUWER)) {
            // met left join lees je ook de brouwers die nul bieren hebben
            while (resultSet.next()) {
                System.out.println(resultSet.getString("naam") + " " + resultSet.getInt("aantalbieren"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
