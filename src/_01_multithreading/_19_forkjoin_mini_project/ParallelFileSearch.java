package _01_multithreading._19_forkjoin_mini_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class FileSearchTask extends RecursiveTask<List<String>> {
    private List<File> files;
    private String keyword;
    private static final int THRESHOLD = 5 ; // Max files per task before splitting

    public FileSearchTask(List<File> files, String keyword) {
        this.files = files;
        this.keyword = keyword;
    }

    @Override
    protected List<String> compute() {
        List<String> matchingFiles = new ArrayList<>();

        if (files.size() <= THRESHOLD ){      // Split into subtasks if too many files
            // Directory search in the files
            for (File file : files){
                if (file.isFile() && containsKeyword(file,keyword)){
                    matchingFiles.add(file.getAbsolutePath());
                }
            }

        }else{
            int mid = files.size() / 2;

            // Split the list into two halves
            List<File> leftFiles = files.subList(0, mid);
            List<File> rightFiles = files.subList(mid, files.size());

            FileSearchTask leftTask = new FileSearchTask(leftFiles,keyword);
            FileSearchTask rightTask = new FileSearchTask(rightFiles,keyword);

            leftTask.fork();
            matchingFiles.addAll(rightTask.compute());
            matchingFiles.addAll(leftTask.join());
        }
        return matchingFiles;
    }

    private boolean containsKeyword(File file, String keyword){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                if (line.contains(keyword)) {
                    return true;
                }
            }
        }catch (IOException e){
            System.err.println("Error reading file: " + file.getAbsolutePath());
        }
        return false;
    }
}
public class ParallelFileSearch {
    public static void main(String[] args) {

        File rootDir = new File("C:\\JMC17\\Java-Masterclass-11"); // Change to a valid directory
        String searchKeyword = "Aliquam"; // Change to the word you want to find

        ForkJoinPool pool = new ForkJoinPool();
        List<File> allFiles = getAllFiles(rootDir);


        List<String> resultFiles = pool.invoke(new FileSearchTask(allFiles,searchKeyword));

        System.out.println("\nFiles containing '"+ searchKeyword +"':");
        for (String filePath : resultFiles ){
            System.out.println(filePath);
        }
        pool.shutdown();
    }

    private static List<File> getAllFiles(File rootDir) {
        List<File> fileList = new ArrayList<>();
        File[] files = rootDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(file);
                } else if (file.isDirectory()) {
                    fileList.addAll(getAllFiles(file)); // Recursively get files from subdirectories
                }
            }
        }
        return fileList;
    }
}
