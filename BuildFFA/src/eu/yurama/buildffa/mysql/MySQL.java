package eu.yurama.buildffa.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import eu.yurama.buildffa.BuildFFA;
import eu.yurama.buildffa.assets.Source;

public class MySQL
{
	
	public static String host;
	public static String port;
	public static String database;
	public static String username;
	public static String password;
	public static Connection con;

	public static void connect()
	{
		if(!isConnected())
		{
			try {
				long ms = System.currentTimeMillis();
				con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
				long took = System.currentTimeMillis() - ms;
				Bukkit.getConsoleSender().sendMessage(Source.PREFIX+"The connection to the database is §astable§7! §8("+took+"ms)");
			} catch (SQLException e) {
				Bukkit.getConsoleSender().sendMessage(Source.PREFIX+"§cFailed by connecting to the database.");
			}
		}
	}
	
	public static void disconnect()
	{
		if(isConnected())
		{
			try {
				long ms = System.currentTimeMillis();
				con.close();
				long took = System.currentTimeMillis() - ms;
				Bukkit.getConsoleSender().sendMessage(Source.PREFIX+"The connection to the database was §cclosed§7! §8("+took+"ms)");
			} catch (SQLException e) {
				Bukkit.getConsoleSender().sendMessage(Source.PREFIX+"§cFailed by disconnecting to the database.");
			}
		}
	}
	
	
	public static boolean isConnected()
	{
		return (con == null ? false : true);
		
	}
	
	public static void update(String qry)
	{
		try {
			PreparedStatement ps = MySQL.con.prepareStatement(qry);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ResultSet getResult(String qry) 
	{
		try {
		PreparedStatement ps = MySQL.con.prepareStatement(qry);
		return ps.executeQuery();
		} catch(SQLException e) {
		}
		return null;
	}
	
	public static boolean isValueInDatabase(String database, String where, String value)
	{
		try
		{
		    PreparedStatement sql = MySQL.con.prepareStatement("SELECT COUNT(*) FROM "+database+" WHERE "+where+"='"+value+"'");
		    ResultSet rs = sql.executeQuery();
		    rs.first();
		    int numberOfRows = rs.getInt("COUNT(*)");
		    sql.close();
		    if(numberOfRows <= 0)
		    {return false;} else {return true;}
		} catch (Exception e){return true;}
	}
	
	public static boolean isValueInDatabase(String database, String where, String value, String where2, String value2)
	{
		try
		{
		    PreparedStatement sql = MySQL.con.prepareStatement("SELECT COUNT(*) FROM "+database+" WHERE "+where+"='"+value+"' AND "+where2+"='"+value2+"'");
		    ResultSet rs = sql.executeQuery();
		    rs.first();
		    int numberOfRows = rs.getInt("COUNT(*)");
		    sql.close();
		    if(numberOfRows <= 0)
		    {return false;} else {return true;}
		} catch (Exception e){return true;}
	}
	
	public static boolean isValueInDatabase(String database, String where, String value, String where2, String value2, String where3, String value3)
	{
		try
		{
		    PreparedStatement sql = MySQL.con.prepareStatement("SELECT COUNT(*) FROM "+database+" WHERE "+where+"='"+value+"' AND "+where2+"='"+value2+"' AND "+where3+"='"+value3+"'");
		    ResultSet rs = sql.executeQuery();
		    rs.first();
		    int numberOfRows = rs.getInt("COUNT(*)");
		    sql.close();
		    if(numberOfRows <= 0)
		    {return false;} else {return true;}
		} catch (Exception e){return true;}
	}
	
	public static String getString(String whattoget, String database, String where, String value, String where2, String value2)
	{
		String n = "n";
		try
		{
			ResultSet rs  = MySQL.getResult("SELECT `"+whattoget+"` FROM `"+database+"` WHERE `"+where+"`='"+value+"' AND "+where2+"='"+value2+"'");
			while(rs.next()){n= rs.getString(1);}
		} catch(Exception e){}
		return n;
	}
	
	public static String getString(String whattoget, String database, String where, String value)
	{
		String n = "n";
		try
		{
			ResultSet rs  = MySQL.getResult("SELECT `"+whattoget+"` FROM `"+database+"` WHERE `"+where+"`='"+value+"'");
			while(rs.next()){n= rs.getString(1);}
		} catch(Exception e){}
		return n;
	}
	
	public static Integer getInteger(String whattoget, String database, String where, String value)
	{
		Integer n = 0;
		try
		{
			ResultSet rs  = MySQL.getResult("SELECT `"+whattoget+"` FROM `"+database+"` WHERE `"+where+"`='"+value+"'");
			while(rs.next()){n= rs.getInt(1);}
		} catch(Exception e){}
		return n.intValue();
	}
	
	public static void startKeepAlive()
	{
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BuildFFA.getInstance(), new Runnable() 
		{
			@Override
			public void run()
			{
				MySQL.update("CREATE TABLE IF NOT EXISTS buildffa_maps (MAPNAME VARCHAR(99) PRIMARY KEY, LOCATION VARCHAR(99))");
				MySQL.update(
						"CREATE TABLE IF NOT EXISTS buildffa_stats (UUID VARCHAR(99) PRIMARY KEY, KILLS INT(99), DEATHS INT(99), INVENTORY VARCHAR(99))");
			}
		}, 0, 20*50);
	}
	
}
