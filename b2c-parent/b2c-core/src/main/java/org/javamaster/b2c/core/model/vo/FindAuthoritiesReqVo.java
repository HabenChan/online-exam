package org.javamaster.b2c.core.model.vo;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;

/**
 * @author yudong
 * @date 2019/7/28
 */
@Data
public class FindAuthoritiesReqVo {
    @NotBlank
    private String username;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
