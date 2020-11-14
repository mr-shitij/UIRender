package Engin.java;

import Engin.java.widget.*;
import Engin.java.widget.menu.RenderMenuBar;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class RenderComponents {
    private final HashMap<String, String> tempUiMap;
    private final String prefix;


    public RenderComponents(String prefix, JSONArray componentsArray){
        this.prefix = prefix;
        tempUiMap = new HashMap<>();

        tempUiMap.put("classDeclaration", "");
        tempUiMap.put("classConstructorBody", "");

        for (Object object : componentsArray) {
            JSONObject widget = (JSONObject) object;
            switch ((String) widget.get("type")) {
                case "button":
                    widget.remove("type");
                    RenderButton b = new RenderButton(this.prefix, widget, uiBuilder.layoutStack.peek());
                    tempUiMap.replace("classDeclaration", tempUiMap.get("classDeclaration") + "\t" + b.getDeclaration() + "\n");
                    tempUiMap.replace("classConstructorBody", tempUiMap.get("classConstructorBody") + "\t\t" + b.getBody() + "\n");
                    break;
                case "panel":
                    widget.remove("type");
                    RenderPanel panel = new RenderPanel(this.prefix, widget, uiBuilder.layoutStack.peek());
                    uiBuilder.layoutStack.pop();
                    tempUiMap.replace("classDeclaration", tempUiMap.get("classDeclaration") + "\t" + panel.getDeclaration() + "\n");
                    tempUiMap.replace("classConstructorBody", tempUiMap.get("classConstructorBody") + "\t\t" + panel.getBody() + "\n");
                    break;
                case "label":
                    widget.remove("type");
                    RenderLabel label = new RenderLabel(this.prefix, widget, uiBuilder.layoutStack.peek());
                    tempUiMap.replace("classDeclaration", tempUiMap.get("classDeclaration") + "\t" + label.getDeclaration() + "\n");
                    tempUiMap.replace("classConstructorBody", tempUiMap.get("classConstructorBody") + "\t\t" + label.getBody() + "\n");
                    break;
                case "textfield":
                    widget.remove("type");
                    RenderTextField field = new RenderTextField(this.prefix, widget, uiBuilder.layoutStack.peek());
                    tempUiMap.replace("classDeclaration", tempUiMap.get("classDeclaration") + "\t" + field.getDeclaration() + "\n");
                    tempUiMap.replace("classConstructorBody", tempUiMap.get("classConstructorBody") + "\t\t" + field.getBody() + "\n");
                    break;
                case "passwordfield":
                    widget.remove("type");
                    RenderPasswordField pass = new RenderPasswordField(this.prefix, widget, uiBuilder.layoutStack.peek());
                    tempUiMap.replace("classDeclaration", tempUiMap.get("classDeclaration") + "\t" + pass.getDeclaration() + "\n");
                    tempUiMap.replace("classConstructorBody", tempUiMap.get("classConstructorBody") + "\t\t" + pass.getBody() + "\n");
                    break;
                case "textarea":
                    widget.remove("type");
                    RenderTextArea area = new RenderTextArea(this.prefix, widget, uiBuilder.layoutStack.peek());
                    tempUiMap.replace("classDeclaration", tempUiMap.get("classDeclaration") + "\t" + area.getDeclaration() + "\n");
                    tempUiMap.replace("classConstructorBody", tempUiMap.get("classConstructorBody") + "\t\t" + area.getBody() + "\n");
                    break;
                case "checkbox":
                    widget.remove("type");
                    RenderCheckbox box = new RenderCheckbox(this.prefix, widget, uiBuilder.layoutStack.peek());
                    tempUiMap.replace("classDeclaration", tempUiMap.get("classDeclaration") + "\t" + box.getDeclaration() + "\n");
                    tempUiMap.replace("classConstructorBody", tempUiMap.get("classConstructorBody") + "\t\t" + box.getBody() + "\n");
                    break;
                case "radio":
                    widget.remove("type");
                    RenderRadioButton radio = new RenderRadioButton(this.prefix, widget, uiBuilder.layoutStack.peek());
                    tempUiMap.replace("classDeclaration", tempUiMap.get("classDeclaration") + "\t" + radio.getDeclaration() + "\n");
                    tempUiMap.replace("classConstructorBody", tempUiMap.get("classConstructorBody") + "\t\t" + radio.getBody() + "\n");
                    break;
                case "radiogroup":
                    widget.remove("type");
                    RenderRadioGroup radioGroup = new RenderRadioGroup(this.prefix, widget, uiBuilder.layoutStack.peek());
                    tempUiMap.replace("classDeclaration", tempUiMap.get("classDeclaration") + "\t" + radioGroup.getDeclaration() + "\n");
                    tempUiMap.replace("classConstructorBody", tempUiMap.get("classConstructorBody") + "\t\t" + radioGroup.getBody() + "\n");
                    break;
                case "combobox":
                    widget.remove("type");
                    RenderComboBox comboBox = new RenderComboBox(this.prefix, widget, uiBuilder.layoutStack.peek());
                    tempUiMap.replace("classDeclaration", tempUiMap.get("classDeclaration") + "\t" + comboBox.getDeclaration() + "\n");
                    tempUiMap.replace("classConstructorBody", tempUiMap.get("classConstructorBody") + "\t\t" + comboBox.getBody() + "\n");
                    break;
                case "table":
                    widget.remove("type");
                    RenderTable table = new RenderTable(this.prefix, widget, uiBuilder.layoutStack.peek());
                    tempUiMap.replace("classDeclaration", tempUiMap.get("classDeclaration") + "\t" + table.getDeclaration() + "\n");
                    tempUiMap.replace("classConstructorBody", tempUiMap.get("classConstructorBody") + "\t\t" + table.getBody() + "\n");
                    break;
                case "list":
                    widget.remove("type");
                    RenderList list = new RenderList(this.prefix, widget, uiBuilder.layoutStack.peek());
                    tempUiMap.replace("classDeclaration", tempUiMap.get("classDeclaration") + "\t" + list.getDeclaration() + "\n");
                    tempUiMap.replace("classConstructorBody", tempUiMap.get("classConstructorBody") + "\t\t" + list.getBody() + "\n");
                    break;
                case "menubar":
                    widget.remove("type");
                    RenderMenuBar bar = new RenderMenuBar(this.prefix, widget, uiBuilder.layoutStack.peek());
                    tempUiMap.replace("classDeclaration", tempUiMap.get("classDeclaration") + "\t" + bar.getDeclaration() + "\n");
                    tempUiMap.replace("classConstructorBody", tempUiMap.get("classConstructorBody") + "\t\t" + bar.getBody() + "\n");
                    break;
                case "slider":
                    widget.remove("type");
                    RenderSlider slider = new RenderSlider(this.prefix, widget, uiBuilder.layoutStack.peek());
                    tempUiMap.replace("classDeclaration", tempUiMap.get("classDeclaration") + "\t" + slider.getDeclaration() + "\n");
                    tempUiMap.replace("classConstructorBody", tempUiMap.get("classConstructorBody") + "\t\t" + slider.getBody() + "\n");
                    break;
                case "progressbar":
                    widget.remove("type");
                    RenderProgressBar progressBar = new RenderProgressBar(this.prefix, widget, uiBuilder.layoutStack.peek());
                    tempUiMap.replace("classDeclaration", tempUiMap.get("classDeclaration") + "\t" + progressBar.getDeclaration() + "\n");
                    tempUiMap.replace("classConstructorBody", tempUiMap.get("classConstructorBody") + "\t\t" + progressBar.getBody() + "\n");
                    break;
                default:
                    throw new Error("render : invalid value specified : " + object);
            }
        }
    }

    public HashMap<String, String> getTempUiMap(){
        return tempUiMap;
    }

}

