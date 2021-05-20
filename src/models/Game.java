/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
/**
 *
 * @author Lenovo
 */
public class Game {

    public static String NOT_FINISHED = "not finished";
    public static String FINISHED = "finished";
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getScore_home() {
        return score_home;
    }

    public void setScore_home(int score_home) {
        this.score_home = score_home;
    }

    public int getScore_away() {
        return score_away;
    }

    public void setScore_away(int score_away) {
        this.score_away = score_away;
    }

    public int getId_statistique() {
        return id_statistique;
    }

    public void setId_statistique(int id_statistique) {
        this.id_statistique = id_statistique;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getNameHome() {
        return nameHome;
    }

    public void setNameHome(String nameHome) {
        this.nameHome = nameHome;
    }

    public String getLogoHome() {
        return logoHome;
    }

    public void setLogoHome(String logoHome) {
        this.logoHome = logoHome;
    }
    public Game() {
    }
    private int id, id_phase,id_week,id_team_home,id_team_away,score_home,score_away,id_statistique,status;
    private String salle , nameHome,logoHome , nameAway , logoAway ;

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", score_home=" + score_home + ", score_away=" + score_away + ", id_statistique=" + id_statistique + ", status=" + status + ", salle=" + salle + ", nameHome=" + nameHome + ", logoHome=" + logoHome + ", nameAway=" + nameAway + ", logoAway=" + logoAway + '}';
    }

   

    public String getNameAway() {
        return nameAway;
    }

    public void setNameAway(String nameAway) {
        this.nameAway = nameAway;
    }

    public String getLogoAway() {
        return logoAway;
    }

    public void setLogoAway(String logoAway) {
        this.logoAway = logoAway;
    }
  
    
}
