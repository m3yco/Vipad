# Vipad
GUI Development for an simple Editor with SWT

To start the application, clone the git project
```bash
git clone https://github.com/m3yco/Vipad.git
```

Then you have to go into the folder TextEditor and build the project
```bash
mvn clean install
```

When you have problems in Eclipse, then update the project
```bash
mvn clean install -U
```

The default language is english, but you can add your own Messagebundle with a properties file or change is into german
```bash
src\main\resources
```

To change the language add the arguments for the variable values locale and country
```bash
Vipad de DE
```
