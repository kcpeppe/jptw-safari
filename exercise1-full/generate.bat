
call ..\setEnv.bat

%JAVA% -classpath target/classes com.kodewerk.profile.GenerateData 25000000

pause
