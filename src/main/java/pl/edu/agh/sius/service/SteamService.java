package pl.edu.agh.sius.service;

import java.util.Map;
import com.github.goive.steamapi.data.SteamApp;

/**
 * Created by ja on 06.05.17.
 */
public interface SteamService {
    SteamApp getInfoAboutGame(String name);
    Map<Integer,String> getAppList();
}
