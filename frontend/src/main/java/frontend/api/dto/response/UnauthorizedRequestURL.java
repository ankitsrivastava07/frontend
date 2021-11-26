package frontend.api.dto.response;

import lombok.Data;

@Data
public class UnauthorizedRequestURL {
    private String redirectURL;
    private boolean redirect=Boolean.FALSE;
    private String message;

}
