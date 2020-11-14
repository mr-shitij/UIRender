package Engin;

import Engin.java.uiBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONRender{
    File file;
    String lang;
    String toWriteFileName;

    public JSONRender(File file){
        this.file=file;
    }

    public void render() throws IOException, ParseException {
        JSONObject obj = (JSONObject) new JSONParser().parse(new FileReader(file));

        String lang = (String) obj.get("lang");
        JSONObject frame = (JSONObject) obj.get("frame");

        if(lang == null || frame == null){
            System.out.println("Lang or Frame attribute not specified");
            throw new Error("exception : Invalid UI File It Should Contain lang and frame attribute");
        }
        toWriteFileName = ((String) frame.get("id")).concat(".java");
        toWriteFileName = file.getParent().concat("/").concat(toWriteFileName);

        switch (lang){
            case "java":
                new uiBuilder(frame, toWriteFileName).build();
                break;
            case "python":
                break;
        }
    }
    public String getToWriteFileName(){
        return toWriteFileName;
    }
}