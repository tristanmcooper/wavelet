import java.io.IOException;
import java.net.URI;

import java.util.ArrayList;

class Handler implements URLHandler {

    ArrayList<String> listOfStrings = new ArrayList<String>();

    int listLength = 0;

    public String handleRequest(URI url) {

        if (url.getPath().equals("/")) {
            return String.format("List: ", listOfStrings);
        } 
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    listLength++;
                    for (int i = 1; i < parameters.length; i++) {
                        listOfStrings.add(parameters[i]);
                    }
                    return String.format("New string added to the" +
                    " list: %s There are now %d items in the list", 
                    parameters[1], listLength);
                }
            }

            return "404 Not Found!";
        }
        
    }
}
class SearchEngine extends Handler{
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);
        Server.start(port, new SearchEngine());


    }
    
}
