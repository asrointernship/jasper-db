/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.io;

import java.io.*;
import java.util.Properties;

/**
 * A Class with static IO methods.
 *
 * @author Jasper Moeys
 */
public class StaticIO {

    private static String currentDirectory = "";

    private StaticIO() {
    }

    /**
     * Sets the current directory. This will not modify the current directory
     * used by the JVM, only for this class.
     *
     * @param directory The directory that files will be written to and read
     * from.
     * @throws IllegalArgumentException If the given String is not the path to a
     * directory.
     */
    public static void setCurrentDirectory(String directory) {
        if (directory == null) {
            directory = "";
        }
        File dir = new File(directory).getAbsoluteFile();
        if (dir.isDirectory()) {
            if (!directory.endsWith(File.separator)) {
                directory = directory + File.separator;
            }
            currentDirectory = directory;
        } else {
            throw new IllegalArgumentException("The given String is not the path to a directory.");
        }
    }

    /**
     * Returns the current directory.
     *
     * @return The directory that files will be written to and read from.
     */
    public static String getCurrentDirectory() {
        return currentDirectory;
    }

    /**
     * Returns a Properties object containing the configuration settings of the
     * application. The configuration settings are read from the file
     * 'socialmap.properties'.
     *
     * @return the Properties object, or null when the properties file doesn't
     * exist or can't be read
     */
    public static Properties getConfig() {
        Properties props = new Properties();
        try {
            InputStream in = new FileInputStream(new File(currentDirectory + "socialmap.properties"));
            props.load(in);
        } catch (IOException ex) {
            //Do nothing
        }
        return props;
    }

    public static void saveConfig(Properties properties) throws IOException {
        OutputStream out = new FileOutputStream(new File(currentDirectory + "socialmap.properties"));
        properties.store(out, null);
    }

    /**
     * This method returns the contents of a file as a String.
     *
     * @param filename The name of the file that has to be read.
     * @param inCurrentDirectory If false the file specified by filename will
     * not only be looked for in the {@link StaticIO#setCurrentDirectory(java.lang.String) current directory},
     * but treated as a normal relative or absolute path.
     * @return The contents of the file specified by filename
     * @throws IOException If something goes wrong while trying to read the
     * file.
     */
    public static String getFileContents(String filename, boolean inCurrentDirectory) throws IOException {
        FileInputStream inputStream = null;
        String res = null;
        try {
            File file = new File(inCurrentDirectory ? currentDirectory + filename : filename);
            int length;
            if (file.length() <= Integer.MAX_VALUE) {
                length = (int) file.length();
            } else {
                throw new IOException("The file is too big.");
            }
            byte[] bytes = new byte[length];
            inputStream = new FileInputStream(file);
            inputStream.read(bytes);
            res = new String(bytes);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return res;
    }

    /**
     * This method returns the contents of a file as a String.
     *
     * @param filename The name of the file that has to be read.
     * @return The contents of the file specified by filename
     * @throws IOException If something goes wrong while trying to read the
     * file.
     */
    public static String getFileContents(String filename) throws IOException {
        return getFileContents(filename, true);
    }

    /**
     * A method that checks whether a file exists.
     *
     * @param filename The name of the file that will be checked.
     * @param inCurrentDirectory If false the file specified by filename will
     * not only be looked for in the {@link StaticIO#setCurrentDirectory(java.lang.String) current directory},
     * but treated as a normal relative or absolute path.
     * @return True if the file exists, false if it doesn't.
     */
    public static boolean fileExists(String filename, boolean inCurrentDirectory) {
        File file = new File(inCurrentDirectory ? currentDirectory + filename : filename);
        return file.exists();
    }

    /**
     * A method that checks whether a file exists.
     *
     * @param filename The name of the file that will be checked.
     * @return True if the file exists, false if it doesn't.
     */
    public static boolean fileExists(String filename) {
        return fileExists(filename, true);
    }

    /**
     * A method that writes a String to a file.
     *
     * @param filename The name of the file that will be written to.
     * @param content The content that will be written to the file.
     * @param inCurrentDirectory If false the file specified by filename will
     * not only be looked for in the {@link StaticIO#setCurrentDirectory(java.lang.String) current directory},
     * but treated as a normal relative or absolute path.
     * @throws IOException If something goes wrong while trying to write to the
     * file.
     */
    public static void writeToFile(String filename, String content, boolean inCurrentDirectory) throws IOException {
        File file = new File(inCurrentDirectory ? currentDirectory + filename : filename);
        OutputStream output = null;
        try {
            output = new FileOutputStream(file);
            output.write(content.getBytes());
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    
    /**
     * A method that writes a String to a file.
     * 
     * @param filename The name of the file that will be written to.
     * @param content The content that will be written to the file.
     * @throws IOException If something goes wrong while trying to write to the file.
     */
    public static void writeToFile(String filename, String content) throws IOException {
        writeToFile(filename, content, true);
    }
    
    /**
     * A method that returns the absolute path to a file.
     * 
     * @param file The path to a file, relative to the {@link StaticIO#setCurrentDirectory(java.lang.String) current directory}.
     * @return The absolute path to the file.
     */
    public static String getPath(String file){
        return getCurrentDirectory() + file;
    }
}
