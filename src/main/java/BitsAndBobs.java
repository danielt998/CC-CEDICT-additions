package src.main.java;

import java.util.ArrayList;
import java.util.List;
public class BitsAndBobs{
  public static void printChinese(String s, String english){
    List<Word> words= new ArrayList<Word>();
    String acc="";
    for (char c:s.toCharArray()){
      words.add(Extract.getWordFromChinese(c));
    }
    for(Word word:words){
      acc=acc+word.getTraditionalChinese();
    }
    acc=acc+' ';
    for(Word word:words){
      acc=acc+word.getSimplifiedChinese();
    }
    acc=acc+" [";
    for(Word word:words){
      acc=acc+word.getPinyinWithTones()+" ";//TODO:get rid of this trailing space
    }
    acc=acc+"] ";
    acc=acc.replace(" ]","]");
    acc=acc+" /"+english+"/";
    System.out.println(acc);
  }
}
