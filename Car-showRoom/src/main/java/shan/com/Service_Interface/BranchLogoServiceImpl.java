package shan.com.Service_Interface;

import org.springframework.stereotype.Service;
import shan.com.util.RestResponeDto;
import shan.com.util.RestTemplates;
import shan.com.util.StringUtils;

/**
 * Created by ThienDN on 9/2/2015.
 */
@Service
public class BranchLogoServiceImpl implements IBranchCar {

    @Override
    public RestResponeDto getListLogo(){
        String url = StringUtils.getValueKey("get_list_logo");
        RestResponeDto result;
        if(!url.equals("")){
            result = RestTemplates.getInstance().getForObject(url, RestResponeDto.class);
            return result;
        }
        return null;
    }
    @Override
    public RestResponeDto getCarInfo(){
        String  url = StringUtils.getValueKey("get_car_detail_info");
        RestResponeDto result;
        if(!equals(url)){
            result = RestTemplates.getInstance().getForObject(url,RestResponeDto.class);
            return result;
        }
        return null;
    }
}
