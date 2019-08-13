package backend.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import backend.League;
import backend.Team;

public class LeagueStandings {
    private League league;

    public LeagueStandings(League league){
        this.league = league;
    }

    public List<Team> getStandingsOrder(){
        List<Team> standings = league.teams;

        Collections.sort(standings, new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                return Integer.valueOf(o1.pointsThisSeason).compareTo(Integer.valueOf(o2.pointsThisSeason));
            }
        });

        Collections.reverse(standings);
        return standings;
    }

}