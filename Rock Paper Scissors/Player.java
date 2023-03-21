import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Player {
    private int score = 0;
    private String name = "";
    private String sign = "";
    Player (String name, String path){
        File file = new File(path);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                if (sc.next().equals(name)) {
                    this.name = name;
                    this.score = sc.nextInt();
                } else {
                    this.name = name;
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("score file not found");
        }
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }
}
