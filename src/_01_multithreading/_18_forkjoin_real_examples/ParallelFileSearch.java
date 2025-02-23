package _01_multithreading._18_forkjoin_real_examples;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/*
 * Parallel File Search
 *
 * If you need to search for a specific file in a large directory, ForkJoinPool can help search multiple folders in parallel.
 *
 * Example: Searching for a File in a Directory
 *
 */
class FileSearchTask extends RecursiveTask<List<File>> {

    private File directory;
    private String fileName;

    public FileSearchTask(File directory, String fileName) {
        this.directory = directory;
        this.fileName = fileName;
    }

    @Override
    protected List<File> compute() {
        List<File> foundFiles = new ArrayList<>();
        List<FileSearchTask> subTasks = new ArrayList<>();

        File[] files = directory.listFiles();
        if (files == null)
            return foundFiles;

        for (File file : files){
            if (file.isDirectory()){
                FileSearchTask subTask = new FileSearchTask(file,fileName);
                subTask.fork(); // search subdirectory asynchronously
                subTasks.add(subTask);
                
            } else if (file.getName().equals(fileName)) {
                foundFiles.add(file);
            }
        }

        for (FileSearchTask subTask : subTasks){
            foundFiles.addAll(subTask.join()); // Get results from subdirectories
        }
        return foundFiles;
    }
}
public class ParallelFileSearch {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        File rootDir = new File("C:\\JMC17\\Java-Masterclass-11");
        String fileName = "example.txt";

        List<File> result = pool.invoke(new FileSearchTask(rootDir , fileName));

        if (result.isEmpty()){
            System.out.println("File not found");
        }else{
            System.out.println("File found at: ");
            result.forEach(file -> System.out.println(file.getAbsolutePath()));
        }

        pool.shutdown();
    }
}
