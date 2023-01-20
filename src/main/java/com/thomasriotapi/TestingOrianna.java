package com.thomasriotapi;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.*;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.league.LeagueEntry;
import com.merakianalytics.orianna.types.core.league.LeaguePositions;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.staticdata.Image;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcon;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.dto.match.Match;

public class TestingOrianna {
    public static void main(String[] args) {
        Orianna.setRiotAPIKey("RGAPI-6d022163-280d-4b18-80de-5d1c6c37e534");
        Summoner sum = Summoner.named("TF Blade").withRegion(Region.NORTH_AMERICA).get();
        //
        //***this should work for match history//MatchHistory mh = MatchHistory.forSummoner(sum).withQueues(Queue.RANKED_SOLO).withSeasons(Season.getLatest()).get();

        String icon = sum.getProfileIcon().getImage().getURL();
        System.out.println(icon);
    }
}
