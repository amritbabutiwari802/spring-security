package com.spring.security.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        int noOfWords=0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter no of sentences");
        noOfWords = scanner.nextInt();
        List<String> sentences = new ArrayList<>();
        System.out.println("Enter sentence and press enter to enter new sentence");
        for(int i=0;i<noOfWords;i++){
            sentences.add(scanner.nextLine());
        }
        for(String sentence: sentences){
            printNoOfVowels(sentence);
        }
    }

   static void printNoOfVowels(String sentence){
        int number = 0;
        String[] s = {"a","e","i","o","u"};
        for(String vowel : s){
            number += noOfALetterInSentence(sentence, vowel);
        }
        String message;
        if(number==0|| number >10){
            message = number==0?"zer0":"too large";
        }else{
            message = number+"";
        }
        System.out.println("number of vowels in '"+sentence+"' is "+message);
    }


   static int noOfALetterInSentence(String sentence, String letter){
        int number = 0;
        while(sentence.contains(letter)){
            number++;
            sentence.replace(letter,"-");
        }

        return number;
    }

}
