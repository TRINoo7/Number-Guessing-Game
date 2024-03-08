import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.lang.model.util.ElementScanner6;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Client
{

8

private ObjectOutputStream output;
private ObjectInputStream input;
private String message="";
private String serverIP;
private Socket connection; // for client using TCP communication
private int port = 8888;
private int cnt=0;
public Client(String s)
{
serverIP=s;
startcon();
startFrame();
}
public void startcon()
{
try
{
try
{
connection = new Socket(InetAddress.getByName(serverIP),port);
}catch(IOException ioEception)
{
JOptionPane.showMessageDialog(null,"Server Might Be
Down!","Warning",JOptionPane.WARNING_MESSAGE);
}

output = new ObjectOutputStream(connection.getOutputStream());
output.flush();
input = new ObjectInputStream(connection.getInputStream());
}
catch(IOException ioException)
{
ioException.printStackTrace();
}

9

}
public void startFrame()
{
JFrame f=new JFrame("Range of Game");
JLabel l;
JButton b1,b2,b3;
l=new JLabel("SET THE DIFFICULTY FOR YOUR GAME");
Font fon=new Font("Times New Roman",Font.BOLD,35);
l.setForeground(Color.WHITE);
l.setFont(fon);
l.setHorizontalAlignment(JLabel.CENTER);
b1=new JButton("EASY");
b2=new JButton("MEDIUM");
b3=new JButton("HARD");
b1.setFont(new Font("Serif",Font.PLAIN,35));
b2.setFont(new Font("Serif",Font.PLAIN,35));
b3.setFont(new Font("Serif",Font.PLAIN,35));
b1.setBackground(Color.ORANGE);
b2.setBackground(Color.ORANGE);
b3.setBackground(Color.ORANGE);
l.setBounds(0,65,1000,85);
b1.setBounds(250,220,500,110);
b2.setBounds(250,380,500,110);
b3.setBounds(250,540,500,110);
f.add(l);
f.add(b1);
f.add(b2);
f.add(b3);
f.setSize(1000,800);
f.setLayout(null);
f.setLocationRelativeTo(null);
f.setVisible(true);
f.getContentPane().setBackground(Color.BLUE);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
b1.addActionListener(new ActionListener()
{

10
public void actionPerformed(ActionEvent e)
{
String message="1";
try
{
output.writeObject(message);
gamestarts();
}
catch(IOException ioException)
{
ioException.printStackTrace();
}
}
}
);
b2.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
String message="2";
try
{
output.writeObject(message);
gamestarts();
}
catch(IOException ioException)
{
ioException.printStackTrace();
}
}
}
);
b3.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
String message="3";

11

try
{
output.writeObject(message);
gamestarts();
}
catch(IOException ioException)
{
ioException.printStackTrace();
}
}
}
);
}
public void gamestarts()
{
JFrame f1=new JFrame("Guess your way outt");
JLabel l1=new JLabel("GUESS THE NUMBER!!!");
JTextField ta=new JTextField();
JTextField ta1=new JTextField();
JTextField gn=new JTextField();
JButton b4=new JButton("Submit");
l1.setBounds(0,30,1000,105);
ta.setBounds(250,180,500,60);
ta1.setBounds(250,240,500,60);
gn.setBounds(350,350,300,120);
b4.setBounds(350,520,300,120);
l1.setFont(new Font("Arial",Font.ITALIC,35));
l1.setHorizontalAlignment(JLabel.CENTER);
l1.setForeground(Color.WHITE);
ta.setFont(new Font("Berlin Sans",Font.PLAIN,20));
ta.setHorizontalAlignment(JTextField.CENTER);
ta.setEditable(false);
ta.setText("Number of guesses left 10");
ta1.setFont(new Font("Berlin Sans",Font.PLAIN,20));
ta1.setHorizontalAlignment(JTextField.CENTER);
ta1.setEditable(false);

12
gn.setFont(new Font("Georgia",Font.BOLD,20));
gn.setHorizontalAlignment(JTextField.CENTER);
b4.setFont(new Font("Serif",Font.PLAIN,35));
b4.setBackground(Color.GRAY);
ta.setBackground(Color.PINK);
ta1.setBackground(Color.PINK);
gn.setBackground(Color.CYAN);
f1.add(l1);
f1.add(ta);
f1.add(ta1);
f1.add(gn);
f1.add(b4);
f1.setSize(1000,800);
f1.getContentPane().setBackground(Color.BLACK);
f1.setLayout(null);
f1.setLocationRelativeTo(null);
f1.setVisible(true);
f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
b4.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
cnt++;
sendMess(gn.getText());
gn.setText("");
String ms=String.valueOf(10-cnt);
String msg="";
ta.setText("Number of guess left "+ms);
try
{
msg=(String) input.readObject();
}
catch(IOException e1)
{
e1.printStackTrace();
}
catch(ClassNotFoundException classNotFoundException)

13

{}
if(msg.equals("1"))
{
JFrame f2=new JFrame("WINNER");
f2.setContentPane(new JLabel(new ImageIcon("D:\\java prog\\CN\\winner 01.gif")));
f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f2.setSize(640,480);
f2.setLocationRelativeTo(null);
f2.setVisible(true);
}
else if(msg.equals("0") || cnt==10)
{
JFrame f2=new JFrame("LOSER");
f2.setContentPane(new JLabel(new ImageIcon("D:\\java prog\\CN\\loser 01.gif")));
f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f2.setSize(500,500);
f2.setLocationRelativeTo(null);
f2.setVisible(true);
}
else
{
ta1.setText("\n"+msg);
}
}
}
);
gn.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
cnt++;
sendMess(gn.getText());
gn.setText("");
String ms=String.valueOf(10-cnt);
String msg="";
ta.setText("Number of guess left "+ms);
try

14

{
msg=(String) input.readObject();
}
catch(IOException e1)
{
e1.printStackTrace();
}
catch(ClassNotFoundException classNotFoundException)
{}
if(msg.equals("1"))
{
JFrame f2=new JFrame("WINNER");
f2.setContentPane(new JLabel(new ImageIcon("D:\\java prog\\CN\\winner 01.gif")));
f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f2.setSize(640,480);
f2.setLocationRelativeTo(null);
f2.setVisible(true);
}
else if(msg.equals("0") || cnt==10)
{
JFrame f2=new JFrame("LOSER");
f2.setContentPane(new JLabel(new ImageIcon("D:\\java prog\\CN\\loser 01.gif")));
f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f2.setSize(500,500);
f2.setLocationRelativeTo(null);
f2.setVisible(true);
}
else
{
ta1.setText("\n"+msg);
}
}
}
);
}
public void sendMess(String s)

15

{
try
{
output.writeObject(s);
output.flush();
}
catch(IOException e)
{
e.printStackTrace();
}
}
public static void main(String args[])
{
Client cs=new Client("127.0.0.1");
}
}
