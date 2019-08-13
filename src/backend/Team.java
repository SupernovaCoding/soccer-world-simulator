package backend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import simulation.MatchSimulation;
import statics.Names;
import statics.Printer;
import statics.Rand;

public class Team{
    public String name;

    public int wins, loses, draws, points;
    public int winsThisSeason, losesThisSeason, drawsThisSeason, pointsThisSeason;
    public int gamesPlayed;
    public int goalsFor, goalsForThisSeason;
    public int goalsAgainst, goalsAgainstThisSeason;

    public int money;
    public int transferBudget, wageBudget;

    public double avg_att, avg_def;
    public double sl_att, sl_def; 

    public ArrayList<Player> players = new ArrayList<>();
    public ArrayList<Player> startingLineup = new ArrayList<>();
    public ArrayList<ArrayList<Player>> positionArrayLists = new ArrayList<>();
    public ArrayList<Player> gks = new ArrayList<>();
    public ArrayList<Player> lbs = new ArrayList<>();
    public ArrayList<Player> cbs = new ArrayList<>();
    public ArrayList<Player> rbs = new ArrayList<>();
    public ArrayList<Player> lms = new ArrayList<>();
    public ArrayList<Player> cms = new ArrayList<>();
    public ArrayList<Player> rms = new ArrayList<>();
    public ArrayList<Player> sts = new ArrayList<>();

    public League league;

    public Team(League league){
        this.league = league;
        TeamBuilder.teamInit(this);
        Collections.addAll(positionArrayLists, gks, lbs, cbs, rbs, lms, cms, rms, sts);
        calculateOverall();
    }

    public void calculateOverall(){
        for(Player player : players){
            avg_att += player.attack;
            avg_def += player.defence;
        }
        avg_att /= players.size(); avg_def /= players.size();
    }

    public void simDay(){
        //Trade Stuff
    }

    public void win(){
        this.wins++;
        this.winsThisSeason++;
        this.points += 3;
        this.pointsThisSeason += 3;
        this.gamesPlayed++;
    }

    public void lose(){
        this.loses++;
        this.losesThisSeason++;
        this.gamesPlayed++;
    }

    public void draw(){
        this.draws++;
        this.drawsThisSeason++;
        this.points++;
        this.pointsThisSeason++;
        this.gamesPlayed++;
    }

    public void pickStartingLineup(Team other){
        startingLineup.clear();
        TeamSelector.generateStartingLineup(this, other);
    }

    public void score(){
        this.goalsFor++;
        this.goalsForThisSeason++;
    }

    public void scoreAgainst(){
        this.goalsAgainst++;
        this.goalsAgainstThisSeason++;
    }

    @Override
    public String toString() {
        return "Team: " + name +
               "\n  Record: " + winsThisSeason + "/" + losesThisSeason + "/" + drawsThisSeason +
               "\n  Points: " + pointsThisSeason +
               "\n  Games Played: " + gamesPlayed +
               "\n  Goal Differential: " + (goalsForThisSeason - goalsAgainstThisSeason) +
               "\n  GF: " + goalsForThisSeason +
               "\n  GA: " + goalsAgainstThisSeason +
               "\n  Money: " + money;
    }
}

class TeamBuilder{

    public static void teamInit(Team team){
        generatePlayers(team);
        team.money = Rand.randomNumberBetween(1000000, 50000000);
        Names.nameTeam(team);
    }

    public static void generatePlayers(Team team){
        for(int i = 0; i < 3; i++){
            Player gk = new Player(team, Player_Position.GK);
            team.players.add(gk);
            team.gks.add(gk);
        }  
        for(int i = 0; i < 3; i++){
            Player lb = new Player(team, Player_Position.LB);
            team.players.add(lb);
            team.lbs.add(lb);
        } 
        for(int i = 0; i < 3; i++){
            Player cb = new Player(team, Player_Position.CB);
            team.players.add(cb);
            team.cbs.add(cb);
        }    
        for(int i = 0; i < 3; i++){
            Player rb = new Player(team, Player_Position.RB);
            team.players.add(rb);
            team.rbs.add(rb);
        }  
        for(int i = 0; i < 3; i++){
            Player lm = new Player(team, Player_Position.LM);
            team.players.add(lm);
            team.lms.add(lm);
        } 
        for(int i = 0; i < 3; i++){
            Player cm = new Player(team, Player_Position.CM);
            team.players.add(cm);
            team.cms.add(cm);
        }    
        for(int i = 0; i < 3; i++){
            Player rm = new Player(team, Player_Position.RM);
            team.players.add(rm);
            team.rms.add(rm);
        } 
        for(int i = 0; i < 3; i++){
            Player st = new Player(team, Player_Position.ST);
            team.players.add(st);
            team.sts.add(st);
        } 
    }
}

class TeamSelector{

    public static void generateStartingLineup(Team team, Team other){
        TeamSelector.sortAll(team);

        team.startingLineup.add(TeamSelector.defense(team, other, team.gks));

        team.startingLineup.add(TeamSelector.defense(team, other, team.lbs));
        team.startingLineup.add(TeamSelector.defense(team, other, team.cbs));
        team.startingLineup.add(TeamSelector.defense(team, other, team.cbs));
        team.startingLineup.add(TeamSelector.defense(team, other, team.rbs));

        team.startingLineup.add(TeamSelector.offense(team, other, team.lms));
        team.startingLineup.add(TeamSelector.offense(team, other, team.cms));
        team.startingLineup.add(TeamSelector.offense(team, other, team.cms));
        team.startingLineup.add(TeamSelector.offense(team, other, team.rms));

        team.startingLineup.add(TeamSelector.offense(team, other, team.sts));
        team.startingLineup.add(TeamSelector.offense(team, other, team.sts));
    }

    public static void sortAll(Team team){
        for(ArrayList<Player> players : team.positionArrayLists){
            Collections.sort(players, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    if(players.get(0).position.equals(Player_Position.GK) ||
                    players.get(0).position.equals(Player_Position.LB) ||
                    players.get(0).position.equals(Player_Position.CB) ||
                    players.get(0).position.equals(Player_Position.RB)){
                        return Integer.valueOf(p1.defence).compareTo(Integer.valueOf(p2.defence));
                    }
                    else{
                        return Integer.valueOf(p1.attack).compareTo(Integer.valueOf(p2.attack));
                    }
                }
            });
        }
    }

    public static Player defense(Team team, Team other, ArrayList<Player> players){
        if(team.losesThisSeason >= team.winsThisSeason){
            if(!team.startingLineup.contains(players.get(0))){return players.get(0);}
            else{return players.get(1);}
        }
        if(other.avg_def - team.avg_def > -5){
            if(!team.startingLineup.contains(players.get(0))){return players.get(0);}
            else{return players.get(1);}
        }
        else if(other.avg_def - team.avg_def < -5 && other.avg_def - team.avg_def > -10){
            if(!team.startingLineup.contains(players.get(1)) && players.size() > 1){return players.get(1);}
            else{return players.get(0);}
        }
        else if(other.avg_def - team.avg_def < -10){
            if(!team.startingLineup.contains(players.get(2)) && players.size() > 2){return players.get(2);}
            else if(!team.startingLineup.contains(players.get(1)) && players.size() > 1){return players.get(1);}
            else{return players.get(0);}
        }
        else{
            while(true){
                Player p = players.get(Rand.random(players.size()));
                if(team.startingLineup.contains(p)){
                    return p;
                }
            } 
        }
    }

    public static Player offense(Team team, Team other, ArrayList<Player> players){
        if(team.losesThisSeason >= team.winsThisSeason){
            if(!team.startingLineup.contains(players.get(0))){return players.get(0);}
            else{return players.get(1);}
        }
        if(other.avg_att - team.avg_att > -5){
            if(!team.startingLineup.contains(players.get(0))){return players.get(0);}
            else{return players.get(1);}
        }
        else if(other.avg_att - team.avg_att < -5 && other.avg_att - team.avg_att > -10){
            if(!team.startingLineup.contains(players.get(1)) && players.size() > 1){return players.get(1);}
            else{return players.get(0);}
        }
        else if(other.avg_att - team.avg_att < -10){
            if(!team.startingLineup.contains(players.get(2)) && players.size() > 2){return players.get(2);}
            else if(!team.startingLineup.contains(players.get(1)) && players.size() > 1){return players.get(1);}
            else{return players.get(0);}
        }
        else{
            while(true){
                Player p = players.get(Rand.random(players.size()));
                if(team.startingLineup.contains(p)){
                    return p;
                }
            } 
        }
    }

}