import twitter4j.GeoLocation;
import twitter4j.Location;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.ArrayList;


public class Twitterer {
  private Twitter twitter;

  public Twitterer(){
    twitter = TwitterFactory.getSingleton();
  }

  public ArrayList<String> getColoradoTweetsGivenQueryTerm(String searchTerm) {
    if (searchTerm.length() == 0) {
      searchTerm = "Computer Science";
    }

    Query query = new Query(searchTerm);
    query.setCount(15);
    query.setGeoCode(new GeoLocation(39.7392, -104.9903), 20, Query.MILES);
    query.setSince("2019-11-2");
    ArrayList<String> results = new ArrayList<>();

    try{

      QueryResult result = twitter.search(query);
      int counter = 0;
      for (Status tweet : result.getTweets()){
        counter++;
        results.add("Tweet #" + counter + ": @ " + tweet.getUser().getName() + " tweeted \"" + tweet.getText() + "\"");
      }
    } catch (TwitterException e) {
      return new ArrayList<String>();
    } catch (Exception e){
      e.printStackTrace();
    }

    return results;
  }

  public ArrayList<String> getTrendsGivenLocationName(String locationName) {
    if (locationName.length() == 0) {
      locationName = "Osaka";
    }

    ArrayList<String> trendingURLs = new ArrayList<>();

    try{
      ResponseList<Location> trendsResources =
          twitter.trends().getAvailableTrends();

      for (Location trendLocation : trendsResources) {
        if (trendLocation.getName().equals(locationName)) {
          int locationWOEID = trendLocation.getWoeid();
          Trend[] locationTrends =
              twitter.getPlaceTrends(locationWOEID).getTrends();

          for (Trend locationTrend : locationTrends) {
            trendingURLs.add(locationTrend.getURL());
          }

          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return trendingURLs;
  }
}
