//import com.artofsolving.jodconverter.DocumentConverter;
//import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
//import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
//import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;


import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Paragraph;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.print.PrintService;
import java.io.*;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;


public class FXController {

    @FXML
    public Button btnSelectWorkCatalog;
    @FXML
    public Button btnOpenMultiFiles;
    @FXML
    public Button btnOpenImgFile;
    @FXML
    public Button btnConvertToPdf;
    @FXML
    public Button btnSignDoc;
    @FXML
    public Button btnOpenPDFDir;
    @FXML
    public Button btnOpenSignDir;
    @FXML
    public Button btnOpenOpisDir;
    @FXML
    public Button btnGenInventory;
    @FXML
    public Button btnClearFiles;

    @FXML
    public TextArea ivWorkCatalog;
    @FXML
    public ListView lvFiles;
    @FXML
    public ImageView imgFile;


    public FileChooser fc = new FileChooser();
    public DirectoryChooser dc = new DirectoryChooser();
    public String initialDirectory = "C:\\";
    public Label statusLabel;


    @FXML
    public void handleBtnSelectWorkCatalog(ActionEvent event) {
        dc.setTitle("Выбери каталог, в который будут сохранены файлы");
        dc.setInitialDirectory(new File(initialDirectory));
        File file = dc.showDialog(null);
        initialDirectory = file.getAbsolutePath();
        if (file != null && file.isDirectory()) {
            ivWorkCatalog.clear();
            ivWorkCatalog.appendText(file.getAbsolutePath());
        } else {
            ivWorkCatalog.clear();
            ivWorkCatalog.appendText("Это не папка");
        }

    }

    @FXML
    private void handleBtnOpenMultiFiles(ActionEvent event) {
        fc.setTitle("Выбери несколько файлов для работы");
        fc.setInitialDirectory(new File(initialDirectory));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XLS DOC PDF", "*.pdf ; *.xl*; *.doc*"),
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );
        List<File> selectedFiles = fc.showOpenMultipleDialog(null);
        lvFiles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (File selectedFile : selectedFiles) {
            if (selectedFiles != null) {
                lvFiles.getItems().add(selectedFile.getAbsoluteFile());


            } else {
                lvFiles.getItems().add("Ошибка ввода, повторить выбор");

            }
        }
        if (selectedFiles != null) {
            initialDirectory = selectedFiles.get(0).getParent();


        }

    }

    @FXML
    private void handleBtnOpenImgFile(ActionEvent event) {
        //errorLabel.setText("");
        fc.setTitle("Выбери картинку");
        fc.setInitialDirectory(new File(initialDirectory));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Изображение", "*.JPG; *.jpg ; *.gif; *.png*; *.bpm ; *.png; *.jpeg"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );
        File file = fc.showOpenDialog(null);
        initialDirectory = file.getAbsolutePath();
        if (file != null && file.isFile()) {
            imgFile.setImage(new Image(file.toURI().toString()));


        } else ;
        //errorLabel.setText("ERROR");
        //imgFile.setImage(new Image("empty.jpg"));
    }

    @FXML
    private void handleBtnConvertToPdf(ActionEvent event) {




    }

    @FXML
    private void handleBtnSignDoc(ActionEvent event) {
    }

    @FXML
    private void handleBtnOpenDir(ActionEvent event) {
    }

    @FXML
    private void handleBtnGenInventory(ActionEvent event) {
        //Получаем список файлов из директории
        // File path = new File(pathDir);
        File[] files = new File[lvFiles.getItems().size()];
        for (int i = 0; i < files.length; i++) {
            files[i] = (File) lvFiles.getItems().get(i);

        }
        //Создаем Эксель файл
        HSSFWorkbook myExcel = new HSSFWorkbook();
        //Создаем в файле лист
        HSSFSheet sheet = myExcel.createSheet();
        int rowNum = 0; //счетчик для строк
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Опись");
        row = sheet.createRow(++rowNum);
        row.createCell(0).setCellValue("№ п/п");
        row.createCell(1).setCellValue("Наименование документа");
        row.createCell(2).setCellValue("Кол-во страниц");
        //Зполняем файл";
        String nameFileOfDir = "";
        int i = 1;
        int pages = 0;
        PDDocument document;
        for (File s : files) {
            // pages = 111111;
            try {
                document = PDDocument.load(s);
                assert document != null;
                pages = document.getNumberOfPages();
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //удяляем расширение файла в наименовании файла
            nameFileOfDir = s.getName();
            int p = s.getName().lastIndexOf('.');
            if (p > 0) {
                nameFileOfDir = nameFileOfDir.substring(0, p);
            }
            if (nameFileOfDir.equals("opis")) continue;
            row = sheet.createRow(++rowNum);
            row.createCell(0).setCellValue(i++);
            row.createCell(1).setCellValue(nameFileOfDir);
            row.createCell(2).setCellValue(pages);
            //Записываем в файл
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(ivWorkCatalog.getText() + "\\opis.xls"))) {
            myExcel.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        statusMsg("Опись готова");

    }

    @FXML
    private void handleBtnClearFiles(ActionEvent event) {
        List<Integer> selectedItemsCopy = new ArrayList<>(lvFiles.getSelectionModel().getSelectedItems());
        lvFiles.getItems().removeAll(selectedItemsCopy);


    }

    public void statusMsg(String s) {
        statusLabel.setText("");
        statusLabel.setText(s);

    }

    public void convertDocPdf (String inputNameFile, String outputNameFile) {
        POIFSFileSystem fs = null;
        Document document = new Document();

        try {
            System.out.println("Starting the test");
            fs = new POIFSFileSystem(new FileInputStream(inputNameFile));

            //This class acts as the bucket that we throw all of the Word data structures into.
            HWPFDocument doc = new HWPFDocument(fs);

            WordExtractor we = new WordExtractor(doc);

            OutputStream file = new FileOutputStream(new File(outputNameFile));

            PdfWriter writer = PdfWriter.getInstance(document, file);

            Range range = doc.getRange();
            document.open();
            writer.setPageEmpty(true);
            document.newPage();
            writer.setPageEmpty(true);

            String[] paragraphs = we.getParagraphText();
            for (int i = 0; i < paragraphs.length; i++) {

                org.apache.poi.hwpf.usermodel.Paragraph pr = range.getParagraph(i);
                // CharacterRun run = pr.getCharacterRun(i);
                // run.setBold(true);
                // run.setCapitalized(true);
                // run.setItalic(true);
                paragraphs[i] = paragraphs[i].replaceAll("\\cM?\r?\n", "");
                System.out.println("Length:" + paragraphs[i].length());
                System.out.println("Paragraph" + i + ": " + paragraphs[i].toString());

                // add the paragraph to the document
                document.add(new Paragraph(paragraphs[i]));
            }

            System.out.println("Document testing completed");
        } catch (Exception e) {
            System.out.println("Exception during test");
            e.printStackTrace();
        } finally {
            // close the document
            document.close();
        }
    }


    public void convertDocPdf2  (String inputNameFile, String outputNameFile) {

        String k=null;
        OutputStream fileForPdf =null;
        try {

            String fileName= inputNameFile;
            //Below Code is for .doc file
            if(fileName.endsWith(".doc"))
            {
                HWPFDocument doc = new HWPFDocument(new FileInputStream(
                        fileName));
                WordExtractor we=new WordExtractor(doc);
                k = we.getText();

                fileForPdf = new FileOutputStream(new File(outputNameFile));
                we.close();
            }

            //Below Code for

            else if(fileName.endsWith(".docx"))
            {
                XWPFDocument docx = new XWPFDocument(new FileInputStream(
                        fileName));
                // using XWPFWordExtractor Class
                XWPFWordExtractor we = new XWPFWordExtractor(docx);
                k = we.getText();

                fileForPdf = new FileOutputStream(new File(
                        outputNameFile));
                we.close();
            }



            Document document = new Document();
            PdfWriter.getInstance(document, fileForPdf);

            document.open();

            document.add(new Paragraph(k));



            document.close();
            fileForPdf.close();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }







}

