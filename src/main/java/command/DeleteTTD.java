package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connectionprovider.ConnectionProvider;

public class DeleteTTD {
	
	
	public boolean execute(int id) {
		
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("DELETE FROM ttd WHERE ID = ?");
			stmt.setInt(1, id);
			
				
				return stmt.execute();
				
			

		} catch (URISyntaxException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
			
		

		
	}

	public static void main(String[] args) {
		DeleteTTD command = new DeleteTTD();
		boolean result =  command.execute(2);
		System.out.println(result);
	}
}
