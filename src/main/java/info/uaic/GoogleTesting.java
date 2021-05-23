package info.uaic;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.CustomsearchRequestInitializer;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoogleTesting {

/*    public static void Testing() throws IOException, GeneralSecurityException {

        String searchQuery = "test"; //The query to search
        String cx = "7f2c402708a368e9e"; //Your search engine

        //Instance Customsearch
        Customsearch cs = new Customsearch.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), null)
                .setApplicationName("MyApplication")
                .setGoogleClientRequestInitializer(new CustomsearchRequestInitializer("AIzaSyD5ZrcI4MpySVubkrgkKlrDZfaA3JzSB-A"))
                .build();

        //Set search parameter
        Customsearch.Cse.List list = cs.cse().list(searchQuery).setCx(cx);

        //Execute search
        Search result = list.execute();
        if (result.getItems()!=null){
            for (Result ri : result.getItems()) {
                //Get title, link, body etc. from search
                System.out.println(ri.getTitle() + ", " + ri.getLink());
            }
        }
    }*/

    public static List<Result> searchOnStackOverflow(String searchQuery) throws GeneralSecurityException, IOException {

        List<Result> results = new ArrayList<>();

        // search engine-ul facut cu google
        String cx = "7f2c402708a368e9e";

        // fac o instanta de Customsearch
        Customsearch cs = new Customsearch.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), null)
                .setApplicationName("ProiectPA")
                .setGoogleClientRequestInitializer(new CustomsearchRequestInitializer("AIzaSyD5ZrcI4MpySVubkrgkKlrDZfaA3JzSB-A")) // my API-key
                .build();

        // aici ii dau dupa ce sa caute basically
        Customsearch.Cse.List list = cs.cse().list(searchQuery).setCx(cx);

        // execut cautarea
        Search result = list.execute();
        if (result.getItems()!=null){
            for (Result ri : result.getItems()) {
                if(ri.getLink().startsWith("https://stackoverflow.com/")) {
                    results.add(ri);
                }
            }
        }

        return results;
    }

    public static List<Result> searchOnWikipedia(String searchQuery)
                                throws GeneralSecurityException, IOException {

        List<Result> results = new ArrayList<>();

        // search engine-ul facut cu google
        String cx = "7f2c402708a368e9e";

        // fac o instanta de Customsearch
        Customsearch cs = new Customsearch.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), null)
                .setApplicationName("ProiectPA")
                .setGoogleClientRequestInitializer(new CustomsearchRequestInitializer("AIzaSyD5ZrcI4MpySVubkrgkKlrDZfaA3JzSB-A")) // my API-key
                .build();

        // aici ii dau dupa ce sa caute basically
        Customsearch.Cse.List list = cs.cse().list(searchQuery).setCx(cx);

        // execut cautarea
        Search result = list.execute();
        if (result.getItems()!=null){
            for (Result ri : result.getItems()) {
                if(ri.getLink().startsWith("https://en.wikipedia.org/wiki/")) {
                    results.add(ri);
                }
            }
        }

        return results;

    }
}
