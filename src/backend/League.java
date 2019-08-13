package backend;

import java.util.ArrayList;
import java.util.Calendar;

import simulation.MatchDate;
import simulation.MatchSimulation;
import statics.InternalCalendar;
import statics.Names;
import statics.Printer;

public class League{

    public ArrayList<Team> teams = new ArrayList<>();

    public String name;
    public int leagueIndetifier;

    public Country country;

    public ArrayList<MatchDate> matchDates = new ArrayList<>();

    public League(Country country){
        this.country = country;
    }

    public void finishInit(){
        leagueIndetifier = (country.leagues.indexOf(this)+1);
        LeagueBuilder.initLeague(this);
        LeagueScheduler.makeSchedule(this, teams);
    }

    public void simDay(){
        gameDay();
        
        for(Team team : teams){
            team.simDay();
        }
    }

    private void gameDay(){
        for(MatchDate mDate : matchDates){
            if(mDate.date.compareTo(InternalCalendar.startDate.getTime()) > 0){
                break;
            }
            else if(mDate.date.compareTo(InternalCalendar.startDate.getTime()) == 0){
                mDate.playAll();
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }

}

class LeagueBuilder{

    public static void initLeague(League league){
        league.name = league.country.name + " League " + league.leagueIndetifier;

        for(int i = 0; i < 20; i++){
            Team team = new Team(league);
            league.teams.add(team);
        }
    }

}


class LeagueScheduler{

    public static void makeSchedule(League league, ArrayList<Team> teams){
        int amountOfTeams = teams.size();
        int totalRounds = (teams.size()-1)*2;
        int matchesPerRound = teams.size()/2;
        int halfMark = totalRounds/2;

        for(int round = 0; round < totalRounds; round++){
            Calendar c = Calendar.getInstance();
            c.setTime(InternalCalendar.beginningOfSeason());
            c.add(Calendar.DATE, 7*round);

            MatchDate matchDate = new MatchDate(c.getTime());

            for(int match = 0; match < matchesPerRound; match++){
                int home = (round+match) % (amountOfTeams - 1);
                int away = (amountOfTeams - 1 - match + round) % (amountOfTeams - 1);

                if(match == 0){
                    away = amountOfTeams-1;
                }

                Team t1,t2;
                if(round < halfMark){
                    t1 = teams.get(home);
                    t2 = teams.get(away);
                }
                else{
                    t1 = teams.get(away);
                    t2 = teams.get(home);
                }

                MatchSimulation sim = new MatchSimulation(t1, t2);
                matchDate.matches.add(sim);
            }
            league.matchDates.add(matchDate);
        }
    }


}