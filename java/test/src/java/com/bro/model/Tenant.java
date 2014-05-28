/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bro.model;

import com.bro.Connector.StaffConnector;
import java.util.List;

/**
 *
 * @author arshed
 */
public class Tenant {
    private String id;
    private List<StaffConnector> staffs;
    private int subscriptionId;
    private String tenantName;
    
    private void loadStaffs() {
        StaffConnector staff = new StaffConnector("123456");   
    }
    
}
