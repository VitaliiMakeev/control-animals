//package org.example.view;
//
//import org.example.controller.Controller2;
//import org.example.moduls.NoFolder;
//import org.example.moduls.SaveData;
//import org.example.moduls.Shop;
//import org.example.moduls.Toy;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class ViewTMP {
//
//
//
//    public void start() throws NoFolder {
//        Shop shop = new Shop();
//        List<Toy> toyList = new ArrayList<>();
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Добро пожаловать!" + "\n" + "Администратирование игравого автомата.");
//        System.out.println("Давайте для начала загрузим игрушек в автомат!");
//        System.out.println("Для прекращения загрузки введите 0 (ноль).");
//        boolean flag = true;
//        while (flag){
//            System.out.println("Введите название " + (toyList.size() + 1) + " игрушки и частоту выпадения и количество:  ");
//            String tmp = scanner.nextLine();
//            String toy = tmp.trim();
//            if (toy.trim().charAt(0) == '0'){
//                break;
//            }
//            List<String> strList = List.of(toy.split(" "));
//            try{
//                if (strList.size() != 3){
//                    throw new RuntimeException("Неверный формат!(имя, вес, кол-во!)");
//                }
//                int n = Integer.parseInt(strList.get(1));
//                int n1 = Integer.parseInt(strList.get(2));
//                String name = strList.get(0).trim();
//                toyList.add(new Toy(shop, name, n, n1));
//            } catch (RuntimeException ex) {
//                System.out.println(ex.getMessage());
//            } catch (Exception e){
//                System.out.println("Необходимо ввести целое число!(1 - 100)");
//            }
//
//        }
//        Controller2 controller = new Controller2(toyList);
//        SaveData saveData = new SaveData();
//
//        while (flag){
//            System.out.println("Выдать игрушку?(y/n): ");
//            String vop = scanner.nextLine();
//            if (vop.length() == 0){
//                System.out.println("Необходимо ответить на вопрос!(y/n)");
//            }else if (vop.trim().charAt(0) != 'y' && vop.trim().charAt(0) != 'n'){
//                System.out.println("Необходимо ответить на вопрос!(y/n)");
//            } else if (vop.trim().charAt(0) == 'y') {
//                try{
//                    System.out.println("id вес имя кол-во");
//                    System.out.println(controller.get());
//                } catch (RuntimeException e1){
//                    System.out.println(e1.getMessage());
//                    String res = saveData.saveData(controller.getToyQueue());
//                    System.out.println(res);
//                    flag = false;
//                }
//            } else if (vop.trim().charAt(0) == 'n') {
//                String res = saveData.saveData(controller.getToyQueue());
//                System.out.println(res);
//                System.out.println("Готово!");
//                flag = false;
//            }
//        }
//
//    }
//
//}
