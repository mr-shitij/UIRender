package Loader;

import javax.swing.*;
import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;

public class Loader {
    JavaCompiler.CompilationTask task;

    DiagnosticCollector<JavaFileObject> diagnostics;
    JavaCompiler compiler;
    StandardJavaFileManager fileManager;
    File file;

    Object obj;
    String packageName;

    public Loader() {
        this.diagnostics = new DiagnosticCollector<>();
        this.compiler = ToolProvider.getSystemJavaCompiler();
        this.fileManager = compiler.getStandardFileManager(diagnostics, null, null);

    }

    public void setPackageName(String packageName){
        this.packageName = packageName;
    }
    public void setFile(File file){
        this.file = file;
    }

    public void Compile(){

        System.out.println("Compile : " + file.getName().substring(file.getName().lastIndexOf("/") + 1));

        Iterable<? extends JavaFileObject> compilationUnit
                = fileManager.getJavaFileObjectsFromFiles(Collections.singletonList(file));

        task = compiler.getTask(
                null,
                fileManager,
                diagnostics,
                null,
                null,
                compilationUnit);
    }
    public void RunProgram() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        if (task.call()) {
            System.out.println("Object is now begin to create");

            URLClassLoader classLoader = new URLClassLoader(new URL[]{new File(file.getParent().substring(0, file.getParent().lastIndexOf( "/"))).toURI().toURL()});

            //System.out.println(classLoader.getName());

            Class<?> loadedClass = classLoader.loadClass(packageName + "." + file.getName().substring(0, file.getName().lastIndexOf(".")));
            obj = loadedClass.newInstance();

        } else {
            for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                System.out.format("Error on line %d in %s%n",
                        diagnostic.getLineNumber(),
                        diagnostic.getSource().toUri());
                System.out.println(diagnostic);
            }
        }
        fileManager.close();
    }

    public void Reload() throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        this.kill();
        this.Compile();
        this.RunProgram();
    }

    public void kill() {
        if (obj != null) {
            ((JFrame) obj).dispose();
        }
        else {
            obj = null;
        }
        System.gc();
    }
}