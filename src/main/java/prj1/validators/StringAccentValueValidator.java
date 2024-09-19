//package prj1.validators;
//
//import java.util.Arrays;
//import java.util.List;
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//import prj1.annotation.AcceptValue;
//
//public class StringAccentValueValidator implements ConstraintValidator<AcceptValue, String> {
//
//  private List<String> values;
//
//  @Override
//  public void initialize(AcceptValue constraintAnnotation) {
//    ConstraintValidator.super.initialize(constraintAnnotation);
//    this.values = Arrays.asList(constraintAnnotation.values());
//  }
//
//  @Override
//  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
//    return values.contains(s);
//  }
//}
