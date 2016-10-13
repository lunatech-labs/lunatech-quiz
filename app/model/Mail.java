package model;

/**
 * Created by irenegrootenboer on 19/09/16.
 */
public class Mail {

    private long id;
    private String mail;


    public Mail(long id, String mail) { //Constructor
        this.id = id;
        this.mail = mail;


    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


}
