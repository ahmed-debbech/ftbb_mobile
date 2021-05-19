/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.Report.gui;

import com.codename1.components.ShareButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.share.EmailShare;
import com.codename1.share.FacebookShare;
import com.codename1.share.SMSShare;
import static com.codename1.ui.CN.share;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.IconHolder;
import com.codename1.ui.Image;
import com.codename1.ui.List;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.ftbb.mobile.Report.models.Galerie;
import com.ftbb.mobile.Report.services.GalerieService;
import gui.MainForm;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


/**
 *
 * @author narug
 */
public class GalerieView extends Form {
   
    
   
    public GalerieView() {
      
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new MainForm().showBack());
        ArrayList <Galerie> list =GalerieService.getInstance().getGaleries();
        int i=0;
        
        for(Galerie g:list){
            
        com.codename1.components.ImageViewer gui_photo = new com.codename1.components.ImageViewer();
        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 8, mm * 10, 0), false);
        Image image = URLImage.createToStorage(placeholder, "image"+i, g.getPhotoUrl());
        gui_photo.setImage(image);
        i++;
        com.codename1.components.SpanLabel gui_title = new com.codename1.components.SpanLabel();
        gui_title.setText(g.getPhotoTitle());
        com.codename1.components.SpanLabel gui_texter = new com.codename1.components.SpanLabel();
        gui_texter.setText(g.getDescription());
        add(gui_photo);
        add(gui_title);
       
        add(gui_texter);
        ShareButton sb=new ShareButton();
        sb.setText("Share");
        sb.setUIID("HomeForAllButtons");
        add(sb);
       
        this.revalidate();
        this.setVisible(true);
         // this.paintComponent(image.getGraphics(), true);
        String imageFile = FileSystemStorage.getInstance().getAppHomePath() + g.getPhotoUrl();
        try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
            ImageIO.getImageIO().save(image, os, ImageIO.FORMAT_PNG, 1);
        } catch(IOException err) {
            Log.e(err);
        }
        sb.setImageToShare(imageFile, "image/JPG");
        
        
        
        }
    }
    
    
    
}
