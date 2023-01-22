package com.thomasriotapi;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.*;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteries;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.league.LeagueEntry;
import com.merakianalytics.orianna.types.core.league.LeaguePositions;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Image;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcon;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.dto.match.Match;

import java.util.Arrays;

import static com.thomasriotapi.OriannaHandler.*;

public class TestingOrianna {
    public static void main(String[] args) {
        Orianna.setRiotAPIKey("RGAPI-30860c17-f3da-4001-a5e9-266156b04245");
        Summoner sum = Summoner.named("tf blade").withRegion(Region.NORTH_AMERICA).get();
        //
        //***this should work for match history//MatchHistory mh = MatchHistory.forSummoner(sum).withQueues(Queue.RANKED_SOLO).withSeasons(Season.getLatest()).get();

        /*Object [] b = new Object[3];
        Object [] a = sum.getChampionMasteries().toArray();
        for (int i = 0; i<3; i++) {
            b[i] = a[i];
        }
        OriannaHandler.getMasteriesPoints("lordofallganjas", "NA");
        System.out.println(sum.getChampionMasteries().get(0));*/
        //System.out.println(Arrays.toString(champMasteries));
        Object[] l = getMostMasteryChampionObject(sum);
        System.out.println(Arrays.toString(l));
        //System.out.println(getNamesFromIds("lordofallganjas", "NA"));

    }

    /*public static String[] getNamesFromIds(Object[]ids,String sumName, String regionString) {
        Region region = stringToRegion(regionString);
        Summoner summoner = Summoner.named(sumName).withRegion(region).get();
        Champion champ = Champion.withId(ids[0].hashCode()).get();
    }*/
}
