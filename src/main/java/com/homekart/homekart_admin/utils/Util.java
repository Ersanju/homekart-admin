package com.homekart.homekart_admin.utils;

public class Util {

    /**
     * Converts Google Drive sharing links to direct links usable for image loading.
     */
    public static String convertGoogleDriveLink(String url) {
        if (url == null || url.isEmpty()) return url;

        String fileId = null;

        try {
            if (url.contains("drive.google.com/file/d/")) {
                // Format: https://drive.google.com/file/d/FILE_ID/view?usp=sharing
                int start = url.indexOf("/d/") + 3;
                int end = url.indexOf("/", start);
                fileId = url.substring(start, end);
            } else if (url.contains("drive.google.com/open?id=")) {
                // Format: https://drive.google.com/open?id=FILE_ID
                int start = url.indexOf("id=") + 3;
                fileId = url.substring(start);
            } else if (url.contains("drive.google.com/uc?id=")) {
                // Format: https://drive.google.com/uc?id=FILE_ID&export=download
                int start = url.indexOf("id=") + 3;
                int end = url.indexOf("&", start);
                if (end != -1) {
                    fileId = url.substring(start, end);
                } else {
                    fileId = url.substring(start);
                }
            }
        } catch (Exception e) {
            return url;
        }

        if (fileId != null) {
            return "https://drive.google.com/uc?export=view&id=" + fileId;
        } else {
            return url;
        }
    }

    // You can add more utility methods here as needed
}
