/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unal.etl.sakila;

import co.edu.unal.etl.sakila.jpa.pojo.BiCity;
import co.edu.unal.etl.sakila.jpa.pojo.BiDate;
import co.edu.unal.etl.sakila.jpa.pojo.BiFilm;
import co.edu.unal.etl.sakila.jpa.pojo.BiLanguage;
import co.edu.unal.etl.sakila.jpa.pojo.BiRental;
import co.edu.unal.etl.sakila.jpa.pojo.BiStore;
import co.edu.unal.etl.sakila.jpa.pojo.City;
import co.edu.unal.etl.sakila.jpa.pojo.Film;
import co.edu.unal.etl.sakila.jpa.pojo.Language;
import co.edu.unal.etl.sakila.jpa.pojo.Rental;
import co.edu.unal.etl.sakila.jpa.pojo.Store;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author edwar
 */
public class Etl {

    //Migrate rental of day
    public void etlRentalOfDay() {
        HashMap<String, BiDate> mapBiDate = mapBiDate();
        HashMap<Integer, Integer> mapBiDateCount = new HashMap<>();

        System.out.println("\n\nRun ETL Rental BY DAY ....\n\n");

        List<Rental> lstR = DbManager.getInstance().listFromHql("SELECT r FROM Rental r");
        for (Rental rental : lstR) {
            //Create BiDate if not exist
            BiDate date = createIfNoExistBiDate(rental.getRentalDate(), mapBiDate);

            //Count Rental
            Integer n = mapBiDateCount.get(date.getDateId());
            if (n == null) {
                n = 0;
            } else {
                n++;
            }
            mapBiDateCount.put(date.getDateId(), n);

        }
        //Create  or update BiRental
        Iterator<Integer> it = mapBiDateCount.keySet().iterator();
        while (it.hasNext()) {
            Integer dateId = it.next();
            Integer count = mapBiDateCount.get(dateId);
            BiRental biRental = new BiRental();
            biRental.setQuantity(count);
            biRental.setDateId(new BiDate(dateId));
            createOrUpdateBiRental(biRental);
        }
    }

    public void etlRentalOfDayByStore() {
        System.out.println("\n\nRun ETL Rental BY STORE....\n\n");

        // Create BiStore
        List<Store> lstS = DbManager.getInstance().listFromHql("SELECT s FROM Store s");
        for (Store store : lstS) {
            BiStore biStore = DbManager.getInstance().findById(new Integer(store.getStoreId().toString()), BiStore.class);
            if (biStore == null) {
                biStore = new BiStore(new Integer(store.getStoreId()));
                biStore.setName(store.getAddressId().getAddress());
                DbManager.getInstance().save(biStore);
            }
        }

        // Create  or update BiRental
        String sql = "SELECT count(r.rental_id),i.store_id "
                + " FROM rental r, inventory i "
                + " WHERE r.inventory_id=i.inventory_id "
                + " GROUP BY i.store_id";
        List<Object[]> lst = DbManager.getInstance().listFromSql(sql);
        for (Object[] o : lst) {
            Integer store_id = new Integer(o[1].toString());
            Integer count = new Integer(o[0].toString());

            BiRental biRental = new BiRental();
            biRental.setQuantity(count);
            biRental.setStoreId(new BiStore(store_id));
            createOrUpdateBiRental(biRental);
        }
    }

    public void etlRentalOfDayByFilm() {
        System.out.println("\n\nRun ETL Rental BY FILM....\n\n");

        // Create BiFilm
        List<Film> lstF = DbManager.getInstance().listFromHql("SELECT f FROM Film f");
        for (Film film : lstF) {
            BiFilm biFilm = DbManager.getInstance().findById(new Integer(film.getFilmId().toString()), BiFilm.class);
            if (biFilm == null) {
                biFilm = new BiFilm(new Integer(film.getFilmId()));
                biFilm.setName(film.getTitle());
                DbManager.getInstance().save(biFilm);
            }
        }

        // Create  or update BiRental
        String sql = "SELECT count(r.rental_id),i.film_id "
                + " FROM rental r, inventory i "
                + " WHERE r.inventory_id=i.inventory_id "
                + " GROUP BY i.film_id";
        List<Object[]> lst = DbManager.getInstance().listFromSql(sql);
        for (Object[] o : lst) {
            Integer film_id = new Integer(o[1].toString());
            Integer count = new Integer(o[0].toString());

            BiRental biRental = new BiRental();
            biRental.setQuantity(count);
            biRental.setFilmId(new BiFilm(film_id));
            createOrUpdateBiRental(biRental);
        }
    }

    public void etlRentalOfDayByLanguage() {
        System.out.println("\n\nRun ETL Rental BY LANGUAGE....\n\n");

        // Create BiLanguage
        List<Language> lstL = DbManager.getInstance().listFromHql("SELECT l FROM Language l");
        for (Language language : lstL) {
            BiLanguage biLanguage = DbManager.getInstance().findById(new Integer(language.getLanguageId().toString()), BiLanguage.class);
            if (biLanguage == null) {
                biLanguage = new BiLanguage(new Integer(language.getLanguageId().toString()));
                biLanguage.setName(language.getName());
                DbManager.getInstance().save(biLanguage);
            }
        }

        // Create  or update BiRental
        String sql = "SELECT count(r.rental_id),f.language_id "
                + " FROM rental r, inventory i, film f "
                + " WHERE r.inventory_id=i.inventory_id "
                + "   AND f.film_id=i.film_id"
                + " GROUP BY f.language_id ";
        List<Object[]> lst = DbManager.getInstance().listFromSql(sql);
        for (Object[] o : lst) {
            Integer language_id = new Integer(o[1].toString());
            Integer count = new Integer(o[0].toString());

            BiRental biRental = new BiRental();
            biRental.setQuantity(count);
            biRental.setLanguageId(new BiLanguage(language_id));
            createOrUpdateBiRental(biRental);
        }
    }

    public void etlRentalOfDayByCity() {
        System.out.println("\n\nRun ETL Rental BY CITY....\n\n");

        // Create BiLanguage
        List<City> lstC = DbManager.getInstance().listFromHql("SELECT c FROM City c");
        for (City city : lstC) {
            BiCity biCity = DbManager.getInstance().findById(new Integer(city.getCityId().toString()), BiCity.class);
            if (biCity == null) {
                biCity = new BiCity(new Integer(city.getCityId().toString()));
                biCity.setName(city.getCity());
                DbManager.getInstance().save(biCity);
            }
        }

        // Create  or update BiRental
        String sql = "SELECT count(r.rental_id),a.city_id "
                + " FROM rental r, inventory i, store s,address a"
                + " WHERE r.inventory_id=i.inventory_id "
                + "   AND i.store_id=s.store_id"
                + "   AND a.address_id=s.address_id"
                + " GROUP BY a.city_id ";
        List<Object[]> lst = DbManager.getInstance().listFromSql(sql);
        for (Object[] o : lst) {
            Integer city_id = new Integer(o[1].toString());
            Integer count = new Integer(o[0].toString());

            BiRental biRental = new BiRental();
            biRental.setQuantity(count);
            biRental.setCityId(new BiCity(city_id));
            createOrUpdateBiRental(biRental);
        }
    }

    /**
     *
     * @return MAP<key yaear-month-day, Val: BiDate>
     */
    private HashMap<String, BiDate> mapBiDate() {
        HashMap<String, BiDate> map = new HashMap<>();
        List<BiDate> lstD = DbManager.getInstance().listFromHql("SELECT d FROM BiDate d");
        for (BiDate biDate : lstD) {
            map.put(keyDate(biDate), biDate);
        }
        return map;
    }

    private String keyDate(BiDate biDate) {
        return biDate.getYear() + "-" + biDate.getMonth() + "-" + biDate.getDay();
    }

    private String keyDate(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        return year + "-" + month + "-" + day;
    }

    private BiDate createBiDate(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        BiDate bid = new BiDate();
        bid.setDay(day);
        bid.setMonth(month);
        bid.setYear(year);

        DbManager.getInstance().save(bid);

        return bid;
    }

    private BiRental findBiRental(BiRental biRental) {
        String storeId = " null ";
        if (biRental.getStoreId() != null) {
            storeId = biRental.getStoreId().getStoreId().toString();
        }
        String cityId = " null ";
        if (biRental.getCityId() != null) {
            cityId = biRental.getCityId().getCityId().toString();
        }
        String filmId = " null ";
        if (biRental.getFilmId() != null) {
            filmId = biRental.getFilmId().getFilmId().toString();
        }
        String languageId = " null ";
        if (biRental.getLanguageId() != null) {
            languageId = biRental.getLanguageId().getLanguageId().toString();
        }
        String dateId = " null ";
        if (biRental.getDateId() != null) {
            dateId = biRental.getDateId().getDateId().toString();
        }

        String hql = "SELECT r "
                + " FROM BiRental r "
                + " WHERE r.storeId.storeId =  " + storeId
                + "   AND r.cityId.cityId =   " + cityId
                + "   AND r.filmId.filmId =   " + filmId
                + "   AND r.languageId.languageId = " + languageId
                + "   AND r.dateId.dateId=" + dateId;
        List<BiRental> lst = DbManager.getInstance().listFromHql(hql);
        if (lst != null && !lst.isEmpty()) {
            return lst.get(0);
        } else {
            return null;
        }
    }

    private void createOrUpdateBiRental(BiRental biRentalNew) {
        BiRental biRentalOld = findBiRental(biRentalNew);
        if (biRentalOld == null) {
            DbManager.getInstance().save(biRentalNew);
        } else {
            DbManager.getInstance().update("UPDATE bi_rental SET quantity=" + biRentalNew.getQuantity() + " WHERE id=" + biRentalOld.getId());
        }
    }

    private BiDate createIfNoExistBiDate(Date d, HashMap<String, BiDate> mapBiDate) {
        BiDate date = mapBiDate.get(keyDate(d));
        if (date == null) {
            date = createBiDate(d);
            mapBiDate.put(keyDate(date), date);
        }
        return date;
    }
}
