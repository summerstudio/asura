@ECHO OFF

SET WORKING_SPACE=D:\github-space\asura

CD %WORKING_SPACE%\asura-base
CALL mvn -Dmaven.test.skip=true package

CD %WORKING_SPACE%\asura-conf
CALL mvn -Dmaven.test.skip=true package

CD %WORKING_SPACE%\asura-quartz
CALL mvn -Dmaven.test.skip=true package

CD %WORKING_SPACE%\asura-dubbo
CALL mvn -Dmaven.test.skip=true package

CD %WORKING_SPACE%\asura-log
CALL mvn -Dmaven.test.skip=true package

CD %WORKING_SPACE%\asura-monitor
CALL mvn -Dmaven.test.skip=true package

CD %WORKING_SPACE%\asura-cache
CALL mvn -Dmaven.test.skip=true package

CD %WORKING_SPACE%\asura-web
CALL mvn -Dmaven.test.skip=true package

CD %WORKING_SPACE%\asura-dao
CALL mvn -Dmaven.test.skip=true package

CALL mvn deploy:deploy-file -DgroupId=com.asura -DartifactId=com-asura-framework-base -Dversion=1.0 -Dpackaging=jar -Dfile=%WORKING_SPACE%\asura-base\target\com-asura-framework-base-1.0.jar -Durl=http://localhost:8081/nexus/content/repositories/asura-release/ -DrepositoryId=asura-release
CALL mvn deploy:deploy-file -DgroupId=com.asura -DartifactId=com-asura-framework-publish -Dversion=1.0 -Dpackaging=jar -Dfile=%WORKING_SPACE%\asura-conf\target\com-asura-framework-publish-1.0.jar -Durl=http://localhost:8081/nexus/content/repositories/asura-release/ -DrepositoryId=asura-release
CALL mvn deploy:deploy-file -DgroupId=com.asura -DartifactId=com-asura-framework-subscribe -Dversion=1.0 -Dpackaging=jar -Dfile=%WORKING_SPACE%\asura-conf\target\com-asura-framework-subscribe-1.0.jar -Durl=http://localhost:8081/nexus/content/repositories/asura-release/ -DrepositoryId=asura-release
CALL mvn deploy:deploy-file -DgroupId=com.asura -DartifactId=com-asura-framework-quartz-ext -Dversion=1.8.6 -Dpackaging=jar -Dfile=%WORKING_SPACE%\asura-quartz\target\com-asura-framework-quartz-ext-1.8.6.jar -Durl=http://localhost:8081/nexus/content/repositories/asura-release/ -DrepositoryId=asura-release
CALL mvn deploy:deploy-file -DgroupId=com.asura -DartifactId=com-asura-framework-quartz-all -Dversion=1.8.6 -Dpackaging=jar -Dfile=%WORKING_SPACE%\asura-quartz\target\com-asura-framework-quartz-all-1.8.6.jar -Durl=http://localhost:8081/nexus/content/repositories/asura-release/ -DrepositoryId=asura-release
CALL mvn deploy:deploy-file -DgroupId=com.asura -DartifactId=com-asura-framework-dubbo-scheduler -Dversion=1.0 -Dpackaging=jar -Dfile=%WORKING_SPACE%\asura-dubbo\target\com-asura-framework-dubbo-scheduler-1.0.jar -Durl=http://localhost:8081/nexus/content/repositories/asura-release/ -DrepositoryId=asura-release
CALL mvn deploy:deploy-file -DgroupId=com.asura -DartifactId=com-asura-framework-logback -Dversion=1.0 -Dpackaging=jar -Dfile=%WORKING_SPACE%\asura-log\target\com-asura-framework-logback-1.0.jar -Durl=http://localhost:8081/nexus/content/repositories/asura-release/ -DrepositoryId=asura-release
CALL mvn deploy:deploy-file -DgroupId=com.asura -DartifactId=com-asura-framework-monitor-center -Dversion=1.0 -Dpackaging=jar -Dfile=%WORKING_SPACE%\asura-monitor\target\com-asura-framework-monitor-center-1.0.jar -Durl=http://localhost:8081/nexus/content/repositories/asura-release/ -DrepositoryId=asura-release
CALL mvn deploy:deploy-file -DgroupId=com.asura -DartifactId=com-asura-framework-monitor-client -Dversion=1.0 -Dpackaging=jar -Dfile=%WORKING_SPACE%\asura-monitor\target\com-asura-framework-monitor-client-1.0.jar -Durl=http://localhost:8081/nexus/content/repositories/asura-release/ -DrepositoryId=asura-release
CALL mvn deploy:deploy-file -DgroupId=com.asura -DartifactId=com-asura-framework-cache -Dversion=1.0 -Dpackaging=jar -Dfile=%WORKING_SPACE%\asura-cache\target\com-asura-framework-cache-1.0.jar -Durl=http://localhost:8081/nexus/content/repositories/asura-release/ -DrepositoryId=asura-release
CALL mvn deploy:deploy-file -DgroupId=com.asura -DartifactId=com-asura-framework-web-oauth -Dversion=1.0 -Dpackaging=jar -Dfile=%WORKING_SPACE%\asura-web\target\com-asura-framework-web-oauth-1.0.jar -Durl=http://localhost:8081/nexus/content/repositories/asura-release/ -DrepositoryId=asura-release
CALL mvn deploy:deploy-file -DgroupId=com.asura -DartifactId=com-asura-framework-web-velocity -Dversion=1.0 -Dpackaging=jar -Dfile=%WORKING_SPACE%\asura-web\target\com-asura-framework-web-velocity-1.0.jar -Durl=http://localhost:8081/nexus/content/repositories/asura-release/ -DrepositoryId=asura-release
CALL mvn deploy:deploy-file -DgroupId=com.asura -DartifactId=com-asura-framework-dao-ibatis -Dversion=1.0 -Dpackaging=jar -Dfile=%WORKING_SPACE%\asura-dao\target\com-asura-framework-dao-ibatis-1.0.jar -Durl=http://localhost:8081/nexus/content/repositories/asura-release/ -DrepositoryId=asura-release

ECHO over!!!!!
PAUSE