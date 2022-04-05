package VTTP.gifphy.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class GiphyService {
    
    @Value("${giphy.api.key}")
    private String giphyKey;


    //api_key=SwAeiDlPGbKxkbiJUhx8Mqx1VEg2yJyb (export = "SwAeiDlPGbKxkbiJUhx8Mqx1VEg2yJyb")

    public List<String> getGifs (String name, Integer limit, String rating){

        String url = "https://api.giphy.com/v1/gifs/search?api_key=" + giphyKey + "&q="
        + name + "&limit=" + limit + "&offset=0&rating=" + rating + "&lang=en";
        
        RequestEntity<Void> req = RequestEntity
        .get(url)
        .accept(MediaType.APPLICATION_JSON)
        .build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        //System.out.println(resp.getBody());

        JsonObject data= null;

        try (InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())){
            JsonReader reader = Json.createReader(is);
            data = reader.readObject();
        } catch (Exception e) {
            
        }
        List<String> rawResult = new ArrayList<>();
        JsonArray resultArray = data.getJsonArray("data");    
        
        String imageUrl = null;
        
        for (int i = 0 ; i < resultArray.size() ; i++){
        // imageUrl= resultArray.getJsonObject(i).getJsonObject("images").getJsonObject("original")
        // .getJsonString("url").toString();
        // rawResult.add(imageUrl);

        JsonObject temp = resultArray.getJsonObject(i);
        JsonObject temp1= (JsonObject) temp.get ("images");
        JsonObject temp2 = (JsonObject) temp1.get("fixed_width");
        imageUrl = temp2.getString("url");
        rawResult.add(imageUrl);
        }
        System.out.println(rawResult);
        return rawResult;

    }


}
