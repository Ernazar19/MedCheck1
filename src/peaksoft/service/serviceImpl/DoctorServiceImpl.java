package peaksoft.service.serviceImpl;

import peaksoft.dao.daoImpl.DoctorDaoImpl;
import peaksoft.db.Database;
import peaksoft.exceptions.MyException;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.service.DoctorService;
import peaksoft.service.GenericService;

import java.util.List;

public class DoctorServiceImpl implements GenericService<Doctor>,DoctorService {
    private Database database ;
    DoctorDaoImpl dao = new DoctorDaoImpl(database);

    public DoctorServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        return dao.findDoctorById(id);
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        return dao.assignDoctorToDepartment(departmentId,doctorsId);
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return dao.getAllDoctorsByHospitalId(id);
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        return dao.getAllDoctorsByDepartmentId(id);
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        boolean isFalse = true;
        try {
            for (Hospital d : database.getHospitals()) {
                if (d.getId().equals(hospitalId)) {
                    isFalse = true;
                    d.getDoctors().add(doctor);
                    return String.format("Doctor with name: %s is successfully saved Hospital by id: %s ", doctor.getFirstName(), hospitalId);
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
                if (database.getHospitals().get(i).getDoctors().get(i).getId().equals(id)) {
                    isFalse = true;
                    database.getHospitals().get(i).getDoctors().remove(database.getHospitals().get(i).getDoctors().get(i));
                    System.out.printf("Doctor with id: %s successfully deleted",id);
                    break;
                }else{
                    isFalse = false;
                }
            }if (isFalse){
                throw new MyException(String.format("Doctor with id:%s is not found",id));
            }

        }catch (MyException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        boolean isFalse = true;
        try {
            for (int i = 0; i <database.getHospitals().get(i).getDoctors().size(); i++) {
                if (database.getHospitals().get(i).getDoctors().get(i).getId().equals(id)) {
                    isFalse = true;
                    database.getHospitals().get(i).getDoctors().get(i).setFirstName(doctor.getFirstName());
                    database.getHospitals().get(i).getDoctors().get(i).setLastName(doctor.getLastName());
                    database.getHospitals().get(i).getDoctors().get(i).setGender(doctor.getGender());
                    database.getHospitals().get(i).getDoctors().get(i).setExperienceYear(doctor.getExperienceYear());
                    return String.format("Department with id: %s is successfully updated!", id);
                } else {
                    isFalse = false;
                }
            }
            if (!isFalse) {
                throw new MyException(String.format("Department with id: %s is not found", id));

            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}