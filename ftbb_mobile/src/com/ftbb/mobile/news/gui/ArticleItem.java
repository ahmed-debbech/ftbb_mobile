/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.news.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.ftbb.mobile.news.services.ServiceArticle;
import com.ftbb.mobile.news.entity.Article;
import java.util.ArrayList;
/**
 *
 * @author root
 */
public class ArticleItem extends Form {
    Form current;
    
    public ArticleItem(Article a){
        current = this;
        setScrollableY(false);
        setLayout(BoxLayout.y());
        setTitle(a.getTitle());
        add(new Label(a.getText()));
        add(new Label(a.getDate().toString()));
    }
}
