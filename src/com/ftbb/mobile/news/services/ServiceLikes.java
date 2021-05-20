/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.news.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.ftbb.mobile.news.entity.Comment;
import com.ftbb.mobile.news.gui.ArticleViewForm;
import com.ftbb.mobile.news.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author root
 */
public class ServiceLikes {
    
    public static int CLIENT_ID = 122;
    
    private static ServiceLikes instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ArrayList<Comment> comments=new ArrayList<>();
    
    private ServiceLikes() {
         req = new ConnectionRequest();
    }
    public static ServiceLikes getInstance() {
        if (instance == null) {
            instance = new ServiceLikes();
        }
        return instance;
    }
    
    public void likeArticle(int art){
        System.out.println(";;w");
        String url = Statics.BASE_URL + "/likes/article/"+art+"/"+CLIENT_ID;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
              
             }
         });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public void likeComment(int cmt){
        System.out.println(";;w");
        String url = Statics.BASE_URL + "/likes/comment/"+cmt+"/"+CLIENT_ID;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
              
             }
         });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public boolean checkLike(int art){
        boolean test = true;
        String url = Statics.BASE_URL + "/likes/check/article/"+art+"/"+CLIENT_ID;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            resultOK = req.getResponseCode()==200;
            JSONParser j = new JSONParser();
                Map<String, Object> list;
                try {
                    String vv = new String(req.getResponseData());
                    list = j.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    ArrayList<Map<String,Object>> dd = (ArrayList<Map<String,Object>>) list.get("root");
                    String val = dd.get(0).get("check").toString();
                    System.out.println("value " + val);
                if(val.equals("1")){
                    ArticleViewForm.getInstance().setColor(true);
                }else{
                        ArticleViewForm.getInstance().setColor(false);
                }
                } catch (IOException ex) {
                }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return test;
    }
    public boolean checkCommentLike(int comment){
        String url = Statics.BASE_URL + "/likes/check/comment/"+comment+"/"+CLIENT_ID;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            resultOK = req.getResponseCode()==200;
            JSONParser j = new JSONParser();
                Map<String, Object> list;
                try {
                    String vv = new String(req.getResponseData());
                    list = j.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    ArrayList<Map<String,Object>> dd = (ArrayList<Map<String,Object>>) list.get("root");
                    String val = dd.get(0).get("check").toString();
                    System.out.println("value " + val);
                if(val.equals("1")){
                    resultOK = true;
                }else{
                        resultOK = false;
                }
                } catch (IOException ex) {
                }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
}
