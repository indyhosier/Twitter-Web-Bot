//package twitter4j.examples.oauth;

import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
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

import twitter4j.api.TrendsResources;
import twitter4j.conf.*;


import java.util.Properties;

public class TwitterDriver {


  public static void main(String[] args) {

    Twitterer bigBird = new Twitterer();

    //bigBird.coQuery("Hello world");
    
    bigBird.getOsakaTrends();

    try{
    bigBird.tweetOut("Hello, World!");
    }

    catch (Exception e){
      e.printStackTrace();
    }
    
    
    
  }

}


  
  

    




