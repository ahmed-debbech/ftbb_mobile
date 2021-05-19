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
import com.codename1.ui.Slider;
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
public class endedPoll extends Form {

    ArrayList<Poll> list = new ArrayList<Poll>();
    ArrayList<Options> optionlist = new ArrayList<Options>();
    ArrayList<Vote> votelist = new ArrayList<Vote>();

    public endedPoll() {

        this.setTitle("Ended Poll");
        this.setLayout(BoxLayout.y());

        Resources theme = UIManager.initFirstTheme("/activepoll");
        UIBuilder ui = new UIBuilder();

        list = pollService.getInstance().getAllPolls();
        optionService os = new optionService();
        voteService vs = new voteService();
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStatus().equals("Ended")) {
                Container cnt = ui.createContainer(theme, "endedpoll");
                TextArea txt = (TextArea) cnt.getChildrenAsList(focusScrolling).get(0);
                txt.setText(list.get(i).getDescription());
                optionlist = os.getAllOptions(list.get(i).getPoll_id());

//        +++++++++++++++++++++++++++++++ calcule result++++++++++++++++++++++++++++++++++++++++       
        int VoteNbr1=0;
        int VoteNbr2=0;
        int totalvote=0;
        int rslt1=0;
        int rslt2=0;
                for (int y = 0; y < optionlist.size(); y++) {
                votelist = vs.getAllVotes(optionlist.get(y).getOption_id());
                if (y==0){VoteNbr1 = votelist.get(0).getVote_nbr();}
                else{VoteNbr2 = votelist.get(0).getVote_nbr();}
                }
        totalvote = VoteNbr1 + VoteNbr2;
        rslt1 = (VoteNbr1 *100)/(totalvote);
        rslt2 = (VoteNbr2 *100)/(totalvote);
                System.out.println(rslt1+" / "+rslt2);
//        +++++++++++++++++++++++++++++++ calcule result++++++++++++++++++++++++++++++++++++++++ 

                for (int y = 0; y < optionlist.size(); y++) {

                    Container cont = (Container) cnt.getChildrenAsList(focusScrolling).get(y + 1);

                    TextField opt = (TextField) cont.getChildrenAsList(focusScrolling).get(1);
                    opt.setText(optionlist.get(y).getDescription());
                    
                    votelist = vs.getAllVotes(optionlist.get(y).getOption_id());
                    if (y==0){Slider rslt = (Slider) cont.getChildrenAsList(focusScrolling).get(0);
                            rslt.setProgress(rslt1);
                            
                            
                               }
                    else {Slider rslt = (Slider) cont.getChildrenAsList(focusScrolling).get(0);
                            rslt.setProgress(rslt2);}}
                     
                
                    this.add(cnt);
                }
            }

//        SpanLabel tasksListSP = new SpanLabel();
//       tasksListSP.setText(pollService.getInstance().getAllPolls().toString());
//        
//        this.add(tasksListSP);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> new activePoll().showBack());

        }
    }
