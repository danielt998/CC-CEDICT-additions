import java.util.*;
public class BitsAndBobs{
  public static void printUnambiguousChinese(String s, String english){
    List<Word> words= new ArrayList<Word>();
    String acc="";
    for (char c:s.toCharArray()){
      words.add(Extract.getWordFromChinese(c));
    }
    for(Word word:words){
      for (char c: word.getSimplifiedChinese().toCharArray()){
              if(!Extract.isUnambiguous(c)){
                      return;
              }
      }
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
