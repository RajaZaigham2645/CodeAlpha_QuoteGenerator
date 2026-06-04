package com.example.codealpha_quotegenetator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView quoteTextView;
    private TextView authorTextView;
    private Button newQuoteButton;
    private ArrayList<Quote> quoteList;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Handle window insets for Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left + (int)(24 * getResources().getDisplayMetrics().density), 
                         systemBars.top + (int)(24 * getResources().getDisplayMetrics().density), 
                         systemBars.right + (int)(24 * getResources().getDisplayMetrics().density), 
                         systemBars.bottom + (int)(24 * getResources().getDisplayMetrics().density));
            return insets;
        });

        // Initialize views
        quoteTextView = findViewById(R.id.quoteTextView);
        authorTextView = findViewById(R.id.authorTextView);
        newQuoteButton = findViewById(R.id.newQuoteButton);

        // Initialize Random and Quotes
        random = new Random();
        loadQuotes();

        // Display initial quote
        displayRandomQuote();

        // New Quote Button Listener
        newQuoteButton.setOnClickListener(v -> displayRandomQuote());

        // Long press to copy quote
        findViewById(R.id.quoteTextView).setOnLongClickListener(v -> {
            copyToClipboard(quoteTextView.getText().toString() + " " + authorTextView.getText().toString());
            return true;
        });
    }

    private void copyToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Quote", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Quote copied to clipboard!", Toast.LENGTH_SHORT).show();
    }

    private void loadQuotes() {
        quoteList = new ArrayList<>();
        // Original Quotes
        quoteList.add(new Quote("The only way to do great work is to love what you do.", "Steve Jobs"));
        quoteList.add(new Quote("Life is what happens when you're busy making other plans.", "John Lennon"));
        quoteList.add(new Quote("The future belongs to those who believe in the beauty of their dreams.", "Eleanor Roosevelt"));
        quoteList.add(new Quote("It does not matter how slowly you go as long as you do not stop.", "Confucius"));
        quoteList.add(new Quote("Success is not final, failure is not fatal: it is the courage to continue that counts.", "Winston Churchill"));
        quoteList.add(new Quote("Believe you can and you're halfway there.", "Theodore Roosevelt"));
        quoteList.add(new Quote("The only impossible journey is the one you never begin.", "Tony Robbins"));
        quoteList.add(new Quote("Your time is limited, don't waste it living someone else's life.", "Steve Jobs"));
        quoteList.add(new Quote("The purpose of our lives is to be happy.", "Dalai Lama"));
        quoteList.add(new Quote("Get busy living or get busy dying.", "Stephen King"));
        quoteList.add(new Quote("You only live once, but if you do it right, once is enough.", "Mae West"));
        quoteList.add(new Quote("Many of life's failures are people who did not realize how close they were to success when they gave up.", "Thomas Edison"));
        quoteList.add(new Quote("The best time to plant a tree was 20 years ago. The second best time is now.", "Chinese Proverb"));
        quoteList.add(new Quote("It's not whether you get knocked down, it's whether you get up.", "Vince Lombardi"));
        quoteList.add(new Quote("Keep your face always toward the sunshine, and shadows will fall behind you.", "Walt Whitman"));
        
        // Additional 20+ Quotes
        quoteList.add(new Quote("The secret of getting ahead is getting started.", "Mark Twain"));
        quoteList.add(new Quote("Our greatest glory is not in never falling, but in rising every time we fall.", "Confucius"));
        quoteList.add(new Quote("Happiness is not something ready made. It comes from your own actions.", "Dalai Lama"));
        quoteList.add(new Quote("The only true wisdom is in knowing you know nothing.", "Socrates"));
        quoteList.add(new Quote("Act as if what you do makes a difference. It does.", "William James"));
        quoteList.add(new Quote("What lies behind us and what lies before us are tiny matters compared to what lies within us.", "Ralph Waldo Emerson"));
        quoteList.add(new Quote("With the new day comes new strength and new thoughts.", "Eleanor Roosevelt"));
        quoteList.add(new Quote("The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty.", "Winston Churchill"));
        quoteList.add(new Quote("Don't let yesterday take up too much of today.", "Will Rogers"));
        quoteList.add(new Quote("You learn more from failure than from success. Don't let it stop you.", "Unknown"));
        quoteList.add(new Quote("If you are working on something that you really care about, you don't have to be pushed.", "Steve Jobs"));
        quoteList.add(new Quote("Experience is simply the name we give our mistakes.", "Oscar Wilde"));
        quoteList.add(new Quote("Change your thoughts and you change your world.", "Norman Vincent Peale"));
        quoteList.add(new Quote("Either you run the day, or the day runs you.", "Jim Rohn"));
        quoteList.add(new Quote("The only limit to our realization of tomorrow will be our doubts of today.", "Franklin D. Roosevelt"));
        quoteList.add(new Quote("Don't watch the clock; do what it does. Keep going.", "Sam Levenson"));
        quoteList.add(new Quote("The best way to predict the future is to create it.", "Abraham Lincoln"));
        quoteList.add(new Quote("Quality is not an act, it is a habit.", "Aristotle"));
        quoteList.add(new Quote("The way to get started is to quit talking and begin doing.", "Walt Disney"));
        quoteList.add(new Quote("Spread love everywhere you go. Let no one ever come to you without leaving happier.", "Mother Teresa"));
    }

    private void displayRandomQuote() {
        if (quoteList.isEmpty()) return;
        
        int randomIndex = random.nextInt(quoteList.size());
        Quote randomQuote = quoteList.get(randomIndex);
        
        // Simple fade animation
        quoteTextView.animate().alpha(0f).setDuration(150).withEndAction(() -> {
            quoteTextView.setText(randomQuote.getText());
            authorTextView.setText("— " + randomQuote.getAuthor());
            quoteTextView.animate().alpha(1f).setDuration(300);
            authorTextView.animate().alpha(1f).setDuration(300);
        });
        authorTextView.animate().alpha(0f).setDuration(150);
    }

    private static class Quote {
        private final String text;
        private final String author;

        public Quote(String text, String author) {
            this.text = text;
            this.author = author;
        }

        public String getText() { return text; }
        public String getAuthor() { return author; }
    }
}
