/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.Report.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.ftbb.mobile.Report.models.Feedback;
import com.ftbb.mobile.Report.models.Report;
import com.ftbb.mobile.Report.services.FeedbackService;
import com.ftbb.mobile.Report.services.ReportService;
import java.util.ArrayList;

/**
 *
 * @author narug
 */
public class ListReportsForm extends Form {

    public ListReportsForm() {
        
        this.setTitle("Reports List");
        this.setLayout(BoxLayout.y());
        
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeReport().showBack());
        
        ArrayList<Report> ar = ReportService.getInstance().getReports();
        Resources theme = UIManager.initFirstTheme("/reportui");
        //UIBuilder.registerCustomComponent("ImageViewer",  com.codename1.components.ImageViewer.class);
        UIBuilder ui = new UIBuilder();
        
        for(Report c : ar){
            Container cont = ui.createContainer(theme, "report");
            Container inside = (Container) cont.getComponentAt(0);
            
            Label l = (Label)inside.getComponentAt(0);
            l.setText(c.getReport_id() + "");
            Label l1 = (Label)inside.getComponentAt(1);
            l1.setText(c.getReport_date());
            Label l3 = (Label)inside.getComponentAt(2);
            l3.setText(c.getDescription());
            Label l4 = (Label)inside.getComponentAt(3);
            l4.setText(c.getCommand_id() + "");
            Container cce = (Container)cont.getComponentAt(1);
            Button bb = (Button) cce.getComponentAt(0);
            bb.addActionListener((e) -> {
                ReportService.getInstance().delete(c.getReport_id());
                                        Dialog.show("Success", "Deleted successfully!",null, "OK");

            });
            Button b3b = (Button) cce.getComponentAt(1);
            b3b.addActionListener((e) -> {
                System.out.println("pass");
                ReportService.getInstance().modify(c.getReport_id());

            });
            this.add(cont);
        }
    }
    
    public static void redirect(int id){
        new AddReportForm(id).show();
    }
    
    
    
    
}
