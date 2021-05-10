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
import com.ftbb.mobile.Report.models.Report;
import com.ftbb.mobile.Report.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author narug
 */
public class ReportService {
    
    
     //var
    boolean resultOK;
    ConnectionRequest req;
    static ReportService instance;
    ArrayList<Report> reports = new ArrayList<>();
    
    
    //constructor
    private ReportService() {
        req = new ConnectionRequest();
    }
    
    //SINGLETON
    public static ReportService getInstance(){
        
        if (instance == null) {
            instance = new ReportService();
        }
        
        return instance;
    }
    
    
    
    
    //ADD TASK 
    public boolean addReportAction(Report r){
        
        String url = Statics.BASE_URL + "/reportapi?commandId="+ r.getCommand_id() + "&email="+ r.getEmail()+"&description="+r.getDescription();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            resultOK = req.getResponseCode()==200;
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    //PARSE TASKS JSON : convert JSON to java objects
    public ArrayList<Report> parseJSONAction(String textJson){
        
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> reportsListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> reportsList = (ArrayList<Map<String,Object>>) reportsListJson.get("root");
            
            for (Map<String, Object> obj : reportsList) {
                
                Report r = new Report();
                    
                r.setReport_id((int)Float.parseFloat(obj.get("reportId").toString()));
                r.setClient_id((int)Float.parseFloat(obj.get("clientId").toString()));
                r.setCommand_id((int)Float.parseFloat(obj.get("commandId").toString()));
                r.setReport_date(obj.get("reportDate").toString());
                r.setEmail(obj.get("email").toString());
                r.setDescription(obj.get("description").toString());
                


                
                reports.add(r);

            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return reports;  
    }



    //GET REPORTS
    public ArrayList<Report> getReports(){
        
         String url = Statics.BASE_URL+"/reportapi/show";
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
             reports = parseJSONAction(new String(request.getResponseData()));
             
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return reports;
    }
    
}
