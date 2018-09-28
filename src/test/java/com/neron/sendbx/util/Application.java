package com.neron.sendbx.util;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.neron.sendbx.tests.BaseTest;
import org.bson.Document;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.Nullable;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    // read spring config java class
    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(TheMindConfig.class);

            // get the bean from spring container
            BaseTest theTest = context.getBean("baseTest", BaseTest.class);

            // close the context








    private String user = "immortalUserDev";
    private String database = "devthemind";
    //private char[] password = "1mM0rta1Us3rSandbx18".toCharArray();
    private String password = "1mM0rta1Us3rSandbx18";
    static String testEmail = "sandbxtest8+77@gmail.com";


    String stage = "stage.be.themind.io";
    private String dev = "internal.db.themind.io";

    public static void main(String[] args) {

        Application app = new Application();


        System.out.println(app.getAnsweredQuestionsId(app.retrieveCollection(), testEmail));
        System.out.println(app.getEmailValidationCode("sandbxtest8+77@gmail.com"));


    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //get verefication code for user that registered with phone number
    @Nullable
    public String getSmsValidationCode(String phoneNumber) {
        BasicDBObject query = new BasicDBObject();
        query.put("profile.phone", phoneNumber);
        @Nullable String smsCode = getVerificationCodes(retrieveCollection(), query, "sms");
        System.out.println("For phone: '" + phoneNumber + "' verification code is: '" + smsCode + "'.");
        return smsCode;
    }


    //get verification code for user that registered with email
    @Nullable
    public String getEmailValidationCode(String email) {
        BasicDBObject query = new BasicDBObject();
        query.put("profile.email", email);
        @Nullable String emailCode = getVerificationCodes(retrieveCollection(), query, "email");
        System.out.println("For email: '" + email + "' verification code is: '" + emailCode + "'.");
        return emailCode;
    }

    //Get ArrayList wits questions Id that user answered
    @SuppressWarnings("unchecked")
    @Nullable
    private ArrayList getAnsweredQuestionsId(MongoCollection<Document> collection, String email) {
        BasicDBObject query = new BasicDBObject();
        query.put("profile.email", email);
        Document doc = collection.find(query).first();
        Document system = (Document) doc.get("system");
        List<String> codes;
        codes = (ArrayList<String>) system.get("answeredIds");
        return (ArrayList) codes;
    }

    //init connection to mongodb
    private MongoCollection<Document> retrieveCollection() {

        MongoClientURI uri = new MongoClientURI("mongodb://"+user+":" +password+"@"+dev+"/?authSource=devthemind&authMechanism=SCRAM-SHA-1");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("devthemind");

         return database.getCollection("users");


    }


    //find document in collection users
    @SuppressWarnings("unchecked")
    @Nullable
    private String getVerificationCodes(MongoCollection<Document> collection, BasicDBObject query, String codeTag) {
        Document doc = collection.find(query).first();
        @Nullable Document system = (Document) doc.get("system");
        Document codes = (Document) system.get("verification_codes");
        Object code = codes.get(codeTag);
        return code != null ? String.valueOf(code) : null;
    }



    //find User and drop it from database
    @SuppressWarnings("unchecked")
    @Nullable
    public void dropUserByEmail(String email) {
        BasicDBObject query = new BasicDBObject();
        query.put("profile.email", email);
        MongoCollection collection =  retrieveCollection();
        Document doc = (Document) collection.find(query).first();
        if (doc!=null){
        collection.deleteOne((query));}
        else System.out.println("didn't find any documents");

    }

    //Check is email present in dataBase
    @SuppressWarnings("unchecked")
    @Nullable
    public boolean checkIsEmailPresent(String email) {
        boolean status = false;
        BasicDBObject query = new BasicDBObject();
        query.put("profile.email", email);
        MongoCollection collection =  retrieveCollection();
        Document doc = (Document) collection.find(query).first();
        if (doc!=null){
            System.out.println("email is exist");
            status = true;
        }
        else System.out.println("didn't find any documents");
        return status;
    }





  ///////////////////////////////////////////////////////////////////////////////


//GRAPHIQL







    //parsing data from file, creating Map
    public static Map<String, String> readTranslationFile(String path) throws IOException {
        Map<String,String> translation = new HashMap<String, String>();
        BufferedReader reader = null;
        try{
            reader  = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String line;
            line=reader.readLine();
            while (line != null){
                String [] split = line.split(";");
                translation.put(split[0], split[1]);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return translation;

    }

    /**
     * Метод выполняет скрипты shell в отдельном потоке.
     *
     * @param command shell скрипт.
     */
    public void runCommand(final String command) {

        // Чтобы не вис интерфейс, запускаем в другом потоке
        new Thread(new Runnable() {
            public void run() {
                OutputStream out = null;
                InputStream in = null;
                try {
                    // Отправляем скрипт в рантайм процесс
                    Process child = Runtime.getRuntime().exec(command);
                    // Выходной и входной потоки
                    out = child.getOutputStream();
                    in = child.getInputStream();

                    //Входной поток может что-нибудь вернуть
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    String line;
                    String result = "";
                    while ((line = bufferedReader.readLine()) != null)
                        result += line;

                    //Обработка того, что он вернул
                    handleBashCommandsResult(result);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (out != null) {
                        try {
                            out.flush();
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    /**
     * Обработка того, что вернул скрипт (возвращает он обычно ключевое слово командой echo)
     *
     * @param result ответ скрипта.
     */
    private void handleBashCommandsResult(String result) {

        if (result.contains("SCRIPT_FINISHED")) {
            //Здесь делаем всё что хотели сделать после завершение скрипта
            System.out.println(result);}
//         else if (.....){
//            //А вот здесь можно сделать что-нибудь после другого скрипта
//        } else {
//            //А вот здесь можно сделать всё остальное
//        }
    }









}
