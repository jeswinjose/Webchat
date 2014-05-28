/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bro.Connector;

import com.bro.model.ChatMode;
import com.bro.model.Defaults;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arshed
 */
public class StaffConnector {
    private  String clientID;
    /*
    Should auto-select a staff and set his phone no
    */
    final String SEND_MESSAGE_URL = "http://[::1]:8000/sendMessage.php";
    private String mobileNo = "919449336312";
    private static final String INTIMATION_MESSAGE = "New client is waiting for you";
    private StringBuilder message = new StringBuilder();
    private ChatMode mode;
    private List<String> clients = new ArrayList<String>();
    public StaffConnector(String mobileNo){
        this.mobileNo = mobileNo;
        setChatMode();
        
         
    }
    public boolean isAvailable(){
        
        if(clients.size()>=Defaults.MAX_CLIENT){
            return false;
        }
        if(mode==ChatMode.Whatsapp&&clients.size()>=1){
            return false;
        }
        return true;
    }
    public void setClient(String client){
        clients.add(client);
    }
    public void removeClient(String client){
        clients.remove(client);
    }
    public final void setChatMode(){
      if(isLoggedIn()){
            mode = ChatMode.Browser;
        }
        else{
            mode = ChatMode.Whatsapp;
        }      
    }
    public String getMobileNumber(){
        return this.mobileNo;
    }
    public void sendMessage(String message) throws MalformedURLException, IOException{
        URL obj = new URL(formMessage(message));
        Scanner scanner = new Scanner(obj.openConnection().getInputStream());
        StringBuilder line = new StringBuilder();
        while(scanner.hasNextLine()){
            line.append(scanner.nextLine());
        }
        scanner.close();
        
    }
    public void sendInitmation() throws IOException{
        sendMessage(INTIMATION_MESSAGE);
    }
    private String formMessage(String message) throws UnsupportedEncodingException{
        String encodedMessage = URLEncoder.encode(message, "utf-8");
       return SEND_MESSAGE_URL+"?"+"no="+mobileNo+"&msg="+encodedMessage; 
    }
    public synchronized void addMessage(String newMessage){
        message.append(newMessage);
        this.notify();
    }
    public synchronized String readMessage(){
        String result="";
        if(message.length()>0){
            String string = message.toString();
            message = new StringBuilder();
            return string;
        } 
        
        try {
            this.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(StaffConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(message.length()>0){
            String string = message.toString();
            message = new StringBuilder();
            return string;
        } 
        return result;
    }
    public static StaffConnector getStaff(String clientID){
        if(onlyStaff==null){
            onlyStaff = new StaffConnector(clientID);
        }
        return onlyStaff;
    }
    /*
    used for testing
    */
    static StaffConnector onlyStaff;
    public static void main(String arg[]) throws IOException{
        StaffConnector staf = getStaff("");
                staf.sendMessage("hellow");
    }

    private boolean isLoggedIn() {
        //to do
        return true;
    }
}
