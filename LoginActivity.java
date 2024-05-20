public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);

        // Initialize your SQLite database
        database = new NewsDbHelper(this).getWritableDatabase();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = usernameEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                // Check if the entered credentials match the predefined values
                if (enteredUsername.equals("user") && enteredPassword.equals("user")) {
                    // Predefined username and password are valid, redirect to the news list
                    startActivity(new Intent(LoginActivity.this, NewsActivity.class));
                } else {
                    // Validate the credentials against the database
                    Cursor cursor = database.query(NewsDbHelper.USER_TABLE_NAME,
                            NewsDbHelper.COLUMN_USERNAME + " = ? AND " + NewsDbHelper.COLUMN_PASSWORD + " = ?",
                             new String[]{enteredUsername, enteredPassword},
                             null, null, null, null);

                    if (cursor != null && cursor.moveToFirst()) {
                        // Username and password are valid, redirect to the news list
                        startActivity(new Intent(LoginActivity.this, NewsActivity.class));
                    } else {
                        // Username and password are invalid, display an error message
                        Toast.makeText(LoginActivity.this, "Invalid username and password", Toast.LENGTH_SHORT).show();
                    }

                    // Close the cursor after use
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        });
    }
}
