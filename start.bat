REM %1 absolute path from 
REM %2 absolute path to

set JAVA_HOME=D:\Work\Temp\ConverterF303\jre-17.0.3
"%JAVA_HOME%\bin\java" -Xms256m -Xmx8192m -jar converter_f303-1.0.1.jar %1 %2
