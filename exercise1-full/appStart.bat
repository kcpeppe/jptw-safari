

call ..\setEnv.bat
call .\jvm.bat
if "%JAVA%" == "" goto usage
call .\compile.bat


%JAVA% -version
%JAVA% %FLAGS% %APP_PROPS% -cp target/classes com.kodewerk.profile.CheckIntegerTestHarness

goto end

:usage
echo "Java executable must be specified in setEnv.bat."

:end
pause

