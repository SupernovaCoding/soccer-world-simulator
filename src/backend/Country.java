package backend;

import java.util.ArrayList;



import statics.Names;
import statics.Rand;

public class Country{

    public ArrayList<League> leagues = new ArrayList<>();
    public ArrayList<String> associatedCities = new ArrayList<>();

    public String name;

    public Country(){
        CountryBuilder.initCountry(this);
    }

    public void simDay(){
        for(League league : leagues){
            league.simDay();
        }
    }

    @Override
    public String toString() {
        return "Country:" +
               "\n  Name: " + name + 
               "\n  Amount Of Leagues: " + leagues.size() +
               "\n  Amount Of Cities: " + associatedCities.size();
    }
}


class CountryBuilder{

    public static void initCountry(Country country){
        Names.nameCountry(country);
        int amountOfLeagues = Rand.randomNumberBetween(3, 5);
        for(int i = 0; i < amountOfLeagues; i++){
            League league = new League(country);
            country.leagues.add(league);
            league.finishInit();
        }
    }

}
