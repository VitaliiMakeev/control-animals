package org.example.Controller;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
    private List<String> simbol = Arrays.asList("!", "?", "-", "+", ":", ";", "'", "@", "#", "%", "(", ")", "^", "$", "`", "№", "*", "/", "|", "[", "]", "{", "}", "_");
    public Controller() {
    }

    /**
     * Проверяет на корректность введенное пользователем имя.
     * @param name введенное пользователем имя
     * @return имя - если все корректно или замечание (что не правильно)
     */
    public String checkName(String name){
        String tmpName = name.trim();
        List<String> tmpList = new ArrayList<>(List.of(name.split(" ")));
        for (String s : tmpList) {
            if (s.length() < 3) {
                return String.format("Слишком короткое имя - %s", s);
            }
        }
        for (int i = 0; i < tmpName.length(); i++) {
            for (String number : this.numbers) {
                if (number.charAt(0) == tmpName.charAt(i)) {
                    return "В имени нельзя писать цифры!";
                }
            }
        }
        for (int i = 0; i < tmpName.length(); i++) {
            for (String s : this.simbol) {
                if (s.charAt(0) == tmpName.charAt(i)) {
                    return "Только латиница и кириллица. Никаких других символов!";
                }
            }
        }
        return tmpName.substring(0, 1).toUpperCase() + tmpName.substring(1);
    }

    /**
     * Проверяет на корректность введенную пользователем дату рождения.
     * @param birthDay введенное пользователем дата рождения
     * @return дату рождения - если все корректно или замечание (что не правильно)
     */
    public String checkBirthDay(String birthDay){
        int tmp = 0;
        String tmpDateIn = birthDay.replaceAll("\\s+", "");
        for (int i = 0; i < tmpDateIn.length(); i++) {
            if (tmpDateIn.charAt(i) == '-'){
                tmp++;
            }
        }
        if (tmp != 2) {
            return "Неверный формат даты. Введите дату в формате: YYYY-MM-DD";
        }
        List<String> tmpDateList = new ArrayList<>(List.of(tmpDateIn.split("-")));
        if (tmpDateList.size() != 3) {
            return "Неверный формат даты. Введите: YYYY-MM-DD";
        }
        if (tmpDateList.get(0).length() != 4){
            return "Год нужно вводить полностью. Формат: YYYY (Например: 1997)";
        } else {
            List<String> tmpYear = new ArrayList<>(List.of(tmpDateList.get(0).split("")));
            for (int i = 0; i < tmpYear.size(); i++) {
                if (!this.numbers.contains(tmpYear.get(i))){
                    return "Только цифры пишем в год!";
                }
            }
            int tmpYearIn = Integer.parseInt(tmpDateList.get(0));
            int year = Year.now().getValue();
            if ((year - tmpYearIn) > 100) {
                return "Зверюга столько не живут! Введите корректный год рождения.";
            } else if ((year - tmpYearIn) < 0) {
                return "Зверюга еще не родилась! Введите корректный год!";
            }

        }
        if (tmpDateList.get(1).length() != 2) {
            return "Месяц нужно вводить полностью цифрами. Формат: MM (Например: 05)";
        } else {
            List<String> tmpMonth = new ArrayList<>(List.of(tmpDateList.get(1).split("")));
            for (int i = 0; i < tmpMonth.size(); i++) {
                if (!this.numbers.contains(tmpMonth.get(i))){
                    return "Только цифры пишем в месяц!";
                }
            }
            int tmpMonthIn = Integer.parseInt(tmpDateList.get(1));
            if (tmpMonthIn < 0 || tmpMonthIn > 12) {
                return "Месяц введен неправильно! Нужно вводить в формате: MM (например: 07)";
            }

        }
        if (tmpDateList.get(2).length() != 2) {
            return "Дни месяца нужно вводить полностью цифрами. Формат: DD (Например: 05)";
        } else {
            List<String> tmpDays = new ArrayList<>(List.of(tmpDateList.get(2).split("")));
            for (int i = 0; i < tmpDays.size(); i++) {
                if (!this.numbers.contains(tmpDays.get(i))){
                    return "Только цифры пишем в дни месяца!";
                }
            }
            int tmpDaysIn = Integer.parseInt(tmpDateList.get(2));
            if (tmpDaysIn < 0 || tmpDaysIn > 31) {
                return "Дни месяца введены неправильно! Нужно вводить в формате: DD (например: 25)";
            }

        }
        return tmpDateIn;
    }

    /**
     * Проверяет на корректность введенную пользователем команду.
     * @param comm введенная пользователем команда
     * @return Команду с заглавной буквой - если все корректно, или замечание (что не правильно)
     */
    public String checkCommand(String comm){
        String tmpComm = comm.trim();
        List<String> tmpList = new ArrayList<>(List.of(comm.split(" ")));
        for (String s : tmpList) {
            if (s.length() < 3) {
                return String.format("Слишком короткая команда - %s", s);
            }
        }
        for (int i = 0; i < tmpComm.length(); i++) {
            for (String number : this.numbers) {
                if (number.charAt(0) == tmpComm.charAt(i)) {
                    return "В командке нельзя писать цифры!";
                }
            }
        }
        for (int i = 0; i < tmpComm.length(); i++) {
            for (String s : this.simbol) {
                if (s.charAt(0) == tmpComm.charAt(i)) {
                    return "Только латиница и кириллица. Никаких других символов!";
                }
            }
        }
        return tmpComm.substring(0, 1).toUpperCase() + tmpComm.substring(1);
    }

    /**
     * Проверяет введенную пользователем команду на дубликаты
     * @param comm введенная пользователем команда
     * @return строку команд через запятую или замечание (что не правильно)
     */
    public String checkDoubleComm(List<String> comm) {
        String result = "";
        List<String> res = new ArrayList<>();
        for (String s : comm) {
            if (!res.contains(s)) {
                res.add(s);
            }
        }
        for (int i = 0; i < res.size(); i++) {
            if (i == res.size() - 1) {
                result = result + res.get(i);
            } else {
                result = result + res.get(i) + ", ";
            }
        }
        return result;
    }

}
