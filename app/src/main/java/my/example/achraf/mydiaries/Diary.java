package my.example.achraf.mydiaries;

public class Diary {
    private int id;
    private String date;
    private String story;

    public Diary(int id, String date, String story){
        this.id = id;
        this.date = date;
        this.story = story;
    }

    public String getDate() {
        return date;
    }

    public String getStory() {
        return story;
    }

    public int getId(){  return id; }



}
