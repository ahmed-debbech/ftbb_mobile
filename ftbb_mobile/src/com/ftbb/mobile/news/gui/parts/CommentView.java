/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.news.gui.parts;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.ftbb.mobile.news.entity.Comment;

/**
 *
 * @author root
 */
public class CommentView {
    private String name;
    private String content;
    private int likes;
    private String date;
    private Container cont;
    
    public CommentView(Comment c){
        this.name = c.getClient_name();
        this.content = c.getContent();
        this.likes = c.getLikes();
        this.date = c.getDate().toString();
        
        Resources theme = UIManager.initFirstTheme("/newsui");
        UIBuilder.registerCustomComponent("ImageViewer",  com.codename1.components.ImageViewer.class);
        UIBuilder ui = new UIBuilder();
        cont = ui.createContainer(theme, "CommentView");
        Label l = (Label)cont.getComponentAt(0);
        l.setText(name);
        Label l1 = (Label)cont.getComponentAt(1);
        l1.setText(content);
        Container cc = (Container)cont.getComponentAt(2);
        Label l4 = (Label)cc.getComponentAt(0);
        l4.setText(date + "");
        Label l3 = (Label)cc.getComponentAt(1);
        l3.setText(likes + " - Likes");
    }
    public Container getView(){
        return cont;
    }
}
