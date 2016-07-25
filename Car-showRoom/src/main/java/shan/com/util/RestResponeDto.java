package shan.com.util;

import java.io.Serializable;

/**
 * Created by ThienDN on 8/29/2015.
 */
public class RestResponeDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int returnedCode;
    private Object data = null;

    public int getReturnedCode() {
        return returnedCode;
    }

    public void setReturnedCode(int returnedCode) {
        this.returnedCode = returnedCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
