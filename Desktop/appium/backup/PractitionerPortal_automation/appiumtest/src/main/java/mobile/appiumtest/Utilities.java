package mobile.appiumtest;

import java.io.*;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.*;


public class Utilities {

    private static final Logger logger = getLogger(Utilities.class);

    private Utilities(){}

    public static <T> String getResourceFilePath(Class<T> getClassObject, String fileName) {
        String absoluteFile = "";
        ClassLoader classLoader = getClassObject.getClassLoader();

        absoluteFile = classLoader.getResource(fileName).getFile();

        return absoluteFile;
    }

    public static JsonElement readJsonFile(String filePath) {
        JsonParser parser = new JsonParser();
        JsonElement result = null;
        try {
            result = parser.parse(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            logger.info(e.getMessage(),e);
        }
        return result;
    }

    public static <T> T deserialize(JsonElement json, Type clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    public static <T> T deserialize(String json, Type clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    public static <T> T load(String sourceFile, String caseName, Class<T> clazz) {
        JsonElement jsonData = readJsonFile(sourceFile).getAsJsonObject().get(caseName);

        return deserialize(jsonData, clazz);
    }

    public static JsonObject objectToJson(Object object) {
        Gson gson = new Gson();
        return gson.toJsonTree(object).getAsJsonObject();
    }

    public static JsonObject stringToJson(String jsonStr) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, JsonObject.class);
    }

    public static JsonArray objectToJsonArray(Object object) {
        Gson gson = new Gson();
        return gson.toJsonTree(object).getAsJsonArray();
    }

    public static String readFile(String filePath) {
        StringBuilder out = new StringBuilder();
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        String encoding = "Utf-8";
        File file = new File(filePath);
        try {

            if (file.isFile() && file.exists()) {
                InputStreamReader in = null;

                in = new InputStreamReader(new FileInputStream(file), encoding);

                for (; ; ) {
                    int count = 0;
                    count = in.read(buffer, 0, buffer.length);
                    if (count < 0)
                        break;
                    out.append(buffer, 0, count);
                }
                in.close();
            } else {
                logger.info("Can not find the file");
            }
        } catch (Exception e) {
            logger.info(e.getMessage(),e);
        }

        return out.toString();
    }

    public static void writeFile(String filePath, String fileCon) {
        File file = new File(filePath);
        writeFile(file, fileCon);
    }

    public static void writeFile(File file, String fileCon) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWritter = new FileWriter(file.getAbsolutePath(), false);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(fileCon);
            bufferWritter.close();

        } catch (IOException e) {
            logger.info(e.getMessage(),e);
        }
    }

    public static void deleteFile(String filePath) {
        try {
            if (filePath != null) {
                File file = new File(filePath);
                if (file.exists() && file.isFile()) {
                    file.delete();
                }
            }
        } catch (Exception e) {
            logger.info(e.getMessage(),e);
        }
    }

    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            logger.info(e.getMessage(),e);
        }
    }

    public static String formatCurrentTime() {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        return format.format(new Date());
    }

    public static long random(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;

        return s;
    }

    public static Logger getLogger(Class<?> clazz) {

        return LoggerFactory.getLogger(clazz);

    }

}
