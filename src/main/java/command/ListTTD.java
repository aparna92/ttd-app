package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.ttd;
import connectionprovider.ConnectionProvider;

public class ListTTD {
	
	public ArrayList<ttd> execute() {
		ArrayList<ttd> ret = new ArrayList<ttd>();
		try {
			Connection connection = ConnectionProvider.getConnection();;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ttd");
			while (rs.next()) {
				ttd e = new ttd();
				e.setId(rs.getInt("id"));
				e.setTitle(rs.getString("title"));
				e.setDes(rs.getString("des"));
				ret.add(e);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	public static void main(String args[])
	{
		
		
		ListTTD list=new ListTTD();
		ArrayList<ttd> demo = list.execute();
		
		for(ttd li : demo){
			System.out.println(li.getTitle());
			System.out.println(li.getDes());
		
		}
		
	}

}
