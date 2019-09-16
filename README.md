# WW

Clone the Repository
    git clone https://github.com/lehersabs/WW.git
    
Import as a Maven Project    

***********************
Steps to Run the Tests:
***********************

Run the testng.xml file. You can comment the classes which are not needed to be tested or run them all together.

Question 1: 
    File to be tested is placed at "./src/main/resources/config.properties".
    1(a) Change the filepath to check the doesFileExist(string path) function with both valid and invalid path in the config            file.
    1(b) Add/Modify the items in the json as the entered examples in the file.

Question 2: 
    The chromedriver executable file(src->main->resources) in the project works for Chrome version 76.
    Please replace the chromedriver based on the chrome browser version where the cases are being run. (Chromedriver               dependency for selenium)

    dayOfWeek needs to be entered in the testng.xml file as parameter value.

Question 3:

    nth value needs to be entered as the value for the parameter, nthSmallestNumberInput, in testng.xml file.
