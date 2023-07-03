package peaksoft.dao.daoImpl;
import peaksoft.db.Database;
import peaksoft.exceptions.MyException;
import peaksoft.model.Department;
import peaksoft.service.DepartmentService;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentService {
    private Database database;

    public DepartmentDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        boolean isFalse = true;
        try {
            for (int i = 0; i < database.getHospitals().size(); i++) {
                if (database.getHospitals().get(i).getId().equals(id)) {
                    isFalse = true;
                    return database.getHospitals().get(i).getDepartments();
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
    public Department findDepartmentByName(String name) {
        boolean isTrue = true;
        try {
            for (int i = 0; i < database.getHospitals().size(); i++) {
                if (database.getHospitals().get(i).getDepartments().get(i).getDepartmentName().equalsIgnoreCase(name)) {
                    isTrue = true;
                    return database.getHospitals().get(i).getDepartments().get(i);
                } else {
                    isTrue = false;
                }
            }
            if (!isTrue) {
                throw new MyException(String.format("Department with name: %s is not found",name));
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
