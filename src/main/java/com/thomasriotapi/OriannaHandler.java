package com.thomasriotapi;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
public class OriannaHandler {
    public static String getId(String sumName, String regionString) {
        Orianna.setRiotAPIKey("RGAPI-2fe67864-45b5-41ff-a1e1-f81713861581");

        Region region;
        Summoner summoner;
        String id = "";

        region = switch (regionString) {
            case "NA" -> Region.NORTH_AMERICA;
            case "KR" -> Region.KOREA;
            case "EUW" -> Region.EUROPE_WEST;
            case "EUNE" -> Region.EUROPE_NORTH_EAST;
            case "LAN" -> Region.LATIN_AMERICA_NORTH;
            case "LAS" -> Region.LATIN_AMERICA_SOUTH;
            case "OCE" -> Region.OCEANIA;
            case "JP" -> Region.JAPAN;
            case "RU" -> Region.RUSSIA;
            default -> null;
        };
        if (region != null) {
            summoner = Summoner.named(sumName).withRegion(region).get();
            id = summoner.getId();
        }

        return id;
    }

}
