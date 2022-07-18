import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // create a http connection end return top 250 movies
        String iMDB_API_KEY = "k_01itq1or";
        String url = "https://imdb-api.com/en/API/Top250Movies/" + iMDB_API_KEY;
        URI uri = URI.create(url);

        // getting api response
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = httpClient.send(httpRequest, BodyHandlers.ofString());
        String body = response.body();

        // extract movie title, image, rating from api
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> parse = jsonParser.parse(body);
        // show data
        parse.stream().forEach(movie -> {
            System.out.println("title: " + movie.get("title") + " rank :" + movie.get("rank")
                    + " rating :" + movie.get("imDbRating"));
        });
    }
}
