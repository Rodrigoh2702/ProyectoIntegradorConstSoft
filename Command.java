public class Command {
    private String firstWord;
    private String secondWord;

    public Command(String firstWord, String secondWord) {
        this.firstWord = firstWord;
        this.secondWord = secondWord;
    }

    public String getCommandWord() {
        return firstWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    public boolean isUnknown() {
        return (firstWord == null);
    }

    public boolean hasSecondWord() {
        return (secondWord != null);
    }
}
