package ibf2022.workshop22rsvp.model;
import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class RSVP implements Serializable {
    @Id 
    private Integer Id;
    
    @Size(min = 5, max = 150, message = "Full name must be between 5 and 150 characters")
    private String fullName;
    
    @Email(message = "Email must not be blank and must be in the correct format")
    @Size(max = 150, message = "Email only accepts a max of 150 characters")
    private String email;

    private String phone;
    private Date confirmationDate;
    private String comments;


    public Integer getId() {return this.Id;}
    public void setId(Integer Id) {this.Id = Id;}

    public String getFullName() {return this.fullName;}
    public void setFullName(String fullName) {this.fullName = fullName;}

    public String getEmail() {return this.email;}
    public void setEmail(String email) {this.email = email;}

    public String getPhone() {return this.phone;}
    public void setPhone(String phone) {this.phone = phone;}

    public Date getConfirmationDate() {return this.confirmationDate;}
    public void setConfirmationDate(Date confirmationDate) {this.confirmationDate = confirmationDate;}

    public String getComments() {return this.comments;}
    public void setComments(String comments) {this.comments = comments;}


    public RSVP(String fullName, String email, String phone, String comments) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.comments = comments;
    }
    
}
