package simulation;

import backend.Team;
import statics.Rand;

public class MatchSimulation {
    Team home, away;
    int hgoals = 0;
    int agoals = 0;
    int maxMinutes = Rand.randomNumberBetween(92, 102);
    int minute = 0;

    public MatchSimulation(Team home, Team away){
        this.home = home;
        this.away = away;
    }

    public void play(){
        home.calculateOverall();
        away.calculateOverall();
        home.pickStartingLineup(away);
        away.pickStartingLineup(home);

        //TODO: make actual game logic

        while(minute <= maxMinutes){
            int num = Rand.random(7);
            int h = Rand.random(7), a = Rand.random(7);

            if(a == h){
                continue;
            }
            else if(h == num){
                chance(home, away);
            }
            else if(a == num){
                chance(away, home);
            }
        }


        if(hgoals > agoals){
            home.win();
            away.lose();
        }
        else if(agoals > hgoals){
            away.win();
            home.lose();
        }
        else{
            home.draw();
            away.draw();
        }
    }

    public void chance(Team attack, Team defence){

    }

    public void givePlayerGoal(Team team){
        int x = Rand.random(10);
    }

}