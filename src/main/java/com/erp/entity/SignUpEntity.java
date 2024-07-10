package com.erp.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;


@Entity

public class SignUpEntity {

	

	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
	private Long userId;

	private String firstName;

	private String lastName;

	@NotNull
	private String email;

	private String mobileNumber;

	private String password;

	private String confirmPassword;

	private String state;

	private String country;

	private String address;
	
	private String fatherName;
	
	private String specialization;
	
	private String specialization1;
	
	private String specialization2;
	
	private String subject1;
	
	private String subject2;
	
	private String subject3;
	
	private String subject4;
	
	private String subject5;
	
	private String subject6;
	
	private String subject15;
	
	private String subject16;
	
	private String language1;
	
	private String language2;
	
	private String language3;
	
	private String language4;
	
	private String language5;
	
	private String language6;
	
	private String language7;
	
	private String language8;

	private String language9;
	private String language15;
	private String language16;
	private String language17;
	private String currentSalary1;
	private String currentSalary2;
	private String currentExsalary1;
	private String currentExsalary2;
	private String currentExsalary;
	private String professional;
	private String description;
	private String statement;
	private String certifications;
	private String dateofbirth;
	private String resume;
	private String photo;
	private String selectedOption;
	private String selectOption;
	private String proficiency;
	private String typeofmode;
	private String years;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private FileEntity file;
	
    @ManyToMany
    @JoinTable(
        name = "user_saved_jobposts",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "jobpost_id")
    )
    private List<JobPost> savedJobPosts = new ArrayList<>();


	
	
	


	public List<JobPost> getSavedJobPosts() {
		return savedJobPosts;
	}
	public void setSavedJobPosts(List<JobPost> savedJobPosts) {
		this.savedJobPosts = savedJobPosts;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getSpecialization1() {
		return specialization1;
	}
	public void setSpecialization1(String specialization1) {
		this.specialization1 = specialization1;
	}
	public String getSpecialization2() {
		return specialization2;
	}
	public void setSpecialization2(String specialization2) {
		this.specialization2 = specialization2;
	}
	public String getSubject1() {
		return subject1;
	}
	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}
	public String getSubject2() {
		return subject2;
	}
	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}
	public String getSubject3() {
		return subject3;
	}
	public void setSubject3(String subject3) {
		this.subject3 = subject3;
	}
	public String getSubject4() {
		return subject4;
	}
	public void setSubject4(String subject4) {
		this.subject4 = subject4;
	}
	public String getSubject5() {
		return subject5;
	}
	public void setSubject5(String subject5) {
		this.subject5 = subject5;
	}
	public String getSubject6() {
		return subject6;
	}
	public void setSubject6(String subject6) {
		this.subject6 = subject6;
	}
	public String getSubject15() {
		return subject15;
	}
	public void setSubject15(String subject15) {
		this.subject15 = subject15;
	}
	public String getSubject16() {
		return subject16;
	}
	public void setSubject16(String subject16) {
		this.subject16 = subject16;
	}
	public String getLanguage1() {
		return language1;
	}
	public void setLanguage1(String language1) {
		this.language1 = language1;
	}
	public String getLanguage2() {
		return language2;
	}
	public void setLanguage2(String language2) {
		this.language2 = language2;
	}
	public String getLanguage3() {
		return language3;
	}
	public void setLanguage3(String language3) {
		this.language3 = language3;
	}
	public String getLanguage4() {
		return language4;
	}
	public void setLanguage4(String language4) {
		this.language4 = language4;
	}
	public String getLanguage5() {
		return language5;
	}
	public void setLanguage5(String language5) {
		this.language5 = language5;
	}
	public String getLanguage6() {
		return language6;
	}
	public void setLanguage6(String language6) {
		this.language6 = language6;
	}
	public String getLanguage7() {
		return language7;
	}
	public void setLanguage7(String language7) {
		this.language7 = language7;
	}
	public String getLanguage8() {
		return language8;
	}
	public void setLanguage8(String language8) {
		this.language8 = language8;
	}
	public String getLanguage9() {
		return language9;
	}
	public void setLanguage9(String language9) {
		this.language9 = language9;
	}
	public String getLanguage15() {
		return language15;
	}
	public void setLanguage15(String language15) {
		this.language15 = language15;
	}
	public String getLanguage16() {
		return language16;
	}
	public void setLanguage16(String language16) {
		this.language16 = language16;
	}
	public String getLanguage17() {
		return language17;
	}
	public void setLanguage17(String language17) {
		this.language17 = language17;
	}
	public String getCurrentSalary1() {
		return currentSalary1;
	}
	public void setCurrentSalary1(String currentSalary1) {
		this.currentSalary1 = currentSalary1;
	}
	public String getCurrentSalary2() {
		return currentSalary2;
	}
	public void setCurrentSalary2(String currentSalary2) {
		this.currentSalary2 = currentSalary2;
	}
	public String getCurrentExsalary1() {
		return currentExsalary1;
	}
	public void setCurrentExsalary1(String currentExsalary1) {
		this.currentExsalary1 = currentExsalary1;
	}
	public String getCurrentExsalary2() {
		return currentExsalary2;
	}
	public void setCurrentExsalary2(String currentExsalary2) {
		this.currentExsalary2 = currentExsalary2;
	}
	public String getCurrentExsalary() {
		return currentExsalary;
	}
	public void setCurrentExsalary(String currentExsalary) {
		this.currentExsalary = currentExsalary;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getCertifications() {
		return certifications;
	}
	public void setCertifications(String certifications) {
		this.certifications = certifications;
	}
	public String getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	public String getSelectOption() {
		return selectOption;
	}
	public void setSelectOption(String selectOption) {
		this.selectOption = selectOption;
	}
	public String getProficiency() {
		return proficiency;
	}
	public void setProficiency(String proficiency) {
		this.proficiency = proficiency;
	}
	public String getTypeofmode() {
		return typeofmode;
	}
	public void setTypeofmode(String typeofmode) {
		this.typeofmode = typeofmode;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	
	
	
	
	
	
	public FileEntity getFile() {
		return file;
	}
	public void setFile(FileEntity file) {
		this.file = file;
	}
	public SignUpEntity( String firstName, String lastName, @NotNull String email, String mobileNumber,
			String password, String confirmPassword, String state, String country, String address, String fatherName,
			String specialization, String specialization1, String specialization2, String subject1, String subject2,
			String subject3, String subject4, String subject5, String subject6, String subject15, String subject16,
			String language1, String language2, String language3, String language4, String language5, String language6,
			String language7, String language8, String language9, String language15, String language16,
			String language17, String currentSalary1, String currentSalary2, String currentExsalary1,
			String currentExsalary2, String currentExsalary, String professional, String description, String statement,
			String certifications, String dateofbirth, String resume, String photo, String selectedOption,
			String selectOption, String proficiency, String typeofmode, String years) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.state = state;
		this.country = country;
		this.address = address;
		this.fatherName = fatherName;
		this.specialization = specialization;
		this.specialization1 = specialization1;
		this.specialization2 = specialization2;
		this.subject1 = subject1;
		this.subject2 = subject2;
		this.subject3 = subject3;
		this.subject4 = subject4;
		this.subject5 = subject5;
		this.subject6 = subject6;
		this.subject15 = subject15;
		this.subject16 = subject16;
		this.language1 = language1;
		this.language2 = language2;
		this.language3 = language3;
		this.language4 = language4;
		this.language5 = language5;
		this.language6 = language6;
		this.language7 = language7;
		this.language8 = language8;
		this.language9 = language9;
		this.language15 = language15;
		this.language16 = language16;
		this.language17 = language17;
		this.currentSalary1 = currentSalary1;
		this.currentSalary2 = currentSalary2;
		this.currentExsalary1 = currentExsalary1;
		this.currentExsalary2 = currentExsalary2;
		this.currentExsalary = currentExsalary;
		this.professional = professional;
		this.description = description;
		this.statement = statement;
		this.certifications = certifications;
		this.dateofbirth = dateofbirth;
		this.resume = resume;
		this.photo = photo;
		this.selectedOption = selectedOption;
		this.selectOption = selectOption;
		this.proficiency = proficiency;
		this.typeofmode = typeofmode;
		this.years = years;
	}
	public SignUpEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SignUpEntity [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", mobileNumber=" + mobileNumber + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", state=" + state + ", country=" + country + ", address=" + address
				+ ", fatherName=" + fatherName + ", specialization=" + specialization + ", specialization1="
				+ specialization1 + ", specialization2=" + specialization2 + ", subject1=" + subject1 + ", subject2="
				+ subject2 + ", subject3=" + subject3 + ", subject4=" + subject4 + ", subject5=" + subject5
				+ ", subject6=" + subject6 + ", subject15=" + subject15 + ", subject16=" + subject16 + ", language1="
				+ language1 + ", language2=" + language2 + ", language3=" + language3 + ", language4=" + language4
				+ ", language5=" + language5 + ", language6=" + language6 + ", language7=" + language7 + ", language8="
				+ language8 + ", language9=" + language9 + ", language15=" + language15 + ", language16=" + language16
				+ ", language17=" + language17 + ", currentSalary1=" + currentSalary1 + ", currentSalary2="
				+ currentSalary2 + ", currentExsalary1=" + currentExsalary1 + ", currentExsalary2=" + currentExsalary2
				+ ", currentExsalary=" + currentExsalary + ", professional=" + professional + ", description="
				+ description + ", statement=" + statement + ", certifications=" + certifications + ", dateofbirth="
				+ dateofbirth + ", resume=" + resume + ", photo=" + photo + ", selectedOption=" + selectedOption
				+ ", selectOption=" + selectOption + ", proficiency=" + proficiency + ", typeofmode=" + typeofmode
				+ ", years=" + years + "]";
	}
	

	




}
