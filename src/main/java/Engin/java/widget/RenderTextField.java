package Engin.java.widget;

import Engin.java.Checker;
import Engin.java.LayoutDecider;
import Engin.java.uiBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;

public class RenderTextField {
    private String declaration = "", body = "", id;

    public RenderTextField(String prefix, JSONObject obj, LayoutDecider layoutDecider){
        this.id = Checker.checkForDuplicate(obj, "id", "textfield");
        uiBuilder.declaredVariables.add(this.id);
        obj.remove("id");


        if(obj.get("global") == null || !((boolean) obj.get("global"))) {
            this.body = this.body.concat("JTextField " + this.id + ";" + "\n");
        }else{
            this.declaration = this.declaration.concat("protected JTextField " + this.id + ";" + "\n");
        }
        obj.remove("global");

        this.body = this.body.concat(this.id + " = new JTextField();" + "\n");

        String scrollPaneId = null;

        for(Object attribute : obj.keySet()){
            switch ((String) attribute){
                case "text":
                    this.body =  this.body.concat("\t\t" + this.id + ".setText(\"" + obj.get(attribute) + "\");" + "\n");
                    break;
                case "columns":
                    this.body =  this.body.concat("\t\t" + this.id + ".setColumns(" + obj.get(attribute) + ");" + "\n");
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
                case "pos":
                    JSONObject pos = (JSONObject) obj.get(attribute);
                    this.body =  this.body.concat("\t\t" + this.id + ".setBounds(" + pos.get("x") + "," + pos.get("y") + "," + pos.get("width") + "," + pos.get("height") + ");" + "\n");
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
                case "align":
                    this.body =  this.body.concat("\t\t" + this.id + ".setHorizontalAlignment(" + Checker.align((String) obj.get(attribute)) + ");" + "\n");
                    break;
                case "layout-align":
                    break;
                default:
                    System.out.println(attribute);
                    throw new Error("Invalid Attribute in TextField");
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
    }

    public String getDeclaration(){
        return declaration;
    }

    public String getBody(){
        return body;
    }
}
