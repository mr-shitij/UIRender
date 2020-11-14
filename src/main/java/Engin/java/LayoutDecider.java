package Engin.java;

import org.json.simple.JSONObject;

public class LayoutDecider{
    JSONObject layoutObject;
    String layoutName;
    String layoutDetails;
    String layoutId;

    public LayoutDecider(JSONObject layoutObject){
        this.layoutObject = layoutObject;
        this.layoutName = (String) this.layoutObject.get("name");
        if(this.layoutName == null ){
            throw new Error("No Layout Name is Specified in Layout");
        }
        this.layoutObject.remove("name");
        uiBuilder.incrementCounter();

        this.layoutId = Checker.checkForDuplicate(layoutObject, "id", this.layoutName);

        uiBuilder.declaredVariables.add(this.layoutId);
        this.layoutDetails = getLayoutConstructorByNameAndProcessAttribute(layoutName);

    }
    public String getLayoutConstructorByNameAndProcessAttribute(String layoutName) {
        switch (layoutName){
            case "borderlayout":
                return processBorderLayout();
            case "flowlayout":
                return processFlowLayout();
            case "gridlayout":
                return processGridLayout();
            default:
                return null;
        }

    }

    private String processBorderLayout() {
        String layout = "BorderLayout " + this.layoutId + " = new BorderLayout();" + "\n";
        for(Object obj : layoutObject.keySet()) {
            switch ((String) obj) {
                case "hgap":
                    layout = layout.concat("\t\t" + this.layoutId + ".setHgap(" + layoutObject.get("hgap") + ");" + "\n");
                    break;
                case "vgap":
                    layout = layout.concat("\t\t" + this.layoutId + ".setVgap(" + layoutObject.get("vgap") + ");" + "\n");
                    break;
                default:
                    break;
            }
        }
        return layout;
    }
    private String processFlowLayout() {
        String layout = "FlowLayout " + this.layoutId + " = new FlowLayout();" + "\n";
        for(Object obj : layoutObject.keySet()) {
            switch ((String) obj) {
                case "align":
                    String align = null;
                    String o = (String) layoutObject.get("align");
                    if ("left".equals(o)) {
                        align = "FlowLayout.LEFT";
                    } else if ("right".equals(o)) {
                        align = "FlowLayout.RIGHT";
                    } else if ("center".equals(o)) {
                        align = "FlowLayout.CENTER";
                    } else if ("leading".equals(o)) {
                        align = "FlowLayout.LEADING";
                    } else if ("trailing".equals(o)) {
                        align = "FlowLayout.TRAILING";
                    }
                    layout = layout.concat("\t\t" + this.layoutId + ".setAlignment(" + align + ");" + "\n");
                    break;
                case "hgap":
                    layout = layout.concat("\t\t" + this.layoutId + ".setHgap(" + layoutObject.get("hgap") + ");" + "\n");
                    break;
                case "vgap":
                    layout = layout.concat("\t\t" + this.layoutId + ".setVgap(" + layoutObject.get("vgap") + ");" + "\n");
                    break;
                default:
                    break;
            }
        }
        return layout;
    }
    private String processGridLayout() {
        String layout = "GridLayout " + this.layoutId + " = new GridLayout();" + "\n";
        for(Object obj : layoutObject.keySet()) {
            switch ((String) obj) {
                case "rows":
                    layout = layout.concat("\t\t" + this.layoutId + ".setRows(" + layoutObject.get("rows") + ");" + "\n");
                    break;
                case "columns":
                    layout = layout.concat("\t\t" + this.layoutId + ".setColumns(" + layoutObject.get("columns") + ");" + "\n");
                    break;
                case "hgap":
                    layout = layout.concat("\t\t" + this.layoutId + ".setHgap(" + layoutObject.get("hgap") + ");" + "\n");
                    break;
                case "vgap":
                    layout = layout.concat("\t\t" + this.layoutId + ".setVgap(" + layoutObject.get("vgap") + ");" + "\n");
                    break;
                default:
                    break;
            }
        }
        return layout;
    }

    public String getLayoutDetails(){
        return this.layoutDetails;
    }
    public String getLayoutName(){
        return this.layoutName;
    }
    public String getLayoutId(){
        return this.layoutId;
    }
}
