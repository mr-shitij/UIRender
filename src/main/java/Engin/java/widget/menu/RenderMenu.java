package Engin.java.widget.menu;

import Engin.java.Checker;
import Engin.java.uiBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RenderMenu {
    private String declaration = "", body = "", id;

    public RenderMenu(JSONObject obj, String prefix){
        this.id = Checker.checkForDuplicate(obj, "id", "menu");
        uiBuilder.declaredVariables.add(this.id);
        obj.remove("id");


        if(obj.get("global") == null || !((boolean) obj.get("global"))) {
            this.body = this.body.concat("\t\t" + "JMenu " + this.id + ";" + "\n");
        }else{
            this.declaration = this.declaration.concat("\t" + "protected JMenu " + this.id + ";" + "\n");
        }
        obj.remove("global");

        this.body = this.body.concat("\t\t" + this.id + " = new JMenu();" + "\n");

        for(Object attribute : obj.keySet()){
            switch ((String) attribute){
                case "text":
                    this.body =  this.body.concat("\t\t" + this.id + ".setText(\"" + obj.get(attribute) + "\");" + "\n");
                    break;
                case "icon":
                    this.body =  this.body.concat("\t\t" + this.id + ".setIcon(new ImageIcon(\"" +  obj.get(attribute) + "\"));" + "\n");
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
                case "enable":
                    if(!(obj.get(attribute) instanceof Boolean)) {
                        throw new Error("Invalid data type for enabled method");
                    }
                    this.body =  this.body.concat("\t\t" + this.id + ".setEnabled(" +  obj.get(attribute) + ");" + "\n");
                    break;
                case "items":
                    if(obj.get(attribute) instanceof JSONArray){
                        for(Object object : (JSONArray)obj.get(attribute)){
                            JSONObject jsonObject = (JSONObject) object;
                            if(jsonObject.get("type") == null){
                                throw new Error("No Type Attribute specified in item of Menu");
                            }
                            if(jsonObject.get("type").equals("item")){
                                jsonObject.remove("type");
                                RenderMenuItem item = new RenderMenuItem(jsonObject, this.id);
                                this.body = this.body.concat(item.getBody());
                                this.declaration = this.declaration.concat(item.getDeclaration());
                            }
                            else if(jsonObject.get("type").equals("menu")){
                                jsonObject.remove("type");
                                RenderMenu menu = new RenderMenu(jsonObject, this.id);
                                this.body = this.body.concat("\t\t" + menu.getBody());
                                this.declaration = this.declaration.concat("\t" + menu.getDeclaration());
                            }
                            else if(jsonObject.get("type").equals("radio")){
                                jsonObject.remove("type");
                                RenderRadioButtonMenuItem radioButtonMenuItem = new RenderRadioButtonMenuItem(this.id, jsonObject);
                                this.body = this.body.concat("\t\t" + radioButtonMenuItem.getBody());
                                this.declaration = this.declaration.concat("\t" + radioButtonMenuItem.getDeclaration());
                            }
                            else if(jsonObject.get("type").equals("checkbox")){
                                jsonObject.remove("type");
                                RenderCheckboxMenuItem checkboxMenuItem = new RenderCheckboxMenuItem(this.id, jsonObject);
                                this.body = this.body.concat("\t\t" + checkboxMenuItem.getBody());
                                this.declaration = this.declaration.concat("\t" + checkboxMenuItem.getDeclaration());
                            }
                            else if(jsonObject.get("type").equals("radiogroup")){
                                jsonObject.remove("type");
                                RenderRadioGroupMenuItem renderRadioGroupMenuItem = new RenderRadioGroupMenuItem(this.id, jsonObject);
                                this.body = this.body.concat("\t\t" + renderRadioGroupMenuItem.getBody());
                                this.declaration = this.declaration.concat("\t" + renderRadioGroupMenuItem.getDeclaration());
                            }
                            else{
                                throw new Error("option-type in \"items\" : " + jsonObject.get("type") + " does not exist or invalid");
                            }
                        }
                    }else {
                        throw new Error("menus option \"items\" should specify in square brackets as a form of array");
                    }
                    break;
                default:
                    System.out.println(attribute);
                    throw new Error("Invalid Attribute in MenuBar");
            }
        }
        this.body = this.body.concat("\t\t" + prefix + ".add(" + this.id + ");" + "\n\n");
    }

    public String getDeclaration(){
        return declaration;
    }

    public String getBody(){
        return body;
    }
}
