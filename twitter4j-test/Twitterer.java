import twitter4j.GeoLocation;
import twitter4j.Location;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;

import java.io.IOException;



public class Twitterer {
    
    private Twitter twitter;

    public Twitterer(){
    twitter = TwitterFactory.getSingleton();
    }


    public void coQuery (String searchTerm) {
        Query query = new Query(searchTerm);
        query.setCount(15);
        query.setGeoCode(new GeoLocation(39.7392, -104.9903), 20, Query.MILES);
        query.setSince("2019-11-2");
    
        try{
          QueryResult result = twitter.search(query);
          int counter = 0;
          System.out.println("Count: " + result.getTweets().size());
          for (Status tweet : result.getTweets()){
            counter++;
            System.out.println("Tweet #" + counter + ": @ " + tweet.getUser().getName() + "tweeted \"" + tweet.getText() + "\"");
          }
        }
          catch (Exception e){
            e.printStackTrace();
          }
          System.out.println();
        
      }



      public void getOsakaTrends(){
          try{
        ResponseList<Location> trendsResources = twitter.trends().getAvailableTrends();
      
      for (Location trendLocation : trendsResources) {
        if (trendLocation.getName().equals("Osaka")) {
          int osakaWOEID = trendLocation.getWoeid();
          Trends osakaTrendsWrapper = twitter.getPlaceTrends(osakaWOEID);
          Trend[] osakaTrends = osakaTrendsWrapper.getTrends();

          Trend testOsakaTrend = osakaTrends[0];
          System.out.println("Test Osaka Trend URL: " + testOsakaTrend.getURL());
        }
      }
    } catch (Exception e) {
      System.out.println("Couldn't get available trends.");
     }
    }


    public void tweetOut(String message) throws TwitterException, IOException{
        Status status = twitter.updateStatus(message);
        System.out.println("Successfully tweeted: " + status.getText());
    }

}
