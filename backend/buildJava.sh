cd microAuth

 mvn spring-boot:build-image -DskipTests

cd ..

cd microChallenge

 mvn spring-boot:build-image -DskipTests

cd ..

cd microOrquestrador

 mvn spring-boot:build-image -DskipTests

cd ..

cd microUsers

 mvn spring-boot:build-image -DskipTests
