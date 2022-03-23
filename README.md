
# TelecoApp

TelecoApp is a Java application that simulates the web services of a telecommunication provider.

## Building

### Database
Import the copy of the database from the file db2_pdb_backup.sql through the commands:
```bash
> mysql -u root -p
mysql> create database db2_pdb;
mysql> use db2_pdb;
mysql> source <path_to_dump>;

```


### Backend
Inside the folder DB2Pject-be run the following commands:

```bash
./mvnw spring-boot:run 
```
The server will be listening on default port 8080. In order to change it you can edit the application.properties file uncommenting and setting the following line

```bash
# server.port=8081
```

### Frontend
Go to DB2Project-fe folder and install through npm all the dependecies with the following command:
```bash
npm install
```

Then run the following command:
```bash
npm start
```

After this you should be able to access the application on 
```bash
localhost:4200
```

## Online App
If you don’t want to build the app you can find the application alive on this url ([TelecoApp](http://db2project.duckdns.org))

## License
[MIT](https://choosealicense.com/licenses/mit/)
