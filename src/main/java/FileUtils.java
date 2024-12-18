package src.main.java;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class FileUtils{
  public static List<String> fileToStringArray(String filename){
    return fileToStringArray(new File(filename));
  }
  public static List<String> fileToStringArray(File file){
    List<String> lines = new ArrayList<String>();
    try{
      Scanner scanner = new Scanner(file);
      while(scanner.hasNextLine()){
        lines.add(scanner.nextLine());
      }
      scanner.close();
    }catch(IOException exception){
      exception.printStackTrace();
    }
    return lines;
  }
  public static void writeToFile(List<String> lines, String outputFileName){
    try {
      Files.write(Paths.get(outputFileName), lines, Charset.defaultCharset());
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  public static String removeExtensionFromFileName(String originalFileName){
    return originalFileName.substring(0, originalFileName.lastIndexOf("."));
  }
}
