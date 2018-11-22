package hello.user;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/restapi/user")
public class UserController {

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public String login() {
        return "login";
    }

    @PostMapping()
    void test(){

    }

    @PutMapping()
    void testg(){

    }
}
