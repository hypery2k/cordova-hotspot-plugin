/* 
 * Copyright (C) 2013-2014 www.Andbrain.com 
 * Faster and more easily to create android apps
 * 
 * */
package com.mady.wifi.datatransfer;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;


public class WifiSocket {
    /**
     * Logging Tag
     */
    private static final String LOG_TAG = "WifiSocket";
    static final int BUFFER = 1024;
    public String receivedMessage = "";
    Context mContext;
    SimpleAsynTask mTask;

    public WifiSocket(Context c) {
        mContext = c;
        mTask = new SimpleAsynTask();
    }

    @SuppressLint("NewApi")
    public static void sendFileSocket(String host, int port, String fileName) {
        Socket socket = null;
        DataOutputStream dataOS = null;
        FileInputStream inpStr = null;
        OutputStream outStr = null;
        try {
            socket = new Socket(host, port);
            dataOS = new DataOutputStream(socket.getOutputStream());

            File file = new File(fileName);
            int size = (int) (file.length());
            dataOS.writeInt(size);
            inpStr = new FileInputStream(file);
            outStr = socket.getOutputStream();

            int read;
            byte[] buffer = new byte[BUFFER];
            while ((read = inpStr.read(buffer, 0, BUFFER)) > 0) {
                outStr.write(buffer, 0, read);
            }

            outStr.flush();

        } catch (IOException e) {
            Log.e(LOG_TAG, "I/O error occurred.", e);
        } finally {
            try {
                if (outStr != null) {
                    outStr.close();
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "I/O error during closing stream writer.", e);
            }
            try {
                if (dataOS != null) {
                    dataOS.close();
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "I/O error during closing data stream writer.", e);
            }
            try {
                if (inpStr != null) {
                    inpStr.close();
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "I/O error during closing reader.", e);
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "I/O error during closing socket.", e);
            }
        }
    }

    public static void receiveFileSocket(int port, String fileName) {
        Socket socket = null;
        DataInputStream dataOS = null;
        InputStream inpStr = null;
        FileOutputStream outStr = null;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            socket = server.accept();
            dataOS = new DataInputStream(socket.getInputStream());

            int size = dataOS.readInt();
            outStr = new FileOutputStream(fileName);
            inpStr = socket.getInputStream();

            byte[] buffer = new byte[BUFFER];
            ByteArrayOutputStream byteOutStr = new ByteArrayOutputStream();
            while (size >= BUFFER) {
                size -= getChunk(inpStr, byteOutStr, buffer);
                outStr.write(byteOutStr.toByteArray());
                byteOutStr.reset();
            }

            int data = 0;
            while ((data = inpStr.read()) != -1)
                byteOutStr.write(data);

            outStr.write(byteOutStr.toByteArray());
        } catch (IOException e) {
            Log.e(LOG_TAG, "I/O error.", e);
        } finally {
            try {
                if (server != null) {
                    server.close();
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "I/O error during closing server.", e);
            }
            try {
                if (outStr != null) {
                    outStr.close();
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "I/O error during closing stream writer.", e);
            }
            try {
                if (dataOS != null) {
                    dataOS.close();
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "I/O error during closing data stream writer.", e);
            }
            try {
                if (inpStr != null) {
                    inpStr.close();
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "I/O error during closing reader.", e);
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "I/O error during closing socket.", e);
            }
        }
    }

    static int getChunk(InputStream inputStr, ByteArrayOutputStream bytes, byte[] buffer) throws
            IOException {
        int read = inputStr.read(buffer, 0, BUFFER);
        while (read < BUFFER) {
            read += inputStr.read(buffer, read, BUFFER - read);
        }
        bytes.write(buffer);
        return read;
    }

    /**
     * Method to Receive message text From  Network .
     *
     * @param port use to  open internal port to receiver message text,
     *             numMessags number of messages want to receive ,
     *             task instructions  you want to execute when receive message
     */
    public void receiveMessage(final int port, final int numMessags, final Runnable task) {
        mTask.runAsynTask(new Runnable() {
            public void run() {
                receiveText(port, numMessags, task);

            }

        });
    }

    public String receiveText(int port, int numMessages, Runnable task) {
        ServerSocket serverSocket = null;
        Socket clientSocket;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        String message = "";

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            Log.e(LOG_TAG, "I/O error.", e);
        }
        try {
            int i = 0;
            while (i < numMessages) {
                clientSocket = serverSocket.accept();   //accept the client connection
                inputStreamReader = new InputStreamReader(clientSocket.getInputStream(), Charset.forName("UTF-8"));
                bufferedReader = new BufferedReader(inputStreamReader); //get the client message
                message = bufferedReader.readLine();
                receivedMessage = message;
                task.run();
                inputStreamReader.close();
                clientSocket.close();
                bufferedReader.close();
                i++;
            }

        } catch (IOException ex) {
            Log.e(LOG_TAG, "I/O error.", ex);
        }
        try {

            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "I/O error during closing reader.", e);
        }
        return message;

    }

    /******************************************************
     * /**
     * Method to send File in  Network.
     *
     * @param ipAddr   ,port  receiver of file
     * @param filePath path of file you want to send
     */

    public void sendFile(final String ipAddr, final int port, final String filePath) {
        mTask.runAsynTask(new Runnable() {
            public void run() {

                sendFileSocket(ipAddr, port, filePath);

            }

        });
    }

    /**
     * Method to Receive File From  Network .
     *
     * @param port     use to  open internal port to receiver file
     * @param filePath path for saving the file received
     */
    public void receiveFile(final int port, final String filePath) {
        mTask.runAsynTask(new Runnable() {
            public void run() {

                receiveFileSocket(port, filePath);

            }

        });
    }

}
