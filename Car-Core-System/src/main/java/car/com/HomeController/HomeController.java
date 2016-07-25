package car.com.HomeController;

import car.com.Model.Model;
import car.com.Service.IbranchService;
import car.com.Service.ModelService;
import car.com.until.RestResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ModelService modelService;
    @Autowired
    private IbranchService ibranchService;

    @RequestMapping(value = "")
    @ResponseBody
    public RestResponseDto test() {
        return new RestResponseDto(200, "Home Thien");
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() throws IOException {
        return "ShandangIT-CoreSystem";
    }

    @RequestMapping(value = "/show-all",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public RestResponseDto showall() throws IOException {
        return modelService.getAllShow();
    }

    @RequestMapping(value = "/update-info", method = RequestMethod.GET)
    @ResponseBody
    public String updateinfo() throws IOException {
        try {
            Model car = new Model();
            car.setShowId(10);
            car.setShowName("Shan-Audi @");
            car.setAddress("119 Dong Khoi");
            car.setCity("The state united");
            modelService.Updateshow(car);
            return "Update success";
        } catch (Exception e) {
            e.printStackTrace();
            return Arrays.toString(e.getStackTrace());
        }
    }

    @RequestMapping(value = "/add-info", method = RequestMethod.GET)
    @ResponseBody
    public String addinfor() throws IOException {
        try {
            Model car = new Model();
            car.setShowName("Shop 08 - Mescedes-Bens");
            car.setAddress("119 Dong Khoi");
            car.setCity("Ha Noi-Nam Dinh");
            modelService.Addshow(car);
            return "Add success";
        } catch (Exception e) {
            e.printStackTrace();
            return Arrays.toString(e.getStackTrace());
        }
    }

    @RequestMapping(value = "/get-list-logo",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public RestResponseDto getListLogo() throws IOException {
        return ibranchService.getListLogo();
    }

    @RequestMapping(value = "/get-car-detail",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public @ResponseBody
    RestResponseDto getCarInfo(){
        return ibranchService.getCarInfo();
    }
    @RequestMapping(value = "/search-info", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public RestResponseDto getShowInfo (int showId){
        return modelService.getShow(showId);
    }

}
