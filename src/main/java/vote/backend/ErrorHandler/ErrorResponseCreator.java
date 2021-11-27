package vote.backend.errorHandler;

public class ErrorResponseCreator {

  private ErrorResponseCreator() {
    throw new IllegalStateException("Utility class");
  }

  public static <T> String notFoundException(
    String object,
    String type,
    T value
  ) {
    return (
      object + " with the " + type + ": " + value + " could not be found"
    );
  }
}
