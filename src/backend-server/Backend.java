import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Backend {
  private static ArrayList<String> requestLocationTrendsURLs(
      String locationName){
    Twitterer twitterer = new Twitterer();
    return twitterer.getTrendsGivenLocationName(locationName);
  }

  private static ArrayList<String> requestTweetsWithQuery(String searchQuery) {
    Twitterer twitterer = new Twitterer();
    return twitterer.getColoradoTweetsGivenQueryTerm(searchQuery);
  }

  private static String formatToJSONArray(ArrayList<String> itemsToAdd) {
    String jsonArray = "[";

    for (int i = 0; i < itemsToAdd.size(); i++) {
      String item = itemsToAdd.get(i);
      jsonArray +=
          "\"" + item.replace("\"", "").replace("[", "").replace("]", "")
              .replace("\n", "") + "\"";

      if (i < itemsToAdd.size() - 1) {
        jsonArray += ",";
      }
    }

    jsonArray += "]";

    return jsonArray;
  }

  private static String handleRequest(String request) {
    String requestType = "";
    String requestBody = "";

    try {
      Scanner requestScanner = new Scanner(request);
      requestType = requestScanner.next();
      requestBody = requestScanner.nextLine().trim();
      requestScanner.close();
    } catch (Exception e) {
      requestType = "Invalid Input";
      requestBody = "Invalid Input";
    }

    ArrayList<String> requestResults = new ArrayList<>();

    if (requestType.equals("trends")) {
      requestResults = Backend.requestLocationTrendsURLs(requestBody);
    } else if (requestType.equals("tweets")) {
      requestResults = Backend.requestTweetsWithQuery(requestBody);
    } else {
      System.err.println("Invalid requestType of: " + requestType);
      return "500";
    }

    return Backend.formatToJSONArray(requestResults);
  }

  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("No port number provided.");
      return;
    }
    int port = Integer.parseInt(args[0]);

    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("Server is listening on port " + port);

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("New client connected");

        Scanner socketReader = new Scanner(socket.getInputStream());
        PrintWriter socketWriter =
            new PrintWriter(socket.getOutputStream(), true);

        System.out.println("Waiting on client to talk...");
        String requestBody = socketReader.nextLine();
        String twitterResponse = handleRequest(requestBody);
        System.out.println("Sending twitter response to client...");
        socketWriter.println(twitterResponse);

        socketReader.close();
        socketWriter.close();
        socket.close();
        System.out.println("Connection closed...");
      }
    } catch (Exception ex) {
      System.out.println("Server exception: " + ex.getMessage());
      ex.printStackTrace();
    }
  }
}
