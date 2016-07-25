package shan.com.Service_Interface;

import org.springframework.stereotype.Service;
import shan.com.util.RestResponeDto;
import shan.com.util.RestTemplates;
import shan.com.util.StringUtils;

@Service
public class ServiceShowRoomImpl implements Ishowroom {

    @Override
    public RestResponeDto showAll(){
        String url = StringUtils.getValueKey("get_showroom_all");
        RestResponeDto result;
        if(!url.equals("")){
            result = RestTemplates.getInstance().getForObject(url, RestResponeDto.class);
            return result;
        }
        return null;
    }
    @Override
    public RestResponeDto search(int showId){
        String url = StringUtils.getValueKey("get_search_one").concat("?showId="+showId);
        RestResponeDto result;
        if(!url.equals("")){
            result = RestTemplates.getInstance().getForObject(url, RestResponeDto.class);
            return result;
        }
        return null;
    }
}
