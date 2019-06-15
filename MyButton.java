import javax.swing.*;
import java.awt.*;/** A JButton that can set bounds when being initilized*/
public class MyButton extends JButton{/** The horizontal coordinate of the beinging point of the button*/
	private int x; /** The vertical coordinate of the beinging point of the button*/
	private int y; /** The width of the button*/
	private int width; /** The height of the button*/
	private int height;/** Text displayed on the button*/
	private String text;/** Initialize value of atrributes*/
	public static Font TypeFace = new Font("Arial",Font.BOLD,20); 
	public MyButton(String text,int x,int y,int width,int height){
		super(text);
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		setButton();
	}/** Set the font and location of the button*/
	public void setButton(){
			setFont(TypeFace);
			setBounds(5,y,540,50);
	}
} 