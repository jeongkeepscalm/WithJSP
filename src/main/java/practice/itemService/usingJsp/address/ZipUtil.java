package practice.itemService.usingJsp.address;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtil {

    private static Path zipSlipProtect(ZipEntry zipEntry, Path targetDir) throws IOException {

        Path targetDirResolved = targetDir.resolve(zipEntry.getName());

        Path normalizePath = targetDirResolved.normalize();
        if (!normalizePath.startsWith(targetDir)) {
            throw new IOException("Bad zip entry: " + zipEntry.getName());
        }
        return normalizePath;
    }

    public static void unzip(String sourceFilePath, String extractPath) throws Exception{

        Path source = Paths.get(sourceFilePath);
        Path target = Paths.get(extractPath);

        FileInputStream is = new FileInputStream(source.toFile());
        ZipInputStream zis = new ZipInputStream(is);

        // list files in zip
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {

            // zipEntry : 알집 내 파일들

            boolean isDirectory = false;
            if (zipEntry.getName().endsWith(File.separator)) {
                isDirectory = true;
            }

            Path newPath = zipSlipProtect(zipEntry, target);

            if (isDirectory) {
                Files.createDirectories(newPath);
            } else {
                if (newPath.getParent() != null) {
                    if (Files.notExists(newPath.getParent())) {
                        Files.createDirectories(newPath.getParent());
                    }
                }
                // copy files
                Files.copy(zis, newPath, StandardCopyOption.REPLACE_EXISTING);
            }

            zipEntry = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();

        is.close();
    }
}
