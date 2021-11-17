
# TelecoApp

TelecoApp is a Java application that simulates the web services of a telecommunication provider.

## Building
### Backend
Inside the folder DB2Pject-be run the following commands:

```bash
./mvnw spring-boot:run 
```
The server will be listening on default port 8080. In order to change it you can edit the application.properties file uncommenting and setting the following line

```json 
# server.port=8081
```

### Frontend
Go to DB2Project-fe folder and install with npm the following dependencies:
- angular/cli
- angular/cdk
- chartjs 
- ng2-charts@2.3.3

Then run the following command:
```bash
ng serve —-proxy-config proxy.conf.json
```

After this you should be able to access the application on 
```
localhost:4200
```

## Online App
If you don’t want to build the app you can find the application alive on this url ([not available yet](http://telecoapp.dn0.it))

## License
[MIT](https://choosealicense.com/licenses/mit/)
