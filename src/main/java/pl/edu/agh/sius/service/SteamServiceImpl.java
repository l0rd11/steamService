package pl.edu.agh.sius.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import com.github.goive.steamapi.SteamApi;
import com.github.goive.steamapi.data.SteamApp;
import com.github.goive.steamapi.exceptions.SteamApiException;

/**
 * Created by ja on 06.05.17.
 */
@Service
public class SteamServiceImpl implements SteamService {

    SteamApi steamApi = new SteamApi("US");


    @Override
    @HystrixCommand(fallbackMethod = "getInfoAboutGameDefault")
    public SteamApp getInfoAboutGame(String name) {
        try {
            int appId = Integer.parseInt(name);
            SteamApp steamApp2 = steamApi.retrieve(appId);
            return steamApp2;
        }catch (NumberFormatException|SteamApiException err){
            try {
                SteamApp steamApp2 = steamApi.retrieve(name);
                return steamApp2;
            }catch (SteamApiException e) {
                e.printStackTrace();
                return null;
            }
        }
    }


    @Override
    @HystrixCommand(fallbackMethod = "getDefaultAppList")
    public Map<Integer, String> getAppList() {
        try {
            return steamApi.listApps();
        }catch (SteamApiException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Map<Integer, String> getDefaultAppList() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1,"Sorry something went wrong ");
        return map;
    }

    public SteamApp getInfoAboutGameDefault(String name) {

        return null;
    }
}
