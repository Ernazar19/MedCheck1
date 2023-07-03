package peaksoft.dao.daoImpl;

import peaksoft.db.Database;
import peaksoft.exceptions.MyException;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.service.PatientService;

import java.util.*;

public class PatientDaoImpl implements PatientService {
    private  Database database;

    public PatientDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        boolean isFalse = true;
        try {
            for (int i = 0; i < database.getHospitals().size(); i++) {
                if (database.getHospitals().get(i).getId().equals(id)) {
                    isFalse = true;
                    database.getHospitals().get(i).setPatients(patients);
                    return String.format("Patients have been successfully added to Hospital with id:%s", id);
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
    public Patient getPatientById(Long id) {
        boolean isFalse = true;
        try {
            for (int i = 0; i < database.getHospitals().size(); i++) {
                if (database.getHospitals().get(i).getPatients().get(i).getId().equals(id)) {
                    isFalse = true;
                    return database.getHospitals().get(i).getPatients().get(i);
                } else {
                    isFalse = false;
                }

            }
            if (isFalse) {
                throw new MyException(String.format("Patient with id: %s is not found", id));
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Map<Integer, Patient> getPatientByAge() {
        Map<Integer, Patient> patientMap = new HashMap<>();
        List<Patient> patients = new ArrayList<>();
        for (Hospital h : database.getHospitals()) {
            patients.addAll(h.getPatients());
        }
        for (Patient p : patients) {
            patientMap.put(p.getAge(), p);
        }
        return patientMap;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        List<Patient> patients = new ArrayList<>();
        for (Hospital h : database.getHospitals()) {
            patients.addAll(h.getPatients());
        }
        switch (ascOrDesc) {
            case "asc" -> {
                patients.sort(Comparator.comparing(Patient::getAge));
                return patients;
            }
            case "desc" -> {
                patients.sort(Comparator.comparing(Patient::getAge).reversed());
                return patients;
            }
        }
        return null;
    }
}
