package application;
import java.util.*;
import java.sql.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;


public class Controller implements Initializable {
	
	public static ArrayList<String> addresses;
	public Text tbox;
	public TextField tField;
	public Circle c1;
	public Circle c2;
	public Circle c3;
	public Circle c4;
	public Circle c5;
	public Circle c6;
	public Circle c7;
	public Circle c8;
	public Circle c9;
	public Circle c10;
	public Label l1;
	public Label l2;
	public Label l3;
	public Label l4;
	public Label l5;
	public Label l6;
	public Label l7;
	public Label l8;
	public Label l9;
	public Label l10;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String ipconfig = "";
		String temp = "";
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "ipconfig");
		builder.redirectErrorStream(true);
		try {
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while(r.readLine()!=null &&  !r.readLine().contains("null")) {
			temp = r.readLine();
			System.out.println(temp);
			if (temp.contains("IPv4 Address") || temp.contains("Default Gateway")){
				ipconfig += temp;
				ipconfig += "\n"; }//if			
		}//while
			}//try
		catch (Exception e) {
			tbox.setText(ipconfig);
			System.out.println(e);
		}//catch
		tbox.setText(ipconfig);
	}
	
	public void labelHover1() {
		getIPInfo(addresses.get(0), l1);
	}//hover
	
	public void labelExit1() {
		l1.setText(addresses.get(0));
	}

	public void labelHover2() {
		getIPInfo(addresses.get(1), l2);
	}//hover
	
	public void labelExit2() {
		l2.setText(addresses.get(1));
	}
	public void labelHover3() {
		getIPInfo(addresses.get(2), l3);
	}//hover
	
	public void labelExit3() {
		l3.setText(addresses.get(2));
	}	
	public void labelHover4() {
		getIPInfo(addresses.get(3), l4);
	}//hover
	
	public void labelExit4() {
		l4.setText(addresses.get(3));
	}	
	public void labelHover5() {
		getIPInfo(addresses.get(4), l5);
	}//hover
	
	public void labelExit5() {
		l5.setText(addresses.get(4));
	}	
	public void labelHover6() {
		getIPInfo(addresses.get(5), l6);
	}//hover
	
	public void labelExit6() {
		l6.setText(addresses.get(5));
	}	
	
	public void labelHover7() {
		getIPInfo(addresses.get(6), l7);
	}//hover
	
	public void labelExit7() {
		l7.setText(addresses.get(6));
	}	
	
	public void labelHover8() {
		getIPInfo(addresses.get(7), l8);
	}//hover
	
	public void labelExit8() {
		l8.setText(addresses.get(7));
	}	
	
	public void labelHover9() {
		getIPInfo(addresses.get(8), l9);
	}//hover
	
	public void labelExit9() {
		l9.setText(addresses.get(8));
	}
	public void labelHover10() {
		getIPInfo(addresses.get(9), l10);
	}//hover
	
	public void labelExit10() {
		l10.setText(addresses.get(9));
	}
	
	public void getIPInfo(String address, Label label) {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:C:/SQLite/whois/whois.db"); 
			System.out.println("SQLite DB connected");
		}
		catch (Exception e) {
			System.out.println(e);}
		String query = "SELECT * from ip WHERE ip_address = '" + address + "';";
		try (Statement stmt = c.createStatement()) {
			      ResultSet rs = stmt.executeQuery(query);
			      while (rs.next()) {
			        String owner = rs.getString("owner");
			        String city = rs.getString("city");
			        String country = rs.getString("country");
			        System.out.println(owner + ", " + city + ", " + country);
					label.setText("IP address: "+address+"/n"+"Owner: "+owner+"/n"+"City: "+city+"/n"+"Country: "+country);
					}//while
			    } //try
				catch (SQLException ex) {
			    System.out.println(ex);
				}//catch
		//set label text
	}//method
		
	public static void main(String[] args) throws Exception {
		Connection c = null;
		

	
			
		}
	public void findRoute() {
		String tracert = "";
		String dest = tField.getText();
		ArrayList<Circle> circles = new ArrayList<Circle>();
		circles.add(c1);
		circles.add(c2);
		circles.add(c3);
		circles.add(c4);
		circles.add(c5);
		circles.add(c6);
		circles.add(c7);
		circles.add(c8);
		circles.add(c9);
		circles.add(c10);
		ArrayList<Label> labels = new ArrayList<Label>();
		labels.add(l1);
		labels.add(l2);
		labels.add(l3);
		labels.add(l4);
		labels.add(l5);
		labels.add(l6);
		labels.add(l7);
		labels.add(l8);
		labels.add(l9);
		labels.add(l10);
		System.out.println("Extracting IPs...");
		tbox.setText("Extracting IPs...");
		addresses = new ArrayList<String>();
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "tracert "+dest);
		builder.redirectErrorStream(true);
		try {
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while(r.readLine()!=null) {
			tracert += r.readLine();
			tracert += "\n";
		}//while
			}//try
		catch (Exception e) {
			System.out.println("Sorry, error.");
		}//catch
		tbox.setText(tracert);
		
		//extract IPs
    	Pattern p = Pattern.compile("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
		Matcher m = p.matcher(tracert);
	       while(m.find()) {
	           addresses.add(m.group());
;	      }
	      addresses.remove(0);
	       
	      //show IPs
	    int len = addresses.size();
	     double x = 14;
	     double y = 299;
	     Circle c; 
	     Label l;
	     
	     for (int i=0; i < len; i++) {
	    	 c = circles.get(i);
	    	 c.setLayoutX(x);
	    	 c.setLayoutY(y);
	    	 c.setFill(Color.DODGERBLUE);
	    	 c.setRadius(8.0);
	    	 c.setStroke(Color.BLACK);
	    	 c.setStrokeType(StrokeType.OUTSIDE);
	    	 l = labels.get(i);
	    	 l.setLayoutX(x-50);
	    	 l.setLayoutY(y+15);
	    	 l.setText(addresses.get(i));
	    	 x += 224/(len-1);
	    	 y = -(277*x/224)+316.3125;
	     }
			
		
	}//findRoute
}
