FROM openjdk:11 as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:11
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
ARG EXTRACTED=/workspace/app/target/extracted

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
COPY --from=build ${EXTRACTED}/spring-boot-loader/ ./
EXPOSE 8080
ENTRYPOINT ["java","-cp","app:app/lib/*","com.dingweibing.interview.InterviewApplication"]