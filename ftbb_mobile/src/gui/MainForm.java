/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;

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
        Button news = new Button("News");
        news.setUIID("main_news_but");
         Button store = new Button("Store");
        store.setUIID("main_news_but");
        Button polls = new Button("Polls");
        polls.setUIID("main_news_but");
        polls.addActionListener((e) -> {
            new activePoll().show();
        });
        this.addAll(news, store, polls);
    }
    
    public MainForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//////////////////////-- DON'T EDIT BELOW THIS LINE!!!


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
