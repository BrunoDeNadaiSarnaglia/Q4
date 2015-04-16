/**
 * Created by Bruno on 4/15/2015.
 */
public class Sample {
    private String workclass;
    private String educatino;
    private String maritalStatus;
    private String occupation;
    private String relationship;
    private String race;
    private String sex;
    private String nativeCountry;
    private String classLabel;

    public Sample(String workclass, String educatino, String maritalStatus, String occupation, String relationship, String race, String sex, String nativeCountry, String classLabel) {
        this.workclass = workclass;
        this.educatino = educatino;
        this.maritalStatus = maritalStatus;
        this.occupation = occupation;
        this.relationship = relationship;
        this.race = race;
        this.sex = sex;
        this.nativeCountry = nativeCountry;
        this.classLabel = classLabel;
    }

    public String getWorkclass() {
        return workclass;
    }

    public String getEducatino() {
        return educatino;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getRelationship() {
        return relationship;
    }

    public String getRace() {
        return race;
    }

    public String getSex() {
        return sex;
    }

    public String getNativeCountry() {
        return nativeCountry;
    }

    public void setWorkclass(String workclass) {
        this.workclass = workclass;
    }

    public void setEducatino(String educatino) {
        this.educatino = educatino;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setNativeCountry(String nativeCountry) {
        this.nativeCountry = nativeCountry;
    }

    public void setClassLabel(String classLabel) {
        this.classLabel = classLabel;
    }

    public String getClassLabel() {
        return classLabel;
    }

    @Override
    public String toString() {
        return "Sample{" +
                "workclass='" + workclass + '\'' +
                ", educatino='" + educatino + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", occupation='" + occupation + '\'' +
                ", relationship='" + relationship + '\'' +
                ", race='" + race + '\'' +
                ", sex='" + sex + '\'' +
                ", nativeCountry='" + nativeCountry + '\'' +
                ", classLabel='" + classLabel + '\'' +
                '}';
    }
}
