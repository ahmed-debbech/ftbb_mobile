/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 package com.ftbb.mobile.news.gui;  
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Container;
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
import java.util.ArrayList;
/** 
 * GUI builder created Form
 * 
 * @author root
 */ 
public class ArticleViewForm extends com.codename1.ui.Form {
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
        
        for(Comment c : l){
            System.out.println("ccc ");
            gui_comments.add(new CommentView(c).getView());
        }
    }
    
    public ArticleViewForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }
    
////////////////////////////////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.components.ImageViewer gui_photo = new com.codename1.components.ImageViewer();
    protected com.codename1.components.SpanLabel gui_title = new com.codename1.components.SpanLabel();
    protected com.codename1.components.SpanLabel gui_texter = new com.codename1.components.SpanLabel();
    protected com.codename1.ui.Label gui_commentlab = new com.codename1.ui.Label();
    protected com.codename1.ui.Container gui_comments = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));


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
        gui_title.setInlineUnselectedStyles("font:16.0mm; fgColor:ff7e79; alignment:center;");
        gui_title.setName("title");
        gui_texter.setPreferredSizeStr("113.227516mm 88.88889mm");
        gui_texter.setText("texter");
        gui_texter.setUIID("texter");
                gui_texter.setInlineStylesTheme(resourceObjectInstance);
        gui_texter.setName("texter");
        gui_commentlab.setPreferredSizeStr("114.021164mm inherit");
        gui_commentlab.setText("Comments - 0");
        gui_commentlab.setUIID("com");
                gui_commentlab.setInlineStylesTheme(resourceObjectInstance);
        gui_commentlab.setName("commentlab");
        gui_comments.setPreferredSizeStr("118.51852mm 42.32804mm");
        gui_comments.setScrollableY(false);
                gui_comments.setInlineStylesTheme(resourceObjectInstance);
        gui_comments.setInlineAllStyles("bgColor:fce8d0; alignment:center;");
        gui_comments.setName("comments");
        addComponent(gui_photo);
        addComponent(gui_title);
        addComponent(gui_texter);
        addComponent(gui_commentlab);
        addComponent(gui_comments);
        ((com.codename1.ui.layouts.LayeredLayout)gui_photo.getParent().getLayout()).setInsets(gui_photo, "-0.26455116mm 0.0mm auto 0.5290985mm").setReferenceComponents(gui_photo, "-1 -1 -1 -1").setReferencePositions(gui_photo, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_title.getParent().getLayout()).setInsets(gui_title, "-2.380951mm 2.3809524mm auto 2.3809495mm").setReferenceComponents(gui_title, "0 0 -1 -1").setReferencePositions(gui_title, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_texter.getParent().getLayout()).setInsets(gui_texter, "0.0mm 0.0mm auto 0.0mm").setReferenceComponents(gui_texter, "1 1 -1 1 ").setReferencePositions(gui_texter, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_commentlab.getParent().getLayout()).setInsets(gui_commentlab, "0.0mm 0.0mm auto 0.0mm").setReferenceComponents(gui_commentlab, "2 1 -1 1 ").setReferencePositions(gui_commentlab, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_comments.getParent().getLayout()).setInsets(gui_comments, "0.0mm 0.0mm auto -0.52910054mm").setReferenceComponents(gui_comments, "3 0 -1 0 ").setReferencePositions(gui_comments, "1.0 0.0 0.0 0.0");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}