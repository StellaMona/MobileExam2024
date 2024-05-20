public class NewsActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize your SQLite database
        database = new NewsDbHelper(this).getWritableDatabase();
        adapter = new NewsAdapter(this, null); // Use null for the data source

        // Populate the news list from the database
        Cursor cursor = database.query(NewsDbHelper.NEWS_TABLE_NAME,
                new String[]{"id", "title", "description", "image", "category"},
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                int image = cursor.getInt(3);
                String category = cursor.getString(4);

                News news = new News(id, title, description, image, category);
                adapter.add(news);
            } while (cursor.moveToNext());

            cursor.close(); // Close the cursor after use
        }

        // Display the news list
        ListView newsList = findViewById(R.id.news_list);
        newsList.setAdapter(adapter);
    }
}
