/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.cameraserver.CameraServer;


/**
* Add your docs here.
*/
public class JeVois {
   // Serial Port Constants
   private static final int BAUD_RATE = 115200;

   // MJPG Streaming Constants
   private static final int MJPG_STREAM_PORT = 1181;

   // Confgure the camera to stream debug images or not.
   private boolean broadcastUSBCam = false;

   // When streaming, use this set of configuration
   private static final int STREAM_WIDTH_PX = 320;// 640;
   private static final int STREAM_HEIGHT_PX = 240 ;//300;
   private static final int STREAM_RATE_FPS = 29; //15;

   // Serial port used for getting target data from JeVois
   private SerialPort visionPort = null;

   // USBCam and server used for broadcasting a webstream of what is seen
   private UsbCamera visionCam = null;
   private MjpegServer camServer = null;

   // Status variables
   private boolean dataStreamRunning = false;
   private boolean camStreamRunning = false;
   private boolean visionOnline = false;

   public JeVois(boolean useUSBStream) {
       int retry_counter = 0;

       // Retry strategy to get this serial port open.
       // I have yet to see a single retry used assuming the camera is plugged in
       // but you never know.
       /*while (visionPort == null && retry_counter++ < 10) {
           try {
               System.out.print("Creating JeVois SerialPort...");
               visionPort = new SerialPort(BAUD_RATE, SerialPort.Port.kUSB);
               System.out.println("SUCCESS!!");
           } catch (Exception e) {
               System.out.println("FAILED!!");
               e.printStackTrace();
               Timer.delay(500);
               System.out.println("Retry " + Integer.toString(retry_counter));
           }
       }*/
       // Report an error if we didn't get to open the serial port
       // if (visionPort == null) {
       //     DriverStation.reportError("Cannot open serial port to JeVois. Not starting vision system.", false);
       //     return;
       // }


      System.out.println("about to start streamer");
       start();
   }

   public void start() {
       //if (broadcastUSBCam) {
           // Start streaming the JeVois via webcam
           // This auto-starts the serial stream
           System.out.println("start has started");
           startCameraStream();
     
   }

   public void stop() {
   //    if (broadcastUSBCam) {
           // Start streaming the JeVois via webcam
           // This auto-starts the serial stream
           stopCameraStream();
     //  }
   }

 
    /**
    * Open an Mjpeg streamer from the JeVois camera
    */
   private void startCameraStream(){

       try{
           System.out.print("Starting JeVois Cam Stream...");
           visionCam = new UsbCamera("VisionProcCam", 0);
        //  visionCam =  CameraServer.getInstance().startAutomaticCapture("VisionProcCam", 0);
          // visionCam.setPixelFormat(PixelFormat.kYUYV);
           visionCam.setVideoMode(PixelFormat.kYUYV, STREAM_WIDTH_PX, STREAM_HEIGHT_PX, STREAM_RATE_FPS);
           //visionCam.setVideoMode(PixelFormat.kBGR, STREAM_WIDTH_PX, STREAM_HEIGHT_PX, STREAM_RATE_FPS);
           camServer = new MjpegServer("VisionCamServer", MJPG_STREAM_PORT);
           camServer.setSource(visionCam);
         //  CameraServer.getInstance().startAutomaticCapture(visionCam);
           camStreamRunning = true;
          
           dataStreamRunning = false;
           System.out.println("SUCCESS!!");
       } catch (Exception e) {
           System.out.println("error cannot start camera");
           DriverStation.reportError("Cannot start camera stream from JeVois", false);
           e.printStackTrace();
       }
   }

    /**
    * Cease the operation of the camera stream. Unknown if needed.
    */
   private void stopCameraStream(){
       if(camStreamRunning){
           camServer.free();
           visionCam.free();
           camStreamRunning = false;
           dataStreamRunning = false;
       }
   }

   public boolean getCameraStatus(){
       return camStreamRunning;
   }
}


