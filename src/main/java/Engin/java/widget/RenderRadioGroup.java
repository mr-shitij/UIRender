package Engin.java.widget;

import Engin.java.Checker;
import Engin.java.LayoutDecider;
import Engin.java.uiBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RenderRadioGroup {
    private String body = "", id, declaration = "";

    public RenderRadioGroup(String prefix, JSONObject obj, LayoutDecider layoutDecider){
        this.id = Checker.checkForDuplicate(obj, "id", "buttongroup");
        uiBuilder.declaredVariables.add(this.id);
        obj.remove("id");

        if(obj.get("global") == null || !((boolean) obj.get("global"))) {
            this.body = this.body.concat("ButtonGroup " + this.id + ";" +"\n");
        }else{
            this.declaration = this.declaration.concat("protected ButtonGroup " + this.id + ";" + "\n");
        }
        obj.remove("global");

        this.body = this.body.concat(this.id + " = new ButtonGroup();" + "\n");


        if(obj.get("buttons") != null) {
            JSONArray buttons = (JSONArray) obj.get("buttons");
            for(Object o : buttons) {
                JSONObject radio = (JSONObject) o;
                switch ((String) radio.get("type")) {
                    case "radio":
                        RenderRadioButton radioButton = new RenderRadioButton(prefix, radio, layoutDecider);
                        this.declaration =  this.declaration.concat(radioButton.getDeclaration());
                        this.body =  this.body.concat(radioButton.getBody());
                        this.body =  this.body.concat("\t\t" + this.id + ".add(" + radioButton.getId() + ");" + "\n");
                        break;
                    case "checkbox":
                        System.out.println(radio);
                        RenderCheckbox checkbox = new RenderCheckbox(prefix, radio, layoutDecider);
                        this.declaration =  this.declaration.concat(checkbox.getDeclaration());
                        this.body =  this.body.concat(checkbox.getBody());
                        this.body =  this.body.concat("\t\t" + this.id + ".add(" + checkbox.getId() + ");" + "\n");
                        break;
                    default:
                        throw new Error("Unrecognizable type " + radio.get("type") + " in RadioGroup");
                }
            }

        }
    }

    public String getBody(){
        return body;
    }
    public String getDeclaration(){
        return declaration;
    }

}
