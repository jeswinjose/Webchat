/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bro.utils;

import java.util.UUID;

/**
 *
 * @author arshed
 */
public class Utils {
  public static String getRandomId(){
      return UUID.randomUUID().toString().replaceAll("-", "").substring(0,24);
    }   
}
