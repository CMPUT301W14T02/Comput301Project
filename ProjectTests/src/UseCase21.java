// use case: View Commentor Username

public void testDisplayUsername() {
        Intent intent = new Intent();
        String text = "ThisIsMyName";
        intent.putExtra(BrowsingCommentsActivity.TEXT_KEY, text);
        setActivityIntent(intent);
        BrowsingCommentsActivity activity = getActivity();
        TextView textView = (TextView)activity.findViewById(ca.ualberta.cs.project301.R.id.commenterUsername);
        assertEquals("Username TextView should be visible", text, textView.getText().toString());
    }  
