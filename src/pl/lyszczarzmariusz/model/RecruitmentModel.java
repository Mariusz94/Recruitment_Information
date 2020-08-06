package pl.lyszczarzmariusz.model;

import java.time.LocalDate;

public class RecruitmentModel {
    private String nameCompany;
    private String offerURL;
    private String workPlace;
    private LocalDate applicationDate;

    public RecruitmentModel() {
    }

    public RecruitmentModel(String nameCompany, String offerURL, String place) {
        this.nameCompany = nameCompany;
        this.offerURL = offerURL;
        this.workPlace = place;
        applicationDate = LocalDate.now();
    }

    public RecruitmentModel(String nameCompany, String offerURL, String workPlace, LocalDate applicationDate) {
        this.nameCompany = nameCompany;
        this.offerURL = offerURL;
        this.workPlace = workPlace;
        this.applicationDate = applicationDate;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getOfferURL() {
        return offerURL;
    }

    public void setOfferURL(String offerURL) {
        this.offerURL = offerURL;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    @Override
    public String toString() {
        return "RecruitmentModel{" +
                "nameCompany='" + nameCompany + '\'' +
                ", offerURL='" + offerURL + '\'' +
                ", place='" + workPlace + '\'' +
                ", applicationDate=" + applicationDate +
                '}';
    }
}
