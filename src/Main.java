
import peaksoft.db.Database;
import peaksoft.enums.Gender;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.service.serviceImpl.DepartmentServiceImpl;
import peaksoft.service.serviceImpl.DoctorServiceImpl;
import peaksoft.service.serviceImpl.HospitalServiceImpl;
import peaksoft.service.serviceImpl.PatientServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Doctor doctor1 = new Doctor(5L, "Ulan", "Kubanychbekov", Gender.MALE, 3);
        Doctor doctor2 = new Doctor(6L, "Aisezim", "Baryktabasova", Gender.FEMALE, 1);
        Doctor doctor3 = new Doctor(7L, "Altynai", "Taalaibekowa", Gender.FEMALE, 6);
        Doctor doctor4 = new Doctor(8L, "Shabdan", "Asanbekov", Gender.MALE, 4);
        List<Doctor> doctors = new ArrayList<>(List.of(doctor1,doctor2,doctor3,doctor4));
        Department department1 = new Department(9L, "Surgical", doctors);
        Department department2 = new Department(10L, "Traumatology", doctors);
        Department department3 = new Department(11L, "Orthopedics", doctors);
        Department department4 = new Department(12L, "Lore", doctors);
        List<Department> departments = new ArrayList<>(List.of(department1,department2,department3,department4));
        Patient patient1 = new Patient(1L, "Munara", "Mamadaalivea", 20, Gender.FEMALE);
        Patient patient2 = new Patient(2L, "Ernazar", "Tilek uulu", 20, Gender.MALE);
        Patient patient3 = new Patient(3L, "Adilet", "Islambek uulu", 17, Gender.FEMALE);
        Patient patient4 = new Patient(4L, "Adinay", "Asanalieva", 16, Gender.FEMALE);
        List<Patient> patients = new ArrayList<>(List.of(patient1,patient2,patient3,patient4));
        Hospital hospital1 = new Hospital(13L, "Respublicanskiy", "Togolok-Moldo-124", departments, doctors, patients);
        Hospital hospital2 = new Hospital(14L, "4-Gor-bolnica", "krivonosova-206", departments, doctors, patients);
        List<Hospital> hospitals = new ArrayList<>(List.of(hospital1, hospital2));
        Database database = new Database(hospitals);
        HospitalServiceImpl hospitalService = new HospitalServiceImpl(database);
        DoctorServiceImpl doctorService = new DoctorServiceImpl(database);
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(database);
        PatientServiceImpl patientService = new PatientServiceImpl(database);
        while (true) {
            System.out.println("""
                    |------------------------------------------|
                    |<~~~~~~~~~~~~HOSPITAL METHODS~~~~~~~~~~~~>|
                    |1-> add Hospital:                         |
                    |2-> find Hospital by Id:                  |
                    |3-> get all Hospital:                     |
                    |4-> get all Patient from Hospital:        |
                    |5-> delete Hospital by Id:                |
                    |6-> get all Hospital by Address:          |
                    |__________________________________________|
                    |<~~~~~~~~~~~DEPARTMENT METHODS~~~~~~~~~~~>|
                    |7-> add Department To Hospital:           |
                    |8-> get all Department by Hospital        |
                    |9-> find Department by Name:              |
                    |10-> delete Department by Id:             |
                    |11-> Update Department by Id:             |
                    |__________________________________________|
                    |<~~~~~~~~~~~~~DOCTOR METHODS~~~~~~~~~~~~~>|
                    |12-> Add Doctor to Hospital:              |
                    |13-> find Doctor by Id:                   |
                    |14-> Update Doctor By Id:                 |
                    |15-> delete Doctor by Id:                 |
                    |16-> Assign Doctor to Department:         |
                    |17-> get all Doctors by Hospital Id:      |
                    |18-> get all Doctors by Department by Id: |
                    |__________________________________________|
                    |<~~~~~~~~~~~~~PATIENT METHOD~~~~~~~~~~~~~>|
                    |19-> add Patient to Hospital:             |
                    |20-> add Patients to Hospital:            |
                    |21-> Update Patient by Id:                |
                    |22-> Remove Patient by Id:                |
                    |23-> Get Patient by Id:                   |
                    |24-> get Patient by Age:                  |
                    |25-> sort Patient by Age:                 |               
                    |__________________________________________|
                    """);
            System.out.print("Choose command: ");
            int num = new Scanner(System.in).nextInt();
            switch (num) {
                case 1 -> {
                    for (Hospital hosp : hospitals) {
                        System.out.println(hospitalService.addHospital(hosp));
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                }
                case 2 -> System.out.println(hospitalService.findHospitalById(13L));
                case 3 -> System.out.println(hospitalService.getAllHospital());
                case 4 -> System.out.println(hospitalService.getAllPatientFromHospital(14L));
                case 5 -> System.out.println(hospitalService.deleteHospitalById(13L));
                case 6 -> System.out.println(hospitalService.getAllHospitalByAddress("krivonosova-206"));
                case 7 -> System.out.println(departmentService.add(13L, department1));
                case 8 -> System.out.println(departmentService.getAllDepartmentByHospital(14L));
                case 9 -> System.out.println(departmentService.findDepartmentByName("Lore"));
                case 10 -> departmentService.removeById(5L);
                case 11 -> System.out.println(departmentService.updateById(10L, department1));
                case 12 -> System.out.println(doctorService.add(6L, doctor1));
                case 13 -> System.out.println(doctorService.findDoctorById(7L));
                case 14 -> System.out.println(doctorService.updateById(2L, doctor3));
                case 15 -> doctorService.removeById(7L);
                case 16 ->
                        System.out.println(doctorService.assignDoctorToDepartment(1L, new ArrayList<>(List.of(5L, 6L))));
                case 17 -> System.out.println(doctorService.getAllDoctorsByHospitalId(14L));
                case 18 -> System.out.println(doctorService.getAllDoctorsByDepartmentId(11L));
                case 19 -> System.out.println(patientService.add(7L, patient1));
                case 20 -> System.out.println(patientService.addPatientsToHospital(9L, patients));
                case 21 -> System.out.println(patientService.updateById(3L, patient3));
                case 22 -> patientService.removeById(1L);
                case 23 -> System.out.println(patientService.getPatientById(1L));
                case 24 -> System.out.println(patientService.getPatientByAge());
                case 25 -> System.out.println(patientService.sortPatientsByAge("asc"));
            }
        }
    }
}



