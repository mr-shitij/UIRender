package Engin.java;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class uiBuilder {

    private static int counter = 0;

    FileWriter writer;
    JSONObject uiToRender;
    HashMap<String , String> uiMap;

    public static List<String> declaredVariables = new ArrayList<>();
    public static Stack<LayoutDecider> layoutStack = new Stack<>();

    public void finalize(){
        uiBuilder.declaredVariables = null;
        uiBuilder.layoutStack = null;
        System.gc();
    }


    public static void init(){
        uiBuilder.declaredVariables = new ArrayList<>();
        uiBuilder.layoutStack = new Stack<>();
    }

    public static int getCounter(){
        return uiBuilder.counter;
    }
    public static void incrementCounter(){
        uiBuilder.counter += 1;
    }


    public uiBuilder(JSONObject object, String fileName) throws IOException {
        uiBuilder.init();
        this.uiToRender = object;
        this.writer = new FileWriter(fileName);
        this.uiMap = new HashMap<>();
    }

    public void setUpClass(){
        uiMap.put("classImport", "package ui;\nimport javax.swing.*;\nimport java.awt.*;\n");
        uiMap.put("classSignatureStart", "public class " + uiToRender.get("id") + " extends JFrame {\n");
        uiMap.put("classDeclaration", "\t" + " \n");
        uiMap.put("classConstructorStart", "\t" + "public " + uiToRender.get("id") + "() {\n");

        JSONObject size = (JSONObject) uiToRender.get("size");
        uiMap.put("classConstructorBody", "\t\t" + "setSize(" + size.get("width") + ", " + size.get("height")  + ");"  + "\n");

        uiMap.replace("classConstructorBody", uiMap.get("classConstructorBody") + "\t\t" + "setTitle(\""+ uiToRender.get("title")  + "\");"  + "\n\n");

        LayoutDecider decider;
        if(uiToRender.get("layout") == null){
            JSONObject ob = new JSONObject();
            ob.put("name", "borderlayout");
            decider =  new LayoutDecider(ob);
        }
        else {
            decider = new LayoutDecider((JSONObject) uiToRender.get("layout"));
        }

        uiBuilder.layoutStack.push(decider);
        uiMap.replace("classConstructorBody", uiMap.get("classConstructorBody") + "\t\t" + decider.getLayoutDetails() + "\n");
        uiMap.replace("classConstructorBody", uiMap.get("classConstructorBody") + "\t\t" + "setLayout(" + decider.getLayoutId() + ");" + "\n");

        uiMap.put("classConstructorEnd", "\t" + "}\n");
        uiMap.put("classSignatureEnd", "}\n");

        uiToRender.remove("id");
        uiToRender.remove("width");
        uiToRender.remove("height");
        uiToRender.remove("title");
        uiToRender.remove("size");

    }

    public void build() throws IOException {

        setUpClass();
        JSONArray componentsArray  = (JSONArray) uiToRender.get("components");

        uiBuilder.declaredVariables.add((String) uiToRender.get("id"));

        RenderComponents renderComponents = new RenderComponents("", componentsArray);
        uiBuilder.layoutStack.pop();


        uiMap.replace("classDeclaration", uiMap.get("classDeclaration") + renderComponents.getTempUiMap().get("classDeclaration"));
        uiMap.replace("classConstructorBody", uiMap.get("classConstructorBody") + renderComponents.getTempUiMap().get("classConstructorBody"));

        uiMap.replace("classConstructorBody", uiMap.get("classConstructorBody") + "\t\t" + "show(" + uiToRender.get("show") + ");" + "\n\n");
        uiToRender.remove("show");

        System.out.println(uiMap.get("classImport"));
        System.out.println(uiMap.get("classSignatureStart"));
        System.out.println(uiMap.get("classDeclaration"));
        System.out.println(uiMap.get("classConstructorStart"));
        System.out.println(uiMap.get("classConstructorBody"));
        System.out.println(uiMap.get("classConstructorEnd"));
        System.out.println(uiMap.get("classSignatureEnd"));

        writer.write(uiMap.get("classImport"));
        writer.flush();

        writer.write(uiMap.get("classSignatureStart"));
        writer.write(uiMap.get("classDeclaration"));
        writer.flush();

        writer.write(uiMap.get("classConstructorStart"));
        writer.write(uiMap.get("classConstructorBody"));
        writer.flush();

        writer.write(uiMap.get("classConstructorEnd"));
        writer.write(uiMap.get("classSignatureEnd"));
        writer.flush();

        writer.close();

    }
}

