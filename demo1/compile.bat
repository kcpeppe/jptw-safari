
call ..\setEnv.bat
if "%JAVA%" == "" goto usage


set SOURCES=src/main/java/com/kodewerk/cheapdrink/*.java
set TARGET=target/classes

%JAVAC% -d %TARGET% %SOURCES% 
goto end

:usage
echo Java executable must be specified in setEnv.bat

:end
pause

