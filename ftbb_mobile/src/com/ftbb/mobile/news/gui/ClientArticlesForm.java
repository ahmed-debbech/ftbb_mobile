package com.ftbb.mobile.news.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.ftbb.mobile.news.services.ServiceArticle;
import com.ftbb.mobile.news.entity.Article;
import java.util.ArrayList;


public class ClientArticlesForm extends Form{
    Form current;
    public ClientArticlesForm(){
        current = this;
        setTitle("Articles");
        setLayout(BoxLayout.y());
        ArrayList<Article> l = ServiceArticle.getInstance().getAllTasks();
        for(Article x : l){
            add(new Label(x.getTitle()));
        }
    }
}