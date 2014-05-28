/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bro.init;

import com.bro.Connector.MessageReciever;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class StartUp extends HttpServlet {
 
  
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("trying to start message Reciever");
        MessageReciever.startMessageReciever();
        
    }


}
