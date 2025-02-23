@echo off
setlocal enabledelayedexpansion

set "JAVA_HOME=C:\Program Files\Java\jdk-23"
set "JAVAFX_HOME=C:\Program Files\Java\javafx-sdk-23.0.2"

echo Starting Song of Twelve Feathers...
"%JAVA_HOME%\bin\java" --module-path "%JAVAFX_HOME%\lib" --add-modules javafx.controls,javafx.fxml -jar SongOfTwelveFeathers.jar

if errorlevel 1 (
    echo Game execution failed!
    pause
    exit /b 1
)

pause