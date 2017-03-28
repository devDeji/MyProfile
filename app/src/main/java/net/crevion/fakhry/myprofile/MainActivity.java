package net.crevion.fakhry.myprofile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView shareBtn, callBtn, fbBtn, activity2;
    private TextView telpField, fbField, emailField, namaField;
    private ImageView imgProfile;
    private Toolbar mToolbar;
    private int RESULTS_ACTIVITY = 1;
    int IMAGE_PICKER_REQUEST;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callBtn = (TextView)findViewById(R.id.callBtn);
        fbBtn = (TextView)findViewById(R.id.fbBtn);
        shareBtn = (TextView)findViewById(R.id.shareBtn);

        telpField = (TextView)findViewById(R.id.telpField);
        fbField = (TextView) findViewById(R.id.fbField);
        emailField = (TextView) findViewById(R.id.emailField);
        namaField = (TextView) findViewById(R.id.namaField);

        imgProfile = (ImageView) findViewById(R.id.profile_image);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Image view clicked", Toast.LENGTH_LONG).show();
                Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
                imageIntent.setType("image/*");
                startActivityForResult(imageIntent, IMAGE_PICKER_REQUEST);
            }
        });

//        Context context;
//        Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(),
//                R.drawable.myphoto);
//
//        ImageView imgv = (ImageView) findViewById(R.id.banar1);
//
//        //  Bitmap bitmap = StringToBitMap(imgv);
//        Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//
//        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        Paint paint = new Paint();
//        paint.setShader(shader);
//        paint.setAntiAlias(true);
//        Canvas c = new Canvas(circleBitmap);
//        c.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
//
//        imgv.setImageBitmap(circleBitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
//        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit){
//            Toast.makeText(this, "setting click", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, EditProfileActivity.class);
//            telpField = (TextView)findViewById(R.id.telpField);
//            fbField = (TextView) findViewById(R.id.fbField);
//            emailField = (TextView) findViewById(R.id.emailField);
//            namaField = (TextView) findViewById(R.id.namaField);
            String telp = telpField.getText().toString();
            String link = fbField.getText().toString();
            String email = emailField.getText().toString();
            String nama = namaField.getText().toString();
            intent.putExtra("telp", telp);
            intent.putExtra("link", link);
            intent.putExtra("email", email);
            intent.putExtra("nama", nama);
//            startActivity(intent);
            startActivityForResult(intent, RESULTS_ACTIVITY);
            return true;
        }else if(id == R.id.action_search){
            Toast.makeText(this, "Search click", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(id == R.id.action_settings){
            Toast.makeText(this, "Setting click", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(id == R.id.action_share){
            Toast.makeText(this, "share click", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == RESULTS_ACTIVITY) && (resultCode == RESULT_OK)){
            Log.v("Cekvalue", "edit ok");
            String nama = data.getStringExtra("nama");
            String telp = data.getStringExtra("telp");
            String link = data.getStringExtra("link");
            String email = data.getStringExtra("email");

            namaField.setText(nama);
            telpField.setText(telp);
            fbField.setText(link);
            emailField.setText(email);

            Log.v("Cekvalue", "nama "+nama);
            Log.v("Cekvalue", "telp "+telp);
            Log.v("Cekvalue", "link "+link);
            Log.v("Cekvalue", "email "+email);
        }
        else if((requestCode == IMAGE_PICKER_REQUEST) && (resultCode == RESULT_OK)){
            Log.v("Cekvalue", "image ok");
            Uri returnedImageURI = data.getData();
            imgProfile.setImageURI(returnedImageURI);
        }
    }

    public void activity2Click(View view){
//        activity2 = (TextView) findViewById(R.id.activity2);
        Intent intent = new Intent(this, EditProfileActivity.class);
//        telpField = (TextView)findViewById(R.id.telpField);
        String telp = telpField.getText().toString();
        intent.putExtra("telp", telp);
        startActivity(intent);
    }

    public void CallClick(View view){
//        callBtn = (TextView)findViewById(R.id.callBtn);
//        telpField = (TextView)findViewById(R.id.telpField);
        Log.v("Tagclick", "Edit Clicked");
        Log.v("Tagclick", telpField.getText().toString());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + telpField.getText().toString()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void fbClick(View view){
//        fbBtn = (TextView)findViewById(R.id.fbBtn);
//        fbField = (TextView)findViewById(R.id.fbField);
        Log.v("Tagclick", "fb Clicked");
        Log.v("Tagclick", fbField.getText().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(fbField.getText().toString()));
//        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
//        }
    }


    public void shareClick(View view){
//        shareBtn = (TextView)findViewById(R.id.shareBtn);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello, Greeting from my apps, Thanks. (Fakhry)");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
        Log.v("Tagclick", "share Clicked");
    }

    public void emailClick(View view){
//        shareBtn = (TextView)findViewById(R.id.shareBtn);
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello, Greeting from my apps, Thanks. (Fakhry)");
//        sendIntent.setType("text/plain");
//        startActivity(sendIntent);
        Toast.makeText(this,"Email Clicked" , Toast.LENGTH_LONG).show();
        Log.v("Tagclick", "email Clicked");
    }
}
