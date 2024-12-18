package src.main.java;

import java.util.ArrayList;
import java.util.List;

public class Main {
    // these represent the indices in the tsv of the intermediate file
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

    public static void main(String[] args) {
        Extract.readInDictionary();
        List<String> lines = FileUtils.fileToStringArray(INPUT_FILE);
        for (String line : lines) {
            String[] segments = line.split("\t");
            if (segments.length > 10 && !segments[ENGLISH].equals("") && !segments[DESCRIPTION].equals("")) {
                continue;
            }

            if (empty(segments)) continue;
            try {
                System.out.println(getTraditional(segments) + " " + getSimplified(segments)
                        + " " + getPinyin(segments)
                        + "/" + getDescription(segments) + "/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean empty(String[] segments) {
        if (segments.length <= ZH_TW) return true;// need to investigate though...
        return segments[ZH].equals("") && segments[ZH_HANS].equals("") && segments[ZH_TW].equals("");
    }

    public static String getPinyin(String[] segments) {
        //TODO:have some sort of priority order of different Chineses
        String simplifiedPinyin = getPinyin(getSimplified(segments));
        String traditionalPinyin = getPinyin(getTraditional(segments));
        if (!simplifiedPinyin.equals("")) {
            return simplifiedPinyin;
        } else if (!traditionalPinyin.equals("")) {
            return traditionalPinyin;
        } else {
            return "";
        }
    }

    public static String getPinyin(String givenWord) {
        if (givenWord.equals("")) {
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
        if (!segments[ZH_HANS].equals("")) {
            return segments[ZH_HANS];
        } else if (!getTraditional(segments).equals("")) {
            return tradToSimp(getTraditional(segments));
        } else {
            return "";
        }
    }

    public static String tradToSimp(String tradWord) {
        List<Word> words = new ArrayList<Word>();
        String acc = "";
        for (char c : tradWord.toCharArray()) {
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
        if (!segments[ZH_HANT].equals("")) {
            return segments[ZH_HANT];
        } else if (!segments[ZH_TW].equals("")) {
            return segments[ZH_TW];
        } else if (!segments[ZH].equals("")) {
            return segments[ZH];//do we want to convert to traditional just in case??
            //.. and so on...
        } else if (!segments[ZH_HANS].equals("")) {
            return simpToTrad(segments[ZH_HANS]);
            //return "";
        } else {
            return "";
        }
    }

    public static String getDescription(String[] segments) {
        return segments[ENGLISH] + ", " + (segments.length > 9 ? segments[DESCRIPTION] : "");
    }
}
