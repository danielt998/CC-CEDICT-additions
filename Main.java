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
  }
}
