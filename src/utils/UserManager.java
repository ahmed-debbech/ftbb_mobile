/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Client;

/**
 *
 * @author Ahmed
 */
public class UserManager {
    private static UserManager instance;
    private static Client client; 
    
    private UserManager(){
        
    }
    public static UserManager getInstance(){
        if(instance == null){
            instance = new UserManager();
            client = new Client();
        }
        return instance;
    }
    public Client getClient(){
        return client;
    }
    public void removeClient(){
        instance = null;
        client = null;
    }
}
