/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package an.registration;

import java.io.Serializable;

/**
 *
 * @author AN
 */
public class RegistrationInsertError implements Serializable{
    private String usernameErr;
    private String passwordErr;
    private String confirmNotMatch;
    private String fullNameLengthErr;
    private String usernameDuplicate;

    public RegistrationInsertError() {
    }
    
    

    /**
     * @return the usernameErr
     */
    public String getUsernameErr() {
        return usernameErr;
    }

    /**
     * @param usernameErr the usernameErr to set
     */
    public void setUsernameErr(String usernameErr) {
        this.usernameErr = usernameErr;
    }

    /**
     * @return the passwordErr
     */
    public String getPasswordErr() {
        return passwordErr;
    }

    /**
     * @param passwordErr the passwordErr to set
     */
    public void setPasswordErr(String passwordErr) {
        this.passwordErr = passwordErr;
    }

    /**
     * @return the confirmNotMatch
     */
    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    /**
     * @param confirmNotMatch the confirmNotMatch to set
     */
    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    /**
     * @return the fullNameLengthErr
     */
    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    /**
     * @param fullNameLengthErr the fullNameLengthErr to set
     */
    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    /**
     * @return the usernameDuplicate
     */
    public String getUsernameDuplicate() {
        return usernameDuplicate;
    }

    /**
     * @param usernameDuplicate the usernameDuplicate to set
     */
    public void setUsernameDuplicate(String usernameDuplicate) {
        this.usernameDuplicate = usernameDuplicate;
    }
    
    
}
