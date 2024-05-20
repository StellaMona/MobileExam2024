public class NewsDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "news.db";
    public static final String NEWS_TABLE_NAME = "news";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_CATEGORY = "category";

    public static final String USER_TABLE_NAME = "users";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";


    public NewsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String newsTableSql = "CREATE TABLE " + NEWS_TABLE_NAME + " ( " +
        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        COLUMN_TITLE + " TEXT NOT NULL, " +
        COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
        COLUMN_IMAGE + " INTEGER NOT NULL, " +
        COLUMN_CATEGORY + " TEXT NOT NULL)";
        db.execSQL(newsTableSql);

         String usersTableSql = "CREATE TABLE " + USER_TABLE_NAME + " ( " +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT NOT NULL, " +
                COLUMN_PASSWORD + " TEXT NOT NULL)";
        db.execSQL(usersTableSql);

        insertSportsNews(db);
        insertTechnologyNews(db);
        insertWeatherNews(db);
    }

    private void insertSportsNews(SQLiteDatabase db) {
        String[] sportsTitles = {
                "How Losing Aaron Rodgers Exposed the Disconnect Inside the New York Jets",
                "One of Lionel Messi's Old Napkins Is Now on Sale for $381,000",
                "The Men Who Practice Against Caitlin Clark Can't Stop Her Either",
                "The Humiliating Soccer Skill That Infuriates Opponents"
        };

        String[] sportsDescriptions = {
                "The 2023 season was supposed to be different. It ended with " +
                        "excuse-making, paranoia and tunnel vision on their injured quarterback",
                "The napkin upon which Messi's first Barcelona agreement was " +
                        "informally written will be sold at auction.",
                "Being a practice player against some of the stars of women's " +
                        "college basketball can either be \"the best job on campus\" or \"a massive nightmare.\"",
                "It's hard to think of any other trick that brings one player " +
                        "so much adulation as a nutmeg."
        };

        int defaultImageResource = R.drawable.sports; // Use the default sports image
        insertNews(db, sportsTitles, sportsDescriptions, defaultImageResource, "Sports");

    }

    private void insertTechnologyNews(SQLiteDatabase db) {
        String[] techTitles = {
                "Sen. Lindsey Graham Tells Mark Zuckerberg He Has Blood On His Hands",
                "Taylor Swift Songs May Vanish From TikTok As Licensing Dispute Boils Over"
        };

        String[] techDescriptions = {
                "Zuckerberg and other tech CEOs testified in front of a " +
                        "Senate committee on Wednesday",
                "Universal Music Group, which represents Swift, said it has " +
                        "not agreed to terms of a new licensing deal with the social media platform."
        };

        int defaultImageResource = R.drawable.technology; // Use the default technology image

        insertNews(db, techTitles, techDescriptions, defaultImageResource, "Technology");
    }

    private void insertWeatherNews(SQLiteDatabase db) {
        // Titles and descriptions for weather news
        String[] weatherTitles = {
                "Weather News Title",
                // Add more titles as needed
        };

        String[] weatherDescriptions = {
                "Weather News Description",
                // Add more descriptions as needed
        };

        // Image resource for weather news
        int defaultImageResource = R.drawable.weather;

        // Insert weather news into the database
        insertNews(db, weatherTitles, weatherDescriptions, defaultImageResource, "Weather");
    }

    private void insertNews(SQLiteDatabase db, String[] titles, String[] descriptions, int imageResource, String category) {
        for (int i = 0; i < titles.length; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, titles[i]);
            values.put(COLUMN_DESCRIPTION, descriptions[i]);
            values.put(COLUMN_IMAGE, imageResource);
            values.put(COLUMN_CATEGORY, category);
            db.insert(NEWS_TABLE_NAME, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing table and recreate on upgrade
        db.execSQL("DROP TABLE IF EXISTS " + NEWS_TABLE_NAME);
        onCreate(db);
    }
}