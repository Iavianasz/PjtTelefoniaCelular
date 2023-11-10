import java.text.SimpleDateFormat;
import java.util.Date;

public class Call {
    private Date date;
    private int duration;

    public Call(Date date, int duration){
        this.date = date;
        this.duration = duration;
    }

    public Date getDate(){
        return date;
    }

    public int getDuration(){
        return duration;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");

        return "Chamada" + "data=" + formatDate.format(date.getTime()) + ", duracao=" + duration;
    }

}
