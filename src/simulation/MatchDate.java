package simulation;

import java.util.ArrayList;
import java.util.Date;

public class MatchDate{
    public Date date;
    public ArrayList<MatchSimulation> matches = new ArrayList<>();

    public MatchDate(Date date){
        this.date = date;
    }

}