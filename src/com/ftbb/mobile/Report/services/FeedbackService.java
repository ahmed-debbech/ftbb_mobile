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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.ftbb.mobile.Report.gui.ListFeedbacksForm;
import com.ftbb.mobile.Report.models.Feedback;
import com.ftbb.mobile.Report.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author narug
 */
public class FeedbackService {
     boolean resultOK;
    ConnectionRequest req;
    static FeedbackService instance;
    ArrayList<Feedback> feedbacks = new ArrayList<>();
    

    public FeedbackService() {
      req = new ConnectionRequest();
    }
    
    public static FeedbackService getInstance(){
        
        if (instance == null) {
            instance = new FeedbackService();
        }
        
        return instance;
    }
    
    public boolean addFeedbackAction(Feedback f){
        
        String url = Statics.BASE_URL + "/feedbackapi?email="+ f.getEmail()+"&topic="+f.getTopic()+"&text="+f.getText()+"&type="+f.getType();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            resultOK = req.getResponseCode()==200;
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    public ArrayList<Feedback> parseJSONAction(String textJson){
        
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> feedbacksListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> feedbacksList = (ArrayList<Map<String,Object>>) feedbacksListJson.get("root");
            
            for (Map<String, Object> obj : feedbacksList) {
                
                Feedback f = new Feedback();
                    
                f.setFeedbackId((int)Double.parseDouble(obj.get("feedbackId").toString()));
                f.setClientId((int)Double.parseDouble(obj.get("clientId").toString()));
                f.setFeedbackDate(obj.get("feedbackDate").toString());
                f.setEmail(obj.get("email").toString());
                f.setTopic(obj.get("topic").toString());
                f.setText(obj.get("text").toString());
                f.setType(obj.get("type").toString());
                


                
                feedbacks.add(f);

            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return feedbacks;  
    }
    
    
    
     public ArrayList<Feedback> getFeedbacks(){
        
         String url = Statics.BASE_URL+"/feedbackapi/show";
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
             feedbacks = parseJSONAction(new String(request.getResponseData()));
             
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return feedbacks;
    }
     
      public void deletefed(int fed){
        String url = Statics.BASE_URL+"/feedbackapi/supp/"+fed;
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
                 
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
    }

   
    public boolean modifyFed(int feedbackId) {
        System.out.println("enter");
         String url = Statics.BASE_URL+"/feedbackapi/modify/"+feedbackId;
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         final int kk = 0;
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 JSONParser j = new JSONParser();
                 Map<String, Object> feedbacksListJson;
                 try {
                     System.out.println("hereee" + feedbackId);
                     feedbacksListJson = j.parseJSON(new CharArrayReader(new String(request.getResponseData()).toCharArray()));
                     ArrayList<Map<String,Object>> feedbacksList = (ArrayList<Map<String,Object>>) feedbacksListJson.get("root");
                String val = feedbacksList.get(0).get("v").toString();
                 System.out.println("val fed : " + val);
                 if(val.equals("valid")){
                     ListFeedbacksForm.redirect(feedbackId);
                 }else{
                     Dialog.show("Success", "Could not edit beacause 24hours expired!",null, "OK");
                 }

                 } catch (IOException ex) {
                 }
                
                 
             }
         });
         
        
     NetworkManager.getInstance().addToQueueAndWait(request);
     return false;
    }
    public void modifyNowFed(Feedback fe){
        String url = Statics.BASE_URL+"/feedbackapi/modifynow/"+fe.getFeedbackId()+"?text="+fe.getText();
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
                 
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
    }
}
