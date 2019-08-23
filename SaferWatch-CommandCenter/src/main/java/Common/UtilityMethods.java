/*************************************** PURPOSE **********************************

 - This class contains all Generic methods which are not related to specific application
 */

package Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.log4j.Logger;

public class UtilityMethods {

	static ConfigManager sys = new ConfigManager();
	static Logger log = Logger.getLogger("UtilityMethods");
	static Thread thread;
	static FileInputStream fileInputStream;
	static Workbook workbook;
	static Sheet sheet;
	static String inputData[][];
	static Map<String, String> data = new HashMap<String, String>();
	static int getRow;
	static int getColumn;

	 
	/**
	 * Purpose - to get the system name
	 * 
	 * @return
	 * @return
	 * @return - String (returns system name)
	 * @throws IOException
	 * @throws BiffException
	 */

	public static String machineName() {
		String sComputername = null;
		try {
			sComputername = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			log.error("Unable to get the hostname..." + e.getCause());
		}
		return sComputername;
	}

	/**
	 * TODO To get the entire exception stack trace
	 * 
	 * @return returns the stack trace
	 */
	public static String getStackTrace() {
		String trace = "";
		int i;
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		for (i = 2; i < stackTrace.length; i++) {
			trace = trace + "\n" + stackTrace[i];
		}
		return trace;
	}

	/**
	 * Purpose - to get current date and time
	 * 
	 * @return - String (returns date and time)
	 */
	public static String getCurrentDateTime() {
		DateFormat formatter = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss");
		Date date = new Date();
		String todayDate = formatter.format(date);
		String dateNow = todayDate.replaceAll("/", "_").replaceAll(":", "_");
		return dateNow;
	}

	/**
	 * Purpose - To convert given time in "yyyy-MM-dd-HH:mm:ss" to IST time
	 * 
	 * @returns date in String format
	 * @throws Exception
	 */

	public static String convertToISTTime(String origTime) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		TimeZone obj = TimeZone.getTimeZone("GMT");
		formatter.setTimeZone(obj);
		try {
			Date date = formatter.parse(origTime);
			formatter = new SimpleDateFormat("dd-MMM-yyyy:HH.mm.ss");
			// System.out.println(date);
			return formatter.format(date);
		} catch (ParseException e) {
			log.error("Cannot parse given date .." + origTime);
			log.info("returning current date and time .." + origTime);
		}
		return getCurrentDateTime();
	}

	/**
	 * Purpose - to generate the random number which will be used while saving a
	 * screenshot
	 * 
	 * @return - returns a random number
	 */
	public static int getRandomNumber() {
		Random rand = new Random();
		int numNoRange = rand.nextInt();
		return numNoRange;
	}

	/**
	 * 
	 * TODO Gives the name of operating system your are currently working on
	 * 
	 * @return returns the OS name
	 */
	public static String getOSName() {
		return System.getProperty("os.name");

	}

	/**
	 * 
	 * TODO Gives the seperator value according to Operation System
	 * 
	 * @return returns the seperator with respect to Operation System
	 */
	public static String getFileSeperator() {
		return System.getProperty("file.separator");
	}

	public static enum Mode {
		ALPHA, ALPHANUMERIC, NUMERIC, SYMBOL
	}

	public static String generateRandomString(int length, Mode mode) {

		StringBuffer buffer = new StringBuffer();
		String characters = "";

		switch (mode) {

		case ALPHA:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;

		case ALPHANUMERIC:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			break;

		case NUMERIC:
			characters = "1234567890";
			break;
		case SYMBOL:
			characters = "!@#$%^&*()";
			break;
		}

		int charactersLength = characters.length();

		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		return buffer.toString();
	}

	/**
	 * This method is used to store all the file in particular Directory.
	 * 
	 * @return All File Names from Directory.
	 */

	public static ArrayList<String> getAllFilesFromFolder(String folderName) {
		ArrayList<String> totalFileName = new ArrayList<String>();
		int fileIndex;
		String fileName = null;

		try {
			String counterFilePath = CommonConstants.INPUT_FOLDER_PATH + CommonConstants.FILE_SEPARATOR + folderName;
			File folderPath = new File(counterFilePath);
			if (folderPath.isDirectory()) {
				log.info("Counter Folder path :" + folderPath);
				File[] totalFiles = folderPath.listFiles();
				if (totalFiles.length > 0) {
					for (fileIndex = 0; fileIndex < totalFiles.length; fileIndex++) {
						if (totalFiles[fileIndex].isFile()) {
							fileName = totalFiles[fileIndex].getName();
							totalFileName.add(fileName);
						}
					}
				} else {
					log.info("No files are available to read.");
				}
			} else {
				log.info("Folder doesnot exist.");
			}
		} catch (Exception e) {

			e.printStackTrace();
			log.info("Unable to read Filders and files.");
		}
		return totalFileName;
	}

	/**
	 * This method is used to split string on basis of delimiter/regular expression.
	 * 
	 * @param string    for Split.
	 * @param seperator on which condition separate string.
	 * @param index     to split string.
	 * @return the array of strings computed by splitting this string around matches
	 *         of the given regular expression
	 */

	public static String splitString(String string, String seperator, int index) {
		String StringAfterSplit[] = null;
		try {
			StringAfterSplit = string.split(seperator);
		} catch (Exception e) {
			log.info("Unable to Split String.");// TODO: handle exception
			e.printStackTrace();
		}
		return StringAfterSplit[index];
	}

	/**
	 * Used to convert String value to integer.
	 * 
	 * @param str
	 * @return
	 */

	public static int convertStringToInt(String targetString) {
		int result = 0;
		try {
			targetString = targetString.replaceAll("[^\\d.]", "");
			result = Integer.parseInt(targetString);

		} catch (Exception e) {
			// TODO: handle exception
			log.error("Unable to convert string");
		}
		return result;
	}

}