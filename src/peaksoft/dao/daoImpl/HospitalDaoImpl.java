package peaksoft.dao.daoImpl;

import peaksoft.db.Database;
import peaksoft.exceptions.MyException;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.service.HospitalService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalDaoImpl implements HospitalService {
    private Database database;

    public HospitalDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String addHospital(Hospital hospital) {
        database.getHospitals().add(hospital);
        return String.format("Hospital with name:%s successfully saved", hospital.getHospitalName());
    }

    @Override
    public Hospital findHospitalById(Long id) {
        boolean isFalse = true;
        try {
            for (int i = 0; i < database.getHospitals().size(); i++) {
                if (database.getHospitals().get(i).getId().equals(id)) {
                    isFalse = true;
                    return database.getHospitals().get(i);
                } else {
                    isFalse = false;
                }
            }
            if (!isFalse) {
                throw new MyException(String.format("Hospital with id: %s is not found", id));
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Hospital> getAllHospital() {
        return database.getHospitals();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        boolean isFalse = false;
        try {
            for (int i = 0; i < database.getHospitals().size(); i++) {
                if (database.getHospitals().get(i).getId().equals(id)) {
                    isFalse = true;
                    return database.getHospitals().get(i).getPatients();
                } else {
                    isFalse = true;
                }
            }
            if (!isFalse) {
                throw new MyException(String.format("Hospital with id: %s is not found", id));
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteHospitalById(Long id) {
        boolean isFalse = false;
        try {
            for (int i = 0; i < database.getHospitals().size(); i++) {
                if (database.getHospitals().get(i).getId().equals(id)) {
                    database.getHospitals().remove(database.getHospitals().get(i));
                    System.out.printf("Hospital by id:%s successfully deleted %n", id);
                    isFalse = true;
                    break;
                } else {
                    isFalse = true;
                }
            }
            if (!isFalse) {
                throw new MyException(String.format("Hospital with id: %s is not found", id));
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        try {
            Map<String, Hospital> map = new HashMap<>();
            for (Hospital h : database.getHospitals()) {
                if (h.getAddress().equalsIgnoreCase(address)) {
                    map.put(h.getAddress(), h);
                    return map;
                } else {
                    throw new MyException("Hospital with address: " + address + " not found!");
                }
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
