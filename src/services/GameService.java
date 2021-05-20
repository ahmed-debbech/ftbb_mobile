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
import models.Classementf;
import models.Competition;
import models.Game;
import static services.ClassementService.instance;
import utils.Statics;

/**
 *
 * @author Lenovo
 */
public class GameService {
    
    boolean resultOK;
    ConnectionRequest req;
    static GameService instance;
    ArrayList<Game> games = new ArrayList<>();
    

      //SINGLETON
    public static GameService getInstance(){
        
        if (instance == null) {
            instance = new GameService();
        }
        
        return instance;
    }
      
    //constructor
    private GameService() {
        req = new ConnectionRequest();
    }
        public   ArrayList<Game> showGameAction(int Id_competition , int Id_phase  , int Id_Week){
        games.clear();
            String url = Statics.BASE_URL + "/api/showGame/"+ Id_competition+ "/" + Id_phase+"/"+ Id_Week;
        req.setUrl(url);   
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {                
             games = parseJSONAction(new String(req.getResponseData()));
             req.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });       
        NetworkManager.getInstance().addToQueueAndWait(req);
        return games;
    }
       
           //PARSE TASKS JSON : convert JSON to java objects
    public ArrayList<Game> parseJSONAction(String textJson){
        
        JSONParser j = new JSONParser();      
        try {          
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> GameList = (ArrayList<Map<String,Object>>) tasksListJson.get("root");
            
            for (Map<String, Object> obj : GameList) {                
                Game t = new Game();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setNameHome(obj.get("nameHome").toString());  
                t.setLogoHome(obj.get("logoHome").toString());  
                t.setSalle(obj.get("salle").toString());
             
                t.setNameAway(obj.get("nameAway").toString());  
                t.setLogoAway(obj.get("logoAway").toString());  
                
                games.add(t);
            }                    
        } catch (IOException ex) {
            ex.printStackTrace();
        }       
        return games ;  
    }
}
