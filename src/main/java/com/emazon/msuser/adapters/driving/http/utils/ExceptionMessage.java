package com.emazon.msuser.adapters.driving.http.utils;

public class ExceptionMessage {

    public static final String FIRST_NAME_NOT_BLANK = "First name must not be empty";
    public static final String LAST_NAME_NOT_BLANK = "Last name must not be empty";
    public static final String DNI_NOT_BLANK = "DNI must not be empty";
    public static final String DNI_PATTERN = "Document number can only contain numbers";
    public static final String PHONE_NUMBER_NOT_BLANK = "Phone number must not be empty";
    public static final String PHONE_NUMBER_PATTERN = "Wrong phone format";
    public static final String BIRTHDATE_NOT_NULL = "Birthdate must not be empty";
    public static final String BIRTHDATE_PAST = "Must be a past date";
    public static final String EMAIL_NOT_BLANK = "Email must not be empty";
    public static final String EMAIL_PATTERN = "Must be a well-formed email address";
    public static final String PASSWORD_NOT_BLANK = "Password must not be empty";

    // Expresiones regulares
    public static final String DOCUMENT_NUMBER_REGEX = "^\\d{9,13}$";
    public static final String PHONE_NUMBER_REGEX = "^\\+?\\d{9,12}$";
}
