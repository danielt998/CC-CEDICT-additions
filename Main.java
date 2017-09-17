import java.util.*;
public class Main{
  public static void main(String[] args){
    CitiesTownsVillages.generatePlaces("cities_all.csv", "city");
    CitiesTownsVillages.generatePlaces("towns.csv", "town");
    CitiesTownsVillages.generatePlaces("villages.csv", "village");
    CitiesTownsVillages.generatePlaces("rivers.csv", "river");
    CitiesTownsVillages.generatePlaces("mountains.csv", "mountain");
    CitiesTownsVillages.generatePlaces("lakes.csv", "lake");
    CitiesTownsVillages.generatePlaces("counties.csv", "county");
    CitiesTownsVillages.generatePlaces("provinces.csv", "province");
    CitiesTownsVillages.generatePlaces("oblasts.csv", "oblast");
    CitiesTownsVillages.generatePlaces("castles.csv", "castle");
    CitiesTownsVillages.generatePlaces("roads.csv", "road");
    CitiesTownsVillages.generatePlaces("deserts.csv", "desert");
    CitiesTownsVillages.generatePlaces("forests.csv", "forest");
    CitiesTownsVillages.generatePlaces("ukraineRaion.csv", "raion in Ukraine");
    CitiesTownsVillages.generatePlaces("cantons.csv", "canton");
    CitiesTownsVillages.generatePlaces("boroughs.csv", "borough");
    CitiesTownsVillages.generatePlaces("hills.csv", "hill");
    CitiesTownsVillages.generatePlaces("stations.csv", "station");
    CitiesTownsVillages.generatePlaces("parks.csv", "park");
    CitiesTownsVillages.generatePlaces("suburbs.csv", "suburb");
    CitiesTownsVillages.generatePlaces("museums.csv", "museum");
    CitiesTownsVillages.generatePlaces("companies.csv", "company");
    generateSimpleEntry("albums.csv","album");
    generateSimpleEntry("books.csv","book");
    generateSimpleEntry("films.csv","film");
    generateSimpleEntry("libraries.csv","library");
    generateSimpleEntry("tvseries.csv","television series");
  }

  public static void generateSimpleEntry(String inputFile,String type){
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
      BitsAndBobs.printChinese(sections[0],sections[1] + ", a "+type);
    }catch(Exception e){
      System.err.println("***ERROR:"+sections[0]+", "+sections[1]);
    }
    }//for
  }

}
