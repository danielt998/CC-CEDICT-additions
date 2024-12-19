package src.main.java;

import java.util.ArrayList;
import java.util.List;

/*
options:
include/exclude cases where pinyin is ambiguouss
only run if both simp + trad exist (whether we autodetect if zh is ttrad/simp or autoconvert iin cases where it is
 unambiguous to do so remains to be decided
if this ^^ we may only want to do it if the simp/trad are equivalent characters

 */

public class Main {
    // these represent the indices in the tsv of the intermediate file
    // TODO: did we forget zh-CN??
    private static final int ZH = 0;
    private static final int ZH_HANS = 1;
    private static final int ZH_HANT = 2;
    private static final int ZH_HK = 3;
    private static final int ZH_MO = 4;
    private static final int ZH_MY = 5;
    private static final int ZH_SG = 6;
    private static final int ZH_TW = 7;
    private static final int ENGLISH = 8;
    private static final int DESCRIPTION = 10;//not sure why 10 and not 9 :P

    //private static String INPUT_FILE = "/media/dtm/wikidata/wikidata_all_out_2.tsv";
    private static final String INPUT_FILE = "intermediate_data/intermediate_after_excluding_stuff.tsv";


    private static boolean UNAMBIGUOUS_PINYIN_ONLY = true;

    // TODO: Add a check that the trad is equivalent to the simp for transliteration purposes (also how does this play with different romanisations?)

    // TODO: For these, we should also allow transliteration in the case where it is completely unambiguous
    private static boolean AUTO_CONVERT_TRAD_TO_SIMP = false;
    private static boolean AUTO_CONVERT_SIMP_TO_TRAD = false;
    private static boolean SIMP_REQUIRED = true; //for these two need to consider what to put in other field if empty
    private static boolean TRAD_REQUIRED = true;
    private static boolean AUTO_DETECT_TRAD_OR_SIMP_FROM_ZH = true;
    private static boolean IGNORE_ENTRIES_WITH_NO_EN_LABEL = true;
    private static boolean IGNORE_ENTRIES_WITH_NO_DESCRIPTION = false;

    public static void main(String[] args) {
        Extract.readInDictionary();
        List<String> lines = FileUtils.fileToStringArray(INPUT_FILE);
        for (String line : lines) {
            String[] segments = line.split("\t");
            if (segments.length > 10 && !segments[ENGLISH].isEmpty() && !segments[DESCRIPTION].isEmpty()) {
                continue;
            }

            if (empty(segments)) continue;
            try {
                if ((TRAD_REQUIRED && getTraditional(segments).isEmpty()) || !containsHan(getTraditional(segments))) {
                    continue;
                }
                if ((SIMP_REQUIRED && getSimplified(segments).isEmpty()) || !containsHan(getTraditional(segments))) {
                    continue;
                }
                if (!containsHan(getTraditional(segments)) && !containsHan(getSimplified(segments))) {
                    continue;
                }
                if (UNAMBIGUOUS_PINYIN_ONLY && !pinyinIsUnambiguous(getSimplified(segments)) && !pinyinIsUnambiguous(getTraditional(segments))) {
                    continue;
                }
                System.out.println(getTraditional(segments) + " " + getSimplified(segments)
                        + " " + getPinyin(segments)
                        + "/" + getDescription(segments) + "/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean containsHan(String name) {
        for (char c : name.toCharArray()) {
            if (Character.UnicodeScript.of(c) == Character.UnicodeScript.HAN) {
                return true;
            }
        }
        return false;
    }

    public static boolean pinyinIsUnambiguous(String name){
        for (char c : name.toCharArray()) {
            if (Character.UnicodeScript.of(c) == Character.UnicodeScript.HAN) {
                // TODO: think about how to handle this
//                if (Extract.getWordsFromChinese(c).isEmpty()) {
//                    throw new Exception("getWordsFromChinese returned empty list in pinyinIsUnambiguous");
//                }
                String firstPinyin = Extract.getWordsFromChinese(c).getFirst().getPinyinWithTones().toLowerCase();
                for (Word character : Extract.getWordsFromChinese(c)) {
                    if (!character.getPinyinWithTones().toLowerCase().equals(firstPinyin)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean empty(String[] segments) {
        if (segments.length <= ZH_TW) return true;// need to investigate though...
        return segments[ZH].isEmpty() && segments[ZH_HANS].isEmpty() && segments[ZH_TW].isEmpty();
    }

    public static String getPinyin(String[] segments) {
        //TODO:have some sort of priority order of different Chineses
        String simplifiedPinyin = getPinyin(getSimplified(segments));
        String traditionalPinyin = getPinyin(getTraditional(segments));
        if (!simplifiedPinyin.isEmpty()) {
            return simplifiedPinyin;
        } else if (!traditionalPinyin.isEmpty()) {
            return traditionalPinyin;
        } else {
            return "";
        }
    }

    public static String getPinyin(String givenWord) {
        if (givenWord.isEmpty()) {
            return "";
        }
        List<Word> words = new ArrayList<Word>();
        for (char c : givenWord.toCharArray()) {
            if (Character.UnicodeScript.of(c) == Character.UnicodeScript.HAN) {
                words.add(Extract.getWordFromChinese(c));
            } else {
                words.add(new Word("" + c, "" + c, "" + c, "" + c, "" + c));
            }
        }
        String acc = "[";
        for (Word word : words) {
            acc = acc + word.getPinyinWithTones() + " ";//TODO:get rid of this trailing space
        }
        acc = acc + "] ";
        acc = acc.replace(" ]", "]");
        return acc;
    }

    public static String getSimplified(String[] segments) {
        if (!segments[ZH_HANS].isEmpty()) {
            return segments[ZH_HANS];
        } else if (isSimp(segments[ZH]) && !segments[ZH].isEmpty()) {
            return segments[ZH];
        } else if (!getTraditional(segments).isEmpty() && AUTO_CONVERT_TRAD_TO_SIMP) {
            return tradToSimp(getTraditional(segments));
        } else {
            return "";
        }
    }

    public static String tradToSimp(String tradWord) {
        List<Word> words = new ArrayList<Word>();
        String acc = "";
        for (char c : tradWord.toCharArray()) {
            //TODO: add anything that isn't HAN straight to acc?
            words.add(Extract.getWordFromChinese(c));
        }
        for (Word word : words) {
            acc = acc + word.getSimplifiedChinese();
        }
        return acc;
    }

    public static String simpToTrad(String simpWord) {
        List<Word> words = new ArrayList<Word>();
        String acc = "";
        for (char c : simpWord.toCharArray()) {
            System.out.print(c + ":");
            words.add(Extract.getWordFromChinese(c));
        }
        for (Word word : words) {
            acc = acc + word.getTraditionalChinese();
        }
        return acc;
    }

    public static String getTraditional(String[] segments) {
        if (!segments[ZH_HANT].isEmpty()) {
            return segments[ZH_HANT];
        } else if (!segments[ZH_TW].isEmpty()) {
            return segments[ZH_TW];
        } else if (isTrad(segments[ZH]) && !segments[ZH].isEmpty()) {
            return segments[ZH];//do we want to convert to traditional just in case??
            //.. and so on...
        } else if (!segments[ZH_HANS].isEmpty() && AUTO_CONVERT_SIMP_TO_TRAD) {
            return simpToTrad(segments[ZH_HANS]);
            //return "";
        } else {
            return "";
        }
    }

    // TODO: think about streams
    // TODO: move elsewhere?
    public static boolean isSimp(String name) {
        for (char c : name.toCharArray()) {
            if (Character.UnicodeScript.of(c) == Character.UnicodeScript.HAN &&
                    Extract.getWordFromSimplifiedChinese(c) == null){
                return false;
            }
        }
        return true;
    }

    public static boolean isTrad(String name) {
        for (char c : name.toCharArray()) {
            if (Character.UnicodeScript.of(c) == Character.UnicodeScript.HAN &&
                    Extract.getWordFromTraditionalChinese(c) == null){
                return false;
            }
        }
        return true;
    }

    public static String getDescription(String[] segments) {
        return segments[ENGLISH] + ", " + (segments.length > 9 ? segments[DESCRIPTION] : "");
    }
}
