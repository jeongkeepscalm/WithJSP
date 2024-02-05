package practice.itemService.usingJsp.exception;

import lombok.Getter;

@Getter
public class CustomNoFileException extends Exception {

    private String fileName;

    public CustomNoFileException(String fileName) {
        super();
        this.fileName = fileName;
    }

}
