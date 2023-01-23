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
        Orianna.setRiotAPIKey("RGAPI-172a12b2-57fe-4992-b6cd-d183e77c4faa");
        Summoner sum = Summoner.named("tf blade").withRegion(Region.NORTH_AMERICA).get();

        //***this should work for match history//MatchHistory mh = MatchHistory.forSummoner(sum).withQueues(Queue.RANKED_SOLO).withSeasons(Season.getLatest()).get();

        String icon = Champion.withId(2).withRegion(Region.NORTH_AMERICA).get().getImage().toString();
        System.out.println(icon);

    }

    /*public static String[] getNamesFromIds(Object[]ids,String sumName, String regionString) {
        Region region = stringToRegion(regionString);
        Summoner summoner = Summoner.named(sumName).withRegion(region).get();
        Champion champ = Champion.withId(ids[0].hashCode()).get();
    }*/
}
