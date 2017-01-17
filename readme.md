# CV_finalproject

    λ Final Lab Practice 	17.01.2017
    λ -
    λ Title:	Detection and Recognition of Text in Natural Images 
    λ Authors:	Simone Hierhold & Benjamin Krenn
         

#1. Introduction

	This project has been created for the course "Computer Vision" at the Universidad de Alcalá.
	It includes a simple Spring (Java) web application on which a user can upload an image. This image
	will then passed to a compiled MATLAB script which runs in the MATLAB Runtime. The MATLAB program
	tries to detect and recognize text within the image via OCR and returns the found text and an output 
	image with a bounding box around the found text to the Java application.
	The resulting text and the output image will be shown to the user. 
	Furthermore the user can request a spoken representation of the output text.


#2. Requirements & Installation

	Currently the binary (jar) is only tested on Windows 10! For Linux the application must be build from source!

	Following programs MUST be installed in order to run the application:

	(1) Java Runtime Environment 7 (or later)
	    http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html

	(2) Matlab Runtime R2016b (9.1)
		https://www.mathworks.com/products/compiler/mcr.html

		More information: https://www.mathworks.com/help/compiler_sdk/cxx/about-the-matlab-runtime.html


#3.	Running the Application

	(1) Double-click on "run.bat"
	(2) Wait for the server to start (if the prompt is not showing new text the server is running)
	(3) Access: http://localhost:8080 with your Browser of choice.
	(4) The application should now be up and running.