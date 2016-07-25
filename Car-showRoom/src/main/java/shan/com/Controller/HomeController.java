package shan.com.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import shan.com.Service_Interface.BranchLogoServiceImpl;
import shan.com.Service_Interface.ServiceShowRoomImpl;
import shan.com.requestandrespone.ModelRespone;
import shan.com.util.RestResponeDto;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController{

	@Autowired
	private ServiceShowRoomImpl showroomService;

	@Autowired
	private BranchLogoServiceImpl branchService;

	private ObjectMapper mapper;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model)
	{
		model.addAttribute("userInfor","Dang Ngoc Thien");
		return "view/login";
	}
    /*
    * Home page*/
    @RequestMapping(value = {"/home","/"},method = RequestMethod.GET)
	public String HomePage(ModelMap model){
		RestResponeDto result = showroomService.showAll();
		RestResponeDto listLogo = branchService.getListLogo();
		RestResponeDto listCarInfo = branchService.getCarInfo();
		if(result.getReturnedCode() == 0){
			model.addAttribute("listLogo",listLogo.getData());
			model.addAttribute("dataShow",result.getData());
			model.addAttribute("listCarInfo",listCarInfo.getData());
			return "view/pc/homepage";
		}else{
			return "view/error-page/error";
		}
	}

}
