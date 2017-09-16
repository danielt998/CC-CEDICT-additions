public class Main{
  public static void main(String[] args){
    CitiesTownsVillages.generatePlaces("cities_all.csv", "city");
    CitiesTownsVillages.generatePlaces("towns.csv", "town");
    CitiesTownsVillages.generatePlaces("villages.csv", "village");
  }
}
