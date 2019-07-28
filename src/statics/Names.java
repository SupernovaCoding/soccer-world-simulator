package statics;

import java.util.ArrayList;

import backend.Country;
import backend.League;
import backend.Player;
import backend.Team;

public class Names{

    public static ArrayList<String> firstNames = new ArrayList<>();
    public static ArrayList<String> lastNames = new ArrayList<>();
    public static ArrayList<String> takenNames = new ArrayList<>();

    public static ArrayList<String> teamNameAdders = new ArrayList<>();
    public static ArrayList<String> takenTeamNames = new ArrayList<>();

    public static ArrayList<String> cityNames = new ArrayList<>();
    public static ArrayList<String> takenCityNames = new ArrayList<>();
    public static ArrayList<String> countryNames = new ArrayList<>();
    public static ArrayList<String> takenCountryNames = new ArrayList<>();


    public Names(){
        fillCityNames();
        fillCountryNames();
        fillFirstNames();
        fillLastNames();
        fillTeamNameAdders();
    }

    private void fillFirstNames(){
        for(int i = 1; i <= 3000; i++){
            Names.firstNames.add("" + i);
        }
    }

    private void fillLastNames(){
        for(int i = 1; i <= 3000; i++){
            Names.lastNames.add("" + i);
        }
    }

    private void fillCityNames(){
        for(int i = 1; i <= 3000; i++){
            Names.cityNames.add("City " + i);
        }
    }

    private void fillCountryNames(){
        for(int i = 1; i <= 3000; i++){
            Names.countryNames.add("Country " + i);
        }
    }

    private void fillTeamNameAdders(){
        Names.teamNameAdders.add("FC");
        Names.teamNameAdders.add("AFC");
        Names.teamNameAdders.add("Athletic");
        Names.teamNameAdders.add("City");
        Names.teamNameAdders.add("United");
        Names.teamNameAdders.add("Forrest");
        Names.teamNameAdders.add("Rovers");
        Names.teamNameAdders.add("Wanderers");
        Names.teamNameAdders.add("Hotspur");
        Names.teamNameAdders.add("");
    }

    public static void namePlayer(Player p){
        String f = Names.firstNames.get(Rand.random(Names.firstNames.size()));
        String l = Names.lastNames.get(Rand.random(Names.lastNames.size()));
        String name = f + " " + l;
        
        while(true){
            if(Names.takenNames.contains(name)){
                String addName = Names.lastNames.get(Rand.random(Names.lastNames.size()));
                if(!name.contains(addName)){
                    name += " " + addName;
                }
            }
            else{
                break;
            }
        }
        
        p.name = name;
        Names.takenNames.add(name);
        
    }

    public static void nameTeam(Team team){
        Country country = team.league.country;

        String city = country.associatedCities.get(Rand.random(country.associatedCities.size()));
        String adder = Names.teamNameAdders.get(Rand.random(Names.teamNameAdders.size()));
        String name = city + " " + adder;

        while(true){
            if(!Names.takenTeamNames.contains(name)){
                break;
            }
            city = country.associatedCities.get(Rand.random(country.associatedCities.size()));
            adder = Names.teamNameAdders.get(Rand.random(Names.teamNameAdders.size()));
        }

        team.name = name;
    }

    public static void nameCountry(Country country){
        String name = "";
        while(true){
            name = Names.countryNames.get(Rand.random(Names.countryNames.size()));
            if(!Names.takenCountryNames.contains(name)){break;}
        }
        country.name = name;
        Names.takenCountryNames.add(name);

        int amountOfCities = Rand.randomNumberBetween(20, 100);
        for(int i = 0; i < amountOfCities; i++){
            String cityName = "";
            while(true){
                cityName = Names.cityNames.get(Rand.random(Names.cityNames.size()));
                if(!Names.takenCityNames.contains(cityName)){break;}
            }
            country.associatedCities.add(cityName);
            Names.takenCityNames.add(cityName);
        }
    }

}