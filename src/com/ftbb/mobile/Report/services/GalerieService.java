/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.Report.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.ftbb.mobile.Report.models.Galerie;
import com.ftbb.mobile.Report.models.Report;
import static com.ftbb.mobile.Report.services.ReportService.instance;
import com.ftbb.mobile.Report.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author narug
 */
public class GalerieService {
    
     boolean resultOK;
    ConnectionRequest req;
    static GalerieService instance;
    ArrayList<Galerie> galeries = new ArrayList<>();
    
    public GalerieService() {
      req = new ConnectionRequest();

    }
    
    public static GalerieService getInstance(){
        
        if (instance == null) {
            instance = new GalerieService();
        }
        
        return instance;
    }

    public ArrayList<Galerie> parseJSONAction(String textJson){
        
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> galeriesListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> galeriesList = (ArrayList<Map<String,Object>>) galeriesListJson.get("root");
            
            for (Map<String, Object> obj : galeriesList) {
                
                Galerie g = new Galerie();
                    
                g.setPhotoUrl(obj.get("photoUrl").toString());
                g.setDescription(obj.get("description").toString());
                g.setPhotoTitle(obj.get("photoTitle").toString());


                
                galeries.add(g);

            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return galeries;  
    }



    //GET REPORTS
    public ArrayList<Galerie> getGaleries(){
        
         String url = Statics.BASE_URL+"/galerieapi/showclient";
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
             galeries = parseJSONAction(new String(request.getResponseData()));
             
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return galeries;
    }

    
    
    
}
