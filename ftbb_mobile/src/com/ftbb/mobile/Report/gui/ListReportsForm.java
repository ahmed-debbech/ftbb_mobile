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
import com.ftbb.mobile.Report.services.ReportService;

/**
 *
 * @author narug
 */
public class ListReportsForm extends Form {

    public ListReportsForm() {
        
        this.setTitle("Reports List");
        this.setLayout(BoxLayout.y());
        
        SpanLabel reportsListSP = new SpanLabel();
        reportsListSP.setText(ReportService.getInstance().getReports().toString());
        
        this.add(reportsListSP);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeReport().showBack());
    }
    
    
    
    
}
