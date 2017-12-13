package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ConnectMysql {
	  public List<String> connectMySqlM(String sql,String listValue,String user,String password,String url) throws SQLException {
		  //String[] arr=null;
		  Driver driver = new com.mysql.jdbc.Driver();
		  Properties info=new Properties();
		  info.put("user", user);
		  info.put("password", password);
		  //String url=url;
		  Connection connect= driver.connect(url, info);
	      Statement stmt = connect.createStatement();
	      ResultSet rs = stmt.executeQuery("select"+"\t"+listValue+"\t"+sql);
	      List<String> list1=new ArrayList<String>();
	      //List<String> split=new ArrayList<String>();
	      if(listValue.contains(",")){
	    	  String []split=listValue.split(",");
	    	  while (rs.next()) {
	    	  for(int j=1;j<=split.length;j++){
	    	  //System.out.println(rs.getString(j));
		
	    	  list1.add(rs.getString(j));
	      }
	    	  }
	      /*
	      if(list1!=null&& list1.size()>=0){
	    	  String[] arr1=new String[list1.size()];
	
	    	  for(int i=0;i<list1.size();i++){
	    		  arr1[i]=list1.get(i);
	    	  }
	    	  arr=arr1;
	    	  System.out.println(list1);
	      }
	      */
	      //return arr;
	      //System.out.println(list1);
	      return list1;
	    	}else{
	    		
		    	  while (rs.next()) {
		    	  
		    	  //System.out.println(rs.getString(1));
			
		    	  list1.add(rs.getString(1));
		      
		    	  }
		    	  return list1;
	    		
	    	}
	  }
	  public static void main(String[] args) throws SQLException{
		  String id="ACC_ON,out,3,in";
		  List<String> imeis=new ArrayList<String>();
		  ConnectMysql conn=new ConnectMysql();
		  imeis=conn.connectMySqlM("FROM alarm_type_configure WHERE id in "+"("+"'"+id+"'"+")", "DISTINCT value_zh", "tuqiang_query", "tuqiang_query", "jdbc:mysql://120.24.75.214:3306/tracker-web");
		System.out.println(imeis.get(1));
		  
	   }
	 }


