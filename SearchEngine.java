import java.io.IOException;
import java.util.*;
import java.net.URI;

class Handler implements URLHandler {
    ArrayList<String> words = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                words.add(parameters[1]);
                return String.format("Word added to the list! The word added was %s", parameters[1]);
            }
            
        } else if (url.getPath().equals("/search")) {
            ArrayList<String> temp = new ArrayList<String>();
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                for (int i = 0; i < words.size(); i++) {
                    if (words.get(i).contains(parameters[1])){
                        temp.add(words.get(i));
                    }
                }
                
                return String.format("Words: %s", temp);
            } else {
                return "Add a query.";
            }
        } else {
            return "404 Not Found! Use a different path.";
        }
        return null;
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}