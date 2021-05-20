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
import models.Phase;
import utils.Statics;

/**
 *
 * @author Lenovo
 */
public class PhaseService {
    
     
    //var
    boolean resultOK;
    ConnectionRequest req;
    static PhaseService instance;
    ArrayList<Phase> phase = new ArrayList<>();
    //constructor
    private PhaseService() {
        req = new ConnectionRequest();
    }
    
    //SINGLETON
    public static PhaseService getInstance(){
        
        if (instance == null) {
            instance = new PhaseService();
        }       
        return instance;
    }

    //PARSE Phase JSON : convert JSON to java objects
    public ArrayList<Phase> parseJSONAction(String textJson){
        
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> phaseList = (ArrayList<Map<String,Object>>) tasksListJson.get("root");
            
            for (Map<String, Object> obj : phaseList) {
                
                Phase t = new Phase();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);              
                t.setName(obj.get("name").toString());
                
                phase.add(t);

            }                 
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return phase;  
    }
    //GET Phase
    public ArrayList<Phase> getPhases(){
        phase.clear();
         String url = Statics.BASE_URL+"/api/showPhase/";
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {            
             phase = parseJSONAction(new String(request.getResponseData()));
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });   
        NetworkManager.getInstance().addToQueueAndWait(request);
        return phase;
    }
    
}