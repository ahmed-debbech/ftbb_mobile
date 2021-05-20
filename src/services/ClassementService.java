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
public class ClassementService {
    
    //var
    boolean resultOK;
    ConnectionRequest req;
    static ClassementService instance;
    ArrayList<Classementf> tasks = new ArrayList<>();
    
    
    //constructor
    private ClassementService() {
        req = new ConnectionRequest();
    }
    
    //SINGLETON
    public static ClassementService getInstance(){
        
        if (instance == null) {
            instance = new ClassementService();
        }
        
        return instance;
    }
    
    //PARSE TASKS JSON : convert JSON to java objects
    public ArrayList<Classementf> parseJSONAction(String textJson){        
     
        JSONParser j = new JSONParser();       
      
        try {
            
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> tasksList = (ArrayList<Map<String,Object>>) tasksListJson.get("root");
            
            for (Map<String, Object> obj : tasksList) {
                
                Classementf t = new Classementf();
                t.setName_team(obj.get("nameEquipe").toString());
                t.setLogo_team(obj.get("logoEquipe").toString());
                t.setCompetition_name(obj.get("competition_name").toString());
                t.setPhase_name(obj.get("poule_name").toString());             
                t.setLogo_team(obj.get("logoEquipe").toString());
                t.setNbr_pts((int) Float.parseFloat(obj.get("nb_pts").toString()));
                 t.setNbr_pts_G((int) Float.parseFloat(obj.get("match_g").toString()));
                t.setNbr_pts_P((int) Float.parseFloat(obj.get("nb_match").toString()));
                t.setNbr_pts_D((int) Float.parseFloat(obj.get("match_d").toString()));
                t.setPts_diff((int) Float.parseFloat(obj.get("diff").toString()));            
           
                
                System.err.println(t);
                tasks.add(t);
            }
                 
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return tasks;  
    }

    //GET TASKS
    public ArrayList<Classementf> getClassement(int Id_competition , int Id_phase){
        tasks.clear();
         String url = Statics.BASE_URL+"/api/showClassement/"+ Id_competition + "/"+Id_phase ;
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {                
             tasks = parseJSONAction(new String(request.getResponseData()));
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return tasks;
    }
}
