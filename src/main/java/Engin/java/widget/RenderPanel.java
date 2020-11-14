package Engin.java.widget;

import Engin.java.Checker;
import Engin.java.LayoutDecider;
import Engin.java.RenderComponents;
import Engin.java.uiBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RenderPanel{
    private String body = "", id = "", declaration = "";

    public RenderPanel(String prefix, JSONObject obj, LayoutDecider layoutDecider){
        this.id = Checker.checkForDuplicate(obj, "id", "panel");
        uiBuilder.declaredVariables.add(this.id);
        obj.remove("id");

        if(obj.get("global") == null || !((boolean) obj.get("global"))) {
            this.body = this.body.concat("JPanel " + this.id + ";" +"\n");
        }else{
            this.declaration = this.declaration.concat("protected JPanel " + this.id + ";" + "\n");
        }
        obj.remove("global");

        this.body = this.body.concat(this.id + " = new JPanel();" + "\n");

        String scrollPaneId = null;

        for(Object attribute : obj.keySet()) {
            switch ((String) attribute) {
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
                case "layout":
                    LayoutDecider decider = new LayoutDecider((JSONObject) obj.get(attribute));
                    uiBuilder.layoutStack.push(decider);

                    this.body = this.body.concat("\t\t" + decider.getLayoutDetails() + "\n");
                    this.body = this.body.concat("\t\t" + this.id.concat(".") + "setLayout(" + decider.getLayoutId() + ");" + "\n");

                    break;
                case "components":
                    JSONArray componentsArray = (JSONArray) obj.get("components");
                    RenderComponents renderComponents = new RenderComponents(this.id, componentsArray);

                    this.body = this.body.concat(renderComponents.getTempUiMap().get("classConstructorBody"));
                    this.declaration = this.declaration.concat(renderComponents.getTempUiMap().get("classDeclaration"));

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
                    throw new Error("Invalid Attribute in Panel");
            }
        }

        if(obj.get("layout") == null){
            JSONObject ob = new JSONObject();
            ob.put("name", "flowlayout");
            LayoutDecider decider =  new LayoutDecider(ob);

            this.body = this.body.concat("\t\t" + decider.getLayoutDetails() + "\n");
            this.body = this.body.concat("\t\t" + this.id.concat(".") + "setLayout(" + decider.getLayoutId() + ");" + "\n");
            uiBuilder.layoutStack.push(decider);
        }

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
    }

    public String getBody(){
        return body;
    }
    public String getDeclaration(){
        return declaration;
    }

}
