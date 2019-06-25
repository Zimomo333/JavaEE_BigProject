package Json;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

public class Jsonmake {
    public static Jsonb js(){
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb;
    }
}