package init.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

public class SystemUtils2018 {

  public static final Logger logger = Logger.getLogger("com.funwork");

  private SystemUtils2018() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * 將檔案轉成Blob.
   */
  public static Blob fileToBlob(String imageFileName) throws IOException, SQLException {
    File imageFile = new File(imageFileName);
    long size = imageFile.length();
    byte[] b = new byte[(int) size];
    int currentBytesRead = 0;
    int totalBytesRead = 0;
    SerialBlob sb = null;
    try (FileInputStream fis = new FileInputStream(imageFile);) {
      while ((currentBytesRead = fis.read(b)) > 0) {
        totalBytesRead += currentBytesRead;
      }
      sb = new SerialBlob(b);
      logger.log(Level.ALL, "totalBytesRead:{0}", totalBytesRead);
    }
    return sb;
  }

  /**
   * 將Blob轉成檔案.
   */
  public static Blob fileToBlob(InputStream is, long size) throws IOException, SQLException {
    byte[] b = new byte[(int) size];
    SerialBlob sb = null;
    int currentBytesRead = 0;
    int totalBytesRead = 0;
    while ((currentBytesRead = is.read(b)) > 0) {
      totalBytesRead += currentBytesRead;
    }
    sb = new SerialBlob(b);
    logger.log(Level.ALL, "totalBytesRead:{0}", totalBytesRead);
    return sb;
  }

  /**
   * 將檔案轉成Clob.
   */
  public static Clob fileToClob(String textFileName) throws IOException, SQLException {
    Clob clob = null;
    try (InputStreamReader isr = new InputStreamReader(new FileInputStream(textFileName), 
         StandardCharsets.UTF_8);) {
      char[] c = new char[8192];
      StringBuilder buf = new StringBuilder();
      int len = 0;
      while ((len = isr.read(c)) != -1) {
        buf.append(new String(c, 0, len));
      }
      char[] ca = buf.toString().toCharArray();
      clob = new SerialClob(ca);
    }
    return clob;
  }

  /**
   * 將Clob轉成檔案.
   */
  public static void clobToFile(Clob clob, File file, String encoding) 
      throws IOException, SQLException {
    try (Reader reader = clob.getCharacterStream();
        BufferedReader br = new BufferedReader(reader);
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, encoding);
        PrintWriter out = new PrintWriter(osw);) {
      String line = null;
      while ((line = br.readLine()) != null) {
        out.println(line);
      }
    }
  }

  public static String extractFileName(String pathName) {
    return pathName.substring(pathName.lastIndexOf('/') + 1);
  }
}
