@ECHO OFF

SET WORKING_SPACE=D:\github-space\asura


CD %WORKING_SPACE%\asura-message
CALL mvn -Dmaven.test.skip=true install




CALL mvn deploy:deploy-file -DgroupId=com.asura -DartifactId=com-asura-framework-message-consumer -Dversion=1.0 -Dpackaging=jar -Dfile=%WORKING_SPACE%\asura-message\target\com-asura-framework-message-consumer-1.0.jar -Durl=http://localhost:8081/nexus/content/repositories/asura-release/ -DrepositoryId=asura-release
CALL mvn deploy:deploy-file -DgroupId=com.asura -DartifactId=com-asura-framework-message-provider -Dversion=1.0 -Dpackaging=jar -Dfile=%WORKING_SPACE%\asura-message\target\com-asura-framework-message-provider-1.0.jar -Durl=http://localhost:8081/nexus/content/repositories/asura-release/ -DrepositoryId=asura-release


ECHO over!!!!!
PAUSE