import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;

public class DB_CCMIdToCampId extends DB_Base<CCMIdToCampIdMapping>{
    private static final String FILE_PATH = DatabaseFilePaths.CCM_ID_TO_CAMP_ID;
    private static final DB_CCMIdToCampId instance = new DB_CCMIdToCampId();

    public DB_CCMIdToCampId() {
        super(FILE_PATH);
    }
    
    @Override
    protected CCMIdToCampIdMapping createEntity(Row row) {
        String ccmId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String campId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        return new CCMIdToCampIdMapping(ccmId, campId);
    }   

    @Override
    protected void writeEntityToRow(Row row, CCMIdToCampIdMapping ccmIdToCampIdMapping) {
        row.createCell(0).setCellValue(ccmIdToCampIdMapping.getCcmId());
        row.createCell(1).setCellValue(ccmIdToCampIdMapping.getCampId());
    }


    public static void createMapping(String ccmId, String campId) {
        instance.create(new CCMIdToCampIdMapping(ccmId, campId));
    }

    public static String getCampId(String ccmId) {
        return instance.read(ccmId, 0).getCampId();
    }

    public static ArrayList<String> getCCMIds(String campId) {
        ArrayList<String> ccmIds = new ArrayList<>();
        ArrayList<CCMIdToCampIdMapping> mappings = instance.getAllById(campId, 1);
        for (CCMIdToCampIdMapping mapping : mappings) {
            ccmIds.add(mapping.getCcmId());
        }
        return ccmIds;
    }

    // Additional methods (create/update/delete) can be added as needed

    public static void deleteMapping(String ccmId, String campId) {
       instance.deleteIdMapping(ccmId, campId, 0, 1);
    }

    public static void deleteMappingsByCampId(String campId) {
        instance.deleteMappingsById(campId, 0);
    }


    public static boolean isExists(String ccmId, String campId) {
        return (instance.getMapping(ccmId, campId, 0, 1) != null);
    }
    //  public static void main(String[] args) {
    //     testDB_CCMIdToCampId();
    //  }

    // private static void testDB_CCMIdToCampId() {
    //     // Dummy data
    //     String ccmId1 = "ccm1";
    //     String campId1 = "camp1";
    //     String ccmId2 = "ccm2";
    //     String campId2 = "camp2";

    //     // Test createMapping
    //     DB_CCMIdToCampId.createMapping(ccmId1, campId1);
    //     DB_CCMIdToCampId.createMapping(ccmId2, campId1);  // Creating a second mapping for the same campId
    //     DB_CCMIdToCampId.createMapping(ccmId1, campId2);
    //     // Test getCampIds
    //     ArrayList<String> campIds1 = DB_CCMIdToCampId.getCampIds(ccmId1);
    //     System.out.println("Camp IDs for CCM ID " + ccmId1 + ": ");
    //     for (String campId : campIds1) {
    //         System.out.println(campId);
    //     }

    //     ArrayList<String> campIds2 = DB_CCMIdToCampId.getCampIds(ccmId2);
    //     System.out.println("Camp IDs for CCM ID " + ccmId2 + ": ");
    //     for (String campId : campIds2) {
    //         System.out.println(campId);
    //     }

    //     // Test getCCMIds
    //     ArrayList<String> ccmIds1 = DB_CCMIdToCampId.getCCMIds(campId1);
    //     System.out.println("CCM IDs for Camp ID " + campId1 + ": ");
    //     for (String ccmId : ccmIds1) {
    //         System.out.println(ccmId);
    //     }

    //     // Test createMapping for additional mappings
    //     DB_CCMIdToCampId.createMapping(ccmId1, campId2);
    //     DB_CCMIdToCampId.createMapping(ccmId2, campId2);

    //     ArrayList<String> ccmIds2 = DB_CCMIdToCampId.getCCMIds(campId2);
    //     System.out.println("CCM IDs for Camp ID " + campId2 + ": ");
    //     for (String ccmId : ccmIds2) {
    //         System.out.println(ccmId);
    //     }

    //     // Test isExists
    //     boolean exists1 = DB_CCMIdToCampId.isExists(ccmId1, campId1);
    //     System.out.println("Mapping exists: " + exists1);

    //     boolean exists2 = DB_CCMIdToCampId.isExists(ccmId2, campId1);
    //     System.out.println("Second mapping exists: " + exists2);

    //     // Test deleteMapping
    //     DB_CCMIdToCampId.deleteMapping(ccmId1, campId1);

    //     // Test isExists after deletion
    //     exists1 = DB_CCMIdToCampId.isExists(ccmId1, campId1);
    //     System.out.println("Mapping exists after deletion: " + exists1);
    // }
    public static void main(String[] args) {
        DB_CCMIdToCampId.createMapping("B789012","1b0327bbb247495a9d93274533ff97e2" );
    }

}
