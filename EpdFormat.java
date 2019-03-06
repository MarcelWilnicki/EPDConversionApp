package com.company.Convert;

import java.awt.image.BufferedImage;

interface EpdFormat {
    byte[] convertToEpdImage(BufferedImage var1);

    byte[] convertToEpdImage(String var1);

    BufferedImage convertToPreviewImage(BufferedImage var1);

    BufferedImage convertToPreviewImage(byte[] var1);

    BufferedImage convertToPreviewImage(String var1);

    String convertToHeaderFile(BufferedImage var1, String var2);

    String convertToHeaderFile(byte[] var1, String var2);

    String getVersionString();

    String getPanelTypeString();

    int getPanelType();

    void setPanelType(int var1);

    byte[] getVersionCode();

    int getPanelWidth();

    void setPanelWidth(int var1);

    int getPanelHight();

    void setPanelHeight(int var1);

    int getPixelFormat();

    void setPixelFormat(int var1);

    int getColorDepth();

    void setColorDepth(int var1);

    String getEpdFormatVersion();

    byte[] getEpdFormatHeader();

    void setEpdFormatHeader(byte[] var1);

    public static final class ColorDepth {
        public static final int COLOR_DEPTH_1BIT = 1;
        public static final int COLOR_DEPTH_4BIT = 4;

        public ColorDepth() {
        }
    }

    public static final class PanelType {
        public static final int PANEL_TYPE_NONE = 0;
        public static final int PANEL_TYPE_PDI20 = 48;
        public static final int PANEL_TYPE_PDI144 = 49;
        public static final int PANEL_TYPE_PDI27 = 50;
        public static final int PANEL_TYPE_PDI441 = 51;
        public static final int PANEL_TYPE_PDI74 = 58;
        public static final int PANEL_TYPE_PDI102 = 61;
        public static final int PANEL_TYPE_PVI19 = 16;
        public static final int PANEL_TYPE_E60 = 24;

        public PanelType() {
        }
    }

    public static final class PixelFormat {
        public static final int PIXEL_FORMAT_TYPE = 0;
        public static final int PIXEL_FORMAT_TYPE1 = 1;
        public static final int PIXEL_FORMAT_TYPE2 = 2;
        public static final int PIXEL_FORMAT_TYPE3 = 3;
        public static final int PIXEL_FORMAT_TYPE4 = 4;
        public static final int PIXEL_FORMAT_TYPE5 = 5;

        public PixelFormat() {
        }
    }
}
