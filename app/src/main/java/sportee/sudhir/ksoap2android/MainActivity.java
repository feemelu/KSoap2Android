package sportee.sudhir.ksoap2android;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
    Button btn;
    private static final String NAMESPACE = "urn:Magento";
    //private static final String URL = "http://new.sporteemail.com/index.php/api/soap/?wsdl";
    private static final String URL = "http://127.0.0.1/index.php/api/soap/?wsdl";
    private static final String METHOD_NAME = "catalogProductList";
    ProgressBar pBar;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tv_res);
        btn = (Button) findViewById(R.id.btn_localhost);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MainActivity2Activity.class);
                startActivity(i);
            }
        });
        new AccessTask().execute();
}

public class AccessTask extends AsyncTask<Void, Void, Void> {
    @Override
    protected void onPreExecute() {
//    pBar.setVisibility(View.VISIBLE);
        Log.e("onPreExecute",
                "----------------onPreExecute-----------------");
    }

    @Override
    protected Void doInBackground(Void... params) {
        readingData();
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {

  //  pBar.setVisibility(View.INVISIBLE);
        Log.e("On post execution" , "Terminated");
        tv.setText("Nothing worked");

    }


    }

    public void readingData() {
        try
        {
        SoapSerializationEnvelope env = new  SoapSerializationEnvelope(SoapEnvelope.VER12);
        env.dotNet = false;
        env.xsd = SoapSerializationEnvelope.XSD;
        env.enc = SoapSerializationEnvelope.ENC;
        SoapObject request = new SoapObject(NAMESPACE, "login");

//        request.addProperty("username", "soaper");
        //request.addProperty("apiKey", "apikey123");
            request.addProperty("username", "cats");
            request.addProperty("apiKey", "sudhir123");

        env.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.debug=true;

//        androidHttpTransport.call("", env);
        Object result = env.getResponse();
        Log.d("sessionId", result.toString());

        String sessionId = result.toString();

        SoapObject requestCart = new SoapObject(NAMESPACE,METHOD_NAME);
        requestCart.addProperty("sessionId", sessionId);
            //requestCart.addProperty("categoryId", 321);
        env.setOutputSoapObject(requestCart);
        androidHttpTransport.call("", env);
        result = env.getResponse();
        Log.d("List of Products", result.toString());
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}

