package es.devfromt.wildplants.dao.impl;

import es.devfromt.wildplants.dao.PlantDao;
import es.devfromt.wildplants.entities.PlantEntity;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository("plantDao")
public class PlantDaoImpl implements PlantDao {

    private static final Logger log= LoggerFactory.getLogger(PlantDaoImpl.class);
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly= true)
    public List<PlantEntity> findAll() {
        return sessionFactory.getCurrentSession().createQuery("select p from Plant p").list();
    }

    @Override
    public List<PlantEntity> findAllWithUse() {
        return (List<PlantEntity>) sessionFactory.getCurrentSession().getNamedQuery("Plant.findAllWithUse").list();
    }

    @Override
    public PlantEntity findById(Long id) {
        return (PlantEntity) sessionFactory.getCurrentSession().getNamedQuery("Plant.findById").setParameter("id", id).uniqueResult();
    }

    @Override
    public PlantEntity findByName(String name) {
        return (PlantEntity) sessionFactory.getCurrentSession().getNamedQuery("Plant.findByName").setParameter("name", name).uniqueResult();
    }

    @Override
    public PlantEntity save(PlantEntity plant) {
        sessionFactory.getCurrentSession().saveOrUpdate(plant);
        return plant;
    }

    @Override
    public void delete(PlantEntity plant) {
    sessionFactory.getCurrentSession().delete(plant);
    }
}
