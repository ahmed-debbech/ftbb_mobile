/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author sbs
 */
public class Options {
    private int option_id,poll_id ;
    private String Description;

    public Options() {
    }

    public Options(int option_id, int poll_id, String Description) {
        this.option_id = option_id;
        this.poll_id = poll_id;
        this.Description = Description;
    }

    public int getOption_id() {
        return option_id;
    }

    public void setOption_id(int option_id) {
        this.option_id = option_id;
    }

    public int getPoll_id() {
        return poll_id;
    }

    public void setPoll_id(int poll_id) {
        this.poll_id = poll_id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
}
