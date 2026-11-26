import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;


public class QrCodeGenerator {

    public static void generateQrCode(String content, String filePath, int width, int height)
            throws WriterException, IOException {

        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);        
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

        Path path = Paths.get(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }    
    
    public static void generateQrCodeAllPatient() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            String contentUrl = "http://172.16.3.14:8081/patient/";
            String fileStock = "/usr/local/cdn/patient/" ;
            String url = "http://localhost:8080/NainaExtrat/Allpatients";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() == 200) {

                ObjectMapper mapper = new ObjectMapper();

                JsonNode patients = mapper.readTree(response.body());

                for (JsonNode patient : patients) {

                    System.out.println("Patient : " + patient);

                    String id = patient.get("id").asText();
                    String nom = patient.get("nom").asText();

                    System.out.println("ID : " + id);
                    System.out.println("Nom : " + nom);
                    try{
                        generateQrCode(contentUrl+id,fileStock+id+".png",300,300);
                    }catch (WriterException | IOException e) {
                        System.err.println("Erreur lors de la génération : " + e.getMessage());
                    }
                    
                }

            } else {
                System.out.println("Erreur HTTP : " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Erreur : " + e.getMessage());

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }
    }



    public static void main(String[] args) {
        // try {
            // generateQrCode("https://github.com/AndriantsehenoAnja/Nainahopital/branches", "qrcode.png", 300, 300);
            generateQrCodeAllPatient();
            System.out.println("QR code généré avec succès.");
        // } 
        // catch (WriterException | IOException e) {
        //     System.err.println("Erreur lors de la génération : " + e.getMessage());
        // }
    }
}