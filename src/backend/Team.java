package backend;

import java.math.BigDecimal;
import java.util.ArrayList;

import statics.Names;
import statics.Printer;
import statics.Rand;

public class Team{
    public String name;

    public int wins, loses, draws, points;
    public int winsThisSeason, losesThisSeason, drawsThisSeason, pointsThisSeason;
    public int gamesPlayed;

    public int money;
    public int transferBudget, wageBudget;

    public ArrayList<Player> players = new ArrayList<>();
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
    }

    /*
    public static void main(String[] args) {
        Names names = new Names();
        Team t = new Team();

        for(Player player : t.players){
            Printer.print(player);
        }
    }
    */

    @Override
    public String toString() {
        return "Team:" +
               "\n  Record: " + winsThisSeason + "/" + losesThisSeason + "/" + drawsThisSeason +
               "\n  Games Played: " + gamesPlayed +
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