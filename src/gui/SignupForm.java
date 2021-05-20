/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import entities.Client;
import javafx.scene.control.PasswordField;
import services.ServiceClient;

/**
 *
 * @author Yassine
 */
public class SignupForm extends Form {

    public SignupForm() {
        this.setTitle("Sign up");
        this.setLayout(BoxLayout.y());
        Button signupBtn = new Button("Sign up");
        
        TextField tfname = new TextField("", "inserer name");
        TextField tfsurname = new TextField("", "inserer surname");
        TextField tfsex = new TextField("", "Male or Femme");
        TextField tfbirthday = new TextField("", "inserer birthday sous forme ****-**-**");
        TextField tfemail = new TextField("", "inserer email", 255, TextArea.EMAILADDR);
        TextField tfpass = new TextField("", "saisir votre password", 20, TextField.PASSWORD);
        TextField tfnumber = new TextField("", "inserer votre numero", 8, TextArea.NUMERIC);
        this.addAll(tfname, tfsurname, tfemail, tfpass, tfnumber, tfbirthday, tfsex, signupBtn);
        signupBtn.addActionListener((l) -> {
          
            if (tfname.getText().length() == 0 || tfsurname.getText().length() == 0 || tfsex.getText().length() ==0 || tfbirthday.getText().length() != 10
                    || tfemail.getText().length() == 0 || tfpass.getText().length() == 0 || tfnumber.getText().length() != 8) {
                Dialog.show("Alert", "Erreur sur l'insertion", null, "OK");
            } else {
                try {
                    Client cl = new Client();
                    cl.setName(tfname.getText());
                    cl.setSurname(tfsurname.getText());
                    cl.setEmail(tfemail.getText());
                    cl.setSex(tfsex.getText());
                    cl.setPassword(tfpass.getText());
                    cl.setNumber(Integer.parseInt(tfnumber.getText()));
                    cl.setBirthday(tfbirthday.getText());
                    if (new ServiceClient ().signup(cl)){
                        new SigninForm().show();
                    Dialog.show("Success", "account successfully registered please login", null, "OK");                     
                    
                    } else {
                     Dialog.show("Alert", "Try Again", null, "OK");
                    }
                
                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> new SigninForm().showBack());

    }
}
