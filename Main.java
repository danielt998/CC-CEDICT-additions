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
  }
}
