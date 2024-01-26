package practice.itemService.usingJsp.login.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.itemService.usingJsp.login.mapper.LoginMapper;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginMapper loginMapper;

    @Override
    public boolean duplicateCheckId(String id) {
        return loginMapper.idDuplicationCheck(id) > 0 ? true : false;
    }
}
