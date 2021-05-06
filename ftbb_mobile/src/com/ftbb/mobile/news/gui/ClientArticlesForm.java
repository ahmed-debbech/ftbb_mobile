package com.ftbb.mobile.news.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.ftbb.mobile.news.services.ServiceArticle;
import com.ftbb.mobile.news.entity.Article;
import com.ftbb.mobile.news.utils.Utilities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ClientArticlesForm extends Form{
    Form current;
    public ClientArticlesForm(){
        current = this;
        setTitle("Articles");
        setLayout(new BorderLayout());
        setScrollableY(true);
        ArrayList<Article> l = ServiceArticle.getInstance().getAllArticles();
        
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);
        int i = 0;
        for(Article x : l){
            Image image = URLImage.createToStorage(placeholder, "icon"+i, x.getPhoto_url());
            data.add(createListEntry(x.getTitle(), x.getDate().toString(), x.getText(), image));
            i++;
        }
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList list = new MultiList(model);
        
        
        super.add(BorderLayout.CENTER, list);
    }
    private Map<String, Object> createListEntry(String title, String date, String text, Image icon) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", title);
        entry.put("Line2", text);
        entry.put("Line3", Utilities.dateOnly(date));
        entry.put("Line4", "0 - likes    0 - comments");
        entry.put("icon", icon);
        return entry;
    }
}