package com.finaldesign.system.util;

public class SystemUtil {
	private SystemUtil() {

	}

	public static String formatBase64Image(String base64Image) {
		String[] imagePre = new String[] { "data:image/png;base64,", "data:image/jpeg;base64," };
		for (String imagePreItem : imagePre) {
			base64Image = base64Image.replaceAll(imagePreItem, "");
		}
		return base64Image;
	}
}
