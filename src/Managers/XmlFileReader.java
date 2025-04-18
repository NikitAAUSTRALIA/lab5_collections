package Managers;

import Entities.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.regex.*;


/**
 * Класс для чтения/записи в xml.
 */
public class XmlFileReader {
    String filePath;
    ConsoleManager console;

    public XmlFileReader(String filePath, ConsoleManager console){
        this.filePath = filePath;
        this.console = console;
    }

    /**
     * Прочитать файл.
     *
     * @throws IOException ошибка при вводе/выводе
     * @return Текст файла
     */
    public String readFile() throws IOException {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            console.println("Файла не найдено, попробуйте снова");
            return null;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 1);
        int i;
        String file = "";
        String now_str = "";
        while((i = bufferedInputStream.read())!= -1){
            now_str = now_str + (char)i;
            if ((char)i == '\n'){
                file += now_str;
                now_str = "";
            }
        }
        file += now_str;
        return file;
    }

    /**
     * Проверить и сохранить объекты в файл
     *
     * @param file Текст файла
     * @param collection Коллекция для сохранения
     */
    public void validFileText(String file, CollectionManager collection){
        Pattern pattern = Pattern.compile("<humanBeing>(?:(?!<humanBeing>)[\\s\\S])*</humanBeing>");
        Matcher matcher = pattern.matcher(file);
        Long id = 0L;
        while (matcher.find()) {
            boolean valid = true;
            Coordinates nowCoordinates = null;
            String HumanBeingName = "";
            boolean RealHeroHumanBeing = false;
            boolean HasToothpickHumanBeing = false;
            Integer ImpactSpeedHumanBeing = 0;
            WeaponType WeaponTypeHumanBeing = WeaponType.BAT;
            Mood MoodHumanBeing = Mood.CALM;
            Car CarHumanBeing = null;
            String nowHumanBeing = matcher.group();
            Pattern patternName = Pattern.compile("<name>([\\s\\S]*?)</name>");
            Matcher matcherName = patternName.matcher(nowHumanBeing);
            if (matcherName.find()){
                HumanBeingName = matcherName.group(1);
            }
            else{
                valid = false;
            }
            Pattern patternCoordinates = Pattern.compile("<coordinates>(?:(?!<coordinates>)[\\s\\S])*</coordinates>");
            Matcher matcherCoordinates = patternCoordinates.matcher(nowHumanBeing);
            if (matcherCoordinates.find()){
                String coordinatesText = matcherCoordinates.group();
                Pattern patternX = Pattern.compile("<x>([\\s\\S]*?)</x>");
                Matcher matcherX = patternX.matcher(coordinatesText);
                Pattern patternY = Pattern.compile("<y>([\\s\\S]*?)</y>");
                Matcher matcherY = patternY.matcher(coordinatesText);
                try {
                    if (matcherX.find() && matcherY.find()) {
                        nowCoordinates = new Coordinates(Double.parseDouble(matcherX.group(1)), Integer.parseInt(matcherY.group(1)));
                    }
                    else{
                        valid = false;
                    }
                }
                catch (Exception ex){
                    valid = false;
                    console.printError(ex.toString());
                }
            }
            else{
                valid = false;
            }
            Pattern patternRealHero = Pattern.compile("<realHero>([\\s\\S]*?)</realHero>");
            Matcher matcherRealHero = patternRealHero.matcher(nowHumanBeing);
            if (matcherRealHero.find()){
                RealHeroHumanBeing = matcherRealHero.group(1).equals("true");
            }
            Pattern patternHasToothpick = Pattern.compile("<hasToothpick>([\\s\\S]*?)</hasToothpick>");
            Matcher matcherHasToothpick = patternHasToothpick.matcher(nowHumanBeing);
            if (matcherHasToothpick.find()){
                HasToothpickHumanBeing = matcherHasToothpick.group(1).equals("true");
            }
            Pattern patternImpactSpeed = Pattern.compile("<impactSpeed>([\\s\\S]*?)</impactSpeed>");
            Matcher matcherImpactSpeed = patternImpactSpeed.matcher(nowHumanBeing);
            if (matcherImpactSpeed.find()){
                try{
                    ImpactSpeedHumanBeing = Integer.parseInt(matcherImpactSpeed.group(1));
                }
                catch (Exception ex){
                    valid = false;
                    console.printError(ex.toString());
                }
            }
            else{
                valid = false;
            }
            Pattern patternWeaponType = Pattern.compile("<weaponType>([\\s\\S]*?)</weaponType>");
            Matcher matcherWeaponType = patternWeaponType.matcher(nowHumanBeing);
            if (matcherWeaponType.find()){
                try{
                    WeaponTypeHumanBeing = WeaponType.valueOf(matcherWeaponType.group(1));
                }
                catch (Exception ex){
                    valid = false;
                    console.printError(ex.toString());
                }
            }
            else{
                valid = false;
            }
            Pattern patternMood = Pattern.compile("<mood>([\\s\\S]*?)</mood>");
            Matcher matcherMood = patternMood.matcher(nowHumanBeing);
            if (matcherMood.find()){
                try {
                    MoodHumanBeing = Mood.valueOf(matcherMood.group(1));
                }
                catch (Exception ex){
                    valid = false;
                    console.printError(ex.toString());
                }
            }
            else{
                valid = false;
            }
            Pattern patternCar = Pattern.compile("<car>([\\s\\S]*?)</car>");
            Matcher matcherCar = patternCar.matcher(nowHumanBeing);
            if (matcherCar.find()){
                Pattern patternCarName = Pattern.compile("<name>([\\s\\S]*?)</name>");
                Matcher matcherCarName = patternCarName.matcher(matcherCar.group());
                if (matcherCarName.find()){
                    CarHumanBeing = new Car(matcherCarName.group(1));
                }
                else{
                    valid = false;
                }
            }
            else{
                valid = false;
            }
            if (valid){
                id += 1;
                HumanBeing validHumanBeing = new HumanBeing(id, HumanBeingName, nowCoordinates, LocalDate.now(), RealHeroHumanBeing, HasToothpickHumanBeing, ImpactSpeedHumanBeing, WeaponTypeHumanBeing, MoodHumanBeing, CarHumanBeing);
                collection.add(validHumanBeing);
            }
            else{
                console.println("HumanBeing-сущность не прошла валидацию, записана в xml-файл неверно");
            }
        }
    }

    /**
     * Проверить, можно ли читать файл.
     *
     * @param file    файл
     * @param console менеджер консоли
     * @return true, если можно читать файл, иначе false
     */
    public static boolean canRead(File file, ConsoleManager console) {
        if (!file.exists()) {
            console.printError("Файл не найден");
            return false;
        }

        if (!file.canRead()) {
            console.printError("Нет прав на чтение файла");
            return false;
        }

        if (!file.isFile()) {
            console.printError("Указанный путь не является файлом");
            return false;
        }

        return true;
    }


    /**
     * Сохранить коллекцию в файл
     *
     * @param collection    файл
     * @return true, если сохранился, иначе false
     */
    public boolean saveCollection(CollectionManager collection){
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(filePath), StandardCharsets.UTF_8)) {
            writer.write("<collection> \n");
            for (HumanBeing obj : collection.getCollection()){
                writer.write("    <humanBeing> \n");
                writer.write("        <name>" + obj.getName() + "</name> \n");
                writer.write("        <coordinates> \n");
                writer.write("            <x>" + obj.getCoordinates().getX().toString() + "</x> \n");
                writer.write("            <y>" + obj.getCoordinates().getY().toString() + "</y> \n");
                writer.write("        </coordinates> \n");
                writer.write("        <realHero>" + obj.getRealHero() + "</realHero> \n");
                writer.write("        <hasToothpick>" + obj.getHasToothpick() + "</hasToothpick> \n");
                writer.write("        <impactSpeed>" + obj.getImpactSpeed().toString() + "</impactSpeed> \n");
                writer.write("        <weaponType>" + obj.getWeaponType().toString() + "</weaponType> \n");
                writer.write("        <mood>" + obj.getMood().toString() + "</mood> \n");
                writer.write("        <car> \n");
                writer.write("            <name>" + obj.getCar().getName() + "</name> \n");
                writer.write("        </car> \n");
                writer.write("    </humanBeing> \n");
            }
            writer.write("</collection> \n");
            return true;
        } catch (Exception e) {
            console.printError("Ошибка при записи в файл: " + e.getMessage());
            return false;
        }
    }
}