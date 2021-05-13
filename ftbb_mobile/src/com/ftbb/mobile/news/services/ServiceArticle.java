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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.ftbb.mobile.news.entity.Article;
import com.ftbb.mobile.news.utils.Statics;

/**
 *
 * @author bhk
 */
public class ServiceArticle {

    
    private static ServiceArticle instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ArrayList<Article> articles=new ArrayList<>();

    private ServiceArticle() {
         req = new ConnectionRequest();
    }

    public static ServiceArticle getInstance() {
        if (instance == null) {
            instance = new ServiceArticle();
        }
        return instance;
    }

    public ArrayList<Article> parseTasks(String jsonText){
        try {
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Article t = new Article();
                t.setTitle(obj.get("title").toString());
                t.setArticle_id((int) Float.parseFloat(obj.get("articleId").toString()));
                t.setText(obj.get("text").toString());
                t.setDate(obj.get("date").toString());
                t.setPhoto_url(obj.get("photoUrl").toString());
                if(obj.get("likes") != null){
                    List<Map<String, Object>> kk = (List<Map<String, Object>> )obj.get("likes");
                    t.setLikes(kk.size());
                }
                if(obj.get("comments") != null){
                    List<Map<String, Object>> kk3 = (List<Map<String, Object>> )obj.get("comments");
                    t.setComment_count(kk3.size());
                }
                //Ajouter la tâche extraite de la réponse Json à la liste
                articles.add(t);
            }
             
        } catch (IOException ex) {
            System.out.println("An error occured");
        }
        return articles;
    }
    
    public ArrayList<Article> getAllArticles(){
        String url = Statics.BASE_URL+"/articles/get/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }
}