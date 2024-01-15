package com.lflm.opggcloneapp;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Division;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Arrays;

public class OriannaHandler {

    final static String API_KEY = "RGAPI-6b6f541f-b81c-4dd6-8d8b-30976bd07e49";

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
            if (("" + wrInt).length() >= 5) {
                wr = ("" + wrInt).substring(0,5) + " %";
            } else {
                wr = "" + wrInt + " %";
            }
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

    //retourne un tableau[3] avec le masteryLevel (Object) des 3 champions avec le plus de masteries
    public static Object [] getMasteryLevel (String sumName, String regionString) {
        Region region = stringToRegion(regionString);
        Summoner summoner = Summoner.named(sumName).withRegion(region).get();
        Object [] champMasteryLevel = new Object[3];
        //parsing
        try {
            JSONArray champArray = (JSONArray) new JSONParser().parse(Arrays.toString(getMostMasteryChampionObject(summoner)));
            for (int i =0; i<3;i++) {
                champMasteryLevel[i] = getJSONValue(champArray, i, "level");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return champMasteryLevel;
    }

    //retourne un tableau[3] avec le mastery score (Object) des 3 champions avec le plus de masteries
    public static Object[] getMasteriesPoints(String sumName, String regionString) {
        Region region = stringToRegion(regionString);
        Summoner summoner = Summoner.named(sumName).withRegion(region).get();
        Object [] champMasteries = new Object[3];
        //parsing
        try {
            JSONArray champArray = (JSONArray) new JSONParser().parse(Arrays.toString(getMostMasteryChampionObject(summoner)));
            for (int i =0; i<3;i++) {
                champMasteries[i] = getJSONValue(champArray, i, "points");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return champMasteries;
    }

    //retourne un tableau[3] avec les noms (String) des 3 champions avec le plus de masteries
    public static String[] getNamesFromIds(String sumName, String regionString) {
        Region region = stringToRegion(regionString);
        Summoner summoner = Summoner.named(sumName).withRegion(region).get();
        Object[] ids = getMasteryChampIds(sumName,regionString);
        String[] champNames = new String[3];
        for (int i = 0;i<3;i++) {
            Champion champ = Champion.withId(ids[i].hashCode()).withRegion(Region.NORTH_AMERICA).get();
            champNames[i] = champ.getName();
        }
        return champNames;
    }

    //retourne un tableau[3] avec le id (Object) des 3 champions avec le plus de masteries
    public static Object[] getMasteryChampIds(String sumName, String regionString) {
        Region region = stringToRegion(regionString);
        Summoner summoner = Summoner.named(sumName).withRegion(region).get();
        Object [] champIds = new Object[3];
        //parsing
        try {
            JSONArray champArray = (JSONArray) new JSONParser().parse(Arrays.toString(getMostMasteryChampionObject(summoner)));
            for (int i =0; i<3;i++) {
                champIds[i] = getJSONValue(champArray, i, "championId");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return champIds;
    }

    //PRIVATE METHODS
    protected static Region stringToRegion(String regionString) {
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

    protected static Object [] getMostMasteryChampionObject(Summoner sum) {
        Object [] mostMastery = new Object[3];
        Object [] allChampions = sum.getChampionMasteries().toArray();
        for (int i = 0; i<3; i++) {
            mostMastery[i] = allChampions[i];
        }
        return mostMastery;
    }

    protected static Object getJSONValue(JSONArray array, int index, String keyword) {
        JSONObject champ = (JSONObject) array.get(index);
        Object value = champ.get(keyword);
        return value;
    }
}
