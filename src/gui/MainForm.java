/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.ftbb.mobile.Report.gui.GalerieView;
import com.ftbb.mobile.Report.gui.HomeFeedback;
import com.ftbb.mobile.Report.gui.HomeReport;
import com.ftbb.mobile.gui.ListProductForm;
import com.ftbb.mobile.news.gui.ClientArticlesForm;
import utils.UserManager;

/**
 * GUI builder created Form
 *
 * @author Ahmed
 */
public class MainForm extends com.codename1.ui.Form {

    public MainForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
        UIManager.initFirstTheme("/mainui");
        setLayout(BoxLayout.y());
        System.out.println("iddd " + UserManager.getInstance().getClient().getName());
        Label wel = new Label("Welcome back "+UserManager.getInstance().getClient().getName()+"!");
        wel.setUIID("welcomming");
        Button news = new Button("Latest news!");
        news.setUIID("main_news_but");
        news.addActionListener((e) -> {
            new ClientArticlesForm().show();
        });
         Button store = new Button("Store");
        store.setUIID("main_news_but");
        store.addActionListener((e) -> {
            new ListProductForm(null).show();
        });
        Button polls = new Button("Vote on polls");
        polls.setUIID("main_news_but");
        polls.addActionListener((e) -> {
            new activePoll().show();
        });
        Button report = new Button("Submit a report");
        report.setUIID("main_news_but");
        report.addActionListener((e) -> {
           new HomeReport().show();
        });
        Button galery = new Button("See latest galeries");
        galery.setUIID("main_news_but");
        galery.addActionListener((e) -> {
           new GalerieView().show();
        });
        Button comp = new Button("Show upcomming matches and scores");
        comp.setUIID("main_news_but");
        comp.addActionListener((e) -> {
           new HomeFormComp().show();
        });
        Button feedback = new Button("Give us your opinion");
        feedback.setUIID("main_news_but");
        feedback.addActionListener((e) -> {
           new HomeFeedback().show();
        });
        Button dec = new Button("Disconnet");
        dec.setUIID("dec");
        dec.addActionListener((e) -> {
           new HomeFeedback().show();
        });
        
        this.addAll(wel,news, store, polls, report, galery, comp, feedback, dec);
    }
    
    public MainForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MainForm");
        setName("MainForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
