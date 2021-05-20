/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.Statics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.*;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import entities.Client;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import utils.UserManager;


/**
 *
 * @author Yassine
 */
public class ServiceClient {
//    public static ServiceClient instance =null;
    public static boolean resultOK=false;
    private static Client c =new Client() ;

    public static Client getC() {
        return c;
    }

    public static void setC(Client d) {
        ServiceClient.c = d;
    }
    
//    private ConnectionRequest req;
//    public static ServiceClient getInstance (){
//        if (instance==null)
//            instance =new ServiceClient();
//        return instance;
//    }
//    public ServiceClient () {
//        req = new ConnectionRequest();
//    }
    //signup?name=ali&surname=ali&number=22345678&birthday=2022-04-03&sex=Male&password=123456&email=aliali@gmail.com
    
    public static  void getClient(String email){
        String url = Statics.BASE_URL+"/client/api/get/"+email;
       ConnectionRequest req = new ConnectionRequest (url);
        req.setPost(false);
        req.addResponseListener((evt)->{
            JSONParser j = new JSONParser();
                Map<String, Object> list;
                try {
                    String vv = new String(req.getResponseData());
                    list = j.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    ArrayList<Map<String,Object>> dd = (ArrayList<Map<String,Object>>) list.get("root");
                    String val = String.valueOf((int) Double.parseDouble(dd.get(0).get("id").toString()));
                    System.out.println("value " + val);
                    UserManager.getInstance().getClient().setId(Integer.parseInt(val));
                    UserManager.getInstance().getClient().setName(dd.get(1).get("name").toString());
                } catch (IOException ex) {
                }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public boolean signup (Client t) {
        String url = Statics.BASE_URL+"/client/api/signup?name="+t.getName()+"&surname="+t.getSurname()
                +"&number="+t.getNumber()+"&birthday="+t.getBirthday()+"&sex="+t.getSex()+"&password="+t.getPassword()+"&email="+t.getEmail();
       ConnectionRequest req = new ConnectionRequest (url);
        req.setPost(false);
        req.addResponseListener((evt)->{
            resultOK=req.getResponseCode()==200;
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public void getinfo (String JsonText) {
        JSONParser jp =new JSONParser();
          Client a = new Client ();
        try {
            Map <String,Object> ClientJson=jp.parseJSON(new CharArrayReader(JsonText.toCharArray()));
            ArrayList<Map<String,Object>> ClientObj = (ArrayList<Map<String, Object>>) ClientJson.get("root");
           for (Map<String,Object> obj:ClientObj ){
              
                c.setName(obj.get("name").toString());
                c.setSurname(obj.get("surname").toString());
               c.setEmail(obj.get("email").toString());
                c.setNumber((int)Float.parseFloat(obj.get("number").toString()));
                c.setSex(obj.get("sex").toString());
                c.setId((int)Float.parseFloat(obj.get("id").toString()));
                
            }
        }catch (Exception e) {
            e.printStackTrace();
        } 
  //   return a;
    }
      public boolean signin (Client t) {
        String url = Statics.BASE_URL+"/client/api/signin?email="+t.getEmail()+"&password="+t.getPassword();
                
       ConnectionRequest request = new ConnectionRequest (url,false);
       request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser j =new JSONParser();
                String Json = new String(request.getResponseData());
                try {
                    if (Json.equals("failed")||Json.equals("passowrd not found"))
                        Dialog.show("Alert", "Email or Password incorrect", null, "OK");
                    else {
                          Json = "["+Json+"]";
                        getinfo(Json);
                        resultOK=request.getResponseCode()==200;
                        System.out.println("data=  "+Json);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                
                
//                c =getinfo(new String(request.getResponseData()));
//                 request.removeResponseListener(this);
//                resultOK=request.getResponseCode()==200;
//                System.out.println(c.getName());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return resultOK;
    }
}
