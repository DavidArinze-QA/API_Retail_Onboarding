package Onboarding;

import com.jayway.jsonpath.JsonPath;
import org.jsoup.Jsoup;
import org.testng.annotations.Test;

import java.io.*;

import static Constants.Final.MailPitURL;
import static io.restassured.RestAssured.given;

public class MailPIT {

    @Test
    public static void getEmailID () {
        BufferedWriter out = null;
        try {

            //---Read File Contents - email number---
            BufferedReader br = new BufferedReader(new FileReader("output.txt"));
            String storedScore="0";
            int storedScoreNumber = 0;
            while ((storedScore = br.readLine()) != null) {
                storedScoreNumber=(Integer.parseInt(storedScore==null?"0":storedScore));


                storedScoreNumber--;
                System.out.println("DECREASED NUMBER IS: "+storedScoreNumber);
                var email = "davetrial"+storedScoreNumber+"@gmail.com";
                System.out.println("EMAIL IS: "+email);
            }

            //---Retrieve Email ID from MailPit---

            String response =
            given().
                    auth().preemptive().basic("admin","kudabank").


                    when().
                    get(MailPitURL+"/search?query=davetrial"+storedScoreNumber+"@gmail.com").
                    jsonPath().prettyPrint();
            String emailID = JsonPath.read(response, "messages[0].ID");
            System.out.println("EMAIL ID IS: "+emailID);



            //---Write File Contents - Email ID number---
            out = new BufferedWriter(new FileWriter("emailID.txt", false));
            out.write(emailID);


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
        }}

    @Test
    public static void getEmailOTP () {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("emailID.txt"));
            String emailID = reader.readLine();
            System.out.println("Email ID is: " + emailID);
            reader.close();


            String response =
                    given().
                            auth().preemptive().basic("admin", "kudabank").
                            pathParam("messageId", emailID).
                            when().
                            get(MailPitURL + "/message/{messageId}").
                            jsonPath().prettyPrint();
            System.out.println(MailPitURL + "/message/{messageId}");

            var trial = JsonPath.read(response, "Text");
            System.out.println(trial);
//        System.out.println("-----Email Body---");
            String rep = Jsoup.parse((String) trial).text();
            String otp = rep.substring(105, 111);
            //System.out.println(rep);
            System.out.println("--------Decoded OTP-------");
            System.out.println(otp);
            //


            BufferedWriter writer = new BufferedWriter(new FileWriter("OTP.txt", false));
            writer.write(otp);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }}