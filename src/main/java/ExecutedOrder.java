public class ExecutedOrder {
    private int id;
    private float amount;
    private String comment;
    private String filename;
    private int line;
    private String result;

    public ExecutedOrder(int id, float amount, String comment, String filename, int line, String result) {
        this.id = id;
        this.amount = amount;
        this.comment = comment;
        this.filename = filename;
        this.line = line;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ExecutedOrder{" +
                "id='" + id + '\'' +
                ", amount='" + amount + '\'' +
                ", comment='" + comment + '\'' +
                ", filename='" + filename + '\'' +
                ", line=" + line +
                ", result='" + result + '\'' +
                '}';
    }
}
