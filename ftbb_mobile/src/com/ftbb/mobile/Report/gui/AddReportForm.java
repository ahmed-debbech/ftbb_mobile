/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.Report.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.ftbb.mobile.Report.models.Report;
import com.ftbb.mobile.Report.services.ReportService;

/**
 *
 * @author narug
 */
public class AddReportForm extends Form{
    
    
    public AddReportForm(){
        this.setTitle("Add Report");
        this.setLayout(BoxLayout.y());
        
        TextField tfcommandid = new TextField("", "Insert command id");
        TextField tfemail = new TextField("", "Insert email");
        TextField tfdesc=new TextField("","Insert desc");
        
        Button submitReportBtn = new Button("Submit");
        submitReportBtn.addActionListener((evt) -> {
            
            if (tfcommandid.getText().length() ==0 || tfemail.getText().length()==0 || tfdesc.getText().length()==0 ) {
                Dialog.show("Alert", "Textfields cannot be empty.",null, "OK");
            }else {
                
                try {
                    
                Report r= new Report();
                r.setCommand_id(Integer.parseInt(tfcommandid.getText()));
                r.setEmail(tfemail.getText());
                r.setDescription(tfdesc.getText());
                    if (ReportService.getInstance().addReportAction(r)) {
                        Dialog.show("Success", "Report added successfully.",null, "OK");
                    }
                    
                } catch (NumberFormatException e) {
                    Dialog.show("Alert", "Report's status must be a number.",null, "OK");
                }
                
                
                
            }
            
            
            
        });
        
        this.addAll(tfcommandid, tfemail,tfdesc, submitReportBtn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeReport().showBack());
        
        
    }
    
}
