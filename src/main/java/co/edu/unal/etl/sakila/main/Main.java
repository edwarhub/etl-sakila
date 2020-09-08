/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unal.etl.sakila.main;

import co.edu.unal.etl.sakila.Etl;

/**
 *
 * @author edwar
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("\n\n------------------------------------------");
        System.out.println("Universidad Nacional de Colombia");
        System.out.println("By. Edwar Rojas edrojasb@unal.edu.co");
        System.out.println("ETL - SAKILA");
        System.out.println("------------------------------------------\n");

        Etl etl = new Etl();

        etl.etlRentalOfDay();
        etl.etlRentalOfDayByStore();
        etl.etlRentalOfDayByFilm();
        etl.etlRentalOfDayByLanguage();
        etl.etlRentalOfDayByCity();

        System.out.println("End ETL");
        
    }

}
