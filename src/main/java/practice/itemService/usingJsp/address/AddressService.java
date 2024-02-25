package practice.itemService.usingJsp.address;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressMapper dao;

    public Map<String, Object> select_obj_date(){
        return dao.select_obj_date();
    }

    public int insert_addr(Map<String, Object> map){
        return dao.insert_addr(map);
    }

    public int update_addr(Map<String, Object> map){
        return dao.update_addr(map);
    }

    public int delete_addr(Map<String, Object> map){
        return dao.delete_addr(map);
    }

    public int insert_bubJung(Map<String, Object> map){
        return dao.insert_bubJung(map);
    }

    public int update_bubJung(Map<String, Object> map){
        return dao.update_bubJung(map);
    }

    public int delete_bubJung(Map<String, Object> map){
        return dao.delete_bubJung(map);
    }

}
