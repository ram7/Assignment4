package Assignment4;

import java.sql.*;

public class Gateway{

	private Connection connect = null;
	private Statement message = null;
	private ResultSet results = null;
	private ItemInventory itemView;
	private PartInventory partView;
	private String insertItemRow = "INSERT INTO Template_Parts"
			+ "(ID, Part_Number, Part_Name, Quantity) VALUES" 
			+ "(?, ?, ?, ?)";
	private String updateItemRow = "UPDATE Template_Parts SET"
			+ "Part_Number = ?, Part_Name = ?, Quantity = ?" 
			+ "WHERE ID = ?";
	
	
	public Gateway(ItemInventory iView){
		this.itemView = iView;
		try{
			connect = DriverManager.getConnection(
					"jdbc:mysql://devcloud.fulgentcorp.com:3306/nqg658",
					"nqg658","u8Wxz1ncYDHB2NxhyoND");
			message = connect.createStatement();
			System.out.println("Connected Successfully to Template Parts List");
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
			System.out.println("Connected Successfully to Templates");
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
	
	public void addPartDB(int id, String partName, String vendor, int quantity){
		try{
			message = connect.prepareStatement(insertItemRow);
			message.setInt(1, id);
			message.setString(2, partName);
			message.setString(3, vendor);
			message.setInt(4, quantity);
			message.executeUpdate();
			message.close();
		} catch (SQLException ex){
			System.out.println("Error: "+ex);
		}
	}

	public void updatePartDB(int id, String partName, String vendor, int quantity){
		try{
			message = connect.prepareStatement(updateItemRow);
			message.setInt(1, id);
			message.setString(2, partName);
			message.setString(3, vendor);
			message.setInt(4, quantity);
			message.executeUpdate();
			message.close();
		} catch (SQLException ex){
			System.out.println("Error: "+ex);
		}
	}
}
