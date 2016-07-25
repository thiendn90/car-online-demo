package car.com.Imple;

import car.com.Dao.DaobranchLogo;
import car.com.Service.IbranchService;
import car.com.until.RestResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThienDN on 9/2/2015.
 */
@Service
@Transactional(readOnly = false,rollbackFor = Exception.class)
public class BranchImplService implements IbranchService {
    @Autowired
    private DaobranchLogo daoBranch;

    public RestResponseDto getListLogo(){
        return daoBranch.getListLogo();
    }
   public RestResponseDto getCarInfo(){
       return daoBranch.getCarInfo();
   }
}
