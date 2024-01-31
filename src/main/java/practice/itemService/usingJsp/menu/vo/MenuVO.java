package practice.itemService.usingJsp.menu.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class MenuVO {

    private Integer menuId;
    private Integer parentMenuId;
    private Integer sort;
    private Integer menuDepth;
    private String usedAt;
    private String menuName;
    private String menuUrl;

}
