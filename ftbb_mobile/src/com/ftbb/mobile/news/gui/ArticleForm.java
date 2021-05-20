/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.news.gui;

import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.ftbb.mobile.news.services.ServiceArticle;
import com.ftbb.mobile.news.entity.Article;
import com.ftbb.mobile.news.utils.Utilities;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.TextModeLayout;

/**
 *
 * @author root
 */
public class ArticleForm extends Form{
    Form current;
    
    public ArticleForm(Article a){
        current = this;
        setTitle(a.getTitle());
        setLayout(BoxLayout.y());
        TextModeLayout tl = new TextModeLayout(3, 2);
        TextComponent title = new TextComponent().label(a.getTitle()).multiline(true);
        
    }
}
