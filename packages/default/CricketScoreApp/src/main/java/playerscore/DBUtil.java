package playerscore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

	public class DBUtil {

	public static Connection dbConnection = null;

	/**
	 * @return Connection
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getDbConnection() throws SQLException, ClassNotFoundException {
		if (dbConnection == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
//			dbConnection =DriverManager.getConnection(  
//					"jdbc:mysql://localhost:3306/socialTest","root","ZohoTest@24");
//			
			dbConnection = DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12376419", "sql12376419",
					"EGna6TPrhl");

		}

		return dbConnection;
	}

	/**
	 * @Desc get MetaData
	 * @param rs
	 * @throws SQLException
	 */
	public static HashMap<String, String> getValueFromResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		HashMap<String, String> valueToBeRead = new HashMap<String, String>();

		while (rs.next()) {
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				String name = rsmd.getColumnName(i);

				valueToBeRead.put(name, rs.getString(i));

			}
		}
		return valueToBeRead;

	}
	
	/**
	 * @Desc get MultiValues
	 * @param rs
	 * @throws SQLException
	 */
	public static ArrayList<HashMap<String,String>>  getMultiValueFromResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		ArrayList<HashMap<String,String>> valueToBeRead = new ArrayList<HashMap<String,String>> ();

		while (rs.next()) {
			HashMap<String,String> temp=new HashMap<String,String>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++)
			{
				String name = rsmd.getColumnName(i);
				
				temp.put(name,  rs.getString(i));
			}
			valueToBeRead.add(temp);
		}
		return valueToBeRead;

	}

	/**
	 * @param query
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static HashMap<String, String> executeQueryForRead(String query)
			throws SQLException, ClassNotFoundException {
		Statement stmt = null;
		ResultSet rs = null;
		HashMap<String, String> valueRead = null;
		try {
			stmt = getDbConnection().createStatement();
			rs = stmt.executeQuery(query);

			valueRead = getValueFromResultSet(rs);

		} catch (Exception e) {
			e.printStackTrace();
			e.getStackTrace();
		} finally {
			rs.close();
			stmt.close();

		}
		return valueRead;
	}
	
	/**
	 * @param query
	 * @return ArrayList<HashMap<String, String>>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<HashMap<String, String>> executeQueryForReadMultiValues(String query)
			throws SQLException, ClassNotFoundException {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String, String>> valueRead = null;
		try {
			stmt = getDbConnection().createStatement();
			rs = stmt.executeQuery(query);

			valueRead = getMultiValueFromResultSet(rs);

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			rs.close();
			stmt.close();

		}
		return valueRead;
	}

//	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException
//	{
//		
//		Utility util=new Utility();
//		String[] playerName= {"ste","David Warner","Virat Kohli","Rohit Sharma","Babar Azam","Ross Taylor","Faf Duplessis","Quinton Deckock","Joe Root","Aaron Finch","Ab Devillers"};
////		String[] quotes= {"",
////				"If people are given quality stuff to watch, they'll watch it.",
////				"I like people who don’t need everyone to like them.",
////				"You need to realise that you must have something to aim for, something to drive you.",
////				"I try to learn with experience. This is my process of learning, so I try my 100 per cent.",
////				"I think I have been happy with what I have achieved to date. Test cricket and cricket in general as a batter, you go through a lot of ups and downs and that's definitely what I have been through, and as a team as well",
////				"If you say something towards me, I will say something back. It doesn’t cause me to concentrate less.",
////				"I'm never going to be the head boy. I'm a little outside the box and I enjoy being a little different to people",
////				"We tried not to be too greedy, and to play smart cricket and take it deep.",
////				"We set a realistic vision on the playoffs.",
////				"You live for those pressure moments. Through an international career, you have ups and downs, but you always feel you are going to be tested in moments like that. It has taken me years to feel comfortable and to feel like I have good composure in those situations."
////		};
//		for(int i=1;i<=10;i++)
//		{
////		System.out.println("INSERT INTO ImageData (imageid, playerid,  imageData) VALUES (1, 1,\""+util.convertBase64FromImagePath("/home/local/ZOHOCORP/athithan-7130/convertImage/1.jpg")+"\")");
////		executeQueryForInsertion("INSERT INTO ImageData (imageid, playerid,  imageDataValue) VALUES ("+i+", "+i+",\""+util.convertBase64FromImagePath("/home/local/ZOHOCORP/athithan-7130/convertImage/"+i+".jpg")+"\")");
//			
////			System.out.println("INSERT INTO playerData (playerId, playerName) VALUES ("+i+",\""+playerName[i]+"\")");
////			executeQueryForInsertion("INSERT INTO PlayerData (playerId, playerName) VALUES ("+i+",\""+playerName[i]+"\")");
////			executeQueryForInsertion("INSERT INTO QuotesData (quotesId, playerId,quoteContent) VALUES ("+i+","+i+",\""+quotes[i]+"\")");
////			System.out.println("INSERT INTO QuotesData (quotesId, playerId,quoteContent) VALUES ("+i+","+i+",\""+quotes[i]+"\")");
//			
//			HashMap<String,String> result=executeQueryForRead("select ImageData.imageDataValue from QuotesData join PlayerData on QuotesData.playerId=PlayerData.playerId join ImageData on ImageData.playerId=PlayerData.playerId where playerName=\""+playerName[i]+"\"");
//			util.convertToImage(result.get("imageDataValue"), playerName[i]);
//		}
////		System.out.println("INSERT INTO ImageData (imageid, playerid, imageType, imageData) VALUES (1, 1, ,"/"");
////		executeQueryForInsertion("INSERT INTO ImageData (imageid, playerid,  imageData) VALUES (1, 1, ,"+util.convertBase64FromImagePath("/home/local/ZOHOCORP/athithan-7130/convertImage/1.jpg"));
//	}

	/**
	 * @param query
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void executeQueryForInsertion(String query) throws SQLException, ClassNotFoundException {
		Statement stmt = null;
		try {
			stmt = getDbConnection().createStatement();
			stmt.executeUpdate(query);
		} finally {
			stmt.close();
		}

	}

	/**
	 * @Desc Close Db Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void closeDbConnection() throws ClassNotFoundException, SQLException {
		getDbConnection().close();
	}
}
