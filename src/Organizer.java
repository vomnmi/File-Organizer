import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Organizer {
    public static void OrganizeFiles(String directoryPath) throws IOException {
        File directory = new File(directoryPath);

        if (directory.isDirectory()) {
            File[] files = directory.listFiles();

            Map<String, String> dirNames = new HashMap<>();
            dirNames.put("audio", "Audio");
            dirNames.put("image", "Images");
            dirNames.put("video", "Videos");
            dirNames.put("application/pdf", "PDFs");
            dirNames.put("text/plain", "Text Files");
            dirNames.put("application/msword", "Word Documents");
            dirNames.put("application/vnd.ms-excel", "Excel Spreadsheets");
            dirNames.put("pptx", "PowerPoint Presentations");
            dirNames.put("ppt", "PowerPoint Presentations");
            dirNames.put("application/zip", "ZIP Archives");
            dirNames.put("application/x-gzip", "GZIP Archives");
            dirNames.put("application/x-tar", "TAR Archives");
            dirNames.put("application/xml", "XML Files");
            dirNames.put("application/json", "JSON Files");


            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        Path filePath = file.toPath();
                        try {
                            String fileFormat = Files.probeContentType(filePath);
                            String category = getCategoryFromContentType(fileFormat, file);

                            if (category != null) {
                                File categoryDirectory = new File(directory, category);

                                if (!categoryDirectory.exists()) {
                                    categoryDirectory.mkdir();
                                }

                                File destination = new File(categoryDirectory, file.getName());
                                file.renameTo(destination);
                            }


                        } catch (IOException e) {
                            System.out.println("Failed to probe content for: " + file.getName());
                        }
                        System.out.println("Folder is organized!");
                    }
                }
            } else {
                System.out.println("No files found in this directory");
            }
        } else {
            System.out.println("Not a directory");
        }
    }

    public static String getCategoryFromContentType(String contentType, File file) {
        if (contentType != null) {
            if (contentType.startsWith("audio")) {
                return "Audio";
            } else if (contentType.startsWith("video")) {
                return "Videos";
            } else if (contentType.startsWith("image")) {
                return "Images";
            } else if (contentType.startsWith("application/pdf")) {
                return "PDFs";
            } else if (contentType.startsWith("text/plain")) {
                return "Text Files";
            } else if (contentType.startsWith("application/msword")) {
                return "Word Documents";
            } else if (contentType.startsWith("application/vnd.ms-excel")) {
                return "Excel Spreadsheets";
            } else if (contentType.startsWith("application/vnd.ms-powerpoint")) {
                return "PowerPoint Presentations";
            } else if (contentType.startsWith("application/zip")) {
                return "ZIP Archives";
            } else if (contentType.startsWith("application/x-gzip")) {
                return "GZIP Archives";
            } else if (contentType.startsWith("application/x-tar")) {
                return "TAR Archives";
            } else if (contentType.startsWith("application/xml")) {
                return "XML Files";
            } else if (contentType.startsWith("application/json")) {
                return "JSON Files";
            } else {
                System.out.println("Content does not recognized: " + file.getName());
            }
        }
        return "Other Files";


    }

}
