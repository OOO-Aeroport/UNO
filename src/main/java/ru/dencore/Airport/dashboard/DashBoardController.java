package ru.dencore.Airport.dashboard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashBoardController {

    @GetMapping("/dashboard")
    public String getPageOfDashBoard(){
        return "dashboard";
    }


}
