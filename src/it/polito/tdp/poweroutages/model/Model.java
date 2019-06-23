package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	
	private List<PowerOutages> outages;
	private List<PowerOutages> worstCase;
	private int maxYears;
	private int maxHours;
	private int maxPeople;
	
	public Model() {
		podao = new PowerOutageDAO();
		
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<PowerOutages> worstCaseAnalysis(int nercid, int maxHours, int maxYears){
		outages = podao.getPowerOutages(nercid);
		//System.out.println(outages);
		List<PowerOutages> parziale= new ArrayList<>();
		worstCase=new ArrayList<PowerOutages>();
		this.maxPeople=0;
		this.maxHours= maxHours;
		this.maxYears= maxYears;
		searchWorstCase(parziale);
		return worstCase;
	}
	private void searchWorstCase(List<PowerOutages> parziale) {
		
		if(people(parziale)>maxPeople) {
			maxPeople= people(parziale);
			worstCase= new ArrayList<PowerOutages>(parziale);
		}
		for(PowerOutages po: outages) {
			if(!parziale.contains(po)) {
			if(isValid(parziale, po)) {
				parziale.add(po);
				searchWorstCase(parziale);
				
			}
		parziale.remove(po);
			}
		}
	}


	private int people(List<PowerOutages> parziale) {
		int persone=0;
		for(PowerOutages po: parziale) {
			persone += po.getCustomers_affected();
		}
		return persone;
	}
	private boolean isValid(List<PowerOutages> parziale, PowerOutages po ) {
		parziale.add(po);
		if(hoursPO(parziale)<maxHours && yearsPO(parziale)<maxYears) {
			parziale.remove(po);
			return true;
		}else {
			parziale.remove(po);
			return false;
		}
		
	}
	private int hoursPO(List<PowerOutages> parziale) {
		int durata=0;
		for(PowerOutages po: parziale) {
			durata += po.duration();
		}
		return durata;
	}
	private int yearsPO(List<PowerOutages> parziale) {
		//Collections.sort(parziale);
		int anno1= parziale.get(0).getDate_event_began().getYear();
		int anno2= parziale.get(parziale.size()-1).getDate_event_began().getYear();
		return anno1-anno2;
	}


//hoursPO(parziale)<maxHours && yearsPO(parziale)<maxYears
}
