package com.liy.common.domain.dto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LiY
 * 分页数据参数
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {

    @Schema(title = "第几页，默认1")
    @Min(0)
    private Integer pageNum = 1;

    @Schema(description = "每页数据条数,默认10")
    @Min(value = 1, message = "最小值不小于1")
    private Integer pageSize = 10;

}
