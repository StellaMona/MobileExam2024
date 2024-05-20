import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        // Retrieve information from the intent
        int id = getIntent().getIntExtra("id", -1);
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        int imageResourceId = getIntent().getIntExtra("image", R.drawable.default_image); // Provide a default image resource
        String category = getIntent().getStringExtra("category");

        // Display the details in the activity
        TextView titleTextView = findViewById(R.id.details_title);
        TextView descriptionTextView = findViewById(R.id.details_description);
        ImageView imageView = findViewById(R.id.details_image);
        TextView categoryTextView = findViewById(R.id.details_category);

        titleTextView.setText(title);
        descriptionTextView.setText(description);
        imageView.setImageResource(imageResourceId);
        categoryTextView.setText(category);
    }
}
