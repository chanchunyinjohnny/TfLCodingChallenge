# TfLCodingChallenge
o	How to build the code
    Please go to the folder that contains the root pom of this project and do maven clean install.
 
o	How to run the output
    - Set up project SDK
    - Add spring boot support in idea
    - Run TflCodingChallengeApplication class
    - go to http://localhost:8080/index.html
    - key in the road name you name
    - the result will be displayed in the result section

o	How to run any tests that you have written
    Please open the IDE you use, and use the ApplicationControllerTestes class.

o	any assumptions that you’ve made
    - Assumed that when the road name is valid, the first element returned is the result we need.
    - Assumed that port 8080 of the local machine is not occupied
    - Assumed that the application is run in a browser that supports javaScript
    - Assumed that you have internet connection
    - The readme instruction assumes that you are using IntelliJ

o	anything else you think is relevant
    please adjust the application.properties file before going this application.

o	How to configure my application to use a different App ID and API key
    - please adjust the placeholder for app_id and app_key in the application.properties file.