git pull
pushd DB2Project-fe/ && npm start;
popd && pushd DB2Project-be/ && ./mvnw spring-boot:run 
popd
