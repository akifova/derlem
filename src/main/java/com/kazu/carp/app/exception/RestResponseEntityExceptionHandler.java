package com.kazu.carp.app.exception;

import com.kazu.carp.app.exception.app.InvalidLicenseException;
import com.kazu.carp.app.exception.common.FieldsCanNotBeNullException;
import com.kazu.carp.app.exception.design.CannotMatchRgbException;
import com.kazu.carp.app.exception.design.CannotReadRgbException;
import com.kazu.carp.app.exception.design.OnlyProductsCanBeRetiredException;
import com.kazu.carp.app.exception.design.plan.FileAlreadyUploadedException;
import com.kazu.carp.app.exception.yarn.YarnPropertiesNotAsDefinedInYarnGroup;
import com.kazu.carp.app.validation.common.KazuError;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 03.08.2021
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    final String DATABASE_ERROR_MESSAGE = "İşlem Başarısız Oldu.\n" +
            "Lütfen Bilgileri Kontrol Edin. \n" +
            "Kayıt İşlemi Yapıyorsanız Aynı Bilgiler Daha Önce Kaydedilmiş Olabilir. \n" +
            "Silme İşlemi Yapıyorsanız, Silinen Öğenin Başka Bir Yerde Kullanılmadığından Emin Olun.";
    // APPLICATION
    @ExceptionHandler({FieldsCanNotBeNullException.class})
    public ResponseEntity<Object> fieldsCanNotBeNull(final FieldsCanNotBeNullException ex, final WebRequest request) {
        List<KazuError> errors = new ArrayList<>();
        KazuError error = KazuError.builder()
                .name("Fields Can Not Be Null Exception")
                .code(ex.getLocalizedMessage())
                .message(ex.getMessage())
                .type("translated")
                .trace(null)
                .build();
        errors.add(error);
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({FileAlreadyUploadedException.class})
    public ResponseEntity<Object> fileAlreadyUpload(final FileAlreadyUploadedException ex, final WebRequest request) {
        List<KazuError> errors = new ArrayList<>();
        KazuError error = KazuError.builder()
                .name("File Already Upload Exception")
                .code(ex.getLocalizedMessage())
                .message("Önceden Yüklenmiş Bir Dosyayı Yüklemeye Çalışıyorsunuz.")
                .type("translated")
                .trace(null)
                .build();
        errors.add(error);
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({CannotMatchRgbException.class})
    public ResponseEntity<Object> cannotMatchRGB(final CannotMatchRgbException ex, final WebRequest request) {
        List<KazuError> errors = new ArrayList<>();
        KazuError error = KazuError.builder()
                .name("Cannot Match RGB Exception")
                .code(ex.getLocalizedMessage())
                .message("Desen İle Paletin Renk Sıraları veya RGB Değerleri uyuşmadı. Lütfen Kontrol Ediniz.")
                .type("translated")
                .trace(null)
                .build();
        errors.add(error);
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({InvalidLicenseException.class})
    public ResponseEntity<Object> invalidLicense(final InvalidLicenseException ex, final WebRequest request) {
        List<KazuError> errors = new ArrayList<>();
        KazuError error = KazuError.builder()
                .name("Invalid License")
                .code(ex.getLocalizedMessage())
                .message("Sertifikanız geçersiz. Lütfen sunucunun yetkili olup olmadığını kontrol edin veya sertifika için yeniden başvurun! ")
                .type("translated")
                .trace(ex.getMessage())
                .build();
        errors.add(error);
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({CannotReadRgbException.class})
    public ResponseEntity<Object> cannotReadRGB(final CannotReadRgbException ex, final WebRequest request) {
        List<KazuError> errors = new ArrayList<>();
        KazuError error = KazuError.builder()
                .name("Cannot Read RGB Exception")
                .code(ex.getLocalizedMessage())
                .message("Desenin RGB Değerleri Okunamadı. Lütfen Doğru Dosya Türü Yüklediğinizden Emin Olunuz.")
                .type("translated")
                .trace(null)
                .build();
        errors.add(error);
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({OnlyProductsCanBeRetiredException.class})
    public ResponseEntity<Object> onlyProductsCanBeRetired(final OnlyProductsCanBeRetiredException ex, final WebRequest request) {
        List<KazuError> errors = new ArrayList<>();
        KazuError error = KazuError.builder()
                .name("Only Products Can Be Retired")
                .code(ex.getLocalizedMessage())
                .message("Sadece 'Üretim Arşivi' Kodlu Desenler Emekliye Ayrılabilir.")
                .type("translated")
                .trace(null)
                .build();
        errors.add(error);
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({YarnPropertiesNotAsDefinedInYarnGroup.class})
    public ResponseEntity<Object> yarnPropertiesNotAsDefinedInYarnGroup(final YarnPropertiesNotAsDefinedInYarnGroup ex, final WebRequest request) {
        List<KazuError> errors = new ArrayList<>();
        KazuError error = KazuError.builder()
                .name("Yarn Properties Not As Defined In Yarn Group")
                .code(ex.getLocalizedMessage())
                .message("Sadece İplik Grubunda Tanımlanmış Olan Özellikler İplik İçin Kaydedilebilir.")
                .type("translated")
                .trace(null)
                .build();
        errors.add(error);
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    // API
    // 400
    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
        List<KazuError> errors = new ArrayList<>();
        KazuError error = KazuError.builder()
                .name("Constraint Violation Exception")
                .code(ex.getLocalizedMessage())
                .message(DATABASE_ERROR_MESSAGE)
                .type("translated")
                .trace(null)
                .build();
        errors.add(error);
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
        List<KazuError> errors = new ArrayList<>();
        KazuError error = KazuError.builder()
                .name("Data Integrity Violation Exception")
                .code(ex.getLocalizedMessage())
                .message(DATABASE_ERROR_MESSAGE)
                .type("translated")
                .trace(null)
                .build();
        errors.add(error);
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        List<KazuError> errors = new ArrayList<>();
        KazuError error = KazuError.builder()
                .name("Http Message Not Readable Exception")
                .code(ex.getLocalizedMessage())
                .message("Servise Gönderilen Bilgi Hatalı. Lütfen Uygulama Geliştiricisi ile İletişime Geçiniz.")
                .type("translated")
                .trace(null)
                .build();
        errors.add(error);
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        List<KazuError> kazuErrors = ex.getBindingResult().getAllErrors().stream().map(m-> KazuError.builder()
                .name("Method Argument Not Valid Exception")
                .type("translated")
                .message(m.getDefaultMessage())
                .code(m.getCode())
                .trace("")
                .build()).collect(Collectors.toList());

        return handleExceptionInternal(ex, kazuErrors, headers, HttpStatus.BAD_REQUEST, request);
    }


    // 404

    @ExceptionHandler(value = { EntityNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        final String bodyOfResponse = createExceptionDetail("Entity Not Found Exception", ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // 409

    @ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class })
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        final String bodyOfResponse = createExceptionDetail("Invalid Data Access Api Usage Exception", ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    // 412

    // 500

    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        final String bodyOfResponse = createExceptionDetail("Null Pointer Exception / Illegal Argument Exception", ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private String createExceptionDetail(String header, String allMessage) {
        String detail = allMessage.length() > 500 ? allMessage.substring(0, 500) + "..." : allMessage;
        return String.format("====%s===\n%s", header, detail);
    }
}
