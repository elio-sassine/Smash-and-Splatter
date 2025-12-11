Description of project: Smash & Splatter is a fun interactive game that tests one’s physics and mathematics knowledge on trajectory and torque.
How to run it: 
1) Install the Java JDK
2) Install JavaFX from https://gluonhq.com/products/javafx/
3) In Netbeans click on Tools -> Libraries -> New Library -> Type name of your OpenFX library (e.g., “JavaFX22”) and click OK.
4) Now under Library Classpath click on Add JAR/Folder, browse to the location where you just saved your JavaFX folder, choose all the .jar files in the lib folder of the OpenFX folder (do not choose the src file in the lib folder) -> Click OK. This provides the JavaFX library to Apache NetBeans.
5) Open the project you downloaded
6) Right-click on your project -> Properties -> Libraries -> Compile (tab) -> Classpath (click on +) -> Add Library -> JavaFX17 -> OK. Then, click on Run tab -> Modulepath (click on +) -> Add Library -> JavaFX17 -> OK
7) Right-click on your project -> Properties -> Run -> VM options: (type this in). (Replace {path_to_javafx} with the actual path to your JavaFX SDK):
		`--module-path "path_to_javafx" --add-modules javafx.controls,javafx.fxml`
8) Press the run button!

Teamwork summary: 
- Elio:
    * Torque model
    * Splatter view controller
    * Splatter sidebar controller
    * Splatter center controller
    * Smash center controller
    * Part of testing
    * Mute button
    * Splatter mascot
    * Debugging and fixing
- Antonia:
    * All graphics
    * Smash view controller
    * Smash sidebar controller
    * Main menu controller
    * Trajectory model
    * Added music
    * Part of testing
    * Smash mascot
    * Debugging and fixing
