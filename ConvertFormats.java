package com.company.Convert;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

class ConvertFormats {
    static String CONVERT_HDEF;
    static String CONVERT_VERSION_STRING = "Crated by Convert";

    ConvertFormats() {
    }

    static byte[] convertToEpdImageFile_ColorDepth1bit_PixelFormatType1(BufferedImage img, Dimension panelSize, byte[] imageHeader) {
        int Wp = panelSize.width;
        int Hp = panelSize.height;
        int[] rawIntPixelData = (int[]) null;
        byte[] rawBytePixelData = (byte[]) null;
        byte[] fullImageData = (byte[]) null;
        img = ConvertTools.scaleImage(img, Wp, Hp);
        img = ConvertTools.downsampleTo8bitGrayScale(img);
        rawIntPixelData = ConvertTools.toIntArray(img);
        rawIntPixelData = ConvertTools.downsampleTo1bitGrayScale(rawIntPixelData);
        rawBytePixelData = ConvertTools.toByteArray(rawIntPixelData);
        rawBytePixelData = convertTo1bit_PixelFormatType1(rawBytePixelData, Wp, Hp);
        fullImageData = new byte[imageHeader.length + rawBytePixelData.length];
        System.arraycopy(imageHeader, 0, fullImageData, 0, imageHeader.length);
        System.arraycopy(rawBytePixelData, 0, fullImageData, imageHeader.length, rawBytePixelData.length);
        return fullImageData;
    }

    static byte[] convertToEpdImageFile_ColorDepth1bit_PixelFormatType2(BufferedImage img, Dimension panelSize, byte[] imageHeader) {
        int Wp = panelSize.width;
        int Hp = panelSize.height;
        int[] rawIntPixelData = (int[]) null;
        byte[] rawBytePixelData = (byte[]) null;
        byte[] fullImageData = (byte[]) null;
        img = ConvertTools.scaleImage(img, Wp, Hp);
        img = ConvertTools.downsampleTo8bitGrayScale(img);
        rawIntPixelData = ConvertTools.toIntArray(img);
        rawIntPixelData = ConvertTools.downsampleTo1bitGrayScale(rawIntPixelData);
        rawBytePixelData = ConvertTools.toByteArray(rawIntPixelData);
        rawBytePixelData = convertTo1bit_PixelFormatType2(rawBytePixelData, Wp, Hp);
        fullImageData = new byte[imageHeader.length + rawBytePixelData.length];
        System.arraycopy(imageHeader, 0, fullImageData, 0, imageHeader.length);
        System.arraycopy(rawBytePixelData, 0, fullImageData, imageHeader.length, rawBytePixelData.length);
        return fullImageData;
    }

    static byte[] convertToEpdImageFile_ColorDepth1bit_PixelFormatType3(BufferedImage img, Dimension panelSize, byte[] imageHeader) {
        int Wp = panelSize.width;
        int Hp = panelSize.height;
        int[] rawIntPixelData = (int[]) null;
        byte[] rawBytePixelData = (byte[]) null;
        byte[] fullImageData = (byte[]) null;
        img = ConvertTools.scaleImage(img, Wp, Hp);
        img = ConvertTools.downsampleTo8bitGrayScale(img);
        rawIntPixelData = ConvertTools.toIntArray(img);
        rawIntPixelData = ConvertTools.downsampleTo1bitGrayScale(rawIntPixelData);
        rawBytePixelData = ConvertTools.toByteArray(rawIntPixelData);
        rawBytePixelData = convertTo1bit_PixelFormatType3(rawBytePixelData, Wp, Hp);
        fullImageData = new byte[imageHeader.length + rawBytePixelData.length];
        System.arraycopy(imageHeader, 0, fullImageData, 0, imageHeader.length);
        System.arraycopy(rawBytePixelData, 0, fullImageData, imageHeader.length, rawBytePixelData.length);
        return fullImageData;
    }

    static byte[] convertToEpdImageFile_ColorDepth1bit_PixelFormatType4(BufferedImage img, Dimension panelSize, byte[] imageHeader) {
        int Wp = panelSize.width;
        int Hp = panelSize.height;
        int[] rawIntPixelData = (int[]) null;
        byte[] rawBytePixelData = (byte[]) null;
        byte[] fullImageData = (byte[]) null;
        img = ConvertTools.scaleImage(img, Wp, Hp);
        img = ConvertTools.downsampleTo8bitGrayScale(img);
        rawIntPixelData = ConvertTools.toIntArray(img);
        rawIntPixelData = ConvertTools.downsampleTo1bitGrayScale(rawIntPixelData);
        rawBytePixelData = ConvertTools.toByteArray(rawIntPixelData);
        rawBytePixelData = convertTo1bit_PixelFormatType4(rawBytePixelData, Wp, Hp);
        fullImageData = new byte[imageHeader.length + rawBytePixelData.length];
        System.arraycopy(imageHeader, 0, fullImageData, 0, imageHeader.length);
        System.arraycopy(rawBytePixelData, 0, fullImageData, imageHeader.length, rawBytePixelData.length);
        return fullImageData;
    }

    static byte[] convertToEpdImageFile_ColorDepth1bit_PixelFormatType5(BufferedImage img, Dimension panelSize, byte[] imageHeader) {
        int Wp = panelSize.width;
        int Hp = panelSize.height;
        int[] rawIntPixelData = (int[]) null;
        byte[] rawBytePixelData = (byte[]) null;
        byte[] fullImageData = (byte[]) null;
        img = ConvertTools.scaleImage(img, Wp, Hp);
        img = ConvertTools.downsampleTo8bitGrayScale(img);
        rawIntPixelData = ConvertTools.toIntArray(img);
        rawIntPixelData = ConvertTools.downsampleTo1bitGrayScale(rawIntPixelData);
        rawBytePixelData = ConvertTools.toByteArray(rawIntPixelData);
        rawBytePixelData = convertTo1bit_PixelFormatType5(rawBytePixelData, Wp, Hp);
        fullImageData = new byte[imageHeader.length + rawBytePixelData.length];
        System.arraycopy(imageHeader, 0, fullImageData, 0, imageHeader.length);
        System.arraycopy(rawBytePixelData, 0, fullImageData, imageHeader.length, rawBytePixelData.length);
        return fullImageData;
    }

    static byte[] convertTo1bit_PixelFormatType1(byte[] picData, int w, int h) {
        byte[][] imgdata = new byte[w][h];
        byte[][] imgdataTemp = new byte[w][h];
        int n = 0;

        int y;
        int j;
        for (y = 0; y < h; ++y) {
            for (j = 0; j < w; ++j) {
                imgdata[j][y] = picData[n];
                imgdataTemp[j][y] = imgdata[j][y];
                ++n;
            }
        }

        for (y = 0; y < h; ++y) {
            for (j = 0; j < w; ++j) {
                if (j % 2 == 1) {
                    imgdata[j][y] = imgdataTemp[w - j][y];
                }
            }
        }

        n = 0;

        for (y = 0; y < h; ++y) {
            for (j = 0; j < w; ++j) {
                picData[n] = imgdata[j][y];
                ++n;
            }
        }

        byte[] newRow = new byte[picData.length * 1 / 8];
        j = 0;

        for (int i = 0; i < picData.length; i += 8) {
            newRow[j] = (byte) (picData[i] << 7 & 128 | picData[i + 1] << 6 & 64 | picData[i + 2] << 5 & 32 | picData[i + 3] << 4 & 16 | picData[i + 4] << 3 & 8 | picData[i + 5] << 2 & 4 | picData[i + 6] << 1 & 2 | picData[i + 7] & 1);
            ++j;
        }

        return newRow;
    }

    static byte[] convertTo1bit_PixelFormatType2(byte[] picData, int w, int h) {
        byte[] newRow = new byte[picData.length * 1 / 8];
        int j = 0;

        for (int i = 0; i < picData.length; i += 8) {
            newRow[j] = (byte) (picData[i + 0] << 7 & 128 | picData[i + 4] << 6 & 64 | picData[i + 1] << 5 & 32 | picData[i + 5] << 4 & 16 | picData[i + 2] << 3 & 8 | picData[i + 6] << 2 & 4 | picData[i + 3] << 1 & 2 | picData[i + 7] & 1);
            ++j;
        }

        return newRow;
    }

    static byte[] convertTo1bit_PixelFormatType3(byte[] picData, int w, int h) {
        byte[] newRow = new byte[picData.length * 1 / 8];
        int j = 0;

        for (int i = 0; i < picData.length; i += 16) {
            newRow[j] = (byte) (picData[i + 6] << 7 & 128 | picData[i + 14] << 6 & 64 | picData[i + 4] << 5 & 32 | picData[i + 12] << 4 & 16 | picData[i + 2] << 3 & 8 | picData[i + 10] << 2 & 4 | picData[i] << 1 & 2 | picData[i + 8] & 1);
            ++j;
            newRow[j] = (byte) (picData[i + 1] << 7 & 128 | picData[i + 9] << 6 & 64 | picData[i + 3] << 5 & 32 | picData[i + 11] << 4 & 16 | picData[i + 5] << 3 & 8 | picData[i + 13] << 2 & 4 | picData[i + 7] << 1 & 2 | picData[i + 15] & 1);
            ++j;
        }

        return newRow;
    }

    static byte[] convertTo1bit_PixelFormatType4(byte[] picData, int w, int h) {
        byte[] newPicData = new byte[picData.length / 8];
        int row = 30;
        int s = 1;

        for (int px = 0; px < picData.length; px += 16) {
            newPicData[row - s] = (byte) (picData[px + 6] << 7 & 128 | picData[px + 14] << 6 & 64 | picData[px + 4] << 5 & 32 | picData[px + 12] << 4 & 16 | picData[px + 2] << 3 & 8 | picData[px + 10] << 2 & 4 | picData[px + 0] << 1 & 2 | picData[px + 8] << 0 & 1);
            newPicData[row + 30 - s] = (byte) (picData[px + 1] << 7 & 128 | picData[px + 9] << 6 & 64 | picData[px + 3] << 5 & 32 | picData[px + 11] << 4 & 16 | picData[px + 5] << 3 & 8 | picData[px + 13] << 2 & 4 | picData[px + 7] << 1 & 2 | picData[px + 15] << 0 & 1);
            ++s;
            if (s == 31) {
                s = 1;
                row += 60;
            }
        }

        return newPicData;
    }

    static byte[] convertTo1bit_PixelFormatType5(byte[] picData, int w, int h) {
        byte[] newRow = new byte[picData.length * 1 / 8];
        int j = 0;

        for (int i = 0; i < picData.length; i += 8) {
            newRow[j] = (byte) (picData[i + 0] << 7 & 128 | picData[i + 4] << 6 & 64 | picData[i + 1] << 5 & 32 | picData[i + 5] << 4 & 16 | picData[i + 2] << 3 & 8 | picData[i + 6] << 2 & 4 | picData[i + 3] << 1 & 2 | picData[i + 7] << 0 & 1);
            ++j;
        }

        return newRow;
    }

    static byte[] convertTo4bit_PixelFormatType1(byte[] imageRawData, int w, int h) {
        byte[][] imgdata = new byte[w][h];
        byte[][] imgdataTemp = new byte[w][h];
        int n = 0;

        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                imgdata[x][y] = imageRawData[n];
                imgdataTemp[x][y] = imgdata[x][y];
                ++n;
            }
        }

        byte[] even = new byte[imageRawData.length / 2];
        byte[] odd = new byte[imageRawData.length / 2];
        int o = 0;
        int e = 0;

        int y;
        for (y = 0; y < h; ++y) {
            for (y = 0; y < w; ++y) {
                if (y % 2 == 1) {
                    odd[o++] = imgdata[w - y][y];
                } else {
                    even[e++] = imgdata[y][y];
                }
            }
        }

        byte[] even_reversed = new byte[even.length];

        for (y = 0; y < even.length; ++y) {
            even_reversed[y] = even[even.length - 1 - y];
        }

        n = 0;

        int j;
        for (y = 0; y < h; ++y) {
            for (j = 0; j < w / 2; ++j) {
                imgdata[j + w / 2][y] = even[n];
                imgdata[j][y] = odd[n];
                ++n;
            }
        }

        n = 0;

        for (y = 0; y < h; ++y) {
            for (j = 0; j < w; ++j) {
                imageRawData[n] = imgdata[j][y];
                ++n;
            }
        }

        byte[] newRow = new byte[imageRawData.length * 4 / 8];
        j = 0;

        int i;
        for (i = 0; i < imageRawData.length; i += 2) {
            newRow[j] = (byte) (imageRawData[i] << 4 & 240 | imageRawData[i + 1] & 15);
            ++j;
        }

        for (i = 0; i < newRow.length; i += 2) {
            byte a = newRow[i];
            newRow[i] = newRow[i + 1];
            newRow[i + 1] = a;
        }

        return newRow;
    }

    static byte[] convertToEpdImageFile_ColorDepth4bit_PixelFormatType1(BufferedImage img, Dimension panelSize, byte[] imageHeader) {
        int Wp = panelSize.width;
        int Hp = panelSize.height;
        int[] rawIntPixelData = (int[]) null;
        byte[] rawBytePixelData = (byte[]) null;
        byte[] fullImageData = (byte[]) null;
        img = ConvertTools.scaleImage(img, Wp, Hp);
        img = ConvertTools.downsampleTo8bitGrayScale(img);
        rawIntPixelData = ConvertTools.toIntArray(img);
        rawIntPixelData = ConvertTools.downsampleTo4bitGrayScale(rawIntPixelData);
        rawBytePixelData = ConvertTools.toByteArray(rawIntPixelData);
        rawBytePixelData = convertTo4bit_PixelFormatType1(rawBytePixelData, Wp, Hp);
        fullImageData = new byte[imageHeader.length + rawBytePixelData.length];
        System.arraycopy(imageHeader, 0, fullImageData, 0, imageHeader.length);
        System.arraycopy(rawBytePixelData, 0, fullImageData, imageHeader.length, rawBytePixelData.length);
        return fullImageData;
    }

    static String convertToHeaderFile_ColorDepth1bit_PixelFormatType1(BufferedImage img, Dimension panelSize, byte[] imageHeader, String name) {
        String convertVersion = CONVERT_VERSION_STRING;
        String convertHdef = CONVERT_HDEF;
        int Wp = panelSize.width;
        int Hp = panelSize.height;
        int[] rawIntPixelData = (int[]) null;
        byte[] rawBytePixelData = (byte[]) null;
        img = ConvertTools.scaleImage(img, Wp, Hp);
        img = ConvertTools.downsampleTo8bitGrayScale(img);
        rawIntPixelData = ConvertTools.toIntArray(img);
        rawIntPixelData = ConvertTools.downsampleTo1bitGrayScale(rawIntPixelData);
        rawBytePixelData = ConvertTools.toByteArray(rawIntPixelData);
        rawBytePixelData = convertTo1bit_PixelFormatType1(rawBytePixelData, Wp, Hp);
        String header = convertHdef + " " + name + "[" + rawBytePixelData.length + "+" + imageHeader.length + "] = {";
        String headerData = ConvertTools.bytesToHexString(imageHeader);
        String imageData = ConvertTools.bytesToHexString(rawBytePixelData);
        String footer = "};  /* " + convertVersion + " */ \n\n";
        String fullHeaderFile = header + headerData + ",\n" + imageData + "\n" + footer;
        return fullHeaderFile;
    }

    static String convertToHeaderFile_ColorDepth1bit_PixelFormatType2(BufferedImage img, Dimension panelSize, byte[] imageHeader, String name) {
        String convertVersion = CONVERT_VERSION_STRING;
        String convertHdef = CONVERT_HDEF;
        int Wp = panelSize.width;
        int Hp = panelSize.height;
        int[] rawIntPixelData = (int[]) null;
        byte[] rawBytePixelData = (byte[]) null;
        img = ConvertTools.scaleImage(img, Wp, Hp);
        img = ConvertTools.downsampleTo8bitGrayScale(img);
        rawIntPixelData = ConvertTools.toIntArray(img);
        rawIntPixelData = ConvertTools.downsampleTo1bitGrayScale(rawIntPixelData);
        rawBytePixelData = ConvertTools.toByteArray(rawIntPixelData);
        rawBytePixelData = convertTo1bit_PixelFormatType2(rawBytePixelData, Wp, Hp);
        String header = convertHdef + " " + name + "[" + rawBytePixelData.length + "+" + imageHeader.length + "] = {";
        String headerData = ConvertTools.bytesToHexString(imageHeader);
        String imageData = ConvertTools.bytesToHexString(rawBytePixelData);
        String footer = "};  /* " + convertVersion + " */ \n\n";
        String fullHeaderFile = header + headerData + ",\n" + imageData + "\n" + footer;
        return fullHeaderFile;
    }

    static String convertToHeaderFile_ColorDepth1bit_PixelFormatType3(BufferedImage img, Dimension panelSize, byte[] imageHeader, String name) {
        String convertVersion = CONVERT_VERSION_STRING;
        String convertHdef = CONVERT_HDEF;
        int Wp = panelSize.width;
        int Hp = panelSize.height;
        int[] rawIntPixelData = (int[]) null;
        byte[] rawBytePixelData = (byte[]) null;
        img = ConvertTools.scaleImage(img, Wp, Hp);
        img = ConvertTools.downsampleTo8bitGrayScale(img);
        rawIntPixelData = ConvertTools.toIntArray(img);
        rawIntPixelData = ConvertTools.downsampleTo1bitGrayScale(rawIntPixelData);
        rawBytePixelData = ConvertTools.toByteArray(rawIntPixelData);
        rawBytePixelData = convertTo1bit_PixelFormatType3(rawBytePixelData, Wp, Hp);
        String header = convertHdef + " " + name + "[" + rawBytePixelData.length + "+" + imageHeader.length + "] = {";
        String headerData = ConvertTools.bytesToHexString(imageHeader);
        String imageData = ConvertTools.bytesToHexString(rawBytePixelData);
        String footer = "};  /* " + convertVersion + " */ \n\n";
        String fullHeaderFile = header + headerData + ",\n" + imageData + "\n" + footer;
        return fullHeaderFile;
    }

    static String convertToHeaderFile_ColorDepth1bit_PixelFormatType4(BufferedImage img, Dimension panelSize, byte[] imageHeader, String name) {
        String convertVersion = CONVERT_VERSION_STRING;
        String convertHdef = CONVERT_HDEF;
        int Wp = panelSize.width;
        int Hp = panelSize.height;
        int[] rawIntPixelData = (int[]) null;
        byte[] rawBytePixelData = (byte[]) null;
        img = ConvertTools.scaleImage(img, Wp, Hp);
        img = ConvertTools.downsampleTo8bitGrayScale(img);
        rawIntPixelData = ConvertTools.toIntArray(img);
        rawIntPixelData = ConvertTools.downsampleTo1bitGrayScale(rawIntPixelData);
        rawBytePixelData = ConvertTools.toByteArray(rawIntPixelData);
        rawBytePixelData = convertTo1bit_PixelFormatType4(rawBytePixelData, Wp, Hp);
        String header = convertHdef + " " + name + "[" + rawBytePixelData.length + "+" + imageHeader.length + "] = {";
        String headerData = ConvertTools.bytesToHexString(imageHeader);
        String imageData = ConvertTools.bytesToHexString(rawBytePixelData);
        String footer = "};  /* " + convertVersion + " */ \n\n";
        String fullHeaderFile = header + headerData + ",\n" + imageData + "\n" + footer;
        return fullHeaderFile;
    }

    static String convertToHeaderFile_ColorDepth1bit_PixelFormatType5(BufferedImage img, Dimension panelSize, byte[] imageHeader, String name) {
        String convertVersion = CONVERT_VERSION_STRING;
        String convertHdef = CONVERT_HDEF;
        int Wp = panelSize.width;
        int Hp = panelSize.height;
        int[] rawIntPixelData = (int[]) null;
        byte[] rawBytePixelData = (byte[]) null;
        img = ConvertTools.scaleImage(img, Wp, Hp);
        img = ConvertTools.downsampleTo8bitGrayScale(img);
        rawIntPixelData = ConvertTools.toIntArray(img);
        rawIntPixelData = ConvertTools.downsampleTo1bitGrayScale(rawIntPixelData);
        rawBytePixelData = ConvertTools.toByteArray(rawIntPixelData);
        rawBytePixelData = convertTo1bit_PixelFormatType5(rawBytePixelData, Wp, Hp);
        String header = convertHdef + " " + name + "[" + rawBytePixelData.length + "+" + imageHeader.length + "] = {";
        String headerData = ConvertTools.bytesToHexString(imageHeader);
        String imageData = ConvertTools.bytesToHexString(rawBytePixelData);
        String footer = "};  /* " + convertVersion + " */ \n\n";
        String fullHeaderFile = header + headerData + ",\n" + imageData + "\n" + footer;
        return fullHeaderFile;
    }

    static String convertToHeaderFile_ColorDepth4bit_PixelFormatType1(BufferedImage img, Dimension panelSize, byte[] imageHeader, String name) {
        String convertVersion = CONVERT_VERSION_STRING;
        String convertHdef = CONVERT_HDEF;
        int Wp = panelSize.width;
        int Hp = panelSize.height;
        int[] rawIntPixelData = (int[]) null;
        byte[] rawBytePixelData = (byte[]) null;
        img = ConvertTools.scaleImage(img, Wp, Hp);
        img = ConvertTools.downsampleTo8bitGrayScale(img);
        rawIntPixelData = ConvertTools.toIntArray(img);
        rawIntPixelData = ConvertTools.downsampleTo4bitGrayScale(rawIntPixelData);
        rawBytePixelData = ConvertTools.toByteArray(rawIntPixelData);
        rawBytePixelData = convertTo4bit_PixelFormatType1(rawBytePixelData, Wp, Hp);
        String header = convertHdef + " " + name + "[" + rawBytePixelData.length + "+" + imageHeader.length + "] = {";
        String headerData = ConvertTools.bytesToHexString(imageHeader);
        String imageData = ConvertTools.bytesToHexString(rawBytePixelData);
        String footer = "};  /* " + convertVersion + " */ \n\n";
        String fullHeaderFile = header + headerData + ",\n" + imageData + "\n" + footer;
        return fullHeaderFile;
    }

    static BufferedImage convertToPreviewImage_ColorDepth1bit(BufferedImage img, Dimension panelSize) {
        int Wp = panelSize.width;
        int Hp = panelSize.height;
        int[] rawPixelData = (int[]) null;
        img = ConvertTools.scaleImage(img, Wp, Hp);
        img = ConvertTools.downsampleTo8bitGrayScale(img);
        rawPixelData = ConvertTools.toIntArray(img);
        rawPixelData = ConvertTools.downsampleTo1bitGrayScale_Inverted(rawPixelData);
        return ConvertTools.toBufferedImage(rawPixelData, Wp, Hp);
    }

    static BufferedImage convertToPreviewImage_ColorDepth4bit(BufferedImage img, Dimension panelSize) {
        int Wp = panelSize.width;
        int Hp = panelSize.height;
        int[] rawPixelData = (int[]) null;
        img = ConvertTools.scaleImage(img, Wp, Hp);
        img = ConvertTools.downsampleTo8bitGrayScale(img);
        rawPixelData = ConvertTools.toIntArray(img);
        rawPixelData = ConvertTools.downsampleTo4bitGrayScale(rawPixelData);
        rawPixelData = ConvertTools.upsampleTo8bitGrayScale(rawPixelData);
        return ConvertTools.toBufferedImage(rawPixelData, Wp, Hp);
    }

    static BufferedImage convertToPreviewImage_ColorDepth1bit_PixelFormatType1(byte[] epdImage) {
        int Wp = epdImage[1] << 8 | epdImage[2];
        int Hp = epdImage[3] << 8 | epdImage[4];
        byte[][] imgdata = new byte[Wp][Hp];
        byte[][] imgdataTemp = new byte[Wp][Hp];
        byte[] epdRawData = (byte[]) null;
        byte[] imageRawData = (byte[]) null;
        int[] imageRawDataInt = (int[]) null;
        epdRawData = new byte[epdImage.length - 16];
        System.arraycopy(epdImage, 16, epdRawData, 0, epdRawData.length);
        imageRawData = new byte[epdRawData.length * 8 / 1];
        int i = 0;

        int n;
        for (n = 0; n < epdRawData.length; ++n) {
            imageRawData[i] = (byte) ((epdRawData[n] & 128) >> 7);
            imageRawData[i + 1] = (byte) ((epdRawData[n] & 64) >> 6);
            imageRawData[i + 2] = (byte) ((epdRawData[n] & 32) >> 5);
            imageRawData[i + 3] = (byte) ((epdRawData[n] & 16) >> 4);
            imageRawData[i + 4] = (byte) ((epdRawData[n] & 8) >> 3);
            imageRawData[i + 5] = (byte) ((epdRawData[n] & 4) >> 2);
            imageRawData[i + 6] = (byte) ((epdRawData[n] & 2) >> 1);
            imageRawData[i + 7] = (byte) (epdRawData[n] & 1);
            i += 8;
        }

        n = 0;

        int y;
        int x;
        for (y = 0; y < Hp; ++y) {
            for (x = 0; x < Wp; ++x) {
                imgdata[x][y] = imageRawData[n];
                imgdataTemp[x][y] = imgdata[x][y];
                ++n;
            }
        }

        for (y = 0; y < Hp; ++y) {
            for (x = 0; x < Wp; ++x) {
                if (x % 2 == 1) {
                    imgdata[x][y] = imgdataTemp[Wp - x][y];
                }
            }
        }

        n = 0;

        for (y = 0; y < Hp; ++y) {
            for (x = 0; x < Wp; ++x) {
                imageRawData[n] = imgdata[x][y];
                ++n;
            }
        }

        imageRawDataInt = ConvertTools.toIntArray(imageRawData);

        for (i = 0; i < imageRawDataInt.length; ++i) {
            if (imageRawDataInt[i] == 1) {
                imageRawDataInt[i] = 0;
            } else {
                imageRawDataInt[i] = 255;
            }
        }

        return ConvertTools.toBufferedImage(imageRawDataInt, Wp, Hp);
    }

    static BufferedImage convertToPreviewImage_ColorDepth4bit_PixelFormatType1(byte[] epdImage) {
        int Wp = epdImage[1] << 8 | epdImage[2];
        int Hp = epdImage[3] << 8 | epdImage[4];
        byte[][] imgdata = new byte[Wp][Hp];
        byte[] epdRawData = (byte[]) null;
        byte[] imageRawData = (byte[]) null;
        epdRawData = new byte[epdImage.length - 16];
        System.arraycopy(epdImage, 16, epdRawData, 0, epdRawData.length);

        int j;
        for (j = 0; j < epdRawData.length; j += 2) {
            byte a = epdRawData[j];
            epdRawData[j] = epdRawData[j + 1];
            epdRawData[j + 1] = a;
        }

        imageRawData = new byte[epdRawData.length * 8 / 4];
        j = 0;

        int n;
        for (n = 0; n < epdRawData.length; ++n) {
            imageRawData[j] = (byte) (epdRawData[n] >> 4 & 15);
            ++j;
            imageRawData[j] = (byte) (epdRawData[n] & 15);
            ++j;
        }

        n = 0;

        for (int y = 0; y < Hp; ++y) {
            for (int x = 0; x < Wp; ++x) {
                imgdata[x][y] = imageRawData[n];
                ++n;
            }
        }

        byte[] even = new byte[imageRawData.length / 2];
        byte[] odd = new byte[imageRawData.length / 2];
        n = 0;

        int o;
        for (int y = 0; y < Hp; ++y) {
            for (o = 0; o < Wp / 2; ++o) {
                even[n] = imgdata[o + Wp / 2][y];
                odd[n] = imgdata[o][y];
                ++n;
            }
        }

        byte[] even_reversed = new byte[even.length];

        for (o = 0; o < even.length; ++o) {
            even_reversed[o] = even[even.length - 1 - o];
        }

        o = 0;
        int e = 0;

        int y;
        int x;
        for (y = 0; y < Hp; ++y) {
            for (x = 0; x < Wp; ++x) {
                if (x % 2 == 1) {
                    imgdata[Wp - x][y] = odd[o++];
                } else {
                    imgdata[x][y] = even[e++];
                }
            }
        }

        n = 0;

        for (y = 0; y < Hp; ++y) {
            for (x = 0; x < Wp; ++x) {
                imageRawData[n] = imgdata[x][y];
                ++n;
            }
        }

        return ConvertTools.toBufferedImage(ConvertTools.upsampleTo8bitGrayScale(ConvertTools.toIntArray(imageRawData)), Wp, Hp);
    }
}
