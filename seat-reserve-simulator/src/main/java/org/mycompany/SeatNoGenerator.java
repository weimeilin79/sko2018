package org.mycompany;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component("SeatNoGenerator")
public class SeatNoGenerator {

	public Reservation simulate() {
		Reservation reservation = new Reservation();
		
		reservation.setCandidatename("Simulator");
		reservation.setEmail("simulator@email.com");
		reservation = genSeat(reservation);
		
		return reservation;
	}
	
	
	private Reservation genSeat(Reservation reservation) {
		Random rn = new Random();
		int rowrange = 19;
		int row =  rn.nextInt(rowrange) + 1;
		int colrange = 48;
		int colno =  rn.nextInt(colrange) + 1;
		
		if(isSeat(row,colno)) {
			
			reservation.setSeatno(row+"_"+colno);
			String seatname=Integer.toString(calSeatName(row,colno));
			reservation.setSeatname(seatname);
			
			
		
		}else {
			return genSeat(reservation);
		}
		
		
		return reservation;
	}
	
	private int calSeatName(int row, int colno) {
		
		int base=0;
				
		if(row==1)  
			base=0-8;
		else if(row==2)  
			base=28-8;	
		else if(row==3)  
			base=56-7;
		else if(row==4)  
			base=86-6;
		else if(row==5)  
			base=118-5;
		else if(row==6)  
			base=152-4;
		else if(row==7)  
			base=188-3;
		else if(row==8)  
			base=226-2;
		else if(row==9)  
			base=266-1;
		else 		
			base=((row-10)*44)+308;
		
		System.out.println(row+"-"+colno+"--->base-"+base+" and getcolseatno(colno)-"+getcolseatno(colno));
		return base+getcolseatno(colno);
	}
	
	private int getcolseatno(int colno) {
		
		if(colno<9)
			return colno;
		else if(colno < 19)
			return colno-1;
		else if(colno < 29)
			return colno-2;
		else if(colno < 40)
			return colno-3;
		else if(colno < 49)
			return colno-4;
		else return 0;
	}
	
	private boolean isSeat(int row, int colno) {
		switch (colno) {
			case 9:  return false;
			case 29:  return false;		
			case 40:  return false;
		}
		
		if(colno < 1 || colno > 48) {
			return false;
		}else if(row == 1 || row == 2 ) {
			if(colno < 11 || colno > 39 )
				return false;
		}else if( row > 2 && row < 10) {
			if (colno < (11-row))
				return false;
			if (colno > (39+row))
				return false;
		}
		
		return true;
	}
}
