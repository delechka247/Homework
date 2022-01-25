package pdfgeneration;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Date;

public class PdfGenerator {

    public static void main(String[] args) throws Exception {
        String firstName = "Иван";
        String lastName = "Иванов";
        createOtchislenieStatement(firstName, lastName);
        createAcademStatement(firstName, lastName);
        createIndividGrafikStatement(firstName, lastName);
        createIndividGrafikExamStatement(firstName, lastName);
        createVyhodIzOtpuskaStatement(firstName, lastName);

    }


    public static void createOtchislenieStatement(String firstName, String lastName)
            throws Exception {
        PdfReader reader = new PdfReader(
                new FileInputStream("Otchislenie.pdf"));
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream("Otchislenie1.pdf"));

        PdfContentByte stream = stamper.getOverContent(1);
        stream.beginText();
        stream.setColorFill(BaseColor.BLUE);

        BaseFont font = BaseFont.createFont("C:\\Windows\\Fonts\\segoepr.ttf", "cp1251", BaseFont.EMBEDDED );

        stream.setFontAndSize(font, 12);
        stream.setTextMatrix(320, 518);
        stream.showText(lastName + " " + firstName);

        stream.endText();
        stamper.setFullCompression();
        stamper.close();
    }

    public static void createIndividGrafikStatement(String firstName, String lastName)
            throws Exception {
        PdfReader reader = new PdfReader(
                new FileInputStream("IndividGrafik.pdf"));
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream("IndividGrafik1.pdf"));

        PdfContentByte stream = stamper.getOverContent(1);
        stream.beginText();
        stream.setColorFill(BaseColor.BLUE);

        BaseFont font = BaseFont.createFont("C:\\Windows\\Fonts\\segoepr.ttf", "cp1251", BaseFont.EMBEDDED );

        stream.setFontAndSize(font, 12);
        stream.setTextMatrix(320, 490);
        stream.showText(lastName + " " + firstName);

        stream.endText();
        stamper.setFullCompression();
        stamper.close();
    }

    public static void createIndividGrafikExamStatement(String firstName, String lastName)
            throws Exception {
        PdfReader reader = new PdfReader(
                new FileInputStream("IndividGrafikExam.pdf"));
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream("IndividGrafikExam1.pdf"));

        PdfContentByte stream = stamper.getOverContent(1);
        stream.beginText();
        stream.setColorFill(BaseColor.BLUE);

        BaseFont font = BaseFont.createFont("C:\\Windows\\Fonts\\segoepr.ttf", "cp1251", BaseFont.EMBEDDED );

        stream.setFontAndSize(font, 12);
        stream.setTextMatrix(320, 558);
        stream.showText(lastName + " " + firstName);

        stream.endText();
        stamper.setFullCompression();
        stamper.close();
    }

    public static void createVyhodIzOtpuskaStatement(String firstName, String lastName)
            throws Exception {
        PdfReader reader = new PdfReader(
                new FileInputStream("VyhodIzOtpuska.pdf"));
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream("VyhodIzOtpuska1.pdf"));

        PdfContentByte stream = stamper.getOverContent(1);
        stream.beginText();
        stream.setColorFill(BaseColor.BLUE);

        BaseFont font = BaseFont.createFont("C:\\Windows\\Fonts\\segoepr.ttf", "cp1251", BaseFont.EMBEDDED );

        stream.setFontAndSize(font, 12);
        stream.setTextMatrix(320, 515);
        stream.showText(lastName + " " + firstName);

        stream.endText();
        stamper.setFullCompression();
        stamper.close();
    }

    public static void createAcademStatement(String firstName, String lastName)
            throws Exception {
        PdfReader reader = new PdfReader(
                new FileInputStream("Academ.pdf"));
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream("Academ1.pdf"));

        PdfContentByte stream = stamper.getOverContent(1);
        stream.beginText();
        stream.setColorFill(BaseColor.BLUE);

        BaseFont font = BaseFont.createFont("C:\\Windows\\Fonts\\segoepr.ttf", "cp1251", BaseFont.EMBEDDED );

        stream.setFontAndSize(font, 12);
        stream.setTextMatrix(320, 523);
        stream.showText(lastName + " " + firstName);

        stream.endText();
        stamper.setFullCompression();
        stamper.close();
    }




}