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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
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
public class endedPoll extends Form {
    ArrayList<Poll> list=new ArrayList<Poll>();
    ArrayList<Options> optionlist = new ArrayList<Options>();
    public endedPoll() {
        this.removeAll();
        this.setTitle("Ended Poll");
        this.setLayout(BoxLayout.y());

        Resources theme = UIManager.initFirstTheme("/activepoll");
        UIBuilder ui = new UIBuilder();
       
        list = pollService.getInstance().getAllPolls();
        
        optionService os = new optionService();
        for (int i = 0; i < list.size(); i++) {
            Container cnt = ui.createContainer(theme, "activepoll");
        TextArea txt=(TextArea)cnt.getChildrenAsList(focusScrolling).get(0);
        txt.setText(list.get(i).getDescription());
            optionlist = os.getAllOptions(list.get(i).getPoll_id());
            for (int y = 0; y < optionlist.size(); y++) {
                Container cont=(Container)cnt.getChildrenAsList(focusScrolling).get(y+1);
                TextField opt1=(TextField)cont.getChildrenAsList(focusScrolling).get(1);
                opt1.setText(optionlist.get(y).getDescription());
                

            }
            this.add(cnt);
        }

//        SpanLabel tasksListSP = new SpanLabel();
//       tasksListSP.setText(pollService.getInstance().getAllPolls().toString());
//        
//        this.add(tasksListSP);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> new activePoll().showBack());

    }
}
