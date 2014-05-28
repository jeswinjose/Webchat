/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bro.model;

import com.bro.Connector.StaffConnector;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author arshed
 */
public class StaffMapping {
    private final Map<String,StaffConnector> clientStaffMapping = new HashMap<String,StaffConnector>();
    private final Map<String,StaffConnector> numberStaffMapping = new HashMap<String,StaffConnector>();
    private final static StaffMapping mapping = new StaffMapping();
    private StaffMapping() {
        
    }
    public static StaffMapping getStaffMapping(){
        return mapping;
    }
    public void addClientToStaff(String id,StaffConnector staff){
        clientStaffMapping.put(id, staff);
    }
    public StaffConnector getStaff(String number){
        return numberStaffMapping.get(number);
    }
    public synchronized void addStaff(StaffConnector staff){
        numberStaffMapping.put(staff.getMobileNumber(), staff);
    }

}
