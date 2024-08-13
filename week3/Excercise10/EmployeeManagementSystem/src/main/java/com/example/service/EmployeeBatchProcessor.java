package com.example.service;

import com.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeBatchProcessor {

    private SessionFactory sessionFactory;

    public EmployeeBatchProcessor(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void processEmployeeBatch(List<Employee> employees) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        int batchSize = 20;
        int count = 0;

        for (Employee employee : employees) {
            session.save(employee);
            count++;

            if (count % batchSize == 0) {
                session.flush();
                session.clear();
            }
        }

        transaction.commit();
    }
}