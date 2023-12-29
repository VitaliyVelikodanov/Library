package app.additions;

public class SearchParams {
    private String pattern;
    private String username;

    public SearchParams(String pattern, String username) {
        this.pattern = pattern;
        this.username = username;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
