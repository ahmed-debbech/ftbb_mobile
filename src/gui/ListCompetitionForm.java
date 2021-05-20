/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.Storage;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Competition;
import services.CompetitionService;

/**
 *
 * @author Lenovo
 */
public class ListCompetitionForm extends Form{
   
    
    List<Competition> listcom = new ArrayList<>();
    
    public ListCompetitionForm(){
        
        
        this.setTitle("Competition List");
        this.setLayout(BoxLayout.y());
        
        
        
        SpanLabel tasksListSP = new SpanLabel();
      //  tasksListSP.setText(.toString()); 
        listcom  = CompetitionService.getInstance().getCompetitions();
        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);
        Image icon1 = URLImage.createToStorage(placeholder, "icon1", "http://www.georgerrmartin.com/wp-content/uploads/2013/03/GOTMTI2.jpg");
        Image icon2 = URLImage.createToStorage(placeholder, "icon2", "https://www.georgerrmartin.com/wp-content/uploads/2013/03/stormswordsMTI.jpg");
        Image icon3 = URLImage.createToStorage(placeholder, "icon3", "http://www.georgerrmartin.com/wp-content/uploads/2013/03/stormswordsMTI.jpg");
        Image icon4 = URLImage.createToStorage(placeholder, "icon4", "http://www.georgerrmartin.com/wp-content/uploads/2012/08/feastforcrows.jpg");
        Image icon5 = URLImage.createToStorage(placeholder, "icon5", "http://georgerrmartin.com/gallery/art/dragons05.jpg");
  
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        
        for (Competition com : listcom) {
            System.out.println(com.getName());
            data.add(createListEntry(com.getName(),icon2));
        }
        
       

        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        this.add( ml);  
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeFormComp().showBack());

}

private Map<String, Object> createListEntry(String name , Image icon2) {
  Map<String, Object> entry = new HashMap<>();
  entry.put("Line1", name);
  entry.put("icon", icon2);

  return entry;
}        
    }    

