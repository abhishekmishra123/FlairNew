/*************************************** PURPOSE **********************************

 - This class contains all methods related to HTML reporting
 */
package Common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import org.apache.log4j.Logger;
import org.testng.Assert;


public class ReportSetup {
	private static Logger log = Logger.getLogger(ReportSetup.class);
	private static boolean isDirCreated = true;
	private static String message;
	public static ConfigManager config = new ConfigManager();
	public static String currentDateTime;
	

	/**
	 * This method setup's reporting environment i.e., creating a root folder
	 * and destination folder for storing report information
	 */
	public static void createFolderStructure() {
		createReportsFolder();
		createLatestResultsFolder();
		createMediaFolders();
	}
	
	public static void dateTime()
	{
		currentDateTime=UtilityMethods.getCurrentDateTime();
	}

	/**
	 * This method creates 'Automation Reports' directory if it does not exist
	 */
	public static void createReportsFolder() {
		File file = new File(getReportsPath());
		if (!file.exists()) {
			isDirCreated = file.mkdir();
		}
		if (!isDirCreated) {
			message = "\n Exception occured while creating 'Automation Results' directory";
			log.error("Check folder permissions of Project Directory..."
					+ message);
			Assert.fail("Check folder permissions of Project Directory..."
					+ message);
		}
	}

	/**
	 * This method creates 'Latest Reports' directory if it does not exist if
	 * directory exists it renames to Results_on_<currentDataTime> folder name
	 * and creates 'Latest Reports' directory
	 */
	public static void createLatestResultsFolder() {
		try {
			File latestResults = new File(getLatestResultsPath());
			if (latestResults.exists()) {
				Path p = Paths.get(getLatestResultsPath());
				BasicFileAttributes view;

				view = Files.getFileAttributeView(p,
						BasicFileAttributeView.class).readAttributes();
				String fCreationTime = view.creationTime().toString();
				String istTime = UtilityMethods.convertToISTTime(fCreationTime
						.split("\\.")[0].replace("T", "-"));
				String oldFolder = getReportsPath() + CommonConstants.FILE_SEPARATOR
						+ "Results_on_" + istTime.replace(":", "_at_");
				File oldResults = new File(oldFolder);
				latestResults.renameTo(oldResults);

			}
			isDirCreated = latestResults.mkdir();
			if (!isDirCreated) {
				message = "\n Exception occured while creating 'Latest Results' directory";
				log.error("Check folder permissions of Project Directory..."
						+ message);
				Assert.fail("Check folder permissions of Project Directory..."
						+ message);
			}
		} catch (IOException e) {
			log.error("Exception occured while creating 'Latest Results' directory or unable to rename current 'Latest Results' directory "
					+ e.getCause());
			Assert.fail("Exception occured while creating 'Latest Results' directory or unable to rename current 'Latest Results' directory "
					+ e.getCause());
		}
	}

	/**
	 * This method creates 'videos, screenshots' directory if they does not
	 * exist
	 */
	public static void createMediaFolders() {
		File imagesFolder = new File(getPassImagesPath());
		if (!imagesFolder.exists()) {
			isDirCreated = imagesFolder.mkdir();
		}
		if (!isDirCreated) {
			message = "\n Exception occured while creating 'Latest Results/screenshots' directory";
			log.error("Check folder permissions of Project Directory..."
					+ message);
			Assert.fail("Check folder permissions of Project Directory..."
					+ message);
		}
	}

	/**
	 * @return - This method returns path to the folder where screenshots are
	 *         stored
	 */
	public static String getImagesPath()

	{
		String screenShotPath = getLatestResultsPath() + CommonConstants.FILE_SEPARATOR
				+ "Screenshots";
		File file1 = new File(screenShotPath);
		if (!file1.exists()) {
			file1.mkdir();
		}
		return getLatestResultsPath() + CommonConstants.FILE_SEPARATOR + "Screenshots";
	}

	/**
	 * @return - These methods returns path to the folder where screenshots are
	 *         stored
	 */
	public static String getPassImagesPath() {
		return setReportPath() + CommonConstants.FILE_SEPARATOR + "PassScreenshots";
	}

	public static String getFailurImagesPath() {
		return setReportPath() + CommonConstants.FILE_SEPARATOR + "FailureScreenshots";
	}

	public static String setReportPath() {
		return getLatestResultsPath() + CommonConstants.FILE_SEPARATOR + "SaferWatch Command Center";
	}

	/**
	 * @return - These methods returns path to the folder where latest results are
	 *         stored
	 */
	public static String getLatestResultsPath() {
		
		return getReportsPath() + CommonConstants.FILE_SEPARATOR + currentDateTime +"_"+config.getProperty("Browser.Name");
	}

	public static String getDownloadPath() {
		return CommonConstants.USER_DIR_PATH + CommonConstants.FILE_SEPARATOR + "Downloaded Files";
	}
	
	/**
	 * @return - This method returns the path to the root of reports folder
	 */
	public static String getReportsPath() {

		return CommonConstants.USER_DIR_PATH + CommonConstants.FILE_SEPARATOR
				+ "Automation Reports";
	}

}
