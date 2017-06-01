package ua.nure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.model.Task;
import ua.nure.service.StorageService;
import ua.nure.util.StringUtils;

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
    private StorageService storageService;

    @Autowired
    public CompileService(StorageService storageService) {
        compiler = ToolProvider.getSystemJavaCompiler();
        this.storageService = storageService;
    }

    private List<Diagnostic<? extends JavaFileObject>> compile(String solution, String test){
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

    public List<Diagnostic<? extends JavaFileObject>> compile(String solution, Task task) {
        String test = task.getTest();

        String solutionClass = StringUtils.getClassName(solution);
        String solutionPath = storageService.save(solutionClass, solution);

        String testClass = StringUtils.getClassName(test);
        String testPath = storageService.save(testClass, test);

        return compile(solutionPath, testPath);
    }
}
