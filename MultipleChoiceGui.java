import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class MultipleChoiceGui{
	private JFrame myFrame = new JFrame();
	private JPanel myPanel = new JPanel();/**Display the text of first option of current question*/
	private MyButton option1 = new MyButton("Paris",5,20,540,50); /**Display the text of second option of current question*/
	private MyButton option2 = new MyButton("Beijing",5,80,540,50);/**Display the text of third option of current question*/
	private MyButton option3 = new MyButton("london",5,140,540,50);/**Display the text of fourth option of current question*/
	private MyButton option4 = new MyButton("Rio",5,200,540,50);/** Record  the sequence of the answer*/
	private MyButton[] answers = {option3,option2,option4,option1};/** Stands for the easy level*/
	private JRadioButton EasyLevel = new JRadioButton("Easy"); /** Stands for the medium level*/
	private JRadioButton MediumLevel = new JRadioButton("Medium");/** Stands for the difficult level*/
	private JRadioButton DifficultLevel = new JRadioButton("Difficult");/** Reset the score and question*/
	private JButton reset = new JButton("reset score"); /** Display the current score of user*/
	private JLabel score = new JLabel("score=0",SwingConstants.RIGHT); /**Display the current question*/
	private JLabel question = new JLabel("Question 1: What is the capital of France?"); 
	private CardLayout card = new CardLayout();
	private int number=0,attempts=0,scores=0,difficulty=2;/** This method is used for set the basic frame and panel and the buttons within*/
	public void start(){
		JPanel QuestionPanel = new JPanel(); /*This panel can display the question and options and score on frame*/
		JPanel DifficultyPanel = new JPanel(); /* This panel can let user select difficulty level at the first place*/
		myFrame.setSize(600,460);
		myPanel.setLayout(card); /* Set the panel's layout*/ 
		myPanel.setSize(600,460);
		QuestionPanel.setSize(600,460);  /*set the size of frame*/
		QuestionPanel.setLayout(null);
		DifficultyPanel.setSize(600,460);
		DifficultyPanel.setLayout(new GridLayout(0,1,1,1));
		MyPanel p1 = new MyPanel(0,10,590,40); /*set the size of panel and location in frame*/
		question.setFont(MyButton.TypeFace);  /* set the Font of the question label*/
		p1.add(question);
		MyPanel p2 = new MyPanel(17,50,550,320); /*set the size of panel and location in frame*/
		p2.setLayout(null);
		p2.setBorder(BorderFactory.createTitledBorder("Possible Answers: Click one"));
		option1.addActionListener(new OptionListener()); /* add OptionListener to the following buttons */
		option2.addActionListener(new OptionListener());
		option3.addActionListener(new OptionListener());
		option4.addActionListener(new OptionListener());
		EasyLevel.addActionListener(new DifficultyListener()); /* add DifficultyListener to the following buttons */
		MediumLevel.addActionListener(new DifficultyListener());
		DifficultLevel.addActionListener(new DifficultyListener());
		p2.add(option1); /* add the following buttons to panel*/
		p2.add(option2);
		p2.add(option3);
		p2.add(option4);
		DifficultyPanel.add(EasyLevel);
		DifficultyPanel.add(MediumLevel);
		DifficultyPanel.add(DifficultLevel);
		MyPanel p3 = new MyPanel(160,375,240,30); /*set the size of panel and location in frame*/
		reset.addActionListener(new ResetListener()); /* add ResetListener to the button*/
		p3.setLayout(new GridLayout(0,2,8,8)); /*set p3's layout and the horizontal gap and the vertical gap*/
		p3.add(score);
		p3.add(reset);
		QuestionPanel.add(p1); /*add the following panels to the frame*/
		QuestionPanel.add(p2);
		QuestionPanel.add(p3);
		myPanel.add(DifficultyPanel); /* add two small panel to the big panel*/
		myPanel.add(QuestionPanel);
		myFrame.add(myPanel); /* add the big panel to frame*/
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		myFrame.setTitle("Multiple Choice Qusetions");
	}
	/** Main method used to run the whole program*/
	public static void main(String []args){
		MultipleChoiceGui test = new MultipleChoiceGui(); /* Create an object of MultipleChoiceGui class*/
		test.start(); /* run the start method*/
	}
	/** An inner class used to listen the reset button*/
	public class ResetListener implements ActionListener{ 
		ButtonGroup g1= new ButtonGroup();/** This method is trigged when someone click reset button*/
		public void actionPerformed(ActionEvent event) {
			card.previous(myPanel); /* Let the difficulty panel be emerged*/
			number=0;
			g1.add(EasyLevel);/* Clear selection state of three radio buttons*/
			g1.add(MediumLevel);
			g1.add(DifficultLevel);
			g1.clearSelection();
			score.setText("score=0");/* reset score, times of attempts and question number to 0*/
			scores=0;
			attempts=0;
		}
	}
	/** An inner class used to listen option buttons*/
	public class OptionListener implements ActionListener{ 
		/** This method is trigged when someone click option button*/
		public void actionPerformed(ActionEvent event){
			if(number<4){
				QuestionReader questions = new QuestionReader(difficulty); /* Create an object of QuestionReader class*/
				if(event.getSource()== answers[number]){
					number=number+1; /* question number increases when user click the right button*/
					if(attempts==0&&number<=4)
						scores=scores+(difficulty*2); /* add score when user click the right button in his first attempt*/
					if(attempts==1&&number<=4){
						scores=scores+difficulty;/* add score when user click the right button in his first attempt*/
						attempts=0; /* reset times of attempts*/
					}
					score.setText("score="+scores); /* Change score on label text*/
					if(number<4){
						option1.setText(questions.OPTION1[number]); /* set the next problem's question and answer*/
						option2.setText(questions.OPTION2[number]);
						option3.setText(questions.OPTION3[number]);
						option4.setText(questions.OPTION4[number]);
						question.setText(questions.QUESTIONS[number]);
					}
				}
				else{
					attempts=attempts+1; /* add the number of attempts by one*/
					if(attempts==2&&number<3){
						number=number+1; /* set the next problem's question and answer*/
						option1.setText(questions.OPTION1[number]);
						option2.setText(questions.OPTION2[number]);
						option3.setText(questions.OPTION3[number]);
						option4.setText(questions.OPTION4[number]);
						question.setText(questions.QUESTIONS[number]);
						attempts=0; /* Reset the number of attempt to 0*/
					}
				}
				questions = null;
			}
		}
	}/** An Inner class used to listen RadioButtons*/
	public class DifficultyListener implements ActionListener{/** This method is trigged when someone click one of three difficulty buttons*/
		public void actionPerformed(ActionEvent event){
			card.next(myPanel); /* let the question panel be emerged */
			if(event.getSource()==EasyLevel){
				difficulty=1; /* set the difficulty level based on user's selection*/
			}
			else if(event.getSource()==MediumLevel){
				difficulty=2; /* set the difficulty level based on user's selection*/
			}
			else{
				difficulty=3;/* set the difficulty level based on user's selection*/
			}
			QuestionReader questions = new QuestionReader(difficulty); 
			option1.setText(questions.OPTION1[number]); /* set different question and option according to different difficulty*/
			option2.setText(questions.OPTION2[number]);
			option3.setText(questions.OPTION3[number]);
			option4.setText(questions.OPTION4[number]);
			question.setText(questions.QUESTIONS[number]);
			questions = null;
		}
	}
}