package questions;

import model.Question;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by irenegrootenboer on 13/09/16.
 */



public class Questions {

    //final int NumberofQuestions = 5;
    Question q1 = new Question(1, "Comment compiler un programme Scala ?", "> scalac HelloWorld.scala", "> scala HelloWorld.scala", "> scalac HelloWorld.scala", "> scalap HelloWorld.scala");
    Question q2 = new Question(2, "Quelle est la date de creation de Lunatech?", "1993", "1993", "1994", "1995");
    Question q3 = new Question(3, "Où sont situés les deux locaux de Lunatech ?", "Marne-la-vallée / Rotterdam", "Paris / Amsterdam", "Haut-de-Seine / Leiden", "Marne-la-vallée / Rotterdam");
    Question q4 = new Question(4, "Que ce que  ‘Nothing’ en Scala?", "Un type", "Un type", "Une méthode", "Une classe");
    Question q5 = new Question(5, "Le nom 'Scala vient de 2 sources. Lesquelles ?", "aa", "vv", "qqq");
    Question q6 = new Question(6, "Derniere question?", "aa", "vv", "qqq");

    Question[] questionArray = {q1, q2, q3, q4, q5, q6}; //Creation d'un tableau qui regroupe toutes les q

    public Questions() {

        q1.setNextQuestion(q2);
        q2.setNextQuestion(q3);
        q3.setNextQuestion(q4);
        q4.setNextQuestion(q5);
        q5.setNextQuestion(q6);
    }


    public Question getQuestion(long id) { //Cet accesseur permet d'acceder au contenu des questions
        Question result = null;

        for (Question question : questionArray) {
            if (question.getId() == id) {
                result = question;
            }
        }
        return result;
    }


    public int answerQuestion(long id, String answer, int score) {
        Question q = getQuestion(id);

        if (q.getRealAnswer().equals(answer)) {
            score++;
        }
        return score;
    }




}

