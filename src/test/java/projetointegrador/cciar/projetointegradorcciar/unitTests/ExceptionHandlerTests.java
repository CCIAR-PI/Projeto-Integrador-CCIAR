package projetointegrador.cciar.projetointegradorcciar.unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import projetointegrador.cciar.projetointegradorcciar.entity.ExceptionHandlerAdvice;

import java.util.Collections;
import java.util.Map;

@SpringBootTest
class ExceptionHandlerTests {

    @Test
    void testHandleValidationException() {

        MethodArgumentNotValidException exception = Mockito.mock(MethodArgumentNotValidException.class);

        FieldError fieldError = new FieldError("objectName", "fieldName", "Error message");

        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(exception.getBindingResult()).thenReturn(bindingResult);

        Mockito.when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(fieldError));

        ExceptionHandlerAdvice exceptionHandler = new ExceptionHandlerAdvice();

        Map<String, String> errors = exceptionHandler.handleValidationException(exception);

        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("Error message", errors.get("fieldName"));
    }
}
