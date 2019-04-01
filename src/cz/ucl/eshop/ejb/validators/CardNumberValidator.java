package cz.ucl.eshop.ejb.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("cardNumberValidator")
public class CardNumberValidator implements Validator {

    private static final String CARDNUMBER_PATTERN = "^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$";
    private Pattern pattern;
    private Matcher matcher;

    public CardNumberValidator(){
        pattern = Pattern.compile(CARDNUMBER_PATTERN);
    }

    public void validate(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {
        matcher = pattern.matcher(value.toString());
        if (!matcher.matches()){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credit card number validation error.", "Please enter a valid credit card number.");
            throw new ValidatorException(facesMessage);
        }
    }
}
