package es.devfromt.wildplants.dao.impl;

import es.devfromt.wildplants.dao.UseDao;
import es.devfromt.wildplants.entities.UseEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("useDao")
@Transactional
public class UseDaoImpl implements UseDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(UseEntity use) {
        sessionFactory.getCurrentSession().saveOrUpdate(use);
    }
}
