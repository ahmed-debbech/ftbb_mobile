/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import static com.codename1.io.rest.Rest.options;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import models.Options;
import models.Vote;

/**
 *
 * @author sbs
 */
public class voteService {
 private static voteService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ArrayList<Vote> votes=new ArrayList<>();

    private voteService() {
         req = new ConnectionRequest();
    }

    public static voteService getInstance() {
        if (instance == null) {
            instance = new voteService();
        }
        return instance;
    }
    
    public ArrayList<Vote> parseTasks(String textJson){
        JSONParser j = new JSONParser();
        try {
            
        Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> voteList = (ArrayList<Map<String,Object>>) tasksListJson.get("root");
            for(Map<String,Object> obj : voteList){
                //Création des tâches et récupération de leurs données
                Vote o = new Vote();
                
                o.setVote_id((int) Float.parseFloat(obj.get("voteId").toString()));
                o.setOption_id((int) Float.parseFloat(obj.get("option/optionId").toString()));
                o.setVote_nbr((int) Float.parseFloat(obj.get("voteNbr").toString())); 
                //Ajouter la tâche extraite de la réponse Json à la liste
                votes.add(o);
            }
             
        } catch (IOException ex) {
            System.out.println("An error occured");
        }
        return votes;
    }
    
    
    public ArrayList<Vote> getAllVotes(){
        String url = Statics.BASE_URL+"votelist";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                votes = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return votes;
    }
}