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
import java.util.List;
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

    public voteService() {
         req = new ConnectionRequest();
    }

    public static voteService getInstance() {
        if (instance == null) {
            instance = new voteService();
        }
        return instance;
    }
    
    
    
    public ArrayList<Vote> parseEvent(String jsonText) {
     
        try {
            votes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            
            for (Map<String, Object> obj : list) {
                Vote e = new Vote();
              double id = Double.parseDouble(obj.get("voteId").toString());
                e.setVote_id((int) id);
                
                
                e.setVote_nbr((int) Float.parseFloat(obj.get("voteNbr").toString())); 
                votes.add(e);
                
            }
        } catch (IOException ex) {

        }
        return votes;
    }
    
    
    
    public ArrayList<Vote> getAllVotes(int id){
        String url = Statics.BASE_URL+"votelist/"+id+"";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                votes = parseEvent(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return votes;
    }
    
    public boolean addvote(int id){
        String url = Statics.BASE_URL+"vote/"+id+"";
        ConnectionRequest req = new ConnectionRequest(url);
      
        req.setPost(false);
        req.addResponseListener((evt)->{
            resultOK = req.getResponseCode()==200;
            });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
       
    }
}