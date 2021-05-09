package com.example.VismaLibrary2021.JsonIO;

import com.example.VismaLibrary2021.DataStructures.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JSONReaderWriter {
    private static final String JSON_PATH_NAME =  "C:\\Users\\Aivaras\\Documents\\GitHub\\Visma-Library-2021-Summer\\books.json";

    public static List<Book> getBooksFromFile(){
        JSONParser jsonParser = new JSONParser();
        List<Book> books = new ArrayList<>();
        File file = new File(JSON_PATH_NAME);

        try{
            if(file.length() == 0){
                return books;
            }
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(JSON_PATH_NAME));

            for(Object object : jsonArray){
                JSONObject book = (JSONObject) object;
                String name = (String) book.get("name");
                String author = (String) book.get("author");
                String category = (String) book.get("category");
                String language = (String) book.get("language");
                String publication = (String) book.get("publication");
                String isbn = (String) book.get("isbn");
                UUID guid = UUID.fromString((String) book.get("guid"));
                boolean isTaken = Boolean.parseBoolean((String) book.get("isTaken"));

                Book newBook = new Book(name, author, category, language, publication, isbn);
                newBook.setGuid(guid);
                newBook.setTaken(isTaken);
                books.add(newBook);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        return books;
    }

    public static void writeToFile(Book book) {
        File file = new File(JSON_PATH_NAME);
        FileWriter fileWriter;
        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", book.getName());
            jsonObject.put("author", book.getAuthor());
            jsonObject.put("category", book.getCategory());
            jsonObject.put("language", book.getLanguage());
            jsonObject.put("publication", book.getPublication().toString());
            jsonObject.put("isbn", book.getIsbn());
            jsonObject.put("guid", book.getGuid().toString());
            jsonObject.put("isTaken", Boolean.toString(book.isTaken()));

            JSONArray jsonArray;
            if(file.length() == 0){
                jsonArray = new JSONArray();
            }
            else {
                jsonArray = (JSONArray) jsonParser.parse(new FileReader(JSON_PATH_NAME));
            }
            jsonArray.add(jsonObject);

            fileWriter = new FileWriter(file);
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException | ParseException e){
            System.out.println(e);
        }
    }

    public static void removeBookFromFile(Book book){
        JSONParser jsonParser = new JSONParser();
        File file = new File(JSON_PATH_NAME);
        FileWriter fileWriter;

        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(JSON_PATH_NAME));

            for(Object object : jsonArray){
                JSONObject bookJSON = (JSONObject) object;
                String guid = (String) bookJSON.get("guid");

                if(guid.equals(book.getGuid().toString())){
                    jsonArray.remove(object);
                    break;
                }
            }

            fileWriter = new FileWriter(file);
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
