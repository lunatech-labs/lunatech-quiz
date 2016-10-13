package model;

/**
 * Created by irenegrootenboer on 19/09/16.
 */
public class Score {
    private long id;

    private String score;

    public Score(long id, String score) { //Constructor
        this.id = id;
        this.score = score;


    }


    public long getId() {return id;}

    public void setId(long id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }


}
