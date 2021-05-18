/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.Options;






public class optionService {
     private static optionService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ArrayList<Options> options=new ArrayList<>();

    public optionService() {
         req = new ConnectionRequest();
    }

    public static optionService getInstance() {
        if (instance == null) {
            instance = new optionService();
        }
        return instance;
    }
 
    
 public ArrayList<Options> parseEvent(String jsonText) {
     
        try {
            options = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            
            for (Map<String, Object> obj : list) {
                
                Options e = new Options();
              double id = Double.parseDouble(obj.get("optionId").toString());
                e.setOption_id((int) id);
                e.setDescription(obj.get("description").toString());
                options.add(e);
            }
        } catch (IOException ex) {

        }
        return options;
    }

    public ArrayList<Options> getAllOptions(int id) {
        String url = Statics.BASE_URL + "/optionlist/"+id+"";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                options = parseEvent(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return options;
    }   
    
}
//    public ArrayList<Options> parseTasks(String textJson){
//        JSONParser j = new JSONParser();
//        try {
//            
//        Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
//            ArrayList<Map<String,Object>> optionList = (ArrayList<Map<String,Object>>) tasksListJson.get("root");
//            
//            System.out.println(optionList);
//            
//            for(Map<String,Object> obj : optionList){
//                //Création des tâches et récupération de leurs données
//                Options o = new Options();
//                
//                o.setOption_id((int) Float.parseFloat(obj.get("optionId").toString()));
//
//                o.setDescription(obj.get("description").toString());
//                System.out.println(obj);
//                //Ajouter la tâche extraite de la réponse Json à la liste
//                options.add(o);
//            }
//             
//        } catch (IOException ex) {
//            System.out.println("An error occured");
//        }
//        return options;
//    }
//    
//    
//    public ArrayList<Options> getAllOptions(int id){
//        String url = Statics.BASE_URL+"optionlist/"+id+"";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                options = parseTasks(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return options;
//    }
//}
