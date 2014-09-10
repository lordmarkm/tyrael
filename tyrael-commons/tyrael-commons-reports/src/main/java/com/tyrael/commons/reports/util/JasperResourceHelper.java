package com.tyrael.commons.reports.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

/**
 * Tools for JasperReport PDF generation. Keep it short and simple.
 *
 * @author Frank Lloyd Teh
 */
public final class JasperResourceHelper {

    private static final Logger LOG = LoggerFactory.getLogger(JasperResourceHelper.class);
    private static final JasperResourceHelper JASPER_RESOURCE_HELPER = new JasperResourceHelper();

    private JasperResourceHelper() {
    }

    //for unit test
    public static JasperResourceHelper getSingleton() {
        return JASPER_RESOURCE_HELPER;
    }

    /**
     * Same as below, but with file name same as template name.
     *
     * @param filename   ?
     * @param params     ?
     * @param collection ?
     * @return ?
     */
    public static ResponseEntity<byte[]> exportToPdfResponseEntity(String filename, Map<String, Object> params, Collection<?> collection) {
        return exportToPdfResponseEntity(filename, filename, params, collection);
    }

    /**
     * Same as below, but with file name same as template name.
     *
     * @param filename   ?
     * @param params     ?
     * @param connection ?
     * @return ?
     */
    public static ResponseEntity<byte[]> exportToPdfResponseEntity(
        String filename, Map<String, Object> params,
        Connection connection) {

        return exportToPdfResponseEntity(filename, filename, params, connection);
    }

    /**
     * Same as below, but with file name same as template name.
     *
     * @param filename   ?
     * @param params     ?
     * @param connection ?
     * @return ?
     */
    public static ResponseEntity<byte[]> exportToXlsResponseEntity(
            String filename, Map<String, Object> params,
            Connection connection) {

        return exportToXlsResponseEntity(filename, filename, params, connection);
    }

    /**
     * Creates a ResponseEntity containing a PDF.
     *
     * @param templateName - Name of jasper template to use.
     * @param filename     - JasperReport file
     * @param params       - parameters to be passed to JasperReport
     * @param collection   - JasperReport dataSource
     * @return ResponseEntity containing the PDF in byte [] form.
     */
    public static ResponseEntity<byte[]> exportToPdfResponseEntity(
        String templateName, String filename,
        Map<String, Object> params, Collection<?> collection) {
        try {
            JasperPrint jasperPrint = createJasperPrint(templateName, params, collection);
            return createResponseEntity(filename, jasperPrint);
        } catch (Exception e) {
            LOG.error("Error generating pdf", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a ResponseEntity containing a PDF.
     *
     * @param templateName - Name of jasper template to use.
     * @param filename     - JasperReport file
     * @param params       - parameters to be passed to JasperReport
     * @param connection   - DB connection
     * @return ResponseEntity containing the PDF in byte [] form.
     */
    public static ResponseEntity<byte[]> exportToPdfResponseEntity(
        String templateName, String filename,
        Map<String, Object> params, Connection connection) {
        try {
            JasperPrint jasperPrint = createJasperPrint(templateName, params, connection);
            return createResponseEntity(filename, jasperPrint);
        } catch (Exception e) {
            LOG.error("Error generating pdf", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a ResponseEntity containing a PDF.
     *
     * @param templateName - Name of jasper template to use.
     * @param filename     - JasperReport file
     * @param params       - parameters to be passed to JasperReport
     * @param connection   - DB connection
     * @return ResponseEntity containing the PDF in byte [] form.
     */
    public static ResponseEntity<byte[]> exportToXlsResponseEntity(
            String templateName, String filename,
            Map<String, Object> params, Connection connection) {
        try {
            JasperPrint jasperPrint = createJasperPrint(templateName, params, connection);
            return createXlsResponseEntity(filename, jasperPrint);
        } catch (Exception e) {
            LOG.error("Error generating pdf", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Merges Reports and returns PDF in a byte array.
     *
     * @param jasperPrints - List of Jasper Prints to be merged.
     * @param filename     - File name of the resulting PDF
     * @return Byte array containing PDF.
     */
    public static ResponseEntity<byte[]> exportToPdfResponseEntity(List<JasperPrint> jasperPrints, String filename) {
        try {

            return createResponseEntity(filename, merge(jasperPrints));
        } catch (Exception e) {
            LOG.error("Error generating pdf", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Merges Reports and returns a file.
     *
     * @param jasperPrints     - List of jasper prints to be merged
     * @param absoluteFilename - absolute filename
     * @return java.io.File
     * @throws JRException - jasper exception
     */
    public static File exportToPdfFile(List<JasperPrint> jasperPrints, String absoluteFilename) throws JRException {
        JasperExportManager.exportReportToPdfFile(merge(jasperPrints), absoluteFilename);
        return new File(absoluteFilename);
    }

    private static JasperPrint merge(List<JasperPrint> jasperPrints) {
        JasperPrint jrPrint = new JasperPrint();
        JasperPrint firstJasperPrint = jasperPrints.get(0);
        jrPrint.setOrientation(firstJasperPrint.getOrientationValue());
        jrPrint.setLocaleCode(firstJasperPrint.getLocaleCode());
        jrPrint.setPageHeight(firstJasperPrint.getPageHeight());
        jrPrint.setPageWidth(firstJasperPrint.getPageWidth());
        jrPrint.setTimeZoneId(firstJasperPrint.getTimeZoneId());
        jrPrint.setName(firstJasperPrint.getName());

        for (JasperPrint jasperPrint : jasperPrints) {
            for (JRPrintPage jrPrintPage : jasperPrint.getPages()) {
                jrPrint.addPage(jrPrintPage);
            }
        }
        return jrPrint;
    }

    /**
     * Creates a {@link JasperPrint}.
     *
     * @param templateName - Jasper File
     * @param params       - Params
     * @param collection   - DataSource
     * @return {@link JasperPrint}
     * @throws JRException - ?
     */
    public static JasperPrint createJasperPrint(
        String templateName, Map<String, Object> params,
        Collection<?> collection) throws JRException {
        JasperReport jasperReport = loadJasper(templateName);
        JRDataSource dataSource = new JRBeanCollectionDataSource(collection);
        return JasperFillManager.fillReport(jasperReport, params, dataSource);
    }

    /**
     * Creates a {@link JasperPrint}.
     *
     * @param templateName - Jasper File
     * @param params       - Params
     * @param connection   - DB connection
     * @return JasperPrint - ?
     * @throws JRException - ?
     */
    public static JasperPrint createJasperPrint(String templateName, Map<String, Object> params, Connection connection) throws JRException {
        JasperReport jasperReport = loadJasper(templateName);
        return JasperFillManager.fillReport(jasperReport, params, connection);
    }

    private static ResponseEntity<byte[]> createResponseEntity(String filename, JasperPrint jasperPrint) throws JRException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("content-disposition", "inline; filename=" + filename + ".pdf");
        headers.set("Pragma", "no-cache");
        headers.set("Cache-Control", "no-cache");

        outputStream.flush();
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    private static ResponseEntity<byte[]> createXlsResponseEntity(String filename, JasperPrint jasperPrint)
        throws JRException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String destDirStr = "reports-jasper/output/";
        String destFilename = destDirStr + jasperPrint.getName();
        File destDir = new File(destDirStr);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT,
                jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                destFilename);
        // export to xls file
        exporter.exportReport();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
        headers.add("content-disposition", "inline; filename=" + jasperPrint.getName() + ".xls");
        headers.set("Pragma", "no-cache");
        headers.set("Cache-Control", "no-cache");

        FileInputStream fis = new FileInputStream(new File(destFilename));
        // send exported xls file to outputStream
        IOUtils.copy(fis, outputStream);

        outputStream.flush();
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    private static JasperReport loadJasper(String filename) throws JRException {
        try {
            InputStream is;
            String filenameL = filename;
            if (!StringUtils.endsWithIgnoreCase(filenameL, ".jasper")) {
                filenameL += ".jasper";
            }
            Resource jasperResource = new ClassPathResource(filenameL);
            if (jasperResource.exists()) {
                is = jasperResource.getInputStream();
            } else {
                is = FileUtils.openInputStream(new File(filenameL));
            }
            return (JasperReport) JRLoader.loadObject(is);
        } catch (IOException e) {
            throw new JRException(e);
        }
    }

}
