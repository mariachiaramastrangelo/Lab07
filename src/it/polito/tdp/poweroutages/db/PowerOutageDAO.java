package it.polito.tdp.poweroutages.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;

public class PowerOutageDAO {

	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	 public List<PowerOutages> getPowerOutages(int nercid){
		 String sql="SELECT * " + 
		 		"FROM `PowerOutages` po " + 
		 		"WHERE po.`nerc_id`=? ";
		 try {
			 Connection conn= ConnectDB.getConnection();
			 List<PowerOutages> result= new ArrayList<>();
			 PreparedStatement st= conn.prepareStatement(sql);
			 st.setInt(1, nercid);
			 ResultSet rs= st.executeQuery();
			 while(rs.next()) {
				 
				 LocalDateTime begin= rs.getTimestamp("date_event_began").toLocalDateTime();
				 LocalDateTime finish= rs.getTimestamp("date_event_finished").toLocalDateTime();
				 
				
				 PowerOutages po= new PowerOutages(rs.getInt("id"), 
						 							rs.getInt("event_type_id"), 
						 							rs.getInt("tag_id"), 
						 							rs.getInt("area_id"), 
						 							rs.getInt("nerc_id"),
						 							rs.getInt("responsible_id"),
						 							rs.getInt("customers_affected"),
						 							begin,
						 							finish,
						 							rs.getInt("demand_loss"));
			 result.add(po);
			 }
			 
			 conn.close();
			 return result;
			 
		 }catch(SQLException e) {
			 e.printStackTrace();
			 throw new RuntimeException(e);
		 }
	 }
}
