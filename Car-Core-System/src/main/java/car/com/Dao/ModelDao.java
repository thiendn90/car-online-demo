package car.com.Dao;

import car.com.Model.Model;
import car.com.until.RestResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ThienDN on 8/24/2015.
 */
@Service
public interface ModelDao {
    public void Addshow(Model add);
    public void Updateshow(Model update);
    public RestResponseDto Deleteshow(int showId);
    public RestResponseDto getShow(int showId);
    public RestResponseDto getAllShow();


}
