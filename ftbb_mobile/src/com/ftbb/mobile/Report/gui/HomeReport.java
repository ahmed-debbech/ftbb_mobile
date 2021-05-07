/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.Report.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author narug
 */
public class HomeReport extends Form {
    
    
    public HomeReport(){
      this.setTitle("Home Report");
        this.setLayout(BoxLayout.y());
        
        Button addReportBtn = new Button("Add Report");
        Button listReportsBtn = new Button("Reports List");
        
        addReportBtn.addActionListener(e -> new AddReportForm().show());
        listReportsBtn.addActionListener(x-> new ListReportsForm().show());
        
        this.addAll(addReportBtn, listReportsBtn);
    }
}
