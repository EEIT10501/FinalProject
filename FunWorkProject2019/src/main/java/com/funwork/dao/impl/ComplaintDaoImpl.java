package com.funwork.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.ComplaintDao;
import com.funwork.model.Complaint;

@Repository
public class ComplaintDaoImpl implements ComplaintDao {

	@Autowired
	SessionFactory factory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Complaint> getAllComplaints() {
		String hql = "FROM Complaint";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Complaint> getComplaintDealList() {
		String hql = "FROM Complaint WHERE status = '待處理' ORDER BY submitTime ASC";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

	@Override
	public Complaint getComplaintById(Integer cpId) {
		Session session = factory.getCurrentSession();
		return session.get(Complaint.class, cpId);
	}

	@Override
	public Complaint processComplaint(Integer cpId, String closeReason) {
		Session session = factory.getCurrentSession();
		Complaint complaint = session.get(Complaint.class, cpId);
		complaint.setProcessDescription(closeReason);
		complaint.setStatus("已處理");
		complaint.setProcessTime(new Timestamp(System.currentTimeMillis()));
		return complaint;
	}

	@Override
	public void insertCp(Complaint cp) {
		Session session = factory.getCurrentSession();
		session.save(cp);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Complaint> getComplaintHistoryList() {
		String hql = "FROM Complaint WHERE status = '已處理' ORDER BY submitTime ASC";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

}
