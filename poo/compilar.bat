rmdir build /S /Q

mkdir build

javac --module-path "C:\Users\beatr\OneDrive\Documentos\IDE_Programa\javafx-sdk-21.0.2\lib" --add-modules javafx.controls,javafx.fxml -cp .;./src;./lib/mariadb-java-client-3.5.1.jar -d ./build ./src/edu/curso/*.java

java --module-path "C:\Users\beatr\OneDrive\Documentos\IDE_Programa\javafx-sdk-21.0.2\lib" --add-modules javafx.controls,javafx.fxml -cp .;./build;./lib/mariadb-java-client-3.5.1.jar %1