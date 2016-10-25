package controllers;

import model.Question;
import play.mvc.*;
import play.data.*;
import views.html.*;
import javax.inject.Inject;
import questions.Questions;
import accueil.Accueil;
import finTest.FinTest;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;


import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Random;
import java.util.Vector;
import play.*;

public class HomeController extends Controller {

    @Inject
    private FormFactory formFactory;

    public Result goIndex() {
        return redirect("/");
    }

    public Result index() {
        return ok(index.render("")); //Pq ne s'affiche pas ?
    }

    public Result goMail(long id) {
        return redirect("/mail/1");
    }

    public Result getMail(long id) {
        Accueil accueil = new Accueil();
        return ok(bonjour.render(accueil.getMail(id)));
    }
   public Result fin(int score){

        return ok("Score de "+ score+ "/6");
    }

    private static final String AUTHORIZATION = "authorization";
    private static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    private static final String REALM = "Basic realm=\"Your Realm Here\"";

    private Boolean authorized() throws Throwable {
      String authHeader = request().getHeader(AUTHORIZATION);
      if (authHeader == null) {
          response().setHeader(WWW_AUTHENTICATE, REALM);
          return false;
      }

      String auth = authHeader.substring(6);
      byte[] decodedAuth = new sun.misc.BASE64Decoder().decodeBuffer(auth);
      String[] credString = new String(decodedAuth, "UTF-8").split(":");

      if (credString == null || credString.length != 2) {
          return false;
      }

      String username = credString[0];
      String password = credString[1];

      // Username and password from configuration
      String user = Play.application().configuration().getString("username");
      String pass = Play.application().configuration().getString("password");
      return (user.equals(username) && pass.equals(password));
    }

    public Result downloadLoosers() throws Throwable {
      return authorized() ? ok(new java.io.File("emailLooser.txt")) : unauthorized();
    }

    public Result downloadWinners() throws Throwable  {
      return authorized() ? ok(new java.io.File("emailWinner.txt")) : unauthorized();
    }

    public Result newUser() {
        Map<String, String[]> form = request().body().asFormUrlEncoded();
        String email = form.get("email")[0];
        String nom = form.get("nom")[0];
        String prenom = form.get("prenom")[0];
        String presence = form.get("presence")[0];

        String a1 = form.get("q1")[0];
        String a2 = form.get("q2")[0];
        String a3 = form.get("q3")[0];
        String a4 = form.get("q4")[0];
        String a5 = form.get("q5")[0];
        String a6 = form.get("q6")[0];

        int score = 0;

        if (a1.equals("1")) {
            score++;
            System.out.println(score);
        }
        if (a2.equals("3")){
            score++;
            System.out.println(score);
        }
        if (a3.equals("2")){
            score++;
            System.out.println(score);
        }
        if (a4.equals("1")){
            score++;
            System.out.println(score);
        }
        if (a5.equals("2")){
            score++;
            System.out.println(score);
        }
        if (a6.equals("2")){
            score++;
            System.out.println(score);
        }

        System.out.println(email);
        System.out.println(prenom);
        System.out.println(nom);
        System.out.println("Score de "  + prenom + ": " + score+ "/6");
        if(score==6) {
            try {//Creation du nouveau fichier text
                File monfichier = new File("emailWinner.txt"); // définir l'arborescence
                FileWriter fluxfichier = new FileWriter(monfichier, true); //True est pour 'append'(rajouter) d'autres coordonnees d'users
                // fluxfichier.write("Liste des adresses email rentrées dans l'application:\n");
                fluxfichier.write(email + " - " + nom + " " + prenom + " - " + "Score de " + prenom + ": " + score + "/6");
                fluxfichier.write("\n");
                fluxfichier.close(); // Ferme le tampon 'fluxfichier' et libérer les ressources associées
            } catch (Exception e) {
                System.out.println("Impossible de creer le fichier");
            }
        }else if(score != 6) {
            try {//Creation du nouveau fichier text
                File monfichier = new File("emailLooser.txt"); // définir l'arborescence
                FileWriter fluxfichier = new FileWriter(monfichier, true); //True est pour 'append'(rajouter) d'autres coordonnees d'users
                // fluxfichier.write("Liste des adresses email rentrées dans l'application:\n");
                fluxfichier.write(email + " - " + nom + " " + prenom + " - " + "Score de " + prenom + ": " + "motif: "+ presence + " - "+ score + "/6");
                fluxfichier.write("\n");
                fluxfichier.close(); // Ferme le tampon 'fluxfichier' et libérer les ressources associées
            } catch (Exception e) {
                System.out.println("Impossible de creer le fichier");
            }
        }
        return ok(auRevoir.render(score));
       // System.out.println("email.txt"); VOIR CONTENUE DU FICHIER AVEC L EMAIL RENTRE
        //return redirect("/question/1"); //Quand on va cliquer sur Valider, va renvoyer a la premiere question.
    }




//FONCTION RANDOM

    public static String getRandomElement (Vector v) {
        Random generator = new Random();
        int rnd = v.size() == 1 ? 0 : generator.nextInt(v.size() - 1);
        return (String)v.get(rnd);
    }


    public Result winner() throws IOException {
      String myLine= null;
      InputStreamReader flog  = null;
      LineNumberReader llog   = null;
      Vector<String> valeur = new Vector<String>();
      flog = new InputStreamReader(new FileInputStream("emailWinner.txt") );
      llog = new LineNumberReader(flog);
      while ((myLine = llog.readLine()) != null) {
          valeur.add(myLine);
      }
      Random generator = new Random();

      //if score = 6 only
      return ok("Et le gagnant est...  " + getRandomElement(valeur)+ "!!!");
      }



    public Result getQuestion(long id){

        Questions questions = new Questions();  //instanciation
        Form<QuestionAnswerForm> form = formFactory.form(QuestionAnswerForm.class); //Permet de creer la form

        return ok(irene.render(questions.getQuestion(id), form));
    }

    //public Result getEmail(long id){
     //   Questions questions = new Questions();
   //     Form<QuestionAnswerForm> form = formFactory.form(QuestionAnswerForm.class);


  //  }

    public Result answerQuestion(long id){   //FORM POUR STOCKER LE SCORE

        Questions questions = new Questions();
        Form<QuestionAnswerForm> form = formFactory.form(QuestionAnswerForm.class);

        QuestionAnswerForm data = form.bindFromRequest().get();

        int score=0;


        Question next = questions.getQuestion(id).getNextQuestion();  /*Debut: passer d'une question a une autre */

        Result result;
        if (next != null) {   //Methode 1 statement
            result = redirect("/question/" + next.getId());

        } else {
            result = redirect("/score/1");

        }

        System.out.println("score:" + score);
        if (score >= 3){
            return ok("a revoir !");
        }
        if (score == 6){
            return ok("Wahou!");
        }
        return result;                                                 /*Fin: passer d'une question a une autre */
        //  return (next != null) ? redirect("/question/" + next.getId()) : redirect("foobar");  //Methode 2 ternary operator (expression)
        //  return redirect((next != null) ? "/question/" + next.getId() : "foobar");             //Methode 3 ternary operator (expression)
    }

    public Result getScore(long id) {
        FinTest finTest = new FinTest();
        return ok();
        //return ok(auRevoir.render(finTest.getScore(id)));
    }






}
