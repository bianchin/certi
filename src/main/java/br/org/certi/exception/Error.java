package br.org.certi.exception;

/**
 * Created by gian on 01/03/20.
 */
public class Error {

    private String message;

    public String getMessage() {
        return message;
    }


    public static final class ErrorBuilder {
        private String message;

        private ErrorBuilder() {
        }

        public static ErrorBuilder anError() {
            return new ErrorBuilder();
        }

        public ErrorBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Error build() {
            Error error = new Error();
            error.message = this.message;
            return error;
        }
    }
}
