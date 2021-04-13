class Question {
 
  public String questText;
  public String optionA;
  public String optionB;
  public String optionC;
  public String optionD;
  public int correctAns;
  public int points;




  Question() {
    questText = "";
    optionA = "";
    optionB = "";
    optionC = "";
    optionD = "";
    correctAns = 0;
    points = 0;

  }
  Question(String aQuestText, String aOptionA, String aOptionB, String aOptionC, String aOptionD, int aCorrectAns, int aPoints){
    questText = aQuestText;
    optionA = aOptionA;
    optionB = aOptionB;
    optionC = aOptionC;
    optionD = aOptionD;
    correctAns = aCorrectAns;
    points = aPoints;

  }

  String getQuestText(){
    return questText;
  }
  String getOptionA(){
    return optionA;
  }
  String getOptionB(){
    return optionB;
  }
  String getOptionC(){
    return optionC;
  }
  String getOptionD(){
    return optionD;
  }
  int getCorrectAns(){
    return correctAns;
  }
  int getPoints(){
    return points;
  }
}
