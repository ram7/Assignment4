package Assignment4;

import java.sql.*;

public class Gateway {

	private Connection connect;
	private Statement message;
	private ResultSet results;
	private ItemInventory itemView;
	private PartInventory partView;
	
	public Gateway(ItemInventory iView){
		this.itemView = iView;
		try{
			connect = DriverManager.getConnection(
					"jdbc:mysql://devcloud.fulgentcorp.com:3306/nqg658",
					"nqg658","u8Wxz1ncYDHB2NxhyoND");
			message = connect.createStatement();
			System.out.println("Connected Successfully to Inventory");
		} catch (SQLException ex){
			System.out.println("Error: "+ex);
		}
	}
	
	public Gateway(PartInventory pView){
		this.partView = pView;
		try{
			connect = DriverManager.getConnection(
					"jdbc:mysql://devcloud.fulgentcorp.com:3306/nqg658",
					"nqg658","u8Wxz1ncYDHB2NxhyoND");
			message = connect.createStatement();
			System.out.println("Connected Successfully to Parts");
		} catch (SQLException ex){
			System.out.println("Error: "+ex);
		}
	}
	
	public void getData(){
		try{
			
			String query = "select * from Inventory";
			results = message.executeQuery(query);
//			System.out.println("Records from Inventory:");
			while(results.next()){
				int invID = results.getInt("ID");
				String part = results.getString("Part");
				String loc = results.getString("Location");
				int quantity = results.getInt("Quantity");
//				System.out.println("Part: "+part+"	"+"Quantity: "+quantity);
			}			
			
			String query2 = "select * from Parts";
			results = message.executeQuery(query2);
//			System.out.println("Records from Parts:");
			while(results.next()){
				int partID = results.getInt("ID");
				String partnum = results.getString("PartNumber");
				String partname = results.getString("PartName");
				String vendor = results.getString("Vendor");
				String unitofQ = results.getString("UnitOfQuantity");
				String expartnum = results.getString("ExternalPartNumber");
//				System.out.println("PartNumber: "+partnum+"	"+"Vendor: "+vendor);
			}
			
		} catch(Exception ex){
			System.out.println(ex);
		}
	}
	

}
