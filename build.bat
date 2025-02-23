REM filepath: /c:/Users/godof/Documents/CEDT/ProgMeth/ZPRO/new_game/javafx-project/build.bat
@echo off
setlocal enabledelayedexpansion

REM Set specific Java and JavaFX paths
set "JAVA_HOME=C:\Program Files\Java\jdk-23"
set "JAVAFX_HOME=C:\Program Files\Java\javafx-sdk-23.0.2"

REM Validate Java installation
if not exist "%JAVA_HOME%\bin\javac.exe" (
    echo Error: Java compiler not found in %JAVA_HOME%\bin
    pause
    exit /b 1
)

REM Validate JavaFX SDK
if not exist "%JAVAFX_HOME%\lib" (
    echo Error: JavaFX SDK not found in %JAVAFX_HOME%\lib
    pause
    exit /b 1
)

echo Found Java at: %JAVA_HOME%
echo Found JavaFX at: %JAVAFX_HOME%

REM Add Java to PATH
set "PATH=%JAVA_HOME%\bin;%PATH%"

REM Create bin directory if it doesn't exist
if not exist bin (
    echo Creating bin directory...
    mkdir bin
)

echo Compiling Java files...
javac --module-path "%JAVAFX_HOME%\lib" --add-modules javafx.controls,javafx.fxml -d bin ^
src\characters\*.java ^
src\items\*.java ^
src\buffs\*.java ^
src\debuffs\*.java ^
src\fields\*.java ^
src\application\*.java ^
src\game\*.java ^
src\ui\*.java ^
src\ui_screens\*.java ^
src\player\*.java ^
src\enemies\*.java ^
src\skills\*.java ^
src\effects\*.java ^
src\managers\*.java

if errorlevel 1 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Creating JAR file...
if exist SongOfTwelveFeathers.jar del SongOfTwelveFeathers.jar
jar cvfm SongOfTwelveFeathers.jar manifest.txt -C bin .

if errorlevel 1 (
    echo JAR creation failed!
    pause
    exit /b 1
)

echo Build completed successfully!
pause