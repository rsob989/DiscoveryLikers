package pl.project.weekop.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {
	
	private static DataSource dataSource;

	public static DataSource getDataSource() {
		if(dataSource==null) {
			try {
				Context initialContext = new InitialContext();
				Context envContext = (Context)initialContext.lookup("java:comp/env");
				DataSource ds = (DataSource)envContext.lookup("jdbc/weekop");
				dataSource = ds;
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return dataSource;
	}
}
