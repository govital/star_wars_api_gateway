package DemoApi.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class StorageHandler {
    @Value("${csvFiles.path}")
    private String csvFilesLocalPath;
    private Path fileStorageLocation;

    public void setFileStorageLocation(){
        fileStorageLocation = Paths.get(csvFilesLocalPath).toAbsolutePath().normalize();
    }
    public Path resolve(String fileName){
        return fileStorageLocation.resolve(fileName).normalize();
    }
}
