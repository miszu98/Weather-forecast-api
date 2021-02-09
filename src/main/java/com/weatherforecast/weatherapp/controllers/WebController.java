package com.weatherforecast.weatherapp.controllers;

import com.weatherforecast.weatherapp.models.Weather;
import com.weatherforecast.weatherapp.services.weatherservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    @Autowired
    private weatherservice ws;

    @GetMapping("/show")
    public String home(Model model){
        model.addAttribute("serviceofweather", ws);
        model.addAttribute("instanceofweather", new Weather());
        return "index";
    }

    @PostMapping("/")
    public String search(@ModelAttribute Weather weather) {
        String miasto = weather.getCity();
        if (!miasto.equals("")){
            ws.setCity(miasto);
        }
        return "redirect:/show";
    }


}
