import sun.misc.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.Files.readAllBytes;

public class WordFreq {
    public static void main(String[] args) throws IOException {
        String Filetxt = args[0];
        String Output = args[1];

        Map<String,Integer> hist = countWords(Filetxt);
        saveHist(hist,Output);

    }

    static Map<String,Integer> countWords(String inputFile) throws IOException {
        // byte[] content = IOUtils.readAllBytes(new FileInputStream(new File(inputFile)));
        byte[] content = readAllBytes(Paths.get(inputFile));
        String fileContent = new String(content);
        Map<String,Integer> histogram = new HashMap<>();
        String[] words = fileContent.split(" ");
        for(int i=0;i<words.length;i++){
            if(histogram.containsKey(words[i])){
                int preCount = histogram.get(words[i]);
                int newCount = ++preCount;
                histogram.put(words[i],newCount);
            }
            else{
                histogram.put(words[i],1);
            }
        }
        return histogram;
    }


    static String MapToString(Map<String,Integer> histogram){
        StringBuilder str = new StringBuilder();
        for (String word : histogram.keySet()){
            String line = word + ":" + histogram.get(word)+"\n";
            str.append(line);
        }
        return str.toString();
    }

    static void saveHist(Map<String,Integer> histogram,String histOutput) throws IOException {
        String strhist = MapToString(histogram);
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(histOutput)));
        bw.write(strhist);
        bw.close();
    }
}
