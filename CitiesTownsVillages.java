import java.util.List;
public class CitiesTownsVillages{
  public static void generatePlaces(String inputFile, String type){
    Extract.readInDictionary();
    //read in file
    //foreach line
    //  split array, get simplified/trad chinese (could be either when read in)
    //  output trad, simp, pinyin "X, a city in Y"
    List<String> lines=FileUtils.fileToStringArray(inputFile);
    boolean firstLine=true;
    for(String line: lines){
      if(firstLine){
        firstLine=false;
        continue;
      }
      String[] sections=line.split(",");
    try{
      BitsAndBobs.printChinese(sections[0],"a "+ type + " in "+sections[2]);
    }catch(Exception e){
      System.out.println("***ERROR:"+sections[0]+","+sections[1]+","+sections[2]);
    }
    }//for
  }
}
