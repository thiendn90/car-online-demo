package car.com.Imple;

import car.com.Dao.ModelDao;
import car.com.Model.Model;
import car.com.Service.ModelService;
import car.com.until.RestResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThienDN on 8/24/2015.
 */
@Service
@Transactional(readOnly = false,rollbackFor = Exception.class)
public class ModelImpService implements ModelService {
    @Autowired
    private ModelDao modelDao;


    public void Addshow(Model model) {
        modelDao.Addshow(model);
//        return new RestResponseDto(0,"Add success with data correct");
    }


    public void Updateshow(Model model) {
        modelDao.Updateshow(model);
    }

    public RestResponseDto Deleteshow(int showid) {
       return modelDao.Deleteshow(showid);

    }


    public RestResponseDto getShow(int showId) {
        return modelDao.getShow(showId);
    }


    public RestResponseDto getAllShow() {
        return modelDao.getAllShow();
    }
}
