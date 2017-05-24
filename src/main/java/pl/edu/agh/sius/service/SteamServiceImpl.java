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

    @HystrixCommand(fallbackMethod = "getInfoAboutGameDefault")
    @Override
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
    public Map<Integer, String> getAppList() {
        try {
            return steamApi.listApps();
        }catch (SteamApiException e) {
            e.printStackTrace();
            return null;
        }
    }


    public SteamApp getInfoAboutGameDefault(String name) {

        return null;
    }
}
