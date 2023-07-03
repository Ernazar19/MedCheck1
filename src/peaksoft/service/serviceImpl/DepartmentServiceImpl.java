package peaksoft.service.serviceImpl;

import peaksoft.dao.daoImpl.DepartmentDaoImpl;
import peaksoft.db.Database;
import peaksoft.exceptions.MyException;
import peaksoft.model.Department;
import peaksoft.model.Hospital;
import peaksoft.service.DepartmentService;
import peaksoft.service.GenericService;

import java.util.List;

public class DepartmentServiceImpl implements GenericService<Department>, DepartmentService {

    private Database database;
    DepartmentDaoImpl dao = new DepartmentDaoImpl(database);

    public DepartmentServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        return dao.getAllDepartmentByHospital(id);
    }

    @Override
    public Department findDepartmentByName(String name) {
        return dao.findDepartmentByName(name);
    }

    @Override
    public String add(Long hospitalId, Department department) {
        boolean isFalse = true;
        try {
            for (Hospital d : database.getHospitals()) {
                if (d.getId().equals(hospitalId)) {
                    isFalse = true;
                    d.getDepartments().add(department);
                    return String.format("Department with name: %s is successfully saved Hospital by id: %s ", department.getDepartmentName(), hospitalId);
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
                if (database.getHospitals().get(i).getDepartments().get(i).getId().equals(id)) {
                    isFalse = true;
                    database.getHospitals().get(i).getDepartments().remove(database.getHospitals().get(i).getDepartments().get(i));
                    System.out.printf("Department with id: %s successfully deleted", id);
                    break;
                } else {
                    isFalse = false;
                }
            }
            if (isFalse) {
                throw new MyException(String.format("Department with id:%s is not found", id));
            }

        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Department department) {
        boolean isFalse = true;
        try {
            for (int i = 0; i < database.getHospitals().size(); i++) {
                if (database.getHospitals().get(i).getDepartments().get(i).getId().equals(id)) {
                    isFalse = true;
                    database.getHospitals().get(i).getDepartments().get(i).setDepartmentName(department.getDepartmentName());
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
