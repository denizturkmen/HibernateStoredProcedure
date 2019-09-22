package com.denizturkmen.Client;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.hibernate.Session;

import com.denizturkmen.Util.HibernateUtil;

public class CallStoredProcedureTest {

	public static void main(String[] args) {
		
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("sp_count_phones");
			procedureQuery.registerStoredProcedureParameter( "personIds", Long.class, ParameterMode.IN);
			procedureQuery.registerStoredProcedureParameter( "phoneCounts", Long.class, ParameterMode.OUT);

			procedureQuery.setParameter("personIds", 2L);

			procedureQuery.execute();
			Long phoneCount = (Long) procedureQuery.getOutputParameterValue("phoneCounts");
			
			System.out.println("Phone Count:"+phoneCount);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
