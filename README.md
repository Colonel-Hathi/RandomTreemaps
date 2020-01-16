# RandomTreemaps

A generator for Random Treemaps; For UU.nl projects; Provided by VIG group


## Authors
* Michael Behrisch (m.behrisch@uu.nl)


## Associated Projecs

No current projects.


## Code Structure

```
${basedir}
├── src
│   ├── main
│   │   ├── java (contains runnable code fragments)
│   │   └── resources (nothing here)
│   ├── net.bouthier (contains all treemap code)
│   └── test
│       ├── java (nothing here)
│       └── resources (nothing here)
├── data
│   ├── large (will be generated if not available and contains large images) 
│   └── small (will be generated if not available and contains thumbnails) 
└── target
    └── classes
```

## Running 

Execute either as standard java project: 

### Main Class

```java
MainDataCreator
```

### maven

(pls. adapt accordingly)

```
mvn exec:java -Dexec.mainClass="MainDataCreator" -Dexec.args="arg0 arg1 arg2"  
```