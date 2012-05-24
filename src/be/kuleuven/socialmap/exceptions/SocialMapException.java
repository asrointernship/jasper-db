/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.exceptions;

/**
 *
 * @author Jasper Moeys
 */
public class SocialMapException extends Exception{
    
    public SocialMapException(){
        super();
    }

    public SocialMapException(Throwable cause) {
        super(cause);
    }

    public SocialMapException(String message, Throwable cause) {
        super(message, cause);
    }

    public SocialMapException(String message) {
        super(message);
    }
    
}
