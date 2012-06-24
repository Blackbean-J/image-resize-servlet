package com.soulgalore.servlet;

import java.io.File;

class Thumbnail {

	private final String imageFileName;
	private final String originalImageName;
	private final String originalImageNameWithExtension;
	private final String imageFileExtension;
	private final String imageDimensions;
	private final String generatedFilePath;
	
	/**
	 * Create a thumbnail. Note will not check the file format.
	 * 
	 * @param theFileName
	 */
	Thumbnail(String theFileName) {

		imageFileName = theFileName;
		originalImageName = imageFileName.substring(0,
				imageFileName.lastIndexOf("-"));
		imageFileExtension = imageFileName.substring(
				imageFileName.lastIndexOf("."), imageFileName.length());
		imageDimensions = imageFileName.substring(
				imageFileName.lastIndexOf("-") + 1,
				imageFileName.lastIndexOf("."));
		originalImageNameWithExtension = originalImageName + imageFileExtension;
		generatedFilePath = createFilePath();
	}

	/**
	 * Get the generated the file path, will always use the original filename, so that
	 * all sizes of one file end up in one directory.
	 * @return the path in the style of two dirs example /205/070/
	 */
	String getGeneratedFilePath() {
		return generatedFilePath;
	}
	
	
	String getImageDimensions() {
		return imageDimensions;
	}

	String getImageFileExtension() {
		return imageFileExtension;
	}

	String getImageFileName() {
		return imageFileName;
	}

	String getOriginalImageName() {
		return originalImageName;
	}

	String getOriginalImageNameWithExtension() {
		return originalImageNameWithExtension; 
	}
	
	/**
	 * Create the generated the file path, will always use the original filename, so that
	 * all sizes of one file end up in one directory.
	 * 
	 * @return the path in the style of two dirs example /205/070/
	 */
	private String createFilePath() {

		// setup the thumbs dir based on the original name, so that all sizes
		// are in the same working dir
		int hashcode = originalImageNameWithExtension.hashCode();

		StringBuilder path = new StringBuilder(File.separator);
		// first dir
		path.append(String.format("%03d", hashcode & 255));
		path.append(File.separator);
		// second dir
		path.append(String.format("%03d", (hashcode >> 8) & 255));
		path.append(File.separator);

		return path.toString();
	}

}
