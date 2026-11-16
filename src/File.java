
public class File {

    private String filename;
    private String fileType;

    public File(String filename, String fileType) {
        setFilename(filename);
        setFileType(fileType);
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "File name cannot be empty");
        }
        this.filename = filename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        if (fileType == null || fileType.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "File type cannot be empty");
        }
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "File name: " + filename +
               ", File type: " + fileType;
    }
}
