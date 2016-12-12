/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_design_1;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class KorrektPersonNummer {

	static Date dagsdato = new Date();
	static Calendar kalender = new GregorianCalendar();
	static Scanner scan = new Scanner(System.in);

	private int year;
	private int month;
	private int day;
	private int yearCalendar = Calendar.YEAR;
        
// Constructors 
    public KorrektPersonNummer() {    }

    public KorrektPersonNummer(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
        
@FXML
    private Label returnMessageToOperator; // varningar och felmedelanden

        // Getters
	public int getYearCalendar() { return yearCalendar; }
        public int getYear() { return year;  }
        public int getMonth(){ return month; }
	public int getDay()  { return day;   }

        // Setters
        public void setYear(int year)   { this.year = year; }
	public void setMonth(int month) { this.month = month; }
        public void setDay(int day)     { this.day = day; }

// här fanns main i en provversion
        
        public static boolean nummerValidering1(String persNr) {

        int numberSize = persNr.length();

        if (numberSize != 12) {
                System.out.println("Personnummer är störe eller mindre än 12 siffror!");
                return false;

        } else if (persNr.matches("\\d+")) {
                return true;
        } else {
                System.out.println("Personnummer får inte innehålla bokstav! ");
                return false;
        }

}

        public static String nummerValidering2(String persNr) {

        int numberSize = persNr.length();

        if (numberSize != 12  ) {
                return ("Personnummer är större eller mindre än 12 siffror!");

        } else if (persNr.matches("\\d+")) {
                return " ";
        } else {
                return ("Personnummer får inte innehålla bokstav! ");
        }

}        

	public static boolean datumValidering1(String persNr) { 

		KorrektPersonNummer kpn = new KorrektPersonNummer();

		kalender.setTime(dagsdato);
		kalender.setLenient(false);

		kpn.setYear(Integer.parseInt(persNr.substring(0, 4)));
		kpn.setMonth(Integer.parseInt(persNr.substring(4, 6)));
		kpn.setDay(Integer.parseInt(persNr.substring(6, 8)));
		

		if (kpn.getYear() > kalender.get(kpn.getYearCalendar())) {

			System.out.println("Personnummer tillhör en ofödd!");
			System.out.println("Födelse år: " + kpn.getYear() + " / nuvarande år: "
					+ kalender.get(kpn.getYearCalendar()));
			//printDatum(kpn.getYear(), kpn.getMonth(), kpn.getDay());
			return false;

		} else {
			if ((kpn.getMonth() <= 12 && kpn.getMonth() > 0) && (kpn.getDay() > 0 && kpn.getDay() <= 31)) {
								
				//printDatum(kpn.getYear(), kpn.getMonth(), kpn.getDay());
				return true;

			} else {
				//printDatum(kpn.getYear(), kpn.getMonth(), kpn.getDay());
				System.out.println("Månad eller dagen stämmer inte! Försök igen..");
				return false;
			}

		}

	}
        	public static String datumValidering2(String persNr) { 

		KorrektPersonNummer kpn = new KorrektPersonNummer();

		kalender.setTime(dagsdato);
		kalender.setLenient(false);

		kpn.setYear(Integer.parseInt(persNr.substring(0, 4)));
		kpn.setMonth(Integer.parseInt(persNr.substring(4, 6)));
		kpn.setDay(Integer.parseInt(persNr.substring(6, 8)));
		

		if (kpn.getYear() > kalender.get(kpn.getYearCalendar())) {

			return("Personnummer tillhör en ofödd (Födelse år: " + kpn.getYear() + " / nuvarande år: "
					+ kalender.get(kpn.getYearCalendar()) + ")");
			//printDatum(kpn.getYear(), kpn.getMonth(), kpn.getDay());
			

		} else {
			if ((kpn.getMonth() <= 12 && kpn.getMonth() > 0) && (kpn.getDay() > 0 && kpn.getDay() <= 31)) {
				return " ";				
				//printDatum(kpn.getYear(), kpn.getMonth(), kpn.getDay());
				

			} else {
				//printDatum(kpn.getYear(), kpn.getMonth(), kpn.getDay());
				return("Månad eller dagen stämmer inte! Försök igen..");
				
			}

		}
                
	}

	public static void printDatum(int year, int month, int day) {

		System.out.println("Year: " + year);
		System.out.println("Month: " + month);
		System.out.println("Day: " + day);
	}

	

	public static boolean checkNumber1(String pn){ 

		int lastNumber = Integer.parseInt(pn.substring(11, 12).toString());

                pn = pn.substring(2, 11).toString();
		int summa = 0;
		int summa2 = 0;
                int[] testa = new int[10];
		

		for (int i = 0; i < 9; i++) {
			int number = Integer.parseInt(pn.substring(i, i +1));
			
			if((i % 2)!= 0){
				summa2 = number;
				
				if(summa2 > 9){
					testa[i]= (1 + (summa2 % 10));
				}else {
					testa[i] = summa2;
				}
				
			}else {
				summa = number *2;
				
				if(summa > 9){
					testa[i] = (1+ (summa %10));
				}else{
					testa[i] = summa;
				}
			}
			
		}		
		
//		for (int j = 0; j < (testa.length) - 1; j++) {
//			System.out.println(testa[j]);
//		}
		
		int total = testa[0] + testa[1] + testa[2] + testa[3] + testa[4] + testa[5] + testa[6] + testa[7] +
					testa[8] + testa[9];
		
		total = total % 10;
		
		if((10 - total) == lastNumber){
			return true;
		}else {
			return false;
		}
	}
	public static String checkNumber2(String pn){ 

		int lastNumber = Integer.parseInt(pn.substring(11, 12).toString());

                pn = pn.substring(2, 11).toString();
		int summa = 0;
		int summa2 = 0;
                int[] testa = new int[10];
		

		for (int i = 0; i < 9; i++) {
			int number = Integer.parseInt(pn.substring(i, i +1));
			
			if((i % 2)!= 0){
				summa2 = number;
				
				if(summa2 > 9){
					testa[i]= (1 + (summa2 % 10));
				}else {
					testa[i] = summa2;
				}
				
			}else {
				summa = number *2;
				
				if(summa > 9){
					testa[i] = (1+ (summa %10));
				}else{
					testa[i] = summa;
				}
			}
			
		}		
		
//		for (int j = 0; j < (testa.length) - 1; j++) {
//			System.out.println(testa[j]);
//		}
		
		int total = testa[0] + testa[1] + testa[2] + testa[3] + testa[4] + testa[5] + testa[6] + testa[7] +
					testa[8] + testa[9];
		
		total = total % 10;
		
		if((10 - total) == lastNumber){
			return("> Personnummer är giltigt");
		}else {
			return("> Personnummer stämmer inte med kontrollsiffran");
		}
	}

	

	

}
