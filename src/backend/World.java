package backend;

import java.util.ArrayList;

import simulation.MatchDate;
import statics.InternalCalendar;
import statics.Names;
import statics.Printer;
import statics.Rand;

public class World{

    public ArrayList<Country> countries = new ArrayList<>();

    
    public static void main(String[] args) {
        new Names();
        new InternalCalendar();
        World world = new World();
        int amountOfCountries = Rand.randomNumberBetween(2, 5);

        for(int i = 0; i < amountOfCountries; i++){
            world.countries.add(new Country());
        }

        for(MatchDate md : world.countries.get(0).leagues.get(0).matchDates){
            Printer.print(md.date);
        }

        Printer.print(world.countries.get(0).leagues.get(0).matchDates.size());
    }
    


}