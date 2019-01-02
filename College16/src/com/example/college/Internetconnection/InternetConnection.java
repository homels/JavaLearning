package com.example.college.Internetconnection;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import Commonality.CFile;
import Commonality.TransportObject;
import Commonality.User;
import Commonality.UserComment;

public class InternetConnection {
	public InternetConnection()
	{};
	public static User MessageTransmit(Object obj)
	{
	    try 
	    {
	    	Socket socket = new Socket("101.200.38.1",9999); //IP：101.200.38.1,9999
	    	//向服务器发送消息 
	    	ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(new TransportObject(1, obj));
			ObjectInputStream in=new ObjectInputStream(socket.getInputStream());	
            User login_user=(User)in.readObject();
		    //关闭流 
	    	out.close(); in.close(); 
	    	//关闭Socket 
		    socket.close();  
		    return login_user;
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	public static ArrayList<CFile> CFileTransmit(Object obj)
	{
		try 
	    {
	    	Socket socket = new Socket("101.200.38.1",9999); //IP：101.200.38.1,9999
	    	//向服务器发送消息 
	    	ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(new TransportObject(2, obj));
			ObjectInputStream in=new ObjectInputStream(socket.getInputStream());	
			@SuppressWarnings("unchecked")
			ArrayList<CFile> cfile=(ArrayList<CFile>)in.readObject();
		    //关闭流 
	    	out.close(); in.close(); 
	    	//关闭Socket 
		    socket.close();  
		    return cfile;
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	public static ArrayList<UserComment> UserCommentTransmit(Object obj)
	{
		try 
	    {
	    	Socket socket = new Socket("101.200.38.1",9999); //IP：101.200.38.1,9999
	    	//向服务器发送消息 
	    	ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(new TransportObject(3, obj));
			ObjectInputStream in=new ObjectInputStream(socket.getInputStream());	
			@SuppressWarnings("unchecked")
			ArrayList<UserComment> cfile=(ArrayList<UserComment>)in.readObject();
		    //关闭流 
	    	out.close(); in.close(); 
	    	//关闭Socket 
		    socket.close();  
		    return cfile;
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	public static boolean FileUpload(Object obj)
	{
		try 
	    {
	    	Socket socket = new Socket("101.200.38.1",9999); //IP：101.200.38.1,9999
	    	//向服务器发送消息 
	    	ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(new TransportObject(4, obj));
			ObjectInputStream in=new ObjectInputStream(socket.getInputStream());	
			@SuppressWarnings("unchecked")
			boolean succeed=(boolean)in.readObject();
		    //关闭流 
	    	out.close(); in.close(); 
	    	//关闭Socket 
		    socket.close();  
		    return succeed;
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
	}
	public static byte[] FileDownload(Object obj)
	{
		try 
	    {
	    	Socket socket = new Socket("101.200.38.1",9999); //IP：101.200.38.1,9999
	    	//向服务器发送消息 
	    	ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(new TransportObject(5, obj));
			ObjectInputStream in=new ObjectInputStream(socket.getInputStream());	
			@SuppressWarnings("unchecked")
			byte[] buffer=(byte[])in.readObject();
		    //关闭流 
	    	out.close(); in.close(); 
	    	//关闭Socket 
		    socket.close();  
		    return buffer;
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	public static boolean ChangeInformation(Object obj)
	{
		try 
	    {
	    	Socket socket = new Socket("101.200.38.1",9999); //IP：101.200.38.1,9999
	    	//向服务器发送消息 
	    	ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(new TransportObject(6, obj));
			ObjectInputStream in=new ObjectInputStream(socket.getInputStream());	
			boolean succeed=(boolean)in.readObject();
		    //关闭流 
	    	out.close(); in.close(); 
	    	//关闭Socket 
		    socket.close();  
		    return succeed;
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
	}
	public static String RegistInformation(Object obj)
	{
		try 
	    {
	    	Socket socket = new Socket("101.200.38.1",9999); //IP：101.200.38.1,9999
	    	//向服务器发送消息 
	    	ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(new TransportObject(7, obj));
			ObjectInputStream in=new ObjectInputStream(socket.getInputStream());	
			String usernumber=(String)in.readObject();
		    //关闭流 
	    	out.close(); in.close(); 
	    	//关闭Socket 
		    socket.close();  
		    return usernumber;
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	public static boolean AddComment(Object obj)
	{
		try 
	    {
	    	Socket socket = new Socket("101.200.38.1",9999); //IP：101.200.38.1,9999
	    	//向服务器发送消息 
	    	ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(new TransportObject(8, obj));
			ObjectInputStream in=new ObjectInputStream(socket.getInputStream());	
			boolean succeed=(boolean)in.readObject();
		    //关闭流 
	    	out.close(); in.close(); 
	    	//关闭Socket 
		    socket.close();  
		    return succeed;
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
	}
}
