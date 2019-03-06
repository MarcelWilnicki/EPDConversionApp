package com.company.Convert;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Properties;

public final class Convert extends ConvertFormats implements EpdFormat {
    private String CONVERT_VERSION_STRING = "0.2.0";
    private final String EPD_FORMAT_VERSION_STRING = "EpdFormat v0.1";
    private int PANEL_TYPE = 48;
    private int PIXEL_FORMAT = 1;
    private int COLOR_DEPTH = 1;
    private byte WIDTH_MSB = 0;
    private byte WIDTH_LSB = -56;
    private byte HEIGHT_MSB = 0;
    private byte HEIGHT_LSB = 96;
    private byte[] IMAGE_HEADER;
    private int PANEL_WIDTH;
    private int PANEL_HEIGHT;
    private String CONVERT_PANEL_TYPE_STRING;
    private String CONVERT_FILENAME_PREFIX;
    private String CONVERT_IMAGE_VAR_TYPDEF;
    private String CONVERT_FILE_VERSION_STRING;
    private byte[] CONVERT_VERSION_CODE;
    private byte[] IMAGE_HEADER_1BIT;
    private int FORCE_1BIT_ONLY;

    public Convert() {
        this.IMAGE_HEADER = new byte[]{(byte)this.PANEL_TYPE, this.WIDTH_MSB, this.WIDTH_LSB, this.HEIGHT_MSB, this.HEIGHT_LSB, (byte)this.COLOR_DEPTH, (byte)this.PIXEL_FORMAT, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.PANEL_WIDTH = 200;
        this.PANEL_HEIGHT = 96;
        this.CONVERT_PANEL_TYPE_STRING = "PervasiveDisplays 2.0 Panel";
        this.CONVERT_FILENAME_PREFIX = "PDI20";
        this.CONVERT_IMAGE_VAR_TYPDEF = "const unsigned char";
        this.CONVERT_FILE_VERSION_STRING = "ConvertPDI20-0.1.0.jar";
        this.CONVERT_VERSION_CODE = new byte[]{48, 0};
        this.IMAGE_HEADER_1BIT = new byte[]{48, 0, -56, 0, 96, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.FORCE_1BIT_ONLY = 0;
        Properties generalProps = Props.propertiesReadResource("convert-general.properties");
        Properties props = Props.propertiesReadResource(generalProps.getProperty("ConvertAppType"));
        this.convertInit(props);
    }

    public Convert(int panel_type) {
        this.IMAGE_HEADER = new byte[]{(byte)this.PANEL_TYPE, this.WIDTH_MSB, this.WIDTH_LSB, this.HEIGHT_MSB, this.HEIGHT_LSB, (byte)this.COLOR_DEPTH, (byte)this.PIXEL_FORMAT, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.PANEL_WIDTH = 200;
        this.PANEL_HEIGHT = 96;
        this.CONVERT_PANEL_TYPE_STRING = "PervasiveDisplays 2.0 Panel";
        this.CONVERT_FILENAME_PREFIX = "PDI20";
        this.CONVERT_IMAGE_VAR_TYPDEF = "const unsigned char";
        this.CONVERT_FILE_VERSION_STRING = "ConvertPDI20-0.1.0.jar";
        this.CONVERT_VERSION_CODE = new byte[]{48, 0};
        this.IMAGE_HEADER_1BIT = new byte[]{48, 0, -56, 0, 96, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.FORCE_1BIT_ONLY = 0;
        Properties props = null;
        switch(panel_type) {
            case 16:
                props = Props.propertiesReadResource("convert-pvi19.properties");
                break;
            case 24:
                props = Props.propertiesReadResource("convert-e60.properties");
                break;
            case 48:
                props = Props.propertiesReadResource("convert-pdi20.properties");
                break;
            case 49:
                props = Props.propertiesReadResource("convert-pdi144.properties");
                break;
            case 50:
                props = Props.propertiesReadResource("convert-pdi27.properties");
                break;
            case 51:
                props = Props.propertiesReadResource("convert-pdi441.properties");
                break;
            case 58:
                props = Props.propertiesReadResource("convert-pdi74.properties");
                break;
            case 61:
                props = Props.propertiesReadResource("convert-pdi102.properties");
                break;
            default:
                Properties generalProps = Props.propertiesReadResource("convert-general.properties");
                props = Props.propertiesReadResource(generalProps.getProperty("ConvertAppType"));
        }

        if (props != null) {
            this.convertInit(props);
        }

    }

    private void convertInit(Properties props) {
        this.PANEL_HEIGHT = Props.getProp(props, "PanelHeight");
        this.PANEL_WIDTH = Props.getProp(props, "PanelWidth");
        this.CONVERT_FILENAME_PREFIX = props.getProperty("ConvertedFileNamePrefix");
        this.CONVERT_IMAGE_VAR_TYPDEF = props.getProperty("ImageViariableTypedefSting");
        this.CONVERT_FILE_VERSION_STRING = props.getProperty("ApplicationVersionString");
        this.IMAGE_HEADER_1BIT = ConvertTools.stringToBytes(props.getProperty("ImageHeader1bitBytes"));
        this.CONVERT_PANEL_TYPE_STRING = props.getProperty("SupportedPanelTypeString");
        this.CONVERT_VERSION_CODE = ConvertTools.stringToBytes(props.getProperty("VersionCode"));
        this.FORCE_1BIT_ONLY = Props.getProp(props, "Force1bitOnly");
        ConvertFormats.CONVERT_HDEF = this.CONVERT_IMAGE_VAR_TYPDEF;
        this.setEpdFormatHeader(this.IMAGE_HEADER_1BIT);
        this.setPanelType(this.IMAGE_HEADER_1BIT[0]);
        this.setPanelWidth(this.PANEL_WIDTH);
        this.setPanelHeight(this.PANEL_HEIGHT);
        this.setPixelFormat(Props.getProp(props, "PixelFormatType"));
        this.setColorDepth(1);
    }

    public int getPanelType() {
        return this.PANEL_TYPE;
    }

    public void setPanelType(int panel_type) {
        this.PANEL_TYPE = panel_type;
    }

    public void setPanelWidth(int width) {
        this.PANEL_WIDTH = width;
        this.WIDTH_LSB = (byte)width;
        this.WIDTH_MSB = (byte)(width >> 8);
    }

    public void setPanelHeight(int height) {
        this.PANEL_HEIGHT = height;
        this.HEIGHT_LSB = (byte)height;
        this.HEIGHT_MSB = (byte)(height >> 8);
    }

    public int getPixelFormat() {
        return this.PIXEL_FORMAT;
    }

    public void setPixelFormat(int pixel_format) {
        this.PIXEL_FORMAT = pixel_format;
    }

    public String getVersionString() {
        return this.CONVERT_FILE_VERSION_STRING;
    }

    public String getPanelTypeString() {
        return this.CONVERT_PANEL_TYPE_STRING;
    }

    public int getPanelWidth() {
        return this.PANEL_WIDTH;
    }

    public int getPanelHight() {
        return this.PANEL_HEIGHT;
    }

    public byte[] getVersionCode() {
        return this.CONVERT_VERSION_CODE;
    }

    public String getEpdFormatVersion() {
        return "EpdFormat v0.1";
    }

    public byte[] getEpdFormatHeader() {
        return this.IMAGE_HEADER;
    }

    public void setEpdFormatHeader(byte[] epdHeader) {
        if (epdHeader.length == 16) {
            this.IMAGE_HEADER = epdHeader;
        }

    }

    public int getColorDepth() {
        return this.COLOR_DEPTH;
    }

    public void setColorDepth(int colorDepth) {
        this.COLOR_DEPTH = colorDepth;
    }

    public byte[] convertToEpdImage(BufferedImage bi) {
        if (this.COLOR_DEPTH == 1) {
            if (this.PIXEL_FORMAT == 1) {
                return ConvertFormats.convertToEpdImageFile_ColorDepth1bit_PixelFormatType1(bi, new Dimension(this.PANEL_WIDTH, this.PANEL_HEIGHT), this.IMAGE_HEADER);
            }

            if (this.PIXEL_FORMAT == 2) {
                return ConvertFormats.convertToEpdImageFile_ColorDepth1bit_PixelFormatType2(bi, new Dimension(this.PANEL_WIDTH, this.PANEL_HEIGHT), this.IMAGE_HEADER);
            }

            if (this.PIXEL_FORMAT == 3) {
                return ConvertFormats.convertToEpdImageFile_ColorDepth1bit_PixelFormatType3(bi, new Dimension(this.PANEL_WIDTH, this.PANEL_HEIGHT), this.IMAGE_HEADER);
            }

            if (this.PIXEL_FORMAT == 4) {
                return ConvertFormats.convertToEpdImageFile_ColorDepth1bit_PixelFormatType4(bi, new Dimension(this.PANEL_WIDTH, this.PANEL_HEIGHT), this.IMAGE_HEADER);
            }

            if (this.PIXEL_FORMAT == 5) {
                return ConvertFormats.convertToEpdImageFile_ColorDepth1bit_PixelFormatType5(bi, new Dimension(this.PANEL_WIDTH, this.PANEL_HEIGHT), this.IMAGE_HEADER);
            }
        }

        return this.COLOR_DEPTH == 4 && this.PIXEL_FORMAT == 1 ? ConvertFormats.convertToEpdImageFile_ColorDepth4bit_PixelFormatType1(bi, new Dimension(this.PANEL_WIDTH, this.PANEL_HEIGHT), this.IMAGE_HEADER) : null;
    }

    public byte[] convertToEpdImage(String headerFile) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BufferedImage convertToPreviewImage(BufferedImage bi) {
        if (this.COLOR_DEPTH == 1) {
            return convertToPreviewImage_ColorDepth1bit(bi, new Dimension(this.PANEL_WIDTH, this.PANEL_HEIGHT));
        } else {
            return this.COLOR_DEPTH == 4 ? convertToPreviewImage_ColorDepth4bit(bi, new Dimension(this.PANEL_WIDTH, this.PANEL_HEIGHT)) : null;
        }
    }

    public BufferedImage convertToPreviewImage(byte[] epdImage) {
        if (epdImage[5] == 1) {
            return convertToPreviewImage_ColorDepth1bit_PixelFormatType1(epdImage);
        } else {
            return epdImage[5] == 4 ? convertToPreviewImage_ColorDepth4bit_PixelFormatType1(epdImage) : null;
        }
    }

    public BufferedImage convertToPreviewImage(String headerFile) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String convertToHeaderFile(BufferedImage bi, String name) {
        if (this.COLOR_DEPTH == 1) {
            if (this.PIXEL_FORMAT == 1) {
                return ConvertFormats.convertToHeaderFile_ColorDepth1bit_PixelFormatType1(bi, new Dimension(this.PANEL_WIDTH, this.PANEL_HEIGHT), this.IMAGE_HEADER, name);
            }

            if (this.PIXEL_FORMAT == 2) {
                return ConvertFormats.convertToHeaderFile_ColorDepth1bit_PixelFormatType2(bi, new Dimension(this.PANEL_WIDTH, this.PANEL_HEIGHT), this.IMAGE_HEADER, name);
            }

            if (this.PIXEL_FORMAT == 3) {
                return ConvertFormats.convertToHeaderFile_ColorDepth1bit_PixelFormatType3(bi, new Dimension(this.PANEL_WIDTH, this.PANEL_HEIGHT), this.IMAGE_HEADER, name);
            }

            if (this.PIXEL_FORMAT == 4) {
                return ConvertFormats.convertToHeaderFile_ColorDepth1bit_PixelFormatType4(bi, new Dimension(this.PANEL_WIDTH, this.PANEL_HEIGHT), this.IMAGE_HEADER, name);
            }

            if (this.PIXEL_FORMAT == 5) {
                return ConvertFormats.convertToHeaderFile_ColorDepth1bit_PixelFormatType5(bi, new Dimension(this.PANEL_WIDTH, this.PANEL_HEIGHT), this.IMAGE_HEADER, name);
            }
        }

        return this.COLOR_DEPTH == 4 && this.PIXEL_FORMAT == 1 ? ConvertFormats.convertToHeaderFile_ColorDepth4bit_PixelFormatType1(bi, new Dimension(this.PANEL_WIDTH, this.PANEL_HEIGHT), this.IMAGE_HEADER, name) : null;
    }

    public String convertToHeaderFile(byte[] epdFile, String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int saveEpdImagePack(File sourceFile, String destinationPath) {
        if (sourceFile == null) {
            return -1;
        } else if (!ConvertTools.isImageFile(sourceFile)) {
            return -2;
        } else {
            BufferedImage originalImage = ConvertTools.getImageFromFile(sourceFile);
            if (originalImage == null) {
                return -3;
            } else {
                int conversionType = this.COLOR_DEPTH;
                String path = sourceFile.getParent();
                String folderName = this.CONVERT_FILENAME_PREFIX + "_Converted";
                boolean mkdir = (new File(path + "\\" + folderName)).mkdir();
                path = path + "\\" + folderName;
                String fileName = sourceFile.getName().substring(0, sourceFile.getName().length() - 4);
                String prefix = this.CONVERT_FILENAME_PREFIX;
                String saveFileNameNoExt = path + "\\" + prefix + "_" + fileName + "_" + conversionType + "bit";
                BufferedImage preview = this.convertToPreviewImage(originalImage);
                if (preview != null) {
                    ConvertTools.saveFile(preview, saveFileNameNoExt + ".png");
                }

                byte[] epdBinary = this.convertToEpdImage(originalImage);
                if (epdBinary != null) {
                    ConvertTools.saveFile(epdBinary, saveFileNameNoExt + ".epd");
                }

                CONVERT_VERSION_STRING = saveFileNameNoExt + ".h" + ": File generated by " + this.CONVERT_FILE_VERSION_STRING;
                String header_const_name = fileName + "_" + conversionType + "bit";
                String hFile = this.convertToHeaderFile(originalImage, header_const_name);
                if (hFile != null) {
                    ConvertTools.saveFile(hFile, saveFileNameNoExt + ".h");
                }

                return 1;
            }
        }
    }

    public int saveEpdPreview(File sourceFile, String destination) {
        if (sourceFile == null) {
            return -1;
        } else if (!ConvertTools.isEpdFile(sourceFile)) {
            return -2;
        } else {
            String path = sourceFile.getParent();
            boolean mkdir = (new File(path + "\\convert")).mkdir();
            path = path + "\\convert";
            String fileName = sourceFile.getName().substring(0, sourceFile.getName().length() - 4);
            String prefix = this.CONVERT_FILENAME_PREFIX;
            String saveFileNameNoExt = path + "\\" + prefix + "_" + fileName + "_preview";
            byte[] file_bytes = ConvertTools.getBytesFromFile(sourceFile);
            if (file_bytes != null) {
                BufferedImage previewFromEPD = this.convertToPreviewImage(file_bytes);
                if (previewFromEPD != null) {
                    ConvertTools.saveFile(previewFromEPD, saveFileNameNoExt + ".png");
                }
            }

            return 1;
        }
    }

    public int saveHeaderPreview(File sourceFile, String destination) {
        if (sourceFile == null) {
            return -1;
        } else if (!ConvertTools.isHeaderFile(sourceFile)) {
            return -2;
        } else {
            String path = sourceFile.getParent();
            boolean mkdir = (new File(path + "\\convert")).mkdir();
            path = path + "\\convert";
            String fileName = sourceFile.getName().substring(0, sourceFile.getName().length() - 4);
            String prefix = this.CONVERT_FILENAME_PREFIX;
            String saveFileNameNoExt = path + "\\" + prefix + "_" + fileName + "_preview";
            String file_bytes = ConvertTools.getStringFromFile(sourceFile);
            if (file_bytes != null) {
                BufferedImage previewFromEPD = this.convertToPreviewImage(file_bytes);
                if (previewFromEPD != null) {
                    ConvertTools.saveFile(previewFromEPD, saveFileNameNoExt + ".png");
                }
            }

            return 1;
        }
    }

    private static final class PanelPropeties {
        private static final String PROPERTIES_PDI144 = "convert-pdi144.properties";
        private static final String PROPERTIES_PDI20 = "convert-pdi20.properties";
        private static final String PROPERTIES_PDI27 = "convert-pdi27.properties";
        private static final String PROPERTIES_PDI441 = "convert-pdi441.properties";
        private static final String PROPERTIES_PDI74 = "convert-pdi74.properties";
        private static final String PROPERTIES_PDI102 = "convert-pdi102.properties";
        private static final String PROPERTIES_E60 = "convert-e60.properties";
        private static final String PROPERTIES_PVI19 = "convert-pvi19.properties";

        private PanelPropeties() {
        }
    }
}