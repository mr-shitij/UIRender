package Engin.java;

import org.json.simple.JSONObject;

import javax.swing.*;

public class Checker{
    public static String checkForDuplicate(JSONObject obj, String check, String text){
        if(obj.get(check) != null){
            if(uiBuilder.declaredVariables.contains(obj.get(check))){
                throw new Error("Duplicate Variable name : " + obj.get(check));
            }else{
                return (String) obj.get(check);
            }
        }else {
            return Checker.generateVariable(text);
        }
    }

    public static String generateVariable(String text){
        uiBuilder.incrementCounter();
        String id = text + uiBuilder.getCounter();
        while(uiBuilder.declaredVariables.contains(id)){
            uiBuilder.incrementCounter();
            id += uiBuilder.getCounter();
        }
        return id;
    }

    public static String alignForBorderLayout(String layoutAlign){
        switch (layoutAlign){
            case "center":
                return "BorderLayout.CENTER";
            case "north":
                return "BorderLayout.NORTH";
            case "south":
                return "BorderLayout.SOUTH";
            case "east":
                return "BorderLayout.EAST";
            case "west":
                return "BorderLayout.WEST";
            default:
                throw new Error("Alignment Property \"" + layoutAlign + "\" for border layout does not exist or supported");
        }

    }

    public static String selectStylesForFont(String styles){
        switch (styles){
            case "plain":
                return "Font.PLAIN";
            case "italic":
                return "Font.ITALIC";
            case "bold":
                return "Font.BOLD";
            case "bold+italic":
                return "Font.BOLD+Font.ITALIC";
            default:
                throw new Error("Style Property \"" + styles + "\" does not exist or supported");
        }

    }

    public static String align(String s) {
        if ("left".equals(s)) {
            return "SwingConstants.LEFT";
        } else if ("right".equals(s)) {
            return "SwingConstants.RIGHT";
        } else if ("center".equals(s)) {
            return "SwingConstants.CENTER";
        } else if ("leading".equals(s)) {
            return "SwingConstants.LEADING";
        } else if ("trailing".equals(s)) {
            return "SwingConstants.TRAILING";
        }else {
            throw new Error("Alignment Property \"" + s + "\" of label does not exist or supported");
        }
    }
    /*
    public static String alignForFlowLayout(String s) {
        if ("left".equals(s)) {
            return "FlowLayout.LEFT";
        } else if ("right".equals(s)) {
            return "FlowLayout.RIGHT";
        } else if ("center".equals(s)) {
            return "FlowLayout.CENTER";
        } else if ("leading".equals(s)) {
            return "FlowLayout.LEADING";
        } else if ("trailing".equals(s)) {
            return "FlowLayout.TRAILING";
        }else {
            throw new Error("Alignment Property \"" + s + "\" of flow layout does not exist or supported");
        }

    }

     */
}