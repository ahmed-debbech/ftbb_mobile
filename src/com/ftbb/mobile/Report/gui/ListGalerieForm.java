/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.Report.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.ftbb.mobile.Report.services.GalerieService;

/**
 *
 * @author narug
 */
public class ListGalerieForm extends Form {

    public ListGalerieForm() {
        
        this.setTitle("Galerie");
        this.setLayout(BoxLayout.y());
        
        //SpanLabel galerieListSP = new SpanLabel();
        //galerieListSP.setText(GalerieService.getInstance().getReports().toString());
        
       // this.add(galerieListSP);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeReport().showBack());
    }
    
    
    
}
