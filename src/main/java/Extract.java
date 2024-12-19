package src.main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*Notes:
  This is a mess of a piece of code that I pulled from another of my projects, it needs sorting out
  Also, it has some useful methods that are of no use for this particular project, so it might be nice
  to create a separate linked GitHub project or something for it later
  Another note:for now, it will only handle unique characters, not words composed of chars, making it
  somewhat less useful than it could be
*/
/*TODO:
  tidy up formatting (e.g. trailing \)
  ensure that split(" /") does not miss anything
  give some thought to how we can implement search for characters/English too...
     maybe create some sort of hashmap
  search should not require exact matches, if whole provided string is a substring of pinyin
  should be something like:
    if it's a (partial) match:
      traverse the list both backwards and forwards as far as possible and add all the matches
  (starting at same place), this should be a match too.
  also,** multiple words have same pinyin** - for themoment, this will return only the first result
  Capitals are causing issues too...
*/
public class Extract {
    private static final String DEFAULT_DICTIONARY_FILENAME = "resources/cedict_ts.u8";
    private static final char COMMENT_CHARACTER = '#';
    private static final Map<String, List<Word>> simplifiedMapping = new HashMap<String, List<Word>>();
    private static final Map<String, List<Word>> traditionalMapping = new HashMap<String, List<Word>>();

    public static void readInDictionary() {
        readInDictionary(DEFAULT_DICTIONARY_FILENAME);
    }

    public static void readInDictionary(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.charAt(0) == COMMENT_CHARACTER) {
                    continue;
                }
                Word word = new Word();
                String[] str = line.split(" /");
                word.setDefinition(str[1]);
                String[] rem = str[0].split("\\[");
                word.setPinyinNoTones(rem[1].replaceAll("[\\[\\]12345 ]", "").toLowerCase());
                word.setPinyinWithTones(rem[1].replaceAll("[\\[\\]]", "").toLowerCase());

                String[] remRem = rem[0].split(" ");
                word.setTraditionalChinese(remRem[0]);
                word.setSimplifiedChinese(remRem[1]);

                simplifiedMapping.computeIfAbsent(word.getSimplifiedChinese(), k -> new ArrayList<>());
                traditionalMapping.computeIfAbsent(word.getTraditionalChinese(), k -> new ArrayList<>());

                simplifiedMapping.get(word.getSimplifiedChinese()).add(word);
                traditionalMapping.get(word.getTraditionalChinese()).add(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Word getWordFromChinese(char c) {
        return getWordFromChinese("" + c);
    }

    public static Word getWordFromChinese(String chineseWord) {
        Word simplified = getWordFromSimplifiedChinese(chineseWord);
        if (simplified != null) {
            return simplified;
        }
        return getWordFromTraditionalChinese(chineseWord);
    }


    public static Word getWordFromTraditionalChinese(char c) {
        return getWordFromTraditionalChinese("" + c);
    }

    public static Word getWordFromTraditionalChinese(String chineseWord) {
        if (traditionalMapping.get(chineseWord) == null) {
            return null;
        }
        return traditionalMapping.get(chineseWord).getFirst();
    }

    public static Word getWordFromSimplifiedChinese(char c) {
        return getWordFromSimplifiedChinese("" + c);
    }

    public static List<Word> getWordsFromTraditionalChinese(char c) {
        return getWordsFromTraditionalChinese("" + c);
    }

    public static List<Word> getWordsFromSimplifiedChinese(char c) {
        return getWordsFromSimplifiedChinese("" + c);
    }

    public static List<Word> getWordsFromTraditionalChinese(String chineseWord) {
        return traditionalMapping.get(chineseWord);
    }

    public static List<Word> getWordsFromSimplifiedChinese(String chineseWord) {
        return simplifiedMapping.get(chineseWord);
    }

    public static List<Word> getWordsFromChinese(char c) {
        return getWordsFromChinese("" + c);
    }

    public static List<Word> getWordsFromChinese(String chineseWord) {
        // NEED TO BE CAREFUL NOT TO MUTATE RETURNED LISTS!!
        // TODO: try to make the lists somehow immutable (as in can't modify actual contents)
        List<Word> words = new ArrayList<>();
        if (getWordsFromTraditionalChinese(chineseWord) != null) {
            words.addAll(getWordsFromTraditionalChinese(chineseWord));
        } else if (getWordsFromSimplifiedChinese(chineseWord) != null){
            words.addAll(getWordsFromSimplifiedChinese(chineseWord));
        }
        return words;
    }

    public static Word getWordFromSimplifiedChinese(String chineseWord) {
        if (simplifiedMapping.get(chineseWord) == null) {
            return null;
        }
        return simplifiedMapping.get(chineseWord).getFirst();
    }

/*TODO:resurrect
  //LINEAR COMPLEXITY
  public static String getEnglish(String chineseWord){
    for (src.main.java.Word word : dictionary){
      if(word.getSimplifiedChinese().equals(chineseWord)
                      || word.getTraditionalChinese().equals(chineseWord)){
        return word.getDefinition();
      }
    }
    return "Chinese word not found";
  }
  public static String getPinyinWithTones(String chineseWord){
    for (src.main.java.Word word : dictionary){
      if(word.getSimplifiedChinese().equals(chineseWord)
                      || word.getTraditionalChinese().equals(chineseWord)){
        return word.getPinyinWithTones();
      }
    }
    return "Chinese word not found";
  }
  */

}
