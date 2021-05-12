/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import Utils.Statics;
import models.Poll;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/**
 *
 * @author sbs
 */
public class pollService {
    
      private static pollService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ArrayList<Poll> polls=new ArrayList<>();

    private pollService() {
         req = new ConnectionRequest();
    }

    public static pollService getInstance() {
        if (instance == null) {
            instance = new pollService();
        }
        return instance;
    }
    
    public ArrayList<Poll> parseTasks(String textJson){
        JSONParser j = new JSONParser();
        try {
            
        Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> pollList = (ArrayList<Map<String,Object>>) tasksListJson.get("root");
            for(Map<String,Object> obj : pollList){
                //Création des tâches et récupération de leurs données
                Poll t = new Poll();
          
                t.setPoll_id((int) Float.parseFloat(obj.get("pollId").toString()));
                t.setDescription(obj.get("description").toString());
                t.setStatus(obj.get("status").toString());
                t.setCreation_date(obj.get("creationDate").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                polls.add(t);
            }
             
        } catch (IOException ex) {
            System.out.println("An error occured");
        }
        return polls;
    }
    
    
    public ArrayList<Poll> getAllPolls(){
        String url = Statics.BASE_URL+"polllist";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                polls = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return polls;
    }
    
}
