package glass.aisglass.Excel;

import glass.aisglass.db.DeliveryDao;
import glass.aisglass.models.Delivery;
import javafx.collections.ObservableList;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public final class Excel {

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public static void export() {
        new Thread(Excel::setDataForExport).start();
    }

    public static void setDataForExport() {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Employees sheet");

            ObservableList<Delivery> deliveries = DeliveryDao.getAll();

            int rownum = 0;
            Cell cell;
            Row row;

            HSSFCellStyle style = createStyleForTitle(workbook);

            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue("Delivery Number");
            cell.setCellStyle(style);

            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue("Delivery Price");
            cell.setCellStyle(style);

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Delivery Address");
            cell.setCellStyle(style);

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Delivery Description");
            cell.setCellStyle(style);

            for (Delivery delivery : deliveries) {
                rownum++;
                row = sheet.createRow(rownum);

                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(delivery.getNumber_of_delivery());

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(delivery.getPrice_of_delivery());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(delivery.getAddress_of_delivery());

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(delivery.getDescription_of_delivery());
            }

            File file = new File("output/delivery.xls");

            FileOutputStream outFile = new FileOutputStream(file);
            workbook.write(outFile);
            System.out.println("Created file: " + file.getAbsolutePath());
            outFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
