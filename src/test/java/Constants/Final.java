package Constants;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Final {

    public static final String URL = "https://retailapigateway.kudabank.com";
    public static final String ONBOARDING = URL + "/kuda-retail-onboardingms/api/v1";
    public static final String BILLS = URL + "/retail-bills-payment/api/v1";

    public static final String MailPitURL ="http://mock-mail.kudabank.com/api/v1";

    //public static final String MockSMSURL ="https://mock-sms.kudabank.com/sms/messages/recipients/2347091597565";

    public static final String MockSMSURL ="http://mock-sms.kudabank.com/sms/messages/recipients/2347090597515?page=0&size=1";


    }