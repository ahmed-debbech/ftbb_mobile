/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import entities.Client;
import services.ServiceClient;

/**
 *
 * @author Yassine
 */
public class SigninForm extends Form{
    public SigninForm () {
        
         this.setTitle("Sign in");
        this.setLayout(BoxLayout.y());
        UIManager.initFirstTheme("/mainui");
        Label email = new Label("Enter your email");
        TextField tfemail = new TextField("", "inserer email",255, TextArea.EMAILADDR);
        tfemail.setUIID("logt");
        Label pass = new Label("Enter your password");
        TextField tfpass = new TextField("","saisir votre password",20, TextField.PASSWORD);
        tfpass.setUIID("logt");
        Button signinBtn = new Button("Sign in");
        signinBtn.setUIID("logb");
        Button signupBtn = new Button("Sign up");
        signupBtn.setUIID("logb");
        signupBtn.addActionListener(l->new SignupForm().show());
        this.addAll(email,tfemail,pass,tfpass,signinBtn,signupBtn);
        signinBtn.addActionListener((l) -> {
          
            if (tfemail.getText().length() == 0 || tfpass.getText().length() == 0) {
                Dialog.show("Alert", "Email or Password incorrect", null, "OK");
            } else {
                try {
                    Client cl = new Client();
                    
                    cl.setEmail(tfemail.getText());
                    ServiceClient.getClient(String.valueOf(tfemail.getText()));
                    cl.setPassword(tfpass.getText());
                    if (new ServiceClient ().signin(cl)){
                     new MainForm().show();
                    }
//                    Dialog.show("Success", " login succés", null, "OK");                     
//                    
//                    } else {
//                     Dialog.show("Alert", "Try Again", null, "OK");
//                    }
                
                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        });
    }
}
