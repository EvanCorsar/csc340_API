package com.csc340.RestAPI;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author sentini
 */
@RestController
public class RestApiController {

    /**
     * Get a random joke and print it to the console
     * 
     * @return random joke
     */
    @GetMapping("/joke")
    public Object getJoke(){
        String url = "https://geek-jokes.sameerkumar.website/api?format=json";
        RestTemplate restTemplate = new RestTemplate();
        Object jSonJoke = restTemplate.getForObject(url, Object.class);
        
        String joke = restTemplate.getForObject(url, String.class);
        
        JSONObject jo = new JSONObject(joke);
        System.out.println(jo.toString());
        String JSONjoke = jo.getString("joke");
        System.out.println("Joke: " + JSONjoke);
        
        return jSonJoke;
    }
    
    /**
     * Fetches data from API, sorts through nested JSON, and prints out the 
     * nation and population for the 2020 year.
     * 
     * @return Population of US from the latest year
     */
    @GetMapping("/population")
    public Object getPop(){
        String url = "https://datausa.io/api/data?drilldowns=Nation&measures=Population&year=latest";
        RestTemplate restTemplate = new RestTemplate();
        Object jSonPopulation = restTemplate.getForObject(url, Object.class);
        
        //console 
        String population = restTemplate.getForObject(url, String.class);
        
        //parse
        JSONObject jo = new JSONObject(population.toString());
        JSONArray result = jo.getJSONArray("data");
        JSONObject result1 = result.getJSONObject(0);
        
        String popNation = result1.getString("Nation");
        int popNum = result1.getInt("Population");
        
        //print 2020's population for US
        System.out.println(popNation + " : " + popNum);

        
        return jSonPopulation;
    }
    

}
