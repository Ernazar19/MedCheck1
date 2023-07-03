package peaksoft.service.serviceImpl;

import peaksoft.dao.daoImpl.PatientDaoImpl;
import peaksoft.db.Database;
import peaksoft.exceptions.MyException;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.service.GenericService;
import peaksoft.service.PatientService;

import java.util.List;
import java.util.Map;

public class PatientServiceImpl implements GenericService<Patient>,PatientService {
    private Database database;
    PatientDaoImpl patientDao = new PatientDaoImpl(database);

    public PatientServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        boolean isFalse = true;
        try {
            for (Hospital d : database.getHospitals()) {
                if (d.getId().equals(hospitalId)) {
                    isFalse = true;
                    d.getPatients().add(patient);
                    return String.format("Patient with name: %s is successfully saved Hospital by id: %s ", patient.getFirstName(), hospitalId);
                } else {
                    isFalse = false;
                }
            }
            if (!isFalse) {
                throw new MyException(String.format("Hospital with id:%s is not found", hospitalId));
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public void removeById(Long id) {
        boolean isFalse = true;
        try {
            for (int i = 0; i < database.getHospitals().size(); i++) {
                if (database.getHospitals().get(i).getPatients().get(i).getId().equals(id)) {
                    isFalse = true;
                    database.getHospitals().get(i).getPatients().remove(database.getHospitals().get(i).getPatients().get(i));
                    System.out.printf("Patient with id: %s successfully deleted",id);
                    break;
                }else{
                    isFalse = false;
                }
            }if (isFalse){
                throw new MyException(String.format("Patient with id:%s is not found",id));
            }

        }catch (MyException e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public String updateById(Long id, Patient patient) {


        boolean isFalse = true;
        try {
            for (int i = 0; i < database.getHospitals().size(); i++) {
                if (database.getHospitals().get(i).getPatients().get(i).getId().equals(id)) {
                    isFalse = true;
                    database.getHospitals().get(i).getPatients().get(i).setFirstName(patient.getFirstName());
                    database.getHospitals().get(i).getPatients().get(i).setLastName(patient.getLastName());
                    database.getHospitals().get(i).getPatients().get(i).setAge(patient.getAge());
                    database.getHospitals().get(i).getPatients().get(i).setGender(patient.getGender());

                    return String.format("Patient with id: %s is successfully updated!", id);
                } else {
                    isFalse = false;
                }
            }
            if (!isFalse) {
                throw new MyException(String.format("Patient with id: %s is not found", id));

            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        return patientDao.addPatientsToHospital(id,patients);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientDao.getPatientById(id);
    }

    @Override
    public Map<Integer, Patient> getPatientByAge() {
        return patientDao.getPatientByAge();
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        return patientDao.sortPatientsByAge(ascOrDesc);
    }
}
