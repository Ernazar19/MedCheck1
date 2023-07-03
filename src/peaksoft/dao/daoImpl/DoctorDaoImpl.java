package peaksoft.dao.daoImpl;
import peaksoft.db.Database;
import peaksoft.exceptions.MyException;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.service.DoctorService;

import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorService {
    private Database database;

    public DoctorDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        boolean isTrue = true;
        try {
            for (int i = 0; i <database.getHospitals().size(); i++) {
                if (database.getHospitals().get(i).getDoctors().get(i).getId().equals(id)){
                    isTrue = true;
                    return database.getHospitals().get(i).getDoctors().get(i);
                }else {
                    isTrue = false;
                }
            }if (!isTrue){
                throw new MyException(String.format("Doctor with id:%s is not found",id));
            }
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        List<Doctor>doctors = new ArrayList<>();
        for (Hospital h:database.getHospitals()) {
            for (Doctor d:h.getDoctors()) {
                for (Long l:doctorsId) {
                    if (d.getId().equals(l)){
                        doctors.add(d);
                    }
                }
            }
        }
        for (Hospital h:database.getHospitals()) {
            for (Department d:h.getDepartments()) {
                if (d.getId().equals(departmentId)){
                    d.getDoctors().addAll(doctors);
                }
            }
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        boolean isTrue = true;
        try {
            for (int i = 0; i <database.getHospitals().size(); i++) {
                if (database.getHospitals().get(i).getDoctors().get(i).getId().equals(id)){
                    isTrue = true;
                    return database.getHospitals().get(i).getDoctors();
                }else {
                    isTrue = false;
                }
            }if (!isTrue){
                throw new MyException(String.format("Hospital with id:%s is not found",id));
            }
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        boolean isTrue = true;
        try {
            for (int i = 0; i <database.getHospitals().size(); i++) {
                if (database.getHospitals().get(i).getDoctors().get(i).getId().equals(id)){
                    isTrue = true;
                    return database.getHospitals().get(i).getDepartments().get(i).getDoctors();
                }else {
                    isTrue = false;
                }
            }if (!isTrue){
                throw new MyException(String.format("Department with id:%s is not found",id));
            }
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
