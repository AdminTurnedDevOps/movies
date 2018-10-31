
**List all available tasks**
> `./gradlew tasks --all`

Some of the available tasks come from the applied plugins defined in `build.gradle` 
(for example the java build task which generates the jar artifact).


**Build the application**
> `./gradlew build`

Executes the build task from the java plugin and generates a jar file in `build/libs/`.  
To see the task dependencies of the build task run: 
> `./gradlew build taskTree`


**Clean the build directory**
> `./gradlew clean`

