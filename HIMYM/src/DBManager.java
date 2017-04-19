import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBManager {

	private static DBManager instance = null;
	private Connection conn;

	public DBManager() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/ec_logisticsdb", "rasp", "testdb");

	}

	public static DBManager Instance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	public ArrayList<Episoden> getStaffel(int numb) throws SQLException {

		ArrayList<Episoden> episoden = new ArrayList<Episoden>();
		PreparedStatement stmt = null;
		String sql;
		ResultSet rs = null;

		try {
			sql = "Select * from season_1";
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			stmt.close();

			while (rs.next()) {

				if (rs.getInt("number") == numb) {
					Episoden e = new Episoden();

					e.setEpisodenNR(rs.getInt("number"));
					e.setDescribtion(rs.getString("description"));
					e.setName(rs.getString("name"));
					episoden.add(e);
				}
			}

			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return episoden;
	}

}
