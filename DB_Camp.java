import java.util.ArrayList;
import java.time.LocalDate;


import org.apache.poi.ss.usermodel.*;

public class DB_Camp extends DB_Base<Camp> {
    private static final String FILE_PATH = DatabaseFilePaths.CAMP;
    private static final DB_Camp instance = new DB_Camp();

    public DB_Camp() {
        super(FILE_PATH);
    }

    @Override
    protected Camp createEntity(Row row) {
        String campId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String name = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        boolean isVisible = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
        LocalDate startDate = LocalDate.parse(row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
        LocalDate endDate = LocalDate.parse(row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
        LocalDate regClosingDate = LocalDate.parse(row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
        String location = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        int totalSlots = (int) row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
        int campCommitteeSlots = (int) row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
        String description = row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String facultyId = row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String staffId = row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        boolean isOpenToAll = row.getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
        return new Camp(campId, name, isVisible, startDate, endDate, regClosingDate, location, totalSlots, campCommitteeSlots, description, facultyId, staffId, isOpenToAll);
    }

    @Override
    protected void writeEntityToRow(Row row, Camp camp) {
    row.createCell(0).setCellValue(camp.getId());
    row.createCell(1).setCellValue(camp.getName());
    row.createCell(2).setCellValue(camp.getIsVisible());
    row.createCell(3).setCellValue(camp.getStartDate().toString());
    row.createCell(4).setCellValue(camp.getEndDate().toString());
    row.createCell(5).setCellValue(camp.getRegClosingDate().toString());
    row.createCell(6).setCellValue(camp.getLocation());
    row.createCell(7).setCellValue(camp.getTotalSlots());
    row.createCell(8).setCellValue(camp.getCampCommitteeSlots());
    row.createCell(9).setCellValue(camp.getDescription());
    row.createCell(10).setCellValue(camp.getFacultyId());
    row.createCell(11).setCellValue(camp.getStaffId());
    row.createCell(12).setCellValue(camp.isOpenToAll());

    }

    public static void createCamp(Camp camp) {
        instance.create(camp);
    }

    public static Camp readCamp(String campId) {
        return instance.read(campId, 0);
    }

    public static ArrayList<Camp> getCampsAvailableForStudent(String facultyId) {
        ArrayList<Camp> allCamps = instance.getAll();
        ArrayList<Camp> availableCamps = new ArrayList<>();

        for (Camp camp : allCamps) {
            if (camp.isOpenToAll() || facultyId.equals(camp.getFacultyId())) {
                availableCamps.add(camp);
            }
        }

        return availableCamps;
    }

    public static void updateCamp(Camp camp) {
       instance.update(camp.getId(), 0, camp);
    }

    public static void deleteCamp(String campId) {
        instance.delete(campId, 0);
    }


    public static ArrayList<Camp> getAllCamps() {
        return instance.getAll();
    }

    public static ArrayList<Camp> getCampsByStaffId(String staffId) {
        return instance.getAllById(staffId, 11);
    }
public static void main(String[] args) {
    // DB_Camp.deleteCamp("1b0327bbb247495a9d93274533ff97e2");
    // for(Camp camp : DB_Camp.getAllCamps()) {
    //     System.out.println(camp.getId());
    // }
    // Camp c1 = new Camp("1", "qwerty", "true", "2022-03-01", "2022-04-01", "2022-04-01", "bishan", 20, 3, "Night time", "facultyid", "staffid", false);
    // Camp c2 = new Camp("2", "qwerty", true, "2022-03-01", "2022-04-01", "2022-04-01", "bishan", 20, 3, "Night time", "facultyid", "staffid", false);
    Camp camp1 = new Camp("abcde", "First camp", true, LocalDate.now(), LocalDate.parse("2023-01-02"), LocalDate.parse("2023-11-29"), 
    "Nanyang", 50, 10, "Halloween Camp", "98d0e59407f946b7aed49150ceba8627", 
    "1", true);
    // DB_Camp.deleteCamp("abcde");
    DB_Camp.createCamp(camp1);
    
    // Camp camp2 = new Camp("abcgr", "Secondcamp", true, LocalDate.now(), LocalDate.parse("2023-01-02"), LocalDate.parse("2023-11-29"), 
    // "Nanyang", 50, 10, "Halloween Camp", "98d0e59407f946b7aed49150ceba8627", 
    // "1", true);
    // Camp camp3 = new Camp("abmye", "First camp", true, LocalDate.now(), LocalDate.parse("2023-01-02"), LocalDate.parse("2023-11-29"), 
    // "Nanyang", 50, 10, "Halloween Camp", "98d0e59407f946b7aed49150ceba8627", 
    // "1", true);
}

}

