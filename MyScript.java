import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

public class DirectoryEncryption {

    private static final String KEY = "abcdefghijklmnop"; // Clé de cryptage

    public static void encryptFile(String inputFile, String outputFile, SecretKeySpec secretKey) throws IOException {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            try (FileInputStream inputStream = new FileInputStream(inputFile);
                 FileOutputStream outputStream = new FileOutputStream(outputFile);
                 CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) >= 0) {
                    cipherOutputStream.write(buffer, 0, bytesRead);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void encryptDirectory(String inputDirectory, String outputDirectory, SecretKeySpec secretKey) throws IOException {
        File dir = new File(inputDirectory);
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    String newOutputDir = outputDirectory + File.separator + file.getName();
                    Files.createDirectories(Paths.get(newOutputDir));
                    encryptDirectory(file.getAbsolutePath(), newOutputDir, secretKey);
                } else {
                    String newOutputFile = outputDirectory + File.separator + file.getName();
                    encryptFile(file.getAbsolutePath(), newOutputFile, secretKey);
                }
            }
        }
    }

    public static void main(String[] args) {
        String inputDirectory = "/chemin/vers/repertoire";
        String outputDirectory = "/chemin/vers/repertoire_crypte";
        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), "AES");

        try {
            Files.createDirectories(Paths.get(outputDirectory));
            encryptDirectory(inputDirectory, outputDirectory, secretKey);
            System.out.println("Cryptage terminé !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
