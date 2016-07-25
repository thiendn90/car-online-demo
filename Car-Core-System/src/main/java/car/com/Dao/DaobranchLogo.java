package car.com.Dao;

import car.com.until.RestResponseDto;
import org.springframework.stereotype.Service;

/**
 * Created by ThienDN on 9/2/2015.
 */
@Service
public interface DaobranchLogo {
    public RestResponseDto getListLogo();
    public RestResponseDto getCarInfo();
}
