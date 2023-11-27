import java.util.ArrayList;

public class Attendee extends Student implements AttendeeEnquiryInterface, AttendeeCampInterface{
    // need add in attribute for the enquiryIdToEnquiyObjMap and campIdToCampObjMap

    public Attendee(String id, String password, String name, String email, boolean isCampCommittee, String facultyId){
        super(id, password, name, email, isCampCommittee, facultyId);
    }

    public Attendee(String id, String password, String name, String email, boolean isCampCommittee, boolean isNewLogin ,String facultyId){
        super(id, password, name, email, isCampCommittee, isNewLogin ,facultyId);
    }

    public Attendee(Student student) {
        super(student.getId(), student.getPassword(), student.getName(), student.getEmail(), student.getIsCampCommittee(), student.isNewLogin(), student.getFacultyId());
    }

        /**
     * Submit an enquiry.
     *
     * @param  e  the enquiry to submit
     */
    public void submitEnquiry(Enquiry e){
        DB_AttendeeIdToEnquiryId.createMapping(getId(), e.getId());
        DB_Enquiry.createEnquiry(e);
    }

        /**
     * Retrieves all the enquiries associated with this object.
     *
     * @return  an ArrayList of Enquiry objects representing all the enquiries
     */
    public ArrayList<Enquiry> viewAllEnquires()
    {
        ArrayList<String> enquiryIds = DB_AttendeeIdToEnquiryId.getEnquiryIds(this.getId());
        ArrayList<Enquiry> enquiries = new ArrayList<>();
        for(String eId: enquiryIds)
        {
            enquiries.add(DB_Enquiry.readEnquiry(eId));
        }
        return enquiries;
    }
    /**
     * Retrieves an Enquiry object by its ID from the database.
     *
     * @param  id  the ID of the Enquiry
     * @return     the Enquiry object with the specified ID
     */
    public Enquiry viewEnquirybyId(String id){
        return DB_Enquiry.readEnquiry(id);
    }
    /**
     * Edits an enquiry by updating it in the database.
     *
     * @param  newE  the new enquiry to be updated
     */
    public void editEnquiry(Enquiry newE){
        DB_Enquiry.updateEnquiry(newE);
    }
    /**
     * Deletes an enquiry.
     *
     * @param  e  the enquiry to be deleted
     * @return    void
     */
    public void deleteEnquiry(Enquiry e)
    {
        DB_AttendeeIdToEnquiryId.deleteMapping(this.getId(), e.getId());
        DB_Enquiry.deleteEnquiry(e.getId());
    }
        /**
     * A function to withdraw an attendee from a camp.
     *
     * @param  campId  the ID of the camp from which the attendee will be withdrawn
     * @return         none
     */
    public void withdrawFromCampAsAttendee(String campId)
    {
        DB_AttendeeIdToCampId.updateWithdrawn(this.getId(), campId);
        Camp updatedCamp = DB_Camp.readCamp(campId);
        updatedCamp.setTotalSlots(updatedCamp.getTotalSlots() - 1); 
        DB_Camp.updateCamp(updatedCamp);
    }

    public ArrayList<Camp> viewRegisteredCampsAsAttendee()
    {
        ArrayList<String> campIds = DB_AttendeeIdToCampId.getCampIds(this.getId());
        ArrayList<Camp> camps = new ArrayList<>();
        for(String cId: campIds)
        {
            camps.add(DB_Camp.readCamp(cId));
        }

        return camps;
    }

}