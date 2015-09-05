package sportee.sudhir.ksoap2android;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
public class MainActivity2Activity extends Activity {
Button backbtn;
    private static final String NAMESPACE = "urn:Magento";
    private static final String URL = "http://127.0.0.1/index.php/api/soap/?wsdl";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        backbtn = (Button) findViewById(R.id.btn_back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        try {
        SoapSerializationEnvelope env = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        env.dotNet = false;
        env.xsd = SoapSerializationEnvelope.XSD;
        env.enc = SoapSerializationEnvelope.ENC;

        SoapObject request = new SoapObject(NAMESPACE, "catalog_product.list");

        request.addProperty("username", "cats");
        request.addProperty("apiKey", "sudhir123");
        env.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        //androidHttpTransport.call("", env);
        Object result = env.getResponse();

            Log.d("sessionId", result.toString());

    } catch (Exception e) {
        e.printStackTrace();
    }


    }
}
