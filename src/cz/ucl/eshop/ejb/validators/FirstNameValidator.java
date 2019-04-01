package cz.ucl.eshop.ejb.validators;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("firstNameValidator")
public class FirstNameValidator implements Validator {

    private static final String FIRSTNAME_PATTERN = "^[A-Z]{1}[a-z, ]+";
    private Pattern pattern;
    private Matcher matcher;

    public FirstNameValidator(){
        pattern = Pattern.compile(FIRSTNAME_PATTERN);
    }

    public void validate(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {
        matcher = pattern.matcher(value.toString());
        if (!matcher.matches()){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "First name validation error.", "Valid first name must have a length from 2 to 50 characters and contain only A-z characters and space.");
            throw new ValidatorException(facesMessage);
        }
    }
}
