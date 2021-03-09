package support;

public class ParseId {

    private static String text;


    public ParseId() {

    }
    public ParseId(String text) {
        ParseId.text = text;
    }

    public static Long getLongId(String text,String key) {
        System.out.println(text);
        System.out.println(key);
        return Long.parseLong(text.substring(key.length()));
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        ParseId.text = text;
    }
}
