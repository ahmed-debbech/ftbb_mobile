/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import models.Options;
import models.Poll;
import services.optionService;
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
        
        
//        Resources theme = UIManager.initFirstTheme("/theme");
//        UIBuilder ui = new UIBuilder();
//        Container cnt = ui.createContainer(theme, "endedpoll_cont");
        ArrayList <Poll> list = pollService.getInstance().getAllPolls();
        ArrayList <Options> optionlist = optionService.getInstance().getAllOptions();
        for(Poll c : list){
       //     this.add(cnt);

            Label l = new Label();
            l.setText(c.getDescription()); 
            this.add(l);
        
        for (Options o : optionlist)
        {
               System.out.println(o.getPoll_id());
//            if (c.getPoll_id() == o.getPoll_id())
//            Label s = new Label();
//            s.setText(o.getDescription()); 
//            this.add(s);
   }



        }
        
//        SpanLabel tasksListSP = new SpanLabel();
//       tasksListSP.setText(pollService.getInstance().getAllPolls().toString());
//        
//        this.add(tasksListSP);
        
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new activePoll().showBack());
        
        
    }
}
