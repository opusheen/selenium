package ru.stqa.training.selenium.appmanager;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
Массивный максимум
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        ArrayList<String> strings = new ArrayList<>();
        Reader r = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(r);

        //ввод строк с клавиатуры
        for (int i = 0; i < 10; i++)
        {
            String s = reader.readLine();
            strings.add(i,s);
        }
        for (String string: strings){
            System.out.println(string);
        }
    }
}
