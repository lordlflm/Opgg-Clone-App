package com.thomasriotapi;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Division;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.core.staticdata.Image;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class OriannaHandler {

    final static String API_KEY = "RGAPI-6d022163-280d-4b18-80de-5d1c6c37e534";

    //public methods
    public static String getId(String sumName, String regionString) {
        Orianna.setRiotAPIKey(API_KEY);

        Region region = stringToRegion(regionString);
        Summoner summoner;
        String id = "";

        if (region != null && sumName != "" && sumName != null) {
            summoner = Summoner.named(sumName).withRegion(region).get();
            id = summoner.getId();
        }
        return id;
    }

    public static String levelToString(String sumName, String regionString) {
        Region region = stringToRegion(regionString);
        String s = "";

        Summoner summoner = Summoner.named(sumName).withRegion(region).get();
        int level = summoner.getLevel();
        s += level;

        return s;
    }

    public static String getRank(String sumName, String regionString) {
        Region region = stringToRegion(regionString);
        Summoner summoner = Summoner.named(sumName).withRegion(region).get();
        String rankString = "Unranked";
        try {
            Tier tier = summoner.getLeague(Queue.RANKED_SOLO).getTier();
            Division division = summoner.getLeaguePosition(Queue.RANKED_SOLO).getDivision();
            int lp = summoner.getLeaguePosition(Queue.RANKED_SOLO).getLeaguePoints();
            rankString = tier.toString().toLowerCase() + " " + division.toString() + " " + lp + " LP";
        } catch (NullPointerException e) {
            return rankString;
        }
        return rankString;

    }

    public static String getSeasonGamesPlayed(String sumName, String regionString) {
        Region region = stringToRegion(regionString);
        Summoner summoner = Summoner.named(sumName).withRegion(region).get();
        String gp = "N/A";
        try {
            int wins = summoner.getLeaguePosition(Queue.RANKED_SOLO).getWins();
            int losses = summoner.getLeaguePosition(Queue.RANKED_SOLO).getLosses();
            int gpInt = wins+losses;
            gp =  "" + gpInt;
        } catch (NullPointerException e) {
            return gp;
        }

        return gp;
    }

    public static String getSeasonWinrate(String sumName, String regionString) {
        Region region = stringToRegion(regionString);
        Summoner summoner = Summoner.named(sumName).withRegion(region).get();
        String wr = "N/A";
        try {
            float wins = summoner.getLeaguePosition(Queue.RANKED_SOLO).getWins();
            float losses = summoner.getLeaguePosition(Queue.RANKED_SOLO).getLosses();
            float wrInt = wins/(wins+losses)*100;
            wr = ("" + wrInt).substring(0,5) + " %";
        } catch (NullPointerException e) {
            return wr;
        }
        return wr;
    }

    public static String profileIcon(String sumName, String regionString) {
        Region region = stringToRegion(regionString);
        Summoner summoner = Summoner.named(sumName).withRegion(region).get();
        String icon = summoner.getProfileIcon().getImage().getURL();
        return icon;
    }

    public static String rankIcon(String sumName, String regionString) {
        Region region = stringToRegion(regionString);
        Summoner summoner = Summoner.named(sumName).withRegion(region).get();
        String s = null;
        try {
            Tier tier = summoner.getLeague(Queue.RANKED_SOLO).getTier();
            s = switch (tier.toString().toLowerCase()) {
                case "iron" -> "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/content/src/leagueclient/rankedcrests/01_iron/images/iron_base.png";
                case "bronze" -> "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/content/src/leagueclient/rankedcrests/02_bronze/images/bronze_base.png";
                case "silver" -> "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/content/src/leagueclient/rankedcrests/03_silver/images/silver_base.png";
                case "gold" -> "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/content/src/leagueclient/rankedcrests/04_gold/images/gold_base.png";
                case "platinum" -> "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/content/src/leagueclient/rankedcrests/05_platinum/images/platinum_base.png";
                case "diamond" -> "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/content/src/leagueclient/rankedcrests/06_diamond/images/diamond_base.png";
                case "master" -> "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/content/src/leagueclient/rankedcrests/07_master/images/master_base.png";
                case "grandmaster" -> "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/content/src/leagueclient/rankedcrests/08_grandmaster/images/grandmaster_base.png";
                case "challenger" -> "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/content/src/leagueclient/rankedcrests/09_challenger/images/challenger_base.png";
                default -> null;
            };
        } catch (NullPointerException e) {
            return s;
        }
        return s;
    }

    public static int [] getMasteryLevel (String sumName, String regionString) {
        Region region = stringToRegion(regionString);
        Summoner summoner = Summoner.named(sumName).withRegion(region).get();
        //parsing
        getMostMasteryChampionObject(summoner);
    }

    public static String [] get

    //private methods
    private static Region stringToRegion(String regionString) {
        Region region = switch (regionString) {
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
        return region;
    }

    private static Object [] getMostMasteryChampionObject(Summoner sum) {
        Object [] mostMastery = new Object[3];
        Object [] allChampions = sum.getChampionMasteries().toArray();
        for (int i = 0; i<3; i++) {
            mostMastery[i] = allChampions[i];
        }
        return mostMastery;
    }
}
