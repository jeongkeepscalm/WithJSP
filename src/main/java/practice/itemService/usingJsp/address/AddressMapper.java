package practice.itemService.usingJsp.address;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface AddressMapper {

    Map<String, Object> select_obj_date();

    int insert_addr(Map<String, Object> map);

    int update_addr(Map<String, Object> map);

    int delete_addr(Map<String, Object> map);

    int insert_bubJung(Map<String, Object> map);

    int update_bubJung(Map<String, Object> map);

    int delete_bubJung(Map<String, Object> map);
}
