package com.company.Convert;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ConvertTools {
    private static String[] ACCEPTABLE_IMAGE_FILE_FORMATS = new String[]{"png", "jpg", "jpeg", "gif", "bmp"};
    private static String[] ACCEPTABLE_EPD_FILE_FORMATS = new String[]{"epd"};
    private static String[] ACCEPTABLE_HEADER_FILE_FORMATS = new String[]{"h"};

    public ConvertTools() {
    }

    public static boolean isGray(BufferedImage img) {
        for (int y = 0; y < img.getHeight(); ++y) {
            for (int x = 0; x < img.getWidth(); ++x) {
                int rgb = img.getRGB(x, y);
                int r = (16711680 & rgb) >> 16;
                int g = ('\uff00' & rgb) >> 8;
                int b = 255 & rgb;
                if (r != g || g != b) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isImageFile(File f) {
        String[] acceptableExtensions = ACCEPTABLE_IMAGE_FILE_FORMATS;
        String fileName = f.getName();
        int extPos = fileName.lastIndexOf(".");
        String ext = fileName.substring(extPos + 1, fileName.length());

        for (int i = 0; i < acceptableExtensions.length; ++i) {
            if (ext.equalsIgnoreCase(acceptableExtensions[i])) {
                System.out.println(ext);
                return true;
            }
        }

        return false;
    }

    public static boolean isEpdFile(File f) {
        String[] acceptableExtensions = ACCEPTABLE_EPD_FILE_FORMATS;
        String fileName = f.getName();
        int extPos = fileName.lastIndexOf(".");
        String ext = fileName.substring(extPos + 1, fileName.length());

        for (int i = 0; i < acceptableExtensions.length; ++i) {
            if (ext.equalsIgnoreCase(acceptableExtensions[i])) {
                System.out.println(ext);
                return true;
            }
        }

        return false;
    }

    public static boolean isHeaderFile(File f) {
        String[] acceptableExtensions = ACCEPTABLE_HEADER_FILE_FORMATS;
        String fileName = f.getName();
        int extPos = fileName.lastIndexOf(".");
        String ext = fileName.substring(extPos + 1, fileName.length());

        for (int i = 0; i < acceptableExtensions.length; ++i) {
            if (ext.equalsIgnoreCase(acceptableExtensions[i])) {
                System.out.println(ext);
                return true;
            }
        }

        return false;
    }

    public static int[] toIntArray(BufferedImage bi) {
        int[] data = new int[bi.getWidth() * bi.getHeight()];
        int i = 0;

        for (int y = 0; y < bi.getHeight(); ++y) {
            for (int x = 0; x < bi.getWidth(); ++x) {
                data[i++] = 255 & bi.getRGB(x, y);
            }
        }

        return data;
    }

    public static int[] toIntArray(byte[] imageBytes) {
        int[] ints = new int[imageBytes.length];

        for (int i = 0; i < imageBytes.length; ++i) {
            ints[i] = imageBytes[i];
        }

        return ints;
    }

    public static byte[] toByteArray(BufferedImage bi) {
        return ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
    }

    public static byte[] toByteArray(int[] intArray) {
        byte[] bytes = new byte[intArray.length];

        for (int i = 0; i < intArray.length; ++i) {
            bytes[i] = (byte) (intArray[i] & 255);
        }

        return bytes;
    }

    public static BufferedImage toBufferedImage(int[] imageIntArray, int imageWidth, int imageHeight) {
        BufferedImage bi = new BufferedImage(imageWidth, imageHeight, 1);

        int i;
        int y;
        for (i = 0; i < imageIntArray.length; ++i) {
            y = imageIntArray[i];
            imageIntArray[i] = y | y << 8 | y << 16;
        }

        i = 0;

        for (y = 0; y < imageHeight; ++y) {
            for (int x = 0; x < imageWidth; ++x) {
                bi.setRGB(x, y, imageIntArray[i++]);
            }
        }

        return bi;
    }

    public static BufferedImage scaleImage(BufferedImage bI, double scaleX, double scaleY) {
        AffineTransform tx = new AffineTransform();
        tx.scale(scaleX, scaleY);
        AffineTransformOp op = new AffineTransformOp(tx, 2);
        return op.filter(bI, (BufferedImage) null);
    }

    public static BufferedImage scaleImage(BufferedImage img, int maxWidth, int maxHeight) {
        int Wi = img.getWidth();
        int Hi = img.getHeight();
        if (Wi == maxWidth && Hi == maxHeight) {
            return img;
        } else {
            if (Wi > maxWidth || Hi > maxHeight) {
                double Ri = (double) Wi / (double) Hi;
                double Rp = (double) maxWidth / (double) maxHeight;
                double scaleFactor;
                if (Ri >= Rp) {
                    scaleFactor = (double) maxWidth / (double) Wi;
                } else {
                    scaleFactor = (double) maxHeight / (double) Hi;
                }

                img = scaleImage(img, scaleFactor, scaleFactor);
            }

            Wi = img.getWidth();
            Hi = img.getHeight();
            BufferedImage centeredImage = new BufferedImage(maxWidth, maxHeight, img.getType());
            Graphics g = centeredImage.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, centeredImage.getWidth(), centeredImage.getHeight());
            g.drawImage(img, (maxWidth - Wi) / 2, (maxHeight - Hi) / 2, Wi, Hi, (ImageObserver) null);
            g.dispose();
            return centeredImage;
        }
    }

    public static int[] downsampleTo1bitGrayScale(int[] imageIntArray) {
        for (int i = 0; i < imageIntArray.length; ++i) {
            if (imageIntArray[i] <= 127) {
                imageIntArray[i] = 255;
            } else {
                imageIntArray[i] = 0;
            }
        }

        return imageIntArray;
    }

    public static int[] downsampleTo1bitGrayScale_Inverted(int[] imageIntArray) {
        for (int i = 0; i < imageIntArray.length; ++i) {
            if (imageIntArray[i] <= 127) {
                imageIntArray[i] = 0;
            } else {
                imageIntArray[i] = 255;
            }
        }

        return imageIntArray;
    }

    public static int[] downsampleTo4bitGrayScale(int[] imageIntArray) {
        for (int i = 0; i < imageIntArray.length; ++i) {
            for (int k = 0; k <= 15; ++k) {
                if (imageIntArray[i] >= 16 * k && imageIntArray[i] < 16 * (k + 1)) {
                    imageIntArray[i] = k;
                }
            }
        }

        return imageIntArray;
    }

    public static BufferedImage downsampleTo8bitGrayScale(BufferedImage bi) {
        if (isGray(bi)) {
            return bi;
        } else {
            BufferedImage gray = new BufferedImage(bi.getWidth(), bi.getHeight(), 10);
            Graphics gr = gray.getGraphics();
            gr.drawImage(bi, 0, 0, (ImageObserver) null);
            gr.dispose();
            return gray;
        }
    }

    public static int[] upsampleTo8bitGrayScale(int[] imageIntArray) {
        for (int i = 0; i < imageIntArray.length; ++i) {
            imageIntArray[i] = (int) (255.0D * ((double) imageIntArray[i] / 15.0D));
        }

        return imageIntArray;
    }

    public static byte[] stringToBytes(String no) {
        byte[] number = new byte[no.length() / 2];
        int length = no.length();
        if (length % 2 == 1) {
            no = "0" + no;
            ++length;
        }

        for (int i = 0; i < no.length(); i += 2) {
            int j = Integer.parseInt(no.substring(i, i + 2), 16);
            number[i / 2] = (byte) (j & 255);
        }

        return number;
    }

    public static byte[] hexStringToBytes(String hex) {
        int len = hex.length();
        byte[] value = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            value[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
        }

        return value;
    }

    public static byte hexCharToByte(char ch) {
        byte value = -1;
        switch (ch) {
            case '0':
                value = 0;
                break;
            case '1':
                value = 1;
                break;
            case '2':
                value = 2;
                break;
            case '3':
                value = 3;
                break;
            case '4':
                value = 4;
                break;
            case '5':
                value = 5;
                break;
            case '6':
                value = 6;
                break;
            case '7':
                value = 7;
                break;
            case '8':
                value = 8;
                break;
            case '9':
                value = 9;
            case ':':
            case ';':
            case '<':
            case '=':
            case '>':
            case '?':
            case '@':
            default:
                break;
            case 'A':
                value = 10;
                break;
            case 'B':
                value = 11;
                break;
            case 'C':
                value = 12;
                break;
            case 'D':
                value = 13;
                break;
            case 'E':
                value = 14;
                break;
            case 'F':
                value = 15;
        }

        return value;
    }

    public static String bytesToHexString(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        byte[] var7 = digest;
        int var6 = digest.length;

        for (int var5 = 0; var5 < var6; ++var5) {
            byte b = var7[var5];
            sb.append("0x");
            sb.append(String.format("%1$02X", b));
            sb.append(", ");
            ++i;
            if (i >= 32) {
                i = 0;
                sb.append("\n");
            }
        }

        String hexString;
        if (i == 0) {
            hexString = sb.substring(0, sb.length() - 3);
        } else {
            hexString = sb.substring(0, sb.length() - 2);
        }

        return hexString;
    }

    public static BufferedImage getImageFromFile(File f) {
        BufferedImage org = null;

        try {
            org = ImageIO.read(f);
        } catch (Exception var4) {
        }

        BufferedImage argb = new BufferedImage(org.getWidth(), org.getHeight(), 2);
        Graphics gr = argb.getGraphics();
        gr.drawImage(org, 0, 0, (ImageObserver) null);
        gr.dispose();
        return argb;
    }

    public static String getStringFromFile(File file) {
        BufferedReader reader = null;

        try {
            String filePath = file.getPath();
            StringBuilder fileData = new StringBuilder(1000);
            reader = new BufferedReader(new FileReader(filePath));
            char[] buf = new char[1024];

            int numRead;
            for (boolean var5 = false; (numRead = reader.read(buf)) != -1; buf = new char[1024]) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
            }

            reader.close();
            String var8 = fileData.toString();
            return var8;
        } catch (Exception var16) {
            Logger.getLogger(ConvertTools.class.getName()).log(Level.SEVERE, (String) null, var16);
        } finally {
            try {
                reader.close();
            } catch (IOException var15) {
                Logger.getLogger(ConvertTools.class.getName()).log(Level.SEVERE, (String) null, var15);
            }

        }

        return null;
    }

    public static byte[] getBytesFromFile(File file) {
        byte[] bytes = (byte[]) null;

        try {
            InputStream is = new FileInputStream(file);
            long length = file.length();
            bytes = new byte[(int) length];
            int offset = 0;

            int numRead;
            for (boolean var6 = false; offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0; offset += numRead) {
            }

            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }

            is.close();
        } catch (Exception var7) {
            System.out.println(var7.getMessage());
        }

        return bytes;
    }

    public static boolean saveFile(BufferedImage bufferedImage, String absoluteDestinationPath) {
        File f = new File(absoluteDestinationPath);

        try {
            ImageIO.write(bufferedImage, "png", f);
            return true;
        } catch (IOException var4) {
            System.out.println(var4.toString());
            return false;
        }
    }

    public static boolean saveFile(String stringData, String absoluteDestinationPath) {
        return saveFile(stringData.getBytes(), absoluteDestinationPath);
    }

    public static boolean saveFile(byte[] byteArray, String absoluteDestinationPath) {
        try {
            FileOutputStream fos = new FileOutputStream(absoluteDestinationPath);
            fos.write(byteArray);
            fos.close();
            return true;
        } catch (IOException var3) {
            System.out.println(var3.toString());
            return false;
        }
    }

    public static byte[] fromHeaderFileToEpd(String hFile, int length) {
        int[] rawData = new int[length];
        char[] charHFile = hFile.toCharArray();
        int indexOfLastByte = -1;

        for (int i = 0; i < length; ++i) {
            indexOfLastByte = hFile.indexOf("0x", indexOfLastByte + 1);
            rawData[i] = hexCharToByte(charHFile[indexOfLastByte + 2]) * 16 + hexCharToByte(charHFile[indexOfLastByte + 3]);
        }

        return toByteArray(rawData);
    }
}