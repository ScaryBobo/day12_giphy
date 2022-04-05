package VTTP.gifphy.controller;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import VTTP.gifphy.service.GiphyService;


@Controller
@RequestMapping (path="/")
public class SearchController {
    @Autowired
    private GiphyService gifSvc;
    @GetMapping(path="/search")

    
    public String getSearchResult(@RequestParam (name="name") String name,
    @RequestParam (name="limit") Integer limit, 
    @RequestParam (name="rating") String rating, Model model){


        List<String> rawResult = new ArrayList<>();
        rawResult = gifSvc.getGifs(name, limit, rating);


        model.addAttribute("pictures", rawResult);

        return "searchResult";
    }
}
