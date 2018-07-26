package net.transino.lms.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author lee
 * @since 5.0
 */
@Slf4j
@Controller
@RequestMapping(value = "/")
public class MainController {
    @GetMapping(value = "/home")
    String home() {
        return "index";
    }
}