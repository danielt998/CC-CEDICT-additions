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
    CitiesTownsVillages.generatePlaces("songs.csv", "song");
    CitiesTownsVillages.generatePlaces("suburbs.csv", "suburb");
    CitiesTownsVillages.generatePlaces("museums.csv", "museum");
    CitiesTownsVillages.generatePlaces("Algeria_municipalities.csv", "municipality in Algeria");
    CitiesTownsVillages.generatePlaces("companies.csv", "company");
    CitiesTownsVillages.generatePlaces("bridges.csv", "bridge");
    CitiesTownsVillages.generatePlaces("vehicles.csv", "vehicle");
    CitiesTownsVillages.generatePlaces("airports.csv", "airport");
    CitiesTownsVillages.generatePlaces("universities.csv", "university");
    CitiesTownsVillages.generatePlaces("monuments.csv", "monument");
    CitiesTownsVillages.generatePlaces("newspapers.csv", "newspaper");
    CitiesTownsVillages.generatePlaces("magazines.csv", "magazine");
    CitiesTownsVillages.generatePlaces("palaces.csv", "palace");
    CitiesTownsVillages.generatePlaces("sculptures.csv", "sculpture");
    CitiesTownsVillages.generatePlaces("houses.csv", "house");
    CitiesTownsVillages.generatePlaces("towers.csv", "tower");
    CitiesTownsVillages.generatePlaces("zoos.csv", "zoo");
    CitiesTownsVillages.generatePlaces("stadiums.csv", "stadium");
    CitiesTownsVillages.generatePlaces("streets.csv", "street");
    CitiesTownsVillages.generatePlaces("tunnels.csv", "tunnel");
    CitiesTownsVillages.generatePlaces("tv_channel.csv", "television channel");
    CitiesTownsVillages.generatePlaces("tv_stations.csv", "television station");
    CitiesTownsVillages.generatePlaces("radio_stations.csv", "radio station");
    CitiesTownsVillages.generatePlaces("cemeteries.csv", "cemetery");
    CitiesTownsVillages.generatePlaces("languages.csv", "language spoken");
    CitiesTownsVillages.generatePlaces("battles.csv", "battle");
    CitiesTownsVillages.generatePlaces("wars.csv", "war");
    CitiesTownsVillages.generatePlaces("islands.csv", "island");
    CitiesTownsVillages.generatePlaces("canals.csv", "canal");
    CitiesTownsVillages.generatePlaces("treaties.csv", "treaty");
    CitiesTownsVillages.generatePlaces("archeological_sites.csv", "archeological site");
    CitiesTownsVillages.generatePlaces("places_of_worship.csv", "place of worship");
    CitiesTownsVillages.generatePlaces("transport_infrastructure_all.csv", "a piece of transport infrastructure");
    generateSimpleEntry("earthquakes.csv","");
    CitiesTownsVillages.generatePlaces("aquaria.csv", "aquarium");
    generateSimpleEntry("albums.csv","album");
    generateSimpleEntry("books.csv","book");
    generateSimpleEntry("films.csv","film");
    generateSimpleEntry("websites.csv","website");
    generateSimpleEntry("sw_applications.csv","a software application");
    generateSimpleEntry("archives.csv","");
    generateSimpleEntry("libraries.csv","library");
    generateSimpleEntry("tvseries.csv","television series");
    generateSimpleEntry("world_events.csv","");
    generateSimpleEntry("islands_no_country.csv", "island");
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
