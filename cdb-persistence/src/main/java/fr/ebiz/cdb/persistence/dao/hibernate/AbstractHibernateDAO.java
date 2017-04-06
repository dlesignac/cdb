package fr.ebiz.cdb.persistence.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractHibernateDAO {

    @Autowired
    private SessionFactory sessionFactory;

    Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

}
