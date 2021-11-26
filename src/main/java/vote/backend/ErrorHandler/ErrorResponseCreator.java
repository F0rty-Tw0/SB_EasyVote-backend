package vote.backend.ErrorHandler;

public class ErrorResponseCreator {

    public static <T> String NotFoundException(String object, String type, T value){
        return (object+ " with the " +type+ ": "+ value+ " could not be found");
    }
}
