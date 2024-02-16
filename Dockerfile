# 최신 17-jdk 이미지로 부터 시작
FROM openjdk:17-jdk

# 인자 정리
ARG JAR_FILE=build/libs/*.jar

# 앞에는 HOST OS의 현재 폴더를 의미
# 뒤에는 컨테이너의 현재 폴더(WORKDIR)를 의미
COPY ${JAR_FILE} app.jar

# docker container에서 실행되는 명령어
ENTRYPOINT ["java","-jar","-DDB_DRIVER=com.mysql.cj.jdbc.Driver","-DDB_PASSWORD=vdongv1620","-DDB_URL=jdbc:mysql://zigzzang-db.c74u6wukkm5o.ap-northeast-2.rds.amazonaws.com:3306/zigzzang","-DDB_USER=admin","/app.jar"]