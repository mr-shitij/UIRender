package Engin.java.widget.menu;

import Engin.java.Checker;
import Engin.java.uiBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RenderMenuItem {
    private String declaration = "", body = "", id;

    public RenderMenuItem(JSONObject obj, String prefix){
        this.id = Checker.checkForDuplicate(obj, "id", "menuItem");
        uiBuilder.declaredVariables.add(this.id);
        obj.remove("id");


        if(obj.get("global") == null || !((boolean) obj.get("global"))) {
            this.body = this.body.concat("\t\t" + "JMenuItem " + this.id + ";" + "\n");
        }else{
            this.declaration = this.declaration.concat("\t" + "protected JMenuItem " + this.id + ";" + "\n");
        }
        obj.remove("global");

        this.body = this.body.concat("\t\t" + this.id + " = new JMenuItem();" + "\n");

        for(Object attribute : obj.keySet()){
            switch ((String) attribute){
                case "text":
                    this.body =  this.body.concat("\t\t" + this.id + ".setText(\"" + obj.get(attribute) + "\");" + "\n");
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
                case "icon":
                    this.body =  this.body.concat("\t\t" + this.id + ".setIcon(new ImageIcon(\"" +  obj.get(attribute) + "\"));" + "\n");
                    break;
                case "enable":
                    if(!(obj.get(attribute) instanceof Boolean)) {
                        throw new Error("Invalid data type for enabled method");
                    }
                    this.body =  this.body.concat("\t\t" + this.id + ".setEnabled(" +  obj.get(attribute) + ");" + "\n");
                    break;
                default:
                    System.out.println(attribute);
                    throw new Error("Invalid Attribute in MenuItem : " + attribute);
            }
        }
        this.body = this.body.concat(prefix + ".add(" + this.id + ");" + "\n\n");
    }

    public String getDeclaration(){
        return declaration;
    }

    public String getBody(){
        return body;
    }
}
