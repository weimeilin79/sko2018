package org.mycompany;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component("SeatNoGenerator")
public class SeatNoGenerator {

	
	public String genSeat() {
		Random rn = new Random();
		int rowrange = 19;
		int row =  rn.nextInt(rowrange) + 1;
		int colrange = 48;
		int colno =  rn.nextInt(colrange) + 1;
		
		if(isSeat(row,colno))
			return row+"_"+colno;
		else {
			return genSeat();
		}
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
