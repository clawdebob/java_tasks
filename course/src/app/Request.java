package app;

import java.util.HashMap;

public class Request {
    public String type;
    public HashMap<String,String> data = new HashMap<String,String>();
    Request(){
        super();
    }
    Request(String type,HashMap<String,String> data){
        super();
        this.type=type;
        this.data=data;
    }
}
