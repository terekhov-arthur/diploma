package ua.nure.service.impl;

import org.springframework.stereotype.Service;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class CompileService {

    private JavaCompiler compiler;

    public CompileService() {
        compiler = ToolProvider.getSystemJavaCompiler();
    }

    public List<Diagnostic<? extends JavaFileObject>> compile(String solution, String test){
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        try(StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null)) {
            List<File> files = Arrays.asList(new File(solution), new File(test));
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(files);

            compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits)
                    .call();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return diagnostics.getDiagnostics();
        }
    }
}
