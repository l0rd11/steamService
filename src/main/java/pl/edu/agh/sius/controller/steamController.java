package pl.edu.agh.sius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.sius.service.SteamService;
import pl.edu.agh.sius.service.Util;
import com.github.goive.steamapi.data.SteamApp;
import java.util.Map;

/**
 * Created by ja on 06.05.17.
 */
@RestController
public class steamController {

    @Autowired
    private SteamService steamService;

    @Autowired
    Util util;


    @RequestMapping(value = "/getInfo/{name}", method = RequestMethod.GET)
    public ResponseEntity<SteamApp> getInfo(@PathVariable("name") String name) {
        SteamApp response = steamService.getInfoAboutGame(name);
        if(response != null)
            return util.createOkResponse(response);
        else
            return util.createBadResponse(response);
    }

    @RequestMapping(value = "/getApps", method = RequestMethod.GET)
    public ResponseEntity<Map<Integer, String>> getApps() {
        return util.createOkResponse(steamService.getAppList());
    }


}
