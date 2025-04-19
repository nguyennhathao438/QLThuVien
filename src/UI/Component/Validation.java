/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Component;

/**
 *
 * @author Nghia0605
 */
public class Validation {
    public static boolean isEmpty(String input){
        if(input == null){
            return true;
        }
        return input.equals("");
    }
    
    public static boolean isNumber(String num){
        if(num == null) return false;
        try {
            long x = Long.parseLong(num);
            return x > 0;
        } catch (NumberFormatException e) {
            return false;
        }
      
    }
}
