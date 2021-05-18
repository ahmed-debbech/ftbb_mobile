/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;

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
import models.Vote;
import services.optionService;
import services.pollService;
import services.voteService;

/**
 *
 * @author sbs
 */
public class activePoll extends Form {

    ArrayList<Poll> list = new ArrayList<Poll>();
    ArrayList<Options> optionlist;
    ArrayList<Vote> votelist = new ArrayList<Vote>();

    public activePoll() {

        this.setTitle("Active Poll");
        this.setLayout(BoxLayout.y());
        Button endedpollBtn = new Button("Ended Poll");

        Resources theme = UIManager.initFirstTheme("/activepoll");
        UIBuilder ui = new UIBuilder();

        list = pollService.getInstance().getAllPolls();

        voteService vs = new voteService();
        optionService os = new optionService();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStatus().equals("Active")) {

                Container cnt = ui.createContainer(theme, "activepoll");
                TextArea txt = (TextArea) cnt.getChildrenAsList(focusScrolling).get(0);
                txt.setText(list.get(i).getDescription());
                optionlist = os.getAllOptions(list.get(i).getPoll_id());
                for (int y = 0; y < optionlist.size(); y++) {
                    Container cont = (Container) cnt.getChildrenAsList(focusScrolling).get(y + 1);
                    TextField opt1 = (TextField) cont.getChildrenAsList(focusScrolling).get(1);
                    opt1.setText(optionlist.get(y).getDescription());
                    Button btn;
                    btn = (Button) cont.getChildrenAsList(focusScrolling).get(0);
                    int optid = optionlist.get(y).getOption_id();
                    btn.addActionListener(a -> {
                       vs.addvote(optid);
                    });

                }
                this.add(cnt);
            }
        }
        endedpollBtn.addActionListener(e -> new endedPoll().show());

        this.addAll(endedpollBtn);

    }

}
