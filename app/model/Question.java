package model;


public class Question {

    private long id;  //Initialisation
    private String question;
    private String[] possibilities;
    private String realAnswer;
    private int score;
    // update
    private Question nextQuestion;


    public Question(long id, String question, String realAnswer, String... possibilities) { //Constructor
        this.id = id;
        this.question = question;
        this.realAnswer = realAnswer;
        this.possibilities = possibilities;

    }
    public Question(int score){
        this.score = score;
    }

    public Question(Question nextQuestion){
        this.nextQuestion = nextQuestion;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getPossibilities() {
        return possibilities;
    }

    public void setPossibilities(String[] possibilities) {
        this.possibilities = possibilities;
    }

    public String getRealAnswer() {
        return realAnswer;
    }

    public void setRealAnswer(String realAnswer) {
        this.realAnswer = realAnswer;
    }

    public Question getNextQuestion() {
        return nextQuestion;
    }

    public void setNextQuestion(Question nextQuestion) {
        this.nextQuestion = nextQuestion;
    }
}




