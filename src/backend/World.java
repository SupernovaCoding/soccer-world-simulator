package backend;

import java.util.ArrayList;
import java.util.List;

import backend.helpers.LeagueStandings;
import simulation.MatchDate;
import statics.InternalCalendar;
import statics.Names;
import statics.Printer;
import statics.Rand;

public class World{

    public ArrayList<Country> countries = new ArrayList<>();

    public World(){
        new Names();
        new InternalCalendar();
        int amountOfCountries = Rand.randomNumberBetween(5, 10);

        for(int i = 0; i < amountOfCountries; i++){
            countries.add(new Country());
        }
    }

    public void simDay(){
        for(Country country : countries){
            country.simDay();
        }
    }

    public void start(){
        Team mostGoalsFor = countries.get(0).leagues.get(0).teams.get(0), 
             mostGoalsAgainst = countries.get(0).leagues.get(0).teams.get(0), 
             mostPoints = countries.get(0).leagues.get(0).teams.get(0);

        for(int i = 0; i < 365; i++){
            simDay();
            InternalCalendar.tick();
        }

        /*
        for(Country c : countries){
            for(League l : c.leagues){
                LeagueStandings ls = new LeagueStandings(l);
                for(Team t : l.teams){
                    if(mostGoalsFor.goalsForThisSeason < t.goalsForThisSeason){
                        mostGoalsFor = t;
                    }
                    if(mostGoalsAgainst.goalsAgainstThisSeason < t.goalsAgainstThisSeason){
                        mostGoalsAgainst = t;
                    }
                    if(mostPoints.pointsThisSeason < t.pointsThisSeason){
                        mostPoints = t;
                    }
                }

                List<Team> standings = ls.getStandingsOrder();
                Printer.print("---League " + l.name + " Standings---");
                for(int i = 0; i < standings.size(); i++){
                    Printer.print((i+1) + ". " + standings.get(i).name + ": " + 
                                 standings.get(i).winsThisSeason + "/" + 
                                 standings.get(i).losesThisSeason + "/" + 
                                 standings.get(i).drawsThisSeason +
                                 "  " + standings.get(i).pointsThisSeason);
                }
                Printer.print("\n");
            }
        }
        */
    }
    
    public static void main(String[] args) {
        

    }
    


}