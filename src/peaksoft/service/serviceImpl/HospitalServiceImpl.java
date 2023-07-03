package peaksoft.service.serviceImpl;

import peaksoft.dao.daoImpl.HospitalDaoImpl;
import peaksoft.db.Database;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.service.HospitalService;

import java.util.List;
import java.util.Map;

public class HospitalServiceImpl implements HospitalService {
    private  Database database;
    HospitalDaoImpl dao = new HospitalDaoImpl(database);

    public HospitalServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public String addHospital(Hospital hospital) {
        return dao.addHospital(hospital);
    }
    @Override
    public Hospital findHospitalById(Long id) {
        return dao.findHospitalById(id);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return dao.getAllHospital();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return dao.getAllPatientFromHospital(id);
    }

    @Override
    public String deleteHospitalById(Long id) {
        return dao.deleteHospitalById(id);
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        return dao.getAllHospitalByAddress(address);
    }
}