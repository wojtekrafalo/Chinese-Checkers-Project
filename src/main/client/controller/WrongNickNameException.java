package client.controller;

public class WrongNickNameException extends Exception{

    public WrongNickNameException() {
        super("Player of given nick exists already");
    }

    public WrongNickNameException(String message) {
        super(message);
    }

    public String getMessage()
    {
        return super.getMessage();
    }
}