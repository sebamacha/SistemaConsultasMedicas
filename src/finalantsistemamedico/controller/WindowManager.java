/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalantsistemamedico.controller;

import finalantsistemamedico.view.Principal;
import java.sql.Connection;

/**
 *
 * @author Seba
 */
public class WindowManager {
     private static Principal principalInstance;

    public static void createPrincipalInstance(Connection connection) {
        if (principalInstance == null) {
            principalInstance = new Principal(connection);
        }
    }

    public static Principal getPrincipalInstance() {
        return principalInstance;
    }
    
}
