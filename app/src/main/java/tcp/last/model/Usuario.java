package tcp.last.model;

import java.util.ArrayList;

public class Usuario {
    private int id;
    private String username;
    private String name;
    private String email;
    private String password;
    private String birth;
    private String telf;
    private String face;
    private String twit;
    private String insta;
    private String image;

    private ArrayList<Recipe> recipeArrayList;
    private ArrayList<Usuario> listFollowMeUsuarios;
    private ArrayList<Usuario> listFollowersUsuarios;

    public Usuario(String name, String email, String photoUrl) {
        this.name = name;
        this.email = email;
        this.image = photoUrl;
    }

    public Usuario(String username, String name, String email, String password, String birth, String telf, String face, String twit, String insta, String image) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.telf = telf;
        this.face = face;
        this.twit = twit;
        this.insta = insta;
        this.image = image;
    }


    public Usuario(String name, String birth, String telf, String face, String twit, String insta, String image) {
        this.name = name;
        this.birth = birth;
        this.telf = telf;
        this.face = face;
        this.twit = twit;
        this.insta = insta;
        this.image = image;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public ArrayList<Recipe> getMealArrayList() {
        return recipeArrayList;
    }

    public void setMealArrayList(ArrayList<Recipe> mealArrayList) {
        this.recipeArrayList = mealArrayList;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getTwit() {
        return twit;
    }

    public void setTwit(String twit) {
        this.twit = twit;
    }

    public String getInsta() {
        return insta;
    }

    public void setInsta(String insta) {
        this.insta = insta;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public ArrayList<Usuario> getListFollowMeUsuarios() {
        return listFollowMeUsuarios;
    }

    public void setListFollowMeUsuarios(ArrayList<Usuario> listFollowMeUsuarios) {
        this.listFollowMeUsuarios = listFollowMeUsuarios;
    }

    public ArrayList<Usuario> getListFollowersUsuarios() {
        return listFollowersUsuarios;
    }

    public void setListFollowersUsuarios(ArrayList<Usuario> listFollowersUsuarios) {
        this.listFollowersUsuarios = listFollowersUsuarios;
    }
}
