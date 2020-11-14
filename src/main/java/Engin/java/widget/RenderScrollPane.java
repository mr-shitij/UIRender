package Engin.java.widget;

import Engin.java.Checker;
import org.json.simple.JSONObject;

public class RenderScrollPane {
    private String declaration = "", body = "", id = "";

    public RenderScrollPane(Object obj, String toAddId){

        if(obj instanceof JSONObject){
            this.id = Checker.checkForDuplicate((JSONObject) obj, "id","scrollPane");

            JSONObject jsonObject = (JSONObject) obj;
            if(jsonObject.get("global") == null || !((boolean) jsonObject.get("global"))) {
                this.body = this.body.concat("JScrollPane " + this.id + ";" + "\n");
            }else{
                this.declaration = this.declaration.concat("protected JScrollPane " + this.id + ";" + "\n");
            }

            jsonObject.remove("global");

            this.body = this.body.concat("\t\t" + this.id + " = new JScrollPane("+ toAddId + ");" + "\n");

            for(Object attribute: jsonObject.keySet()) {
                switch ((String) attribute) {
                    case "vertical":
                        if(jsonObject.get(attribute) instanceof  Boolean) {
                            if(((boolean) jsonObject.get(attribute))) {
                                this.body = this.body.concat("\t\t" + this.id + ".setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);" + "\n");
                            }else {
                                    this.body = this.body.concat("\t\t" + this.id + ".setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);" + "\n");
                            }
                        }else {
                            throw new Error("Unrecognized option in scroll-pane for vertical choice");
                        }
                        break;
                    case "horizontal":
                        if(jsonObject.get(attribute) instanceof  Boolean) {
                            if(((boolean) jsonObject.get(attribute))) {
                                this.body = this.body.concat("\t\t" + this.id + ".setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);" + "\n");
                            }else {
                                this.body = this.body.concat("\t\t" + this.id + ".setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);" + "\n");
                            }
                        }else {
                            throw new Error("Unrecognized option in scroll-pane for horizontal choice");
                        }
                        break;
                    default:
                        throw new Error("Unrecognized option in scroll-pane");
                }
            }

        }else if(obj instanceof Boolean){
            if(((boolean) obj)) {
                this.id = Checker.generateVariable("scrollPane");
                this.body = this.body.concat("\t\t" + "JScrollPane " + this.id + " = new JScrollPane(" + toAddId + ");" + "\n");
            }else {
                this.id = null;
            }

        }else{
            throw new Error("Invalid Option in ScrollPane");
        }
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getDeclaration() {
        return declaration;
    }
}
