package ru.dencore.Airport.dashboard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для получения страницы с dashboard'ом
 */
@Controller
@RequiredArgsConstructor
public class DashBoardController {

    @GetMapping("/dashboard")
    public String getPageOfDashBoard() {
        return "dashboard";
    }


}
