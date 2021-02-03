package damianmatysko;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) throws IOException {
        URL data = new URL("http://www.itsovy.sk:5000/data");
        URLConnection yc = data.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;

        List<WordX> wordXList = new ArrayList<>();// = new Gson().fromJson(, WordX.class);
        String s = "";

        while ((inputLine = in.readLine()) != null) {
            s = inputLine;
        }
        System.out.println(s);

        JSONArray jsonArray = new JSONObject(s).getJSONArray("world_x");
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            wordXList.add(new WordX(jsonObject.getInt("pop"), jsonObject.getString("code"), jsonObject.getString("district"), jsonObject.getString("name")));
        }


        Predicate<WordX> popPre = n -> n.getPop() > 100000;
        HollandPredicate holPre = new HollandPredicate();

        wordXList.stream().filter(popPre).filter(holPre).forEach(System.out::println);
    }
}
