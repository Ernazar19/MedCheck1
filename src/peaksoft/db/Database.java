package peaksoft.db;

import peaksoft.model.Hospital;


import java.util.ArrayList;
import java.util.List;

public class Database {
    private  List<Hospital> hospitals= new ArrayList<>();

    public Database(List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    public List<Hospital> getHospitals() {
        return hospitals;
    }
    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }
}
