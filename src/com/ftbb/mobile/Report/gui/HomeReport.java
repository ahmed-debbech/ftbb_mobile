/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.Report.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import gui.MainForm;

/**
 *
 * @author narug
 */
public class HomeReport extends Form {
    
    
    public HomeReport(){
        Resources theme=UIManager.initFirstTheme("/reportui");
      this.setTitle("Home Report");
        this.setLayout(BoxLayout.y());
        
        Button addReportBtn = new Button("Add Report");
        addReportBtn.setUIID("HomeForAllButtons");
        Button listReportsBtn = new Button("Reports List");
        listReportsBtn.setUIID("HomeForAllButtons");
        
        addReportBtn.addActionListener(e -> new AddReportForm().show());
        listReportsBtn.addActionListener(x-> new ListReportsForm().show());
        
        this.addAll(addReportBtn, listReportsBtn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new MainForm().showBack());
    }
}
