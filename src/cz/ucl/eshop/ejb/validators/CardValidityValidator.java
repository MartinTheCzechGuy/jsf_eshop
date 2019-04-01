package cz.ucl.eshop.ejb.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("cardValidityValidator")
public class CardValidityValidator implements Validator {

    private static final String VALIDITY_PATTERN = "^[0-9]{2}/[0-9]{2}$";
    private Pattern pattern;
    private Matcher matcher;

    public CardValidityValidator(){
        pattern = Pattern.compile(VALIDITY_PATTERN);
    }

    public void validate(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {
        matcher = pattern.matcher(value.toString());
        if (!matcher.matches()){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credit card validity number validation error.", "Please enter a valid validation number.");
            throw new ValidatorException(facesMessage);
        }
    }
}
