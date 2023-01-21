#Создаем папку для .class file
rm -rf target
mkdir -p target

#Используем -d для указания отдельного места назначения файла класса.
javac -d ./target/ src/java/edu/school21/printer/*/*.java

#Копируем
cp -R src/resources ./target

jar cfm target/images-to-chars-printer.jar src/manifest.txt -C target/ .

java -jar target/images-to-chars-printer.jar . 0