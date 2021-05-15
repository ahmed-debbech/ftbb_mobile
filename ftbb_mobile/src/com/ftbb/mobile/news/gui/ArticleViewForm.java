/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 package com.ftbb.mobile.news.gui;  
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.ftbb.mobile.news.entity.Article;
import com.ftbb.mobile.news.entity.Comment;
import com.ftbb.mobile.news.gui.parts.CommentView;
import com.ftbb.mobile.news.services.ServiceComment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/** 
 * GUI builder created Form
 * 
 * @author root
 */ 
public class ArticleViewForm extends com.codename1.ui.Form {
    
    public int CLIENT_ID = 122;
    
    public ArticleViewForm(Article a, String photofile) {
        this(com.codename1.ui.util.Resources.getGlobalResources());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new ClientArticlesForm().showBack());
        setScrollableY(true);
        gui_title.setText(a.getTitle());
        setTitle(a.getTitle());
        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);
        Image image = URLImage.createToStorage(placeholder, photofile, a.getPhoto_url());
        gui_photo.setImage(image);
        gui_texter.setText(a.getText());
        gui_comments.setScrollableY(true);
        ArrayList<Comment> l = ServiceComment.getInstance().getAllComments(a.getArticle_id());
        gui_commentlab.setText("Comments - "  + l.size());
        gui_num_like.setText(a.getLikes()+"");
        for(Comment c : l){
            gui_comments.add(new CommentView(c).getView());
        }
        gui_Button.addActionListener((e) -> {
             if (gui_TextField.getText().length() ==0) {
                Dialog.show("Alert", "Comment cannot be empty.",null, "OK");
            }else {
                
                try {
                    
                Comment task = new Comment(gui_TextField.getText(), a.getArticle_id(),CLIENT_ID);
                    if (ServiceComment.getInstance().addComment(task)) {
                        Dialog.show("Success", "Houuuray! comment added.",null, "OK");
                    }
                    
                } catch (NumberFormatException ee) {
                    Dialog.show("Alert", "An error occured :(.",null, "OK");
                }
            }
        });

    }
    
    public ArticleViewForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.components.ImageViewer gui_photo = new com.codename1.components.ImageViewer();
    protected com.codename1.components.SpanLabel gui_title = new com.codename1.components.SpanLabel();
    protected com.codename1.components.SpanLabel gui_texter = new com.codename1.components.SpanLabel();
    protected com.codename1.ui.Container gui_Container = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.X_AXIS));
    protected com.codename1.ui.Label gui_commentlab = new com.codename1.ui.Label();
    protected com.codename1.ui.Button gui_art_like_but = new com.codename1.ui.Button();
    protected com.codename1.ui.Label gui_num_like = new com.codename1.ui.Label();
    protected com.codename1.ui.Container gui_comments = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    protected com.codename1.ui.Container gui_Box_Layout_X = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.X_AXIS));
    protected com.codename1.ui.TextField gui_TextField = new com.codename1.ui.TextField();
    protected com.codename1.ui.Button gui_Button = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(false);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("ArticleViewForm");
        setName("ArticleViewForm");
        gui_photo.setPreferredSizeStr("172.22223mm 41.26984mm");
        gui_photo.setUIID("photo");
                gui_photo.setInlineStylesTheme(resourceObjectInstance);
        gui_photo.setName("photo");
        gui_title.setPreferredSizeStr("112.96297mm 28.306879mm");
        gui_title.setText("title");
        gui_title.setUIID("title");
                gui_title.setInlineStylesTheme(resourceObjectInstance);
        gui_title.setInlineAllStyles("font:16.0mm; bgColor:fce8d0; fgColor:ff7e79; alignment:left;");
        gui_title.setInlineUnselectedStyles("font:16.0mm; fgColor:ff7e79; alignment:left;");
        gui_title.setName("title");
        gui_texter.setPreferredSizeStr("113.227516mm 77.77778mm");
        gui_texter.setText("texter");
        gui_texter.setUIID("texter");
                gui_texter.setInlineStylesTheme(resourceObjectInstance);
        gui_texter.setInlineAllStyles("font:18.0mm;");
        gui_texter.setName("texter");
        gui_Container.setPreferredSizeStr("110.05291mm 15.343915mm");
                gui_Container.setInlineStylesTheme(resourceObjectInstance);
        gui_Container.setInlineAllStyles("bgColor:ff7e79;");
        gui_Container.setName("Container");
        gui_comments.setPreferredSizeStr("118.51852mm 41.00529mm");
        gui_comments.setScrollableY(false);
                gui_comments.setInlineStylesTheme(resourceObjectInstance);
        gui_comments.setInlineAllStyles("font:6.0mm; bgColor:fce8d0; transparency:0; alignment:center;");
        gui_comments.setName("comments");
        gui_Box_Layout_X.setPreferredSizeStr("110.05291mm 10.8465605mm");
        gui_Box_Layout_X.setScrollableY(false);
                gui_Box_Layout_X.setInlineStylesTheme(resourceObjectInstance);
        gui_Box_Layout_X.setName("Box_Layout_X");
        addComponent(gui_photo);
        addComponent(gui_title);
        addComponent(gui_texter);
        addComponent(gui_Container);
        gui_commentlab.setText("Comments - 0");
        gui_commentlab.setUIID("com");
                gui_commentlab.setInlineStylesTheme(resourceObjectInstance);
        gui_commentlab.setInlineAllStyles("font:4.0mm native:MainBold native:MainBold; fgColor:fb6800; alignment:left;");
        gui_commentlab.setName("commentlab");
        gui_art_like_but.setUIID("art_like_but");
                gui_art_like_but.setInlineStylesTheme(resourceObjectInstance);
        gui_art_like_but.setName("art_like_but");
        gui_art_like_but.setIcon(resourceObjectInstance.getImage("like.png"));
        gui_num_like.setText("num");
        gui_num_like.setUIID("num_like");
                gui_num_like.setInlineStylesTheme(resourceObjectInstance);
        gui_num_like.setInlineAllStyles("fgColor:b4b4b4;");
        gui_num_like.setName("num_like");
        gui_Container.addComponent(gui_commentlab);
        gui_Container.addComponent(gui_art_like_but);
        gui_Container.addComponent(gui_num_like);
        addComponent(gui_comments);
        addComponent(gui_Box_Layout_X);
                gui_TextField.setInlineStylesTheme(resourceObjectInstance);
        gui_TextField.setName("TextField");
        gui_TextField.setColumns(13);
        gui_Button.setText("Comment");
                gui_Button.setInlineStylesTheme(resourceObjectInstance);
        gui_Button.setInlineAllStyles("fgColor:fdc607;");
        gui_Button.setName("Button");
        gui_Box_Layout_X.addComponent(gui_TextField);
        gui_Box_Layout_X.addComponent(gui_Button);
        ((com.codename1.ui.layouts.LayeredLayout)gui_photo.getParent().getLayout()).setInsets(gui_photo, "-0.26455116mm 0.0mm auto 0.5290985mm").setReferenceComponents(gui_photo, "-1 -1 -1 -1").setReferencePositions(gui_photo, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_title.getParent().getLayout()).setInsets(gui_title, "-2.380951mm 2.3809524mm auto 2.3809495mm").setReferenceComponents(gui_title, "0 0 -1 -1").setReferencePositions(gui_title, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_texter.getParent().getLayout()).setInsets(gui_texter, "0.0mm 0.0mm auto 0.0mm").setReferenceComponents(gui_texter, "1 1 -1 1 ").setReferencePositions(gui_texter, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container.getParent().getLayout()).setInsets(gui_Container, "0.0mm auto auto 0.0mm").setReferenceComponents(gui_Container, "2 -1 -1 1 ").setReferencePositions(gui_Container, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_comments.getParent().getLayout()).setInsets(gui_comments, "0.0mm -5.9604645E-8mm 0.0mm 0.0mm").setReferenceComponents(gui_comments, "3 0 5 0 ").setReferencePositions(gui_comments, "1.0 0.0 1.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Box_Layout_X.getParent().getLayout()).setInsets(gui_Box_Layout_X, "auto 0.0mm 0.0mm 0.0mm").setReferenceComponents(gui_Box_Layout_X, "-1 -1 -1 -1").setReferencePositions(gui_Box_Layout_X, "0.0 0.0 0.0 0.0");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}