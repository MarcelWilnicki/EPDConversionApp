package com.company.Convert;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

class ConvertView extends JFrame {
    public static String name = "ConvertPDI20-0.0.9.jar";
    public int state = 0;
    public String info;
    public static JLabel label = null;
    public static String instruction = "Drag & drop any file(s) here.";

    public ConvertView() {
        Properties propsM = Props.propertiesReadResource("convert-general.properties");
        Properties props = Props.propertiesReadResource(propsM.getProperty("ConvertAppType"));
        name = props.getProperty("ApplicationVersionString");
        this.setTitle(name);
        int w = 350;
        int h = 350;
        JComponent cp = (JComponent) this.getContentPane();
        cp.setTransferHandler(new ConvertView.MyFileTransferHandler());
        label = new JLabel(instruction, 0);
        cp.add(label);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(3);
        this.setSize(w, h);
        this.setLocation((screenSize.width - w) / 2, (screenSize.height - h) / 2);
    }

    public static void main(String[] args) {
        (new ConvertView()).setVisible(true);
    }

    public static void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException var3) {
        }

    }

    class MyFileTransferHandler extends TransferHandler {
        MyFileTransferHandler() {
        }

        public boolean canImport(JComponent arg0, DataFlavor[] arg1) {
            for (int i = 0; i < arg1.length; ++i) {
                DataFlavor flavor = arg1[i];
                if (flavor.equals(DataFlavor.javaFileListFlavor)) {
                    return true;
                }

                if (flavor.equals(DataFlavor.stringFlavor)) {
                    return true;
                }
            }

            return false;
        }

        public boolean importData(JComponent comp, Transferable t) {
            DataFlavor[] flavors = t.getTransferDataFlavors();

            for (int i = 0; i < flavors.length; ++i) {
                DataFlavor flavor = flavors[i];

                try {
                    if (flavor.equals(DataFlavor.javaFileListFlavor)) {
                        List l = (List) t.getTransferData(DataFlavor.javaFileListFlavor);
                        Iterator iter = l.iterator();

                        while (iter.hasNext()) {
                            File file = (File) iter.next();
                            System.out.println("GOT FILE: " + file.getAbsolutePath());
                            Convert convert = new Convert();
                            convert.setColorDepth(1);
                            convert.saveEpdImagePack(file, (String) null);
                            convert.saveEpdPreview(file, (String) null);
                            convert.saveHeaderPreview(file, (String) null);
                            convert.setColorDepth(4);
                            convert.saveEpdImagePack(file, (String) null);
                            convert.saveEpdPreview(file, (String) null);
                            convert.saveHeaderPreview(file, (String) null);
                        }

                        return true;
                    }

                    if (flavor.equals(DataFlavor.stringFlavor)) {
                        String fileOrURL = (String) t.getTransferData(flavor);
                        System.out.println("GOT STRING: " + fileOrURL);

                        try {
                            URL url = new URL(fileOrURL);
                            System.out.println("Valid URL: " + url.toString());
                            return true;
                        } catch (MalformedURLException var10) {
                            System.err.println("Not a valid URL");
                            return false;
                        }
                    }
                } catch (IOException var11) {
                    System.err.println("IOError getting data: " + var11);
                } catch (UnsupportedFlavorException var12) {
                    System.err.println("Unsupported Flavor: " + var12);
                }
            }

            Toolkit.getDefaultToolkit().beep();
            return false;
        }
    }
}
C:\Users\nao\Desktop\E-paper\JavaProgram\src\com\company\Convert