/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.List;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import models.Poll;
import services.pollService;

/**
 *
 * @author sbs
 */
public class endedPoll extends Form{
    public endedPoll()
    {
        
        this.setTitle("Ended Poll");
        this.setLayout(BoxLayout.y());
        
        
        Resources theme = UIManager.initFirstTheme("/pollUI");
        UIBuilder ui = new UIBuilder();
        Container cnt = ui.createContainer(theme, "pollview");
        ArrayList <Poll> list = pollService.getInstance().getAllPolls();
        
//        for(Poll c : list){
//            this.add(cnt);
//        }
        
//        SpanLabel tasksListSP = new SpanLabel();
       // tasksListSP.setText(pollService.getInstance().getAllPolls().toString());
//        
//        this.add(tasksListSP);
        
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new activePoll().showBack());
        
        
    }
}
