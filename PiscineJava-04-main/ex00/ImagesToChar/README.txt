#Создаем папку для .class file
rm -rf target
mkdir -p target

#Используем -d для указания отдельного места назначения файла класса.
javac -d ./target/ src/java/edu/school21/printer/*/*.java

#Используем -cp path or -classpath path  (где найти файлы классов)
java -cp ./target edu.school21.printer.app.Program . 0 ${PWD}/it.bmp


