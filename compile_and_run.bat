@echo off
echo Compiling...

:: Set JavaFX path
set JAVAFX_PATH=javafx-sdk-23.0.2\lib

:: Create bin directory if not exists
if not exist "bin" mkdir bin

:: Clean bin directory
del /Q bin\*

:: Compile
javac --module-path "%JAVAFX_PATH%" ^
--add-modules javafx.controls,javafx.fxml ^
-d bin ^
-sourcepath src ^
src/application/MainApp.java

:: Check compilation status
if %errorlevel% equ 0 (
    echo Compilation successful! Running the game...
    :: Run the application
    java --module-path "%JAVAFX_PATH%" ^
    --add-modules javafx.controls,javafx.fxml ^
    -cp bin ^
    application.MainApp
) else (
    echo Compilation failed! Check the errors above.
)

pause