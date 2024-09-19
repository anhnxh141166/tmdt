//package prj1.annotation;
//
//import java.lang.annotation.Documented;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import prj1.validators.StringAccentValueValidator;
//
//@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE,
//    ElementType.ANNOTATION_TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@Constraint(validatedBy = StringAccentValueValidator.class)
//public @interface AcceptValue {
//
//  String message() default "Invalid value !  ";
//
//  String[] values();
//
//  Class<?>[] groups() default {};
//
//  Class<? extends Payload>[] payload() default {};
//}