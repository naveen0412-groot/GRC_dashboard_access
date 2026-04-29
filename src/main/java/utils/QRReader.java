
package utils;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import org.openqa.selenium.JavascriptExecutor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class QRReader {

    public static String detectAndCrop(String screenshotPath, String outputFolder) {

        try {
            File file = new File(screenshotPath);

            if (!file.exists()) {
                System.err.println("FILE NOT FOUND: " + screenshotPath);
                return null;
            }

            // FIX: Use FileInputStream to prevent read errors
            BufferedImage screenshot = ImageIO.read(new FileInputStream(file));

            LuminanceSource source = new BufferedImageLuminanceSource(screenshot);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Result result = new MultiFormatReader().decode(bitmap);
            ResultPoint[] points = result.getResultPoints();

            float minX = Math.min(points[0].getX(), Math.min(points[1].getX(), points[2].getX()));
            float maxX = Math.max(points[0].getX(), Math.max(points[1].getX(), points[2].getX()));
            float minY = Math.min(points[0].getY(), Math.min(points[1].getY(), points[2].getY()));
            float maxY = Math.max(points[0].getY(), Math.max(points[1].getY(), points[2].getY()));

            int padding = 50;

            int x = (int) Math.max(minX - padding, 0);
            int y = (int) Math.max(minY - padding, 0);
            int width = (int) (maxX - minX + padding * 2);
            int height = (int) (maxY - minY + padding * 2);

            System.out.println("minX=" + minX + ", minY=" + minY);
            System.out.println("maxX=" + maxX + ", maxY=" + maxY);
            System.out.println("Crop area: x=" + x + ", y=" + y + ", width=" + width + ", height=" + height);

            BufferedImage qrCropped = screenshot.getSubimage(x, y, width, height);

            File outDir = new File(outputFolder);
            if (!outDir.exists()) outDir.mkdirs();

            String outputPath = outputFolder + "/cropped_qr_" + System.currentTimeMillis() + ".png";
            ImageIO.write(qrCropped, "png", new File(outputPath));

            System.out.println("QR text: " + result.getText());
            System.out.println("QR cropped saved: " + outputPath);

            String qrUrl = result.getText();


            return qrUrl;

        } catch (Exception e) {
            System.err.println("QR crop failed: " + e.getMessage());
            return null;
        }
    }
}
