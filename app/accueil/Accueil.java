package accueil;
import model.Mail;





/**
 * Created by irenegrootenboer on 19/09/16.
 */
public class Accueil  {



    public Mail getMail(long id) {
            if (id == 1) { return new Mail(1," A propos de vous " ) ;}

            else return null;
        }
}


        //return result;

  //  pour id=1:

    //BAR EMAIL
    //BOUTON VALIDER
    // Clique bouton valider--> afficher en dessous :"Votre adresse email a bien été enregistrée ! Commençons le quiz.");}
    // BOUTON 'commencer le test'
    // Clique bouton 'commencer le test' --> Page SUIVANTE question/1





