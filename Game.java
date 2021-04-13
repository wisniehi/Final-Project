import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.util.ArrayList;
import java.io.*;

class Game implements ActionListener {
  JLabel lblWelcome, lblEnterName;
  JTextField field1;
  JLabel lblQuestion;
  JLabel lblScore;
  JButton btnNextQuestion, btnRestart, btnEnterName, btnSubmit;
  JLabel lblPrompt;
  JFrame frame;
  JPanel pnl1, pnl2, pnl3, pnl4, pnl5, pnl6;
  JPanel pnlQuestions, pnlMaster, pnlControl;
  
  ArrayList<Question> questList;
  ArrayList<JButton> answerList;
  int index;
  int score;
  String pName;
    public String questText;
  public String optionA;
  public String optionB;
  public String optionC;
  public String optionD;
  public String correctAns;
  public String points;


  
  
  Game(){
    pName = "";
    index = 0;
    score = 0;
    answerList =  new ArrayList<JButton>();
    questList = new ArrayList<Question>();
   
   try{
     FileReader myFile = new FileReader("trivia.txt");
     BufferedReader reader = new BufferedReader(myFile);
     
     while (reader.ready()){
         questText = reader.readLine();
         optionA = reader.readLine();
         optionB = reader.readLine();
         optionC = reader.readLine();
         optionD = reader.readLine();
         correctAns = reader.readLine();
         points = reader.readLine();
        
                  Question quest = new Question(questText, optionA, optionB, optionC, optionD, Integer.parseInt(correctAns), Integer.parseInt(points));
questList.add(quest);


        }
        reader.close();
      }

     catch (IOException excpt1){
      System.out.println("An error occured: " + excpt1);

    }

   
    
    
    frame = new JFrame("Trivia Game");
    frame.setLayout(new BorderLayout());
    frame.setSize(550,220);
    
    lblWelcome = new JLabel("Welcome to our Game!");
    lblWelcome.setForeground(Color.blue);
    lblEnterName = new JLabel("Enter your Name:  ");
    btnEnterName = new JButton("Enter");
    btnEnterName.setForeground(Color.blue);
    btnEnterName.addActionListener(this);
    field1 = new JTextField(10);
    field1.setActionCommand("myTF");
    field1.addActionListener(this);

    lblQuestion = new JLabel(questList.get(index).getQuestText() + " (Worth " + questList.get(index).getPoints() + " Points)");

    lblPrompt = new JLabel("");


    answerList.add(new JButton(questList.get(index).getOptionA()));
    answerList.add(new JButton(questList.get(index).getOptionB()));
    answerList.add(new JButton(questList.get(index).getOptionC()));
    answerList.add(new JButton(questList.get(index).getOptionD()));

    lblScore = new JLabel("Current Score: " + score,JLabel.CENTER);
    lblScore.setForeground(Color.red);
    
    btnNextQuestion = new JButton("Next Question");
    btnNextQuestion.setForeground(Color.blue);
    btnNextQuestion.addActionListener(this);
    btnRestart = new JButton("Restart");
    btnRestart.setForeground(Color.red);
    btnRestart.addActionListener(this);
    btnSubmit = new JButton("Submit Score");
    btnSubmit.addActionListener(this);
    pnl1 = new JPanel(new FlowLayout());
    pnl2 = new JPanel(new FlowLayout());
    pnl3 = new JPanel(new FlowLayout());
    pnl4 = new JPanel(new FlowLayout());
    pnl5 = new JPanel(new FlowLayout());
    pnl6 = new JPanel(new FlowLayout());
    pnlMaster = new JPanel(new FlowLayout());
    pnl1.add(lblWelcome);
    pnl2.add(lblPrompt);
    pnl2.add(lblEnterName);
    pnl2.setBackground(Color.yellow);
    pnl2.add(field1);
    pnl2.add(btnEnterName);
    pnl3.add(lblQuestion);
    pnl3.setBackground(Color.orange);
      for (int i = 0; i < 4; i++){
        answerList.get(i).addActionListener(this);
        pnl4.add(answerList.get(i));
        pnl4.setBackground(Color.green);
      }
    pnl5.add(lblPrompt);
    pnl5.add(lblScore);
    pnl6.add(btnNextQuestion);
    pnl6.add(btnSubmit);
    btnSubmit.setVisible(false);
    pnl6.add(btnRestart);
    pnlMaster.add(pnl1);
    pnlMaster.add(pnl2);
    pnlMaster.add(pnl3);
    pnlMaster.add(pnl4);
    pnlMaster.add(pnl5);
    pnlMaster.add(pnl6);
    frame.add(pnlMaster);

    frame.setVisible(true);


  }

  void nextQuest(){
    if (index < questList.size() -1){
      index = index + 1;
      System.out.println(index);
    }
    else{
      lblPrompt.setText("Thanks " + pName + " the Game is Over!");
      pnl1.setVisible(false);
      pnl2.setVisible(false);
      pnl3.setVisible(false);
      pnl4.setVisible(false);
      
     
      btnNextQuestion.setVisible(false);
      btnSubmit.setVisible(true);

    }
    lblQuestion.setText(questList.get(index).getQuestText()+ " (Worth " + questList.get(index).getPoints() +" Points)");
    answerList.get(0).setText(questList.get(index).getOptionA());
    answerList.get(1).setText(questList.get(index).getOptionB());
    answerList.get(2).setText(questList.get(index).getOptionC());
    answerList.get(3).setText(questList.get(index).getOptionD());
  } 
  void record(){

    try{
      FileWriter toWriteFile;
      toWriteFile = new FileWriter("scores.txt", true);
      BufferedWriter writeOut = new BufferedWriter(toWriteFile);
      writeOut.write(pName);
      writeOut.newLine();
      writeOut.write(Integer.toString(score));
      writeOut.newLine();
      writeOut.flush();
      btnSubmit.setVisible(false);
      writeOut.close();
      
     }
     catch (IOException excpt2){
       excpt2.printStackTrace();
     }

  }

  public void actionPerformed(ActionEvent ae){
    String correctAns = answerList.get(questList.get(index).getCorrectAns()-1).getText();
    System.out.println(correctAns);
    
    if(ae.getActionCommand().equals(correctAns)) {
      lblPrompt.setText("Correct!");
      score += questList.get(index).getPoints();
      lblScore.setText("Score: " + score);
      nextQuest();
    }
    else if(ae.getActionCommand().equals("Next Question")){
      lblPrompt.setText("");
      nextQuest();
    }
    else if(ae.getActionCommand().equals("Restart")){
      index = 0;
      score = 0;
      lblPrompt.setText("");
      pName = "";
      lblEnterName.setText("Enter your Name:  ");
      field1.setText("");
      field1.setVisible(true);
      btnEnterName.setVisible(true);
      lblScore.setText("Current Score: ");
      btnNextQuestion.setVisible(true);
      btnSubmit.setVisible(false);

      pnl1.setVisible(true);
      pnl2.setVisible(true);
      pnl3.setVisible(true);
      pnl4.setVisible(true);
      pnl5.setVisible(true);
      pnl6.setVisible(true);
    }
    else if(ae.getActionCommand().equals("Enter")){
    pName = field1.getText();
    lblEnterName.setText("Welcome " + pName);
    field1.setVisible(false);
    btnEnterName.setVisible(false);
    }
   else if(ae.getActionCommand().equals("Submit Score")){
      record();
    }
    else{
      lblPrompt.setText("Incorrect");
      nextQuest();
    }
  }
}
