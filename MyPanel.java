import javax.swing.*;/** A JPanel that can set bounds when being initilized*/
public class MyPanel extends JPanel{
	int x;
	int y;
	int width;
	int height;/** Set the location of the panel*/
	public MyPanel(int x,int y,int width,int height){
		setBounds(x,y,width,height);}
}