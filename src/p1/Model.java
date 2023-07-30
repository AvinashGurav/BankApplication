package p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleDriver;

public class Model {


	private String pwd;
	private String custid;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;
	private String accno;
	private String balance;
	private String email;
	private String newpwd;
	private String tamt;
	private ArrayList al;
	private Object session;
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	public String getTamt() {
		return tamt;
	}
	public void setTamt(String tamt) {
		this.tamt = tamt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void connectDataBase()
	{
		try
		{
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("Driver loaded successfully");
			con=DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE","system","system");
			System.out.println("connection established");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public boolean login()
	{
		String s="SELECT * FROM BANK WHERE CUSTID=? AND PASSWORD=?";
		try
		{
			pstmt=con.prepareStatement(s);
			pstmt.setString(1,custid);
			pstmt.setString(2,pwd);
			res=pstmt.executeQuery();
			if(res.next()==true)
			{
				setAccno(res.getString(2));
				return true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
public boolean checkBalance()
{
	String s="SELECT * FROM BANK WHERE ACCNO=?";
	try
	{
	pstmt=con.prepareStatement(s);	
	pstmt.setString(1,accno);
	res=pstmt.executeQuery();
	if(res.next()==true)
	{
		setBalance(res.getString("BALANCE"));
		return true;	
	}
	}
	catch(SQLException e)
	{
		e.printStackTrace();
		
	}
	return false;
}
boolean changePassword()
{
	try {
		String s="UPDATE BANK SET PASSWORD=? WHERE ACCNO=?";
		pstmt=con.prepareStatement(s);
		pstmt.setString(1,newpwd);
		pstmt.setString(2,accno);
        int status=pstmt.executeUpdate();
        if(status==0)
        {
        	return false;
        }
        
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return true;
}
boolean transfer()
{	
	String s="UPDATE BANK SET BALANCE=BALANCE-? WHERE ACCNO=? ";
	try 
	{
		pstmt=con.prepareStatement(s);
        pstmt.setString(1,tamt);
        pstmt.setString(2,accno);
        int status=pstmt.executeUpdate();  
        if(status==0)
        {
        	return false;
        	
        }
	}
	
    catch(Exception e)
        {
        	e.printStackTrace();
        
	    }
        return true;
	}
public void statement()
{
String s1="INSERT INTO BANKSTATEMENT VALUES(?,?)";
try
{
	pstmt=con.prepareStatement(s1);
	pstmt.setString(1,accno);
	pstmt.setString(2,tamt);
	pstmt.executeQuery();
}
catch(Exception e)
{
	e.printStackTrace();
}
}
   public ArrayList getStatement()
   {
	 String s="SELECT * FROM BANKSTATEMENT WHERE ACCNO=?";
	 try
	 {
		pstmt=con.prepareStatement(s);
		pstmt.setString(1, accno);
		res=pstmt.executeQuery();
		al=new ArrayList();
		while(res.next()==true)
		{
		al.add(res.getString(2));
	   }
	 }
	 catch(Exception e)
	 {
	   e.printStackTrace();
	 }
	 return al;
   }
   boolean resetPwd()
   {
	   try
	   {
      String s="UPDATE BANK SET PWD=? WHERE EMAIL=?";
     pstmt=con.prepareStatement(s);
     pstmt.setString(1, pwd);
     pstmt.setString(2, email);
     int status=pstmt.executeUpdate();
     if(status==0)
     {
      return false;
     }
	   }
	   catch(Exception e)
	   {
		e.printStackTrace();   
	   }
	   return true;
   }

	
	}
	

	







