/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import models.*;
import utils.Statics;

/**
 *
 * @author Lenovo
 */
public class WeekService {
    
       
    //var
    boolean resultOK;
    ConnectionRequest req;
    static WeekService instance;
    ArrayList<Week> weeks = new ArrayList<>();
    //constructor
    private WeekService() {
        req = new ConnectionRequest();
    }
    
    //SINGLETON
    public static WeekService getInstance(){
        
        if (instance == null) {
            instance = new WeekService();
        }       
        return instance;
    }

    //PARSE Phase JSON : convert JSON to java objects
    public ArrayList<Week> parseJSONAction(String textJson){
        
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> weekList = (ArrayList<Map<String,Object>>) tasksListJson.get("root");
            
            for (Map<String, Object> obj : weekList) {            
                Week t = new Week();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);              
                t.setName_week(obj.get("nameWeek").toString());         
                weeks.add(t);
            }                 
        } catch (IOException ex) {
            ex.printStackTrace();
        }        
        return weeks;  
    }
    //GET weeks
    public ArrayList<Week> getWeeks(){
        weeks.clear();
         String url = Statics.BASE_URL+"/api/showWeeks";
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {            
             weeks = parseJSONAction(new String(request.getResponseData()));
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });   
        NetworkManager.getInstance().addToQueueAndWait(request);
        return weeks;
    }
    
}
