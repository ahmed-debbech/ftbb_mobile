/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.news.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.ftbb.mobile.news.entity.Article;

/**
 * GUI builder created Form
 *
 * @author root
 */
public class ArticleViewForm extends com.codename1.ui.Form {

    public ArticleViewForm(Article a, String photofile) {
        this(com.codename1.ui.util.Resources.getGlobalResources());
        setScrollableY(true);
        gui_title.setText(a.getTitle());
        setTitle(a.getTitle());
        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);
        Image image = URLImage.createToStorage(placeholder, photofile, a.getPhoto_url());
        gui_Image_Viewer.setImage(image);
        
    }
    
    public ArticleViewForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.Label gui_title = new com.codename1.ui.Label();
    protected com.codename1.components.ImageViewer gui_Image_Viewer = new com.codename1.components.ImageViewer();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(true);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("ArticleViewForm");
        setName("ArticleViewForm");
        gui_title.setPreferredSizeStr("114.021164mm 19.047619mm");
        gui_title.setText("title");
        gui_title.setUIID("title");
                gui_title.setInlineStylesTheme(resourceObjectInstance);
        gui_title.setInlineAllStyles("bgColor:ff7e79; fgColor:ff7e79;");
        gui_title.setName("title");
        gui_title.setGap(2);
        gui_Image_Viewer.setPreferredSizeStr("114.55026mm 50.0mm");
        gui_Image_Viewer.setUIID("photo");
                gui_Image_Viewer.setInlineStylesTheme(resourceObjectInstance);
        gui_Image_Viewer.setName("Image_Viewer");
        addComponent(gui_title);
        addComponent(gui_Image_Viewer);
        ((com.codename1.ui.layouts.LayeredLayout)gui_title.getParent().getLayout()).setInsets(gui_title, "28.794992% 2.3809521mm auto 3.1746032mm").setReferenceComponents(gui_title, "-1 -1 -1 -1").setReferencePositions(gui_title, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Image_Viewer.getParent().getLayout()).setInsets(gui_Image_Viewer, "2.6455026mm 0.0mm -5.026456mm 0.0mm").setReferenceComponents(gui_Image_Viewer, "-1 0 0 0 ").setReferencePositions(gui_Image_Viewer, "0.0 0.0 1.0 0.0");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
