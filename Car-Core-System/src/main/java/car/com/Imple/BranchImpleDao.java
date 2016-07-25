package car.com.Imple;

import car.com.Dao.DaobranchLogo;
import car.com.Model.BranchDto;
import car.com.Model.DemoCarInfo;
import car.com.until.RestResponseDto;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ThienDN on 9/2/2015.
 */
@Service
public class BranchImpleDao implements DaobranchLogo {

    @Autowired
    private SessionFactory session;

    public RestResponseDto getListLogo(){
        Criteria criteria = session.getCurrentSession().createCriteria(BranchDto.class);
        return  new RestResponseDto(0,criteria.list());
    }

    public RestResponseDto getCarInfo(){
        Criteria criteria = session.getCurrentSession().createCriteria(DemoCarInfo.class);
        return new RestResponseDto(0,criteria.list());
    }
}
