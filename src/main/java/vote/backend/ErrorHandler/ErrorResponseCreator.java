package vote.backend.ErrorHandler;

public class ErrorResponseCreator {

    public static <T> String NotFoundException(T object, String type, String value){
        return (object+ " with the " +type+ ": "+ value+ " could not be found");
    }
}
