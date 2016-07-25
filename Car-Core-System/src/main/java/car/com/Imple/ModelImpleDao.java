package car.com.Imple;

import car.com.Dao.ModelDao;
import car.com.Model.Model;
import car.com.until.RestResponseDto;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ThienDN on 8/24/2015.
 */
@Service
public class ModelImpleDao implements ModelDao {
    @Autowired
    private SessionFactory session;

    public void Addshow(Model model) {
        String sql = "insert into showroom (showName, address, city) values (:ten, :dia, :tp)";
        Query query = session.getCurrentSession().createSQLQuery(sql)
                .setParameter("ten", model.getShowName())
                .setParameter("dia", model.getAddress())
                .setParameter("tp", model.getCity());
        query.executeUpdate();

    }

    public void Updateshow(Model model) {
        String sql ="update  showroom set showName = :ten, address = :diachi, city = :tp where showId = :code";
        Query query = session.getCurrentSession().createSQLQuery(sql)
                .setParameter("code", model.getShowId())
                .setParameter("ten", model.getShowName())
                .setParameter("diachi", model.getAddress())
                .setParameter("tp", model.getCity());
        query.executeUpdate();
    }

    public RestResponseDto Deleteshow(int showid) {
        String sql = "delete from showroom where showId = :shid";
        Query query = session.getCurrentSession().createSQLQuery(sql)
                .setParameter("shid",showid);
        query.executeUpdate();
        return new RestResponseDto(0,"Delete success.");
    }

    public RestResponseDto getShow(int showId) {
        Criteria criteria = session.getCurrentSession().createCriteria(Model.class);
        criteria.add(Restrictions.eq("showId",showId));
        Model model = (Model)criteria.uniqueResult();
        return new RestResponseDto(0,model);//return a object Model
    }

    public RestResponseDto getAllShow() {
        Criteria criteria = session.getCurrentSession().createCriteria(Model.class);
        return new RestResponseDto(0,criteria.list());
    }

}
