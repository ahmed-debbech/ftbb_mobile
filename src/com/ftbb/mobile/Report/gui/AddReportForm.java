/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.Report.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.ftbb.mobile.Report.models.Report;
import com.ftbb.mobile.Report.services.ReportService;
import com.codename1.ui.util.Resources;

/**
 *
 * @author narug
 */
public class AddReportForm extends Form{
    
    //Form current;
    public AddReportForm(){
       this.setLayout(BoxLayout.y());
       // Toolbar tb =new Toolbar(true);
       //current =this;
       //setToolbar(tb);
       //getTitleArea().setUIID("Container");
       this.setTitle("Add Report");
      // getContentPane().setScrollVisible(false);
       
       
        
        
        TextField tfcommandid = new TextField("", "Insert command id");
        tfcommandid.setUIID("TextFieldBlack");
        //addStringValue("tfcommandid",tfcommandid);
        
        TextField tfemail = new TextField("", "Insert email");
        tfemail.setUIID("TextFieldBlack");
        //addStringValue("tfemail",tfemail);
        
        
        TextField tfdesc=new TextField("","Insert desc");
        tfdesc.setUIID("TextFieldBlack");
        //addStringValue("tfdesc",tfdesc);
        
        Button submitReportBtn = new Button("Submit");
       // addStringValue("",submitReportBtn);
        
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
    public AddReportForm(int id){
        this.setTitle("Modify Report");
        this.setLayout(BoxLayout.y());
        
        Label ied = new Label("ID: " + id);
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
                r.setReport_id(id);
                r.setCommand_id(Integer.parseInt(tfcommandid.getText()));
                r.setEmail(tfemail.getText());
                r.setDescription(tfdesc.getText());
                   ReportService.getInstance().modifyNow(r);
                        Dialog.show("Success", "Report modified successfully.",null, "OK");
                    
                    
                } catch (NumberFormatException e) {
                    Dialog.show("Alert", "Report's status must be a number.",null, "OK");
                }
                
                
                
            }
            
            
            
        });
        
        
        this.addAll(ied, tfcommandid, tfemail,tfdesc, submitReportBtn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeReport().showBack());
        
    }
    
    
    /*
    private void addStringValue(String s,Component v){
    
        add(BorderLayout.west(new Label(s,"PaddedLabel")))
        .add(BorderLayout.CENTER,v);
        add(createLineSeparator(0xeeeeee));
        
        
    }
    */
    
    
}
