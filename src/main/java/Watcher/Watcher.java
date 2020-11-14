package Watcher;

import Engin.JSONRender;
import Loader.Loader;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class Watcher extends Thread {
    WatchService watchService;
    Loader loader;
    JSONRender render;

    public Watcher(File file) throws IOException, ParseException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        super();
        this.watchService = FileSystems.getDefault().newWatchService();

        this.render = new JSONRender(file);
        render.render();

        this.loader = new Loader();
        loader.setPackageName("ui");
        loader.setFile(new File(render.getToWriteFileName()));

        loader.Compile();
        loader.RunProgram();

        Path path = Paths.get(file.getParent());

        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_MODIFY);

    }

    private void reload() throws IOException, ParseException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        render.render();
        loader.Reload();
    }

    public void run() {
        try {
            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    System.out.println(render.getToWriteFileName());

                    if(!event.context().toString().contains(".java") && !event.context().toString().contains(".class")){
                        reload();
                    }
                    System.out.println(
                            "Event kind:" + event.kind()
                                    + ". File affected: " + event.context() + ".");
                }
                key.reset();
            }
        }catch (InterruptedException | IOException | ParseException | InstantiationException | IllegalAccessException | ClassNotFoundException exception){
            System.out.println(exception.getMessage());
        }

    }

}