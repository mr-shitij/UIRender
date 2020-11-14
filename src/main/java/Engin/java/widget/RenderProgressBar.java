package Engin.java.widget;

import Engin.java.Checker;
import Engin.java.LayoutDecider;
import Engin.java.uiBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RenderProgressBar {
    private String declaration = "", body = "", id;

    public RenderProgressBar(String prefix, JSONObject obj, LayoutDecider layoutDecider){
        this.id = Checker.checkForDuplicate(obj, "id", "progressBar");
        uiBuilder.declaredVariables.add(this.id);
        obj.remove("id");


        if(obj.get("global") == null || !((boolean) obj.get("global"))) {
            this.body = this.body.concat("JProgressBar " + this.id + ";" + "\n");
        }else{
            this.declaration = this.declaration.concat("protected JProgressBar " + this.id + ";" + "\n");
        }
        obj.remove("global");

        this.body = this.body.concat(this.id + " = new JProgressBar();" + "\n");

        for(Object attribute : obj.keySet()){
            switch ((String) attribute){
                case "val":
                    try {
                        long val = Integer.parseInt(String.valueOf(obj.get(attribute)));
                        this.body = this.body.concat("\t\t" + this.id + ".setValue(" + val + ");" + "\n");
                    }
                    catch (NumberFormatException e){
                        throw new Error("Invalid value in slider Attribute val ");
                    }
                    break;
                case "min":
                    try {
                        long min = Integer.parseInt(String.valueOf(obj.get(attribute)));
                        this.body = this.body.concat("\t\t" + this.id + ".setMinimum(" + min + ");" + "\n");
                    }
                    catch (NumberFormatException e){
                        throw new Error("Invalid value in slider Attribute min ");
                    }
                    break;
                case "max":
                    try {
                        long max = Integer.parseInt(String.valueOf(obj.get(attribute)));
                        this.body = this.body.concat("\t\t" + this.id + ".setMaximum(" + max + ");" + "\n");
                    }
                    catch (NumberFormatException e){
                        throw new Error("Invalid value in slider Attribute max ");
                    }
                    break;
                case "orientation":
                    String ori = (String) obj.get(attribute);
                    if(ori.toLowerCase().equals("vertical")){
                        this.body = this.body.concat("\t\t" + this.id + ".setOrientation(" + "SwingConstants.VERTICAL" + ");" + "\n");
                    }
                    else if(ori.toLowerCase().equals("horizontal")){
                        this.body = this.body.concat("\t\t" + this.id + ".setOrientation(" + "SwingConstants.HORIZONTAL" + ");" + "\n");
                    }
                    else{
                        throw new Error("Invalid value in slider Attribute orientation ");
                    }
                    break;
                case "text":
                    try {
                        String text = String.valueOf(obj.get(attribute));
                        this.body = this.body.concat("\t\t" + this.id + ".setString(\"" + text + "\");" + "\n");
                    }
                    catch (NumberFormatException e){
                        throw new Error("Invalid value in slider Attribute text ");
                    }
                    break;
                case "indeterminate":
                    try {
                        boolean val = (Boolean) obj.get(attribute);
                        this.body = this.body.concat("\t\t" + this.id + ".setIndeterminate(" + val + ");" + "\n");
                    }
                    catch (Exception e){
                        throw new Error("Invalid value in slider Attribute indeterminate ");
                    }
                    break;
                case "string-painted":
                    try {
                        boolean val = (Boolean) obj.get(attribute);
                        this.body = this.body.concat("\t\t" + this.id + ".setStringPainted(" + val + ");" + "\n");
                    }
                    catch (Exception e){
                        throw new Error("Invalid value in slider Attribute stringPainted ");
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
                case "layout-align":
                    break;
                default:
                    System.out.println(attribute);
                    throw new Error("Invalid Attribute in Slider");
            }
        }

        System.out.println("Test : " + obj.get("font"));

        if(!prefix.equals("")){
            prefix = prefix.concat(".");
        }

        if(layoutDecider.getLayoutName().equals("borderlayout") && obj.get("layout-align") != null ){
            this.body =  this.body.concat("\t\t" + prefix + "add(" + this.id + ", " + Checker.alignForBorderLayout((String) obj.get("layout-align")) + ");" + "\n");
        }else {
            this.body = this.body.concat("\t\t" + prefix + "add(" + this.id + ");" + "\n");
        }
    }

    public String getDeclaration(){
        return declaration;
    }

    public String getBody(){
        return body;
    }
}
