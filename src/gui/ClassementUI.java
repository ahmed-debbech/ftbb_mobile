/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import static com.codename1.io.Log.e;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Competition;
import models.*;
import models.Phase;
import services.ClassementService;
import services.CompetitionService;
import services.GameService;
import services.PhaseService;


import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;

/**
 *
 * @author Lenovo
 */
public class ClassementUI extends Form {
    
        List<Competition> competitions = new ArrayList<>() ;   
        List<Phase> phases = new ArrayList<>(); 
         List<Classementf> classement = new ArrayList<>(); 
       //  SpanLabel initLabel = new SpanLabel();
  
         public ClassementUI()
         {
             this.setTitle("Classement List");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
         Button devGuide = new Button("Show PDF");
   
devGuide.addActionListener(e -> {
    FileSystemStorage fs = FileSystemStorage.getInstance();
    String fileName = fs.getAppHomePath() + "pdf-sample.pdf";
    if(!fs.exists(fileName)) {
        Util.downloadUrlToFile("http://127.0.0.1:8000/PDF/pdf-sample.pdf", fileName, true);
    }
    Display.getInstance().execute(fileName);
});  
        
        
         competitions =  CompetitionService.getInstance().getCompetitions();
         phases =  PhaseService.getInstance().getPhases();
        
         ComboBox<Map<String, String>> comboCompetition = new ComboBox<>();
         ComboBox<Map<String, String>> comboPhase = new ComboBox<>();
        
         
                 for(Phase p : phases)
        {  
            Map<String, String> entry = new HashMap<>();
            entry.put("id", Integer.toString(p.getId()));
             entry.put("name", p.getName());
            comboPhase.addItem(entry);      
        }
        for(Competition c : competitions)
        {  
            Map<String, String> entry = new HashMap<>();
           entry.put("id", Integer.toString(c.getId()));
             entry.put("name", c.getName());
             comboCompetition.setName(c.getName());
            comboCompetition.addItem(entry);      
        }
        
       classement = ClassementService.getInstance().getClassement(Integer.parseInt(comboCompetition.getSelectedItem().get("id")) ,Integer.parseInt(comboPhase.getSelectedItem().get("id")));     
        
     
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        
        for (Classementf com : classement) {
    
                EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(150, 150, comboCompetition.CENTER), true);
		Image airf_serv = URLImage.createToStorage(placeholder, com.getName_team(), "http://127.0.0.1:8000/uploads/logo/"+com.getLogo_team(), URLImage.RESIZE_SCALE);
                
                 data.add(createListEntry(com.getName_team(),airf_serv, com.getNbr_pts(), com.getNbr_pts_P(), com.getNbr_pts_G(), com.getNbr_pts_D(), com.getPts_diff()));
        }
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
    
         comboCompetition.addActionListener(new ActionListener() {
    @Override 
    public void actionPerformed(ActionEvent evt) {
        classement.clear();
        classement =  ClassementService.getInstance().getClassement(Integer.parseInt(comboCompetition.getSelectedItem().get("id")) ,Integer.parseInt(comboPhase.getSelectedItem().get("id")));
        
        data.clear();       
        for (Classementf com : classement) {
            System.out.println(com.getName_team());
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(150, 150, comboCompetition.CENTER), true);
		Image airf_serv = URLImage.createToStorage(placeholder, com.getName_team(), "http://127.0.0.1:8000/uploads/logo/"+com.getLogo_team(), URLImage.RESIZE_SCALE);
                
                 data.add(createListEntry(com.getName_team(),airf_serv, com.getNbr_pts(), com.getNbr_pts_P(), com.getNbr_pts_G(), com.getNbr_pts_D(), com.getPts_diff()));
        }
        
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
          ml.setModel(model);
       
    }
    
    }); 
          
          
           comboPhase.addActionListener(new ActionListener() {
    @Override 
    public void actionPerformed(ActionEvent evt) {
        classement.clear();
        classement =  ClassementService.getInstance().getClassement(Integer.parseInt(comboCompetition.getSelectedItem().get("id")) ,Integer.parseInt(comboPhase.getSelectedItem().get("id")));
        
        data.clear();       
        for (Classementf com : classement) {
            System.out.println(com.getName_team());
             EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(150, 150, comboCompetition.CENTER), true);
		Image airf_serv = URLImage.createToStorage(placeholder, com.getName_team(), "http://127.0.0.1:8000/uploads/logo/"+com.getLogo_team(), URLImage.RESIZE_SCALE);
                
                 data.add(createListEntry(com.getName_team(),airf_serv , com.getNbr_pts(), com.getNbr_pts_P(), com.getNbr_pts_G(), com.getNbr_pts_D(), com.getPts_diff()));
        }
        
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
          ml.setModel(model);
       
    }
    
    }); 
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
             System.out.println(comboCompetition);
       this.add(comboCompetition);
        this.add(comboPhase); 
        this.add("---------");
        this.add(ml);
        this.add("---------");
        this.add(devGuide);
         }
         
   private Map<String, Object> createListEntry(String name ,Image img , int  pts , int ptsP , int ptsG , int ptsD , int diff ) {
  Map<String, Object> entry = new HashMap<>();
  entry.put("Line1", name);
  entry.put("Line2","NBR_pts ptsP ptsG ptsD diff");
  entry.put("Line3","     " +pts + "        " + ptsP + "        " + ptsG + "        " + ptsD + "     " + diff);
  entry.put("icon", img);

  return entry;
}     
            
       
}
