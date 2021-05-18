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
import com.codename1.ui.events.ActionListener;
import com.ftbb.mobile.news.entity.Article;
import com.ftbb.mobile.news.entity.Comment;
import com.ftbb.mobile.news.utils.Statics;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author root
 */
public class ServiceComment {
    private static ServiceComment instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ArrayList<Comment> comments=new ArrayList<>();
    
    private ServiceComment() {
         req = new ConnectionRequest();
    }
    public static ServiceComment getInstance() {
        if (instance == null) {
            instance = new ServiceComment();
        }
        return instance;
    }
    
    public ArrayList<Comment> parseTasks(String jsonText){
        try {
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Comment t = new Comment();
                t.setId((int) Double.parseDouble(obj.get("id").toString()));
                t.setContent(obj.get("content").toString());
                String dat = obj.get("date").toString().substring(0,19);
                dat = dat.replace('T', ' ');
                System.out.println("dattt " + dat);
                t.setDate(Timestamp.valueOf(dat));
                Map<String, Object> k = (Map<String, Object> )obj.get("client");
                t.setClient_name(k.get("name").toString() + " " + k.get("surname").toString());
                if(obj.get("likes") != null){
                    List<Map<String, Object>> kk = (List<Map<String, Object>> )obj.get("likes");
                    t.setLikes(kk.size());
                }
                comments.add(t);
            }
             
        } catch (IOException ex) {
            System.out.println("An error occured");
        }
        return comments;
    }
    
    public ArrayList<Comment> getAllComments(int art){
        comments = new ArrayList<>();
        String url = Statics.BASE_URL+"/comments/get/"+art;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                comments = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return comments;
    }
    
    public boolean addComment(Comment cmt){
        String url = Statics.BASE_URL + "/comments/add/"+cmt.getArticle_id()+"/"+cmt.getClient_id()+"?content="+cmt.getContent();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            resultOK = req.getResponseCode()==200;
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

}
