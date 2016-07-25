package shan.com.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import shan.com.util.RestResponeDto;

/**
 * Created by ThienDN on 9/1/2015.
 */
@Controller
@RequestMapping(value = "/member/")
public class UserController {

    @RequestMapping(value = "userinfo",method = RequestMethod.GET)
    public String getUser(){
        return "view/pc/user";
    }
}
