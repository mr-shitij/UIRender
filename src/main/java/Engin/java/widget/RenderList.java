package Engin.java.widget;

import Engin.java.Checker;
import Engin.java.LayoutDecider;
import Engin.java.uiBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;

public class RenderList {
    private String declaration = "", body = "", id;

    public RenderList(String prefix, JSONObject obj, LayoutDecider layoutDecider){
        this.id = Checker.checkForDuplicate(obj, "id", "list");
        uiBuilder.declaredVariables.add(this.id);
        obj.remove("id");

        if(obj.get("global") == null || !((boolean) obj.get("global"))) {
            this.body = this.body.concat("JList " + this.id + ";" + "\n");
        }else{
            this.declaration = this.declaration.concat("protected JList " + this.id + ";" + "\n");
        }
        obj.remove("global");

        if (obj.get("type") != null){
            obj.remove("type");
        }

        this.body = this.body.concat(this.id + " = new JList();" + "\n");

        String scrollPaneId = null;

        for(Object attribute : obj.keySet()){
            switch ((String) attribute){
                case "data":
                    try {
                        JSONArray arr = (JSONArray) obj.get(attribute);
                        if(arr.size() <= 0){
                            throw new Error("No value specified in data array for JList");
                        }
                        this.body = this.body.concat("\t\t" + this.id + ".setListData(new String[]{" + arr.toString().substring(1, arr.toString().length() - 1) + "});" + "\n");
                    }catch (ClassCastException ignored){
                        this.body =  this.body.concat("\t\t" + this.id + ".setListData(new String[]{\"" + obj.get(attribute) + "\"});" + "\n");
                    }
                    break;
                case "font":
                    JSONObject font = (JSONObject) obj.get(attribute);
                    String name = (String) font.get("name");
                    String style = (String) font.get("style");

                    if(name == null || style == null || font.get("size") == null){
                        throw new Error("name, style and size all attribute should specify in font");
                    }
                    long size = (long) font.get("size");

                    this.body =  this.body.concat("\t\t" + this.id + ".setFont(new Font(\"" + name + "\", " + Checker.selectStylesForFont(style) + ", " + size + ")" + ");" + "\n");
                    break;
                case "foreground-color":
                    try {
                        JSONArray arr = (JSONArray) obj.get(attribute);
                        if(arr.size() != 3){
                            throw new Error("Color array Should be specified in [R, G, B] values");
                        }
                        this.body =  this.body.concat("\t\t" + this.id + ".setForeground(new Color(" + arr.get(0) + ", " + arr.get(1) + ", " + arr.get(2) + ")" + ");" + "\n");
                    }catch (ClassCastException ignored){
                        this.body =  this.body.concat("\t\t" + this.id + ".setForeground(Color." + ((String) obj.get(attribute)).toUpperCase() + ");" + "\n");
                    }
                    break;
                case "background-color":
                    try {
                        JSONArray arr = (JSONArray) obj.get(attribute);
                        if(arr.size() != 3){
                            throw new Error("Color array Should be specified in [R, G, B] values");
                        }
                        this.body =  this.body.concat("\t\t" + this.id + ".setBackground(new Color(" + arr.get(0) + ", " + arr.get(1) + ", " + arr.get(2) + ")" + ");" + "\n");
                    }catch (ClassCastException ignored){
                        this.body =  this.body.concat("\t\t" + this.id + ".setBackground(Color." + ((String) obj.get(attribute)).toUpperCase() + ");" + "\n");
                    }
                    break;
                case "selection-foreground-color":
                    try {
                        JSONArray arr = (JSONArray) obj.get(attribute);
                        if(arr.size() != 3){
                            throw new Error("Color array Should be specified in [R, G, B] values");
                        }
                        this.body =  this.body.concat("\t\t" + this.id + ".setSelectionForeground(new Color(" + arr.get(0) + ", " + arr.get(1) + ", " + arr.get(2) + ")" + ");" + "\n");
                    }catch (ClassCastException ignored){
                        this.body =  this.body.concat("\t\t" + this.id + ".setSelectionForeground(Color." + ((String) obj.get(attribute)).toUpperCase() + ");" + "\n");
                    }
                    break;
                case "selection-background-color":
                    try {
                        JSONArray arr = (JSONArray) obj.get(attribute);
                        if(arr.size() != 3){
                            throw new Error("Color array Should be specified in [R, G, B] values");
                        }
                        this.body =  this.body.concat("\t\t" + this.id + ".setSelectionBackground(new Color(" + arr.get(0) + ", " + arr.get(1) + ", " + arr.get(2) + ")" + ");" + "\n");
                    }catch (ClassCastException ignored){
                        this.body =  this.body.concat("\t\t" + this.id + ".setSelectionBackground(Color." + ((String) obj.get(attribute)).toUpperCase() + ");" + "\n");
                    }
                    break;
                case "pos":
                    JSONObject pos = (JSONObject) obj.get(attribute);
                    this.body =  this.body.concat("\t\t" + this.id + ".setBounds(" + pos.get("x") + "," + pos.get("y") + "," + pos.get("width") + "," + pos.get("height") + ");" + "\n");
                    break;
                case "width":
                        this.body =  this.body.concat("\t\t" + this.id + ".setFixedCellWidth(" + obj.get(attribute) + ");" + "\n");
                    break;
                case "height":
                        this.body =  this.body.concat("\t\t" + this.id + ".setFixedCellHeight(" + obj.get(attribute) + ");" + "\n");
                    break;
                case "enable":
                    if(!(obj.get(attribute) instanceof Boolean)) {
                        throw new Error("Invalid data type for enabled method");
                    }
                    this.body =  this.body.concat("\t\t" + this.id + ".setEnabled(" +  obj.get(attribute) + ");" + "\n");
                    break;
                case "scroll-pane":
                    RenderScrollPane scrollPane = new RenderScrollPane(obj.get(attribute), this.id);
                    this.body = this.body.concat("\t\t" + scrollPane.getBody());
                    this.declaration = this.declaration.concat("\t" + scrollPane.getDeclaration());
                    scrollPaneId = scrollPane.getId();
                    break;
                case "layout-align":
                    break;
                default:
                    System.out.println(attribute);
                    throw new Error("Invalid Attribute in RadioButton " + attribute);
            }
        }

        System.out.println("Test : " + obj.get("font"));

        if(!prefix.equals("")){
            prefix = prefix.concat(".");
        }

        if(layoutDecider.getLayoutName().equals("borderlayout") && obj.get("layout-align") != null ){
            if(scrollPaneId != null && obj.get("scroll-pane") != null) {
                this.body = this.body.concat("\t\t" + prefix + "add(" + scrollPaneId + ", " + Checker.alignForBorderLayout((String) obj.get("layout-align")) + ");" + "\n");
            }else {
                this.body = this.body.concat("\t\t" + prefix + "add(" + this.id + ", " + Checker.alignForBorderLayout((String) obj.get("layout-align")) + ");" + "\n");
            }
        }else {
            if(scrollPaneId != null && obj.get("scroll-pane") != null) {
                this.body = this.body.concat("\t\t" + prefix + "add(" + scrollPaneId + ");" + "\n");
            }else {
                this.body = this.body.concat("\t\t" + prefix + "add(" + this.id + ");" + "\n");
            }
        }

//        if(layoutDecider.getLayoutName().equals("borderlayout") && obj.get("layout-align") != null ){
//            this.body =  this.body.concat("\t\t" + prefix + "add(" + this.id + ", " + Checker.alignForBorderLayout((String) obj.get("layout-align")) + ");" + "\n");
//        }else if(obj.get("align") != null ){
//            this.body =  this.body.concat("\t\t" + prefix + "add(" + this.id + ", " + Checker.alignForFlowLayout((String) obj.get("align")) + ");" + "\n");
//        }else {
//            this.body = this.body.concat("\t\t" + prefix + "add(" + this.id + ");" + "\n");
//        }


/*
 else if(obj.get("align") != null ){
            if(scrollPaneId != null && obj.get("scroll-pane") != null) {
                this.body =  this.body.concat("\t\t" + prefix + "add(" + scrollPaneId + ", "  + Checker.alignForFlowLayout((String) obj.get("align")) + ");" + "\n");
            }else {
                this.body =  this.body.concat("\t\t" + prefix + "add(" + this.id + ", "  + Checker.alignForFlowLayout((String) obj.get("align")) + ");" + "\n");
            }
        }

*/

    }

    public String getDeclaration(){
        return declaration;
    }

    public String getBody(){
        return body;
    }

    public String getId() {
        return id;
    }
}
