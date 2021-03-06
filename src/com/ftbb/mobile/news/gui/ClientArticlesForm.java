package com.ftbb.mobile.news.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.ftbb.mobile.news.services.ServiceArticle;
import com.ftbb.mobile.news.entity.Article;
import com.ftbb.mobile.news.utils.Utilities;
import gui.MainForm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ClientArticlesForm extends Form{
    Form current;
    String [] files = new String[500];
    public ClientArticlesForm(){
        current = this;
        setTitle("Articles");
        setLayout(BoxLayout.y());
        setScrollableY(true);
        UIManager.initFirstTheme("/theme");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new MainForm().show());
        Picker p = new Picker();
        p.setStrings("Select Sort...","Most commented", "Most Liked", "Newest");
        p.setSelectedString("Select Sort...");
        ArrayList<Article> l= null;
        p.addActionListener((e) -> {
            ArrayList<Article> tt;
            if(p.getSelectedString().equals("Most Commented")){
                tt = ServiceArticle.getInstance().getAllArticles(0);
            }else{
                if(p.getSelectedString().equals("Most Liked")){
                    tt = ServiceArticle.getInstance().getAllArticles(1);
                }else{
                    if(p.getSelectedString().equals("Newest")){
                        tt = ServiceArticle.getInstance().getAllArticles(2);
                    }
                }
            }
        });
        if(p.getSelectedString().equals("Select Sort...")){
            l = ServiceArticle.getInstance().getAllArticles();
        }
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);
        int i = 0;
        for(Article x : l){
            Image image = URLImage.createToStorage(placeholder, "icon"+i, x.getPhoto_url());
            files[i] = "icon"+i;
            data.add(createListEntry(x.getArticle_id(), x.getTitle(), x.getDate().toString(), x.getText(), image, x.getComment_count(), x.getLikes()));
            i++;
        }
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList list = new MultiList(model);
        
        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Map<String, Object> value = (Map<String, Object>)list.getSelectedItem();
                Article aa = new Article();
                aa.setArticle_id(Integer.parseInt(value.get("id").toString()));
                aa.setTitle(value.get("Line1").toString());
                aa.setText(value.get("Line2").toString());
                aa.setLikes(Integer.parseInt(value.get("num_like").toString()));
                new ArticleViewForm(aa, files[list.getSelectedIndex()]).show();
            }
        });
        this.add(p);
        this.add( list);
    }
    private Map<String, Object> createListEntry(int id, String title, String date, String text, Image icon, int com, int like) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", title);
        entry.put("Line2", text);
        entry.put("Line3", Utilities.dateOnly(date));
        entry.put("Line4", like+" - likes    "+com+" - comments");
        entry.put("icon", icon);
        entry.put("num_like", like);
        entry.put("id", id);
        return entry;
    }
}