public class NewsAdapter extends ArrayAdapter<News> {
    private Context context;
    private List<News> newsList;

    public NewsAdapter(Context context, List<News> newsList) {
        super(context, 0, newsList);
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.news_item, parent, false) : convertView;

        News news = getItem(position); // Use getItem() to retrieve the News object

        TextView titleTextView = view.findViewById(R.id.title);
        TextView descriptionTextView = view.findViewById(R.id.description);
        ImageView imageView = view.findViewById(R.id.image);

        if (news != null) {
            titleTextView.setText(news.getTitle());
            descriptionTextView.setText(news.getDescription());
            imageView.setImageResource(news.getImageResourceId());
        }

        view.setOnClickListener(new View.OnClickListener() {
           @Override
    public void onClick(View v) {
        // Handle click event
        News clickedNews = getItem(position);
        
        // Check if the clickedNews is not null
        if (clickedNews != null) {
            // Assuming you have a NewsDetailsActivity to display detailed news
            Intent intent = new Intent(context, NewsDetailsActivity.class);

            // Pass the relevant information to the NewsDetailsActivity
            intent.putExtra("id", clickedNews.getId());
            intent.putExtra("title", clickedNews.getTitle());
            intent.putExtra("description", clickedNews.getDescription());
            intent.putExtra("image", clickedNews.getImageResourceId());
            intent.putExtra("category", clickedNews.getCategory());

            // Start the NewsDetailsActivity
            context.startActivity(intent);
        }
    }
});

        return view;
    }
}
