Simple JSF e-shop, to run it, you have to do the following steps:

1) Download JDK 8 or higher and Wildfly 16 (app uses h2 DB which is included in Wildfly)
2) Configure the DB server - in the Wildflyfolder/standalone/standalone.xml find the `<datasources>` element and edit it as following:
      ```
      <datasource jndi-name="java:jboss/datasources/ExampleDS"
                  pool-name="ExampleDS" enabled="true" use-java-context="true"
                  statistics-enabled="${wildfly.datasources.statistics-enabled:${wildfly.statistics-enabled:false}}">
                    <connection-url>jdbc:h2:tcp://localhost/~/h2DB_localFiles/test-eshop</connection-url>
                    <driver>h2</driver>
                    <security>
                        <user-name>sa</user-name>
                        <password>sa</password>
                    </security>
      </datasource>
      ```
3) go to ..\modules\system\layers\base\com\h2database\h2\main and run the DB from terminal: java -jar h2-x.y.zzz.jar
4) log to the db console - the password is "sa" so as the username
5) run the SQL script (folder "h2 initialization SQL) and create the DB schema
6) go to IDE, setup the server and set artifact to deploy
7) run the app  
