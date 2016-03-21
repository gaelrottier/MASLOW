//package fr.unice.mbds.maslow;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
////import android.support.design.widget.FloatingActionButton;
////import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//
////import org.apache.http.HttpResponse;
////import org.apache.http.client.HttpClient;
////import org.apache.http.client.methods.HttpPost;
////import org.apache.http.entity.StringEntity;
////import org.apache.http.impl.client.DefaultHttpClient;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//public class sendJsonData extends AppCompatActivity {
//    EditText edEtat;
//    Button btnPost;
//    TestClass testClass;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_send_json_data);
//
//
//        // get reference to the views
//
//        edEtat = (EditText) findViewById(R.id.etat);
//        btnPost = (Button) findViewById(R.id.sendData);
//
//
//    }
//
//    public static String POST(String url, TestClass testClass){
//        InputStream inputStream = null;
//        String result = "";
//        try {
//
////            // 1. create HttpClient
////            HttpClient httpclient = new DefaultHttpClient();
////
////            // 2. make POST request to the given URL
////            HttpPost httpPost = new HttpPost("http://hmkcode.appspot.com/jsonservlet");
//
//            String json = "";
//
//            // 3. build jsonObject
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.accumulate("etat", testClass.getEtat());
//
//
//            // 4. convert JSONObject to JSON to String
//            json = jsonObject.toString();
//
//            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
//            // ObjectMapper mapper = new ObjectMapper();
//            // json = mapper.writeValueAsString(person);
//
////            // 5. set json to StringEntity
////            StringEntity se = new StringEntity(json);
////
////            // 6. set httpPost Entity
////            httpPost.setEntity(se);
//
//            // 7. Set some headers to inform server about the type of the content
//            httpPost.setHeader("Accept", "application/json");
//            httpPost.setHeader("Content-type", "application/json");
//
//            // 8. Execute POST request to the given URL
//            HttpResponse httpResponse = httpclient.executeForJson(httpPost);
//
//            // 9. receive response as inputStream
//            inputStream = httpResponse.getEntity().getContent();
//
//            // 10. convert inputstream to string
//            if(inputStream != null)
//                result = convertInputStreamToString(inputStream);
//            else
//                result = "Did not work!";
//
//        } catch (Exception e) {
//            Log.d("InputStream", e.getLocalizedMessage());
//            result = "Did'nt work!";
//        }
//
//        // 11. return result
//        return result;
//    }
//
//
//    public void onClick(View view) {
//
//        switch(view.getId()){
//            case R.id.sendData:
//                if(!validate()) {
//                    Toast.makeText(getBaseContext(), "Enter some data!", Toast.LENGTH_LONG).show();
//                    // call AsynTask to perform network operation on separate thread
//                }
//                new HttpAsyncTask().executeForJson("http://hmkcode.appspot.com/jsonservlet");
//                System.out.println("success");
//                break;
//        }
//
//    }
//    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... urls) {
//
//            testClass = new TestClass();
//            testClass.setEtat(edEtat.getText().toString());
//
//
//            return POST(urls[0],testClass);
//        }
//        // onPostExecute displays the results of the AsyncTask.
//        @Override
//        protected void onPostExecute(String result) {
//            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    private boolean validate(){
//        if(edEtat.getText().toString().trim().equals(""))
//            return false;
//
//        else
//            return true;
//    }
//    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
//        String line = "";
//        String result = "";
//        while((line = bufferedReader.readLine()) != null)
//            result += line;
//
//        inputStream.close();
//        return result;
//
//    }
//}
