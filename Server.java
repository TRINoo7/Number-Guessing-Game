import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
class MyException extends Exception
{
public MyException(String str)
{
super(str);
}
}
public class Server
{
private ObjectOutputStream output;
private ObjectInputStream input;
private Socket connection; // for client using TCP communication
private ServerSocket server; // listen for clients
private int totalClients = 50;
private int port = 8888;
private int randomNum;
public Server()
{
startRunning();
}
public void startRunning()
{
try
{
server=new ServerSocket(port,totalClients);
while(true)
{
try
{
System.out.println("Waiting for connection...");
connection=server.accept();
System.out.println("Connected to "+connection.getInetAddress().getHostName());
output = new ObjectOutputStream(connection.getOutputStream());
output.flush();
input = new ObjectInputStream(connection.getInputStream());
startGame();
}
catch(EOFException eofException)
{
}
}
}
catch(IOException ioException)
{
ioException.printStackTrace();
}
}
private void startGame() throws IOException
{
int range;
String msg="";
try
{
msg=(String) input.readObject();
}
catch(ClassNotFoundException classNotFoundException)
{}
range=Integer.parseInt(msg);
if(range==1)
{
int min=1;
int max=20;
randomNum=(int) (Math.random()*(max-min+1)+min);
}
else if(range==2)
{
int min=1;
int max=50;
randomNum=(int) (Math.random()*(max-min+1)+min);
}
else if(range==3)
{
int min=1;
int max=100;
randomNum=(int) (Math.random()*(max-min+1)+min);
}
//System.out.println(randomNum);
startGuessing();
}
private void startGuessing()
{
int ctr=0;
int gn=0;
while(ctr<10 && randomNum!=gn)
{
String ms="";
try
{
ms=(String) input.readObject();
try
{
gn=Integer.parseInt(ms);
}
catch(NumberFormatException e)
{
JOptionPane.showMessageDialog(null,"Enter a valid integer
value!","Warning",JOptionPane.WARNING_MESSAGE);
}
ctr++;
if(gn>randomNum)
{
String ms1="Number is lesser";
output.writeObject(ms1);
output.flush();
}
else if(gn<randomNum)
{
String ms1="Number is greater";
output.writeObject(ms1);
output.flush();
}
}
catch(IOException e)
{
e.printStackTrace();
}
catch(ClassNotFoundException classNotFoundException)
{}
}
if(randomNum==gn)
{
try
{
String ms="1";
output.writeObject(ms);
output.flush();
}
catch(IOException e)
{
e.printStackTrace();
}
}
else if(randomNum!=gn)
{
try
{
String ms="0";
output.writeObject(ms);
output.flush();
}
catch(IOException e)
{
e.printStackTrace();
}
}
}
public static void main(String args[])
{
Server mys=new Server();
}
}
