package frontend.validation;

import frontend.api.request.ChangePasswordReqest;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, ChangePasswordReqest> {

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(ChangePasswordReqest changePasswordReqest, ConstraintValidatorContext context) {

        String password=changePasswordReqest.getPassword();
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
            // at least 8 characters
            new LengthRule(8, 30),

            // at least one upper-case character
            new CharacterRule(EnglishCharacterData.UpperCase, 1),

            // at least one lower-case character
            new CharacterRule(EnglishCharacterData.LowerCase, 1),

            // at least one digit character
            new CharacterRule(EnglishCharacterData.Digit, 1),

            // at least one symbol (special character)
            new CharacterRule(EnglishCharacterData.Special, 1),

            // no whitespace
            new WhitespaceRule(),

            // rejects passwords that contain a sequence of >= 5 characters alphabetical  (e.g. abcdef)
            new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),

            // rejects passwords that contain a sequence of >= 5 characters numerical   (e.g. 12345)
            new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false)
        ));

        changePasswordReqest.getPassword().equals(changePasswordReqest.getConfirmPassword());

        boolean isValid = changePasswordReqest.getPassword().equals(changePasswordReqest.getConfirmPassword());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate( "Password not matched" )
                    .addPropertyNode( "matchingPassword" ).addConstraintViolation();
        }

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);

        String messageTemplate = messages.stream()
            .collect(Collectors.joining(","));
        context.buildConstraintViolationWithTemplate(messageTemplate)
            .addConstraintViolation()
            .disableDefaultConstraintViolation();
        return false;
    }
}