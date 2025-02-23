package _01_multithreading._18_forkjoin_real_examples;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import static java.nio.file.Files.readAllBytes;

/* Parallel Word Count in Large Text Files
 *
 * If you need to count word occurrences in a large text file, ForkJoinPool can help split the
 *  file into chunks and process them in parallel.
 *
 * Example: Parallel Word Count
 *
 */

class WordCountTask extends RecursiveTask<Integer>{
    private String[] words;
    private int start,end;
    private static final int THRESHOLD = 1000;

    public WordCountTask(String[] words, int start, int end) {
        this.words = words;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= THRESHOLD){
            int count = 0;
            for (int i = start; i < end ; i++) {
                if (!words[i].trim().isEmpty()) count++;
            }
            return count;
        }else{
            int mid = (start + end) / 2;

            WordCountTask leftTask = new WordCountTask(words , start, mid);
            WordCountTask rightTask = new WordCountTask(words , mid, end);

            leftTask.fork();
            int rightCount = rightTask.compute();
            int leftCount = leftTask.join();

            return rightCount + leftCount;
        }
    }
}
public class ParallelWordCount {
    public static void main(String[] args) throws IOException {
        String content = new String(readAllBytes(new File("lorem-ipsum.txt").toPath()));
        String[] words = content.split("\\s+");

        ForkJoinPool pool = new ForkJoinPool();
        int wordCount = pool.invoke(new WordCountTask(words, 0 , words.length));

        System.out.println("Total Word Count: "+wordCount);
    }
}
