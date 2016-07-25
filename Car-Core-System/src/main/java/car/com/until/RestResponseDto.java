package car.com.until;

import java.io.Serializable;

/**
 * Created by ThienDN on 8/26/2015.
 */
public class RestResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int returnedCode;
    private Object data;

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
    public RestResponseDto(){}

    public RestResponseDto(int returnedCode, Object data) {
        this.returnedCode = returnedCode;
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RestResponseDto{" +
                "returnedCode=" + returnedCode +
                ", data=" + data +
                '}');
        return builder.toString();
    }
}
