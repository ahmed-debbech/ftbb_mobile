/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import models.Competition;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.events.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import models.*;
import utils.Statics;
/**
 *
 * @author Lenovo
 */
public class CompetitionService {
    //var
    boolean resultOK;
    ConnectionRequest req;
    static CompetitionService instance;
    ArrayList<Competition> competitions = new ArrayList<>();
    

    
    //constructor
    private CompetitionService() {
        req = new ConnectionRequest();
    }
    
    //SINGLETON
    public static CompetitionService getInstance(){
        
        if (instance == null) {
            instance = new CompetitionService();
        }
        
        return instance;
    }
    
    
    
    
    //ADD Competition 
    public boolean addCompetitionAction(Competition t){
         String url = Statics.BASE_URL + "/api/addCompetition/"+ t.getName() + "/" + t.getCalendar();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            resultOK = req.getResponseCode()==200;
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    //PARSE TASKS JSON : convert JSON to java objects
    public ArrayList<Competition> parseJSONAction(String textJson){
         
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> competitionsList = (ArrayList<Map<String,Object>>) tasksListJson.get("root");
            
            for (Map<String, Object> obj : competitionsList) {
                
                Competition t = new Competition();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setName(obj.get("name").toString());
                
                competitions.add(t);

            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return competitions;  
    }



    //GET Competition
    public ArrayList<Competition> getCompetitions(){
        competitions.clear();
         String url = Statics.BASE_URL+"/api/showCompetition";
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
             competitions = parseJSONAction(new String(request.getResponseData()));
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });
         
        NetworkManager.getInstance().addToQueueAndWait(request);
        return competitions;
    }
    
   
    
}
