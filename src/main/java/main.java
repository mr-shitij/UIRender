import Engin.JSONRender;
import Watcher.Watcher;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;


public class main {
    public static void main(String[] args) {

        Options options = new Options();

        Option packagePathOption = new Option("p", "path", true,  "Path For UI Package");
        packagePathOption.setRequired(true);
        options.addOption(packagePathOption);

        Option hotReloadingOption = new Option("h", "hotReloading", true,  "To Enable HotReloading");
        hotReloadingOption.setRequired(true);
        options.addOption(hotReloadingOption);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            String path = cmd.getOptionValue("path");
            String reloading = cmd.getOptionValue("hotReloading");

            if(reloading.equals("true")){

                 Watcher watcher = new Watcher(new File(path));
                 watcher.start();

                System.out.println("HotReloading Enable");

            }else{

                JSONRender render = new JSONRender(new File(path));
                render.render();

                System.out.println("HotReloading Not Enable");
            }

        } catch (ParseException | IOException | org.json.simple.parser.ParseException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
