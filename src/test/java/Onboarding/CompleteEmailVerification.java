package Onboarding;

import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static Constants.Final.MailPitURL;
import static Constants.Final.ONBOARDING;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class CompleteEmailVerification {

    @Test
    public static void enterOTP () {


        try {
            BufferedReader reader1 = new BufferedReader(new FileReader("OTP.txt"));
            String OTP = reader1.readLine();
            System.out.println("OTP is: " + OTP);

            BufferedReader reader2 = new BufferedReader(new FileReader("output.txt"));
            int emailNumber = Integer.parseInt(reader2.readLine());
            emailNumber--;
            System.out.println("Email Number is: " + emailNumber);

            reader1.close();
            reader2.close();


        JSONObject request = new JSONObject();
        request.put("code", OTP);
        request.put("email", "davetrial"+emailNumber+"@gmail.com");

        System.out.println(request.toJSONString());

        baseURI = ONBOARDING;
        given().

                     header("Content-Type","application/json").
                     contentType(ContentType.JSON).
                     accept(ContentType.JSON).
                     body(request.toJSONString()).
                when().
                     post("/Email/CompleteVerification").
                then().
                    statusCode(200).
                log().all();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
