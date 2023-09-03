package Onboarding;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import java.io.*;
import static Constants.Final.ONBOARDING;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class StartEmailVerification {

    @Test
    public static void main()  {

        BufferedWriter out = null;
        try {

            //---Read File Contents - email number---
            BufferedReader br = new BufferedReader(new FileReader("output.txt"));
            String storedScore="0";
            int storedScoreNumber = 0;
            while ((storedScore = br.readLine()) != null) {
                storedScoreNumber=(Integer.parseInt(storedScore==null?"0":storedScore));
            }

            //---Start Email Verification with generated email number---

                 String email= "davetrial"+ storedScoreNumber + "@gmail.com";



        System.out.println(email);
        JSONObject request = new JSONObject();
        request.put("email", email);

        //System.out.println(request.toJSONString());

        baseURI = ONBOARDING;
        //System.out.println(ONBOARDING);
        given().

                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("/Email/StartVerification").
                then().
                statusCode(200).
                log().all();

            //---Write File Contents - incremented email number---
            out = new BufferedWriter(new FileWriter("output.txt", false));
            out.write(String.valueOf(storedScoreNumber+1));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }}}

