import java.io.IOException;
import java.net.URI;

import java.util.ArrayList;

class Handler implements URLHandler {

    ArrayList<String> listOfStrings = new ArrayList<String>();

    // int listLength = 0;
    public String printArray() {
        String finalString = "";
        for (int i = 0; i < listOfStrings.size(); i++) {
            finalString += listOfStrings.get(i)+"," +"\t";
        }
        return finalString;

    }

    public String handleRequest(URI url) {

        if (url.getPath().equals("/")) {
            return String.format("List: ", listOfStrings);
        } 
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    for (int i = 1; i < parameters.length; i++) {
                        listOfStrings.add(parameters[i]);
                    }
                    String printedArray = printArray();
                    return String.format(
                    "'%s' added to the list.\n\nThere are now this many items in the list: %d \n\nList: \t%s", 
                    parameters[1], listOfStrings.size(), printedArray);
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
