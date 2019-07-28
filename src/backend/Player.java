package backend;

import java.math.BigDecimal;
import java.math.RoundingMode;

import statics.Names;
import statics.Printer;
import statics.Rand;

public class Player{
    //public int overall;
    //public int player_number;
    //public Country nationality;

    public int attack, defence;
    public int goals, goalsThisSeason;
    public int assists, assistsThisSeason;
    public int tackles, tacklesThisSeason;
    public int saves, savesThisSeason;
    public int age, contractLength;

    public String name;

    public BigDecimal value;
    
    public Team team;

    public Player_Position position;

    public Player(Team team, Player_Position position){
        this.team = team;
        this.position = position;
        PlayerBuilder.playerInit(this);
    }

    /*
    public static void main(String[] args) {
        Player p = new Player(null, Player_Position.GK);
        Printer.print(p);
        p = new Player(null, Player_Position.LB);
        Printer.print(p);
        p = new Player(null, Player_Position.LM);
        Printer.print(p);
        p = new Player(null, Player_Position.ST);
        Printer.print(p);
    }
    */

    @Override
    public String toString() {
        return "Player:" + 
               "\n  Name: " + name +
               "\n  Attack: " + attack +
               "\n  Defence: " + defence +
               "\n  Value: " + value.setScale(2, RoundingMode.DOWN) +
               "\n  Position: " + position;
    }
}

class PlayerBuilder{

    public static void playerInit(Player player){
        PlayerBuilder.generateAttackDefence(player);
        PlayerBuilder.generateValue(player);
    }

    public static void generateAttackDefence(Player player){
        switch(player.position){
            case GK:
                player.defence = Rand.randomNumberBetween(40, 99);
                player.attack = Rand.randomNumberBetween(10, 30);
                break;
            case LB:
                player.defence = Rand.randomNumberBetween(40, 99);
                player.attack = Rand.randomNumberBetween(20, 40);
                break; 
            case CB:
                player.defence = Rand.randomNumberBetween(40, 99);
                player.attack = Rand.randomNumberBetween(20, 40);
                break; 
            case RB:
                player.defence = Rand.randomNumberBetween(40, 99);
                player.attack = Rand.randomNumberBetween(20, 40);
                break; 
            case LM:
                player.defence = Rand.randomNumberBetween(40, 70);
                player.attack = Rand.randomNumberBetween(40, 99);
                break; 
            case CM:
                player.defence = Rand.randomNumberBetween(40, 70);
                player.attack = Rand.randomNumberBetween(40, 99);
                break; 
            case RM:
                player.defence = Rand.randomNumberBetween(40, 70);
                player.attack = Rand.randomNumberBetween(40, 99);
                break; 
            case ST:
                player.defence = Rand.randomNumberBetween(10, 30);
                player.attack = Rand.randomNumberBetween(40, 99);
                break;
        }
        player.age = Rand.randomNumberBetween(18, 36);
        player.contractLength = Rand.randomNumberBetween(1, 5);
        Names.namePlayer(player);
    }

    public static void generateValue(Player player){
        switch(player.position){
            case GK:
                player.value = new BigDecimal((player.defence+player.attack/10) * 10000 + (player.defence*10000));
                break;
            case LB:
                player.value = new BigDecimal((player.defence+player.attack/5) * 10000 + (player.defence*10000));
                break; 
            case CB:
                player.value = new BigDecimal((player.defence+player.attack/5) * 10000 + (player.defence*10000));
                break; 
            case RB:
                player.value = new BigDecimal((player.defence+player.attack/5) * 10000 + (player.defence*10000));
                break; 
            case LM:
                player.value = new BigDecimal((player.defence/5+player.attack) * 10000 + (player.attack*10000));
                break; 
            case CM:
                player.value = new BigDecimal((player.defence/5+player.attack) * 10000 + (player.attack*10000));
                break; 
            case RM:
                player.value = new BigDecimal((player.defence/5+player.attack) * 10000 + (player.attack*10000));
                break; 
            case ST:
                player.value = new BigDecimal((player.defence/10+player.attack) * 10000 + (player.attack*10000));
                break;
        }
    }
    //public static void namePlayer()
    //public static void numberPlayer()
}


enum Player_Position{

    //TODO: Add more positions when you decide to put in custom formation.
    GK, LB, CB, RB, LM, CM, RM, ST;

}