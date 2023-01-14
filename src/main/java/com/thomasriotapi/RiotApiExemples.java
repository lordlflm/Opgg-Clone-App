package com.thomasriotapi;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
public class RiotApiExemples {
    public static void getChampionExemple(String accountName) {
        Orianna.setRiotAPIKey("RGAPI-9f6eaf01-e19c-4c7c-b4cc-4f8faa044bd1");
        Orianna.setDefaultRegion(Region.NORTH_AMERICA);

        Summoner summoner = Orianna.summonerNamed(accountName).get();
        System.out.println(summoner.getName() + " is level " + summoner.getLevel() + " on the " + summoner.getRegion().toString().toLowerCase() + " server.");
    }
}