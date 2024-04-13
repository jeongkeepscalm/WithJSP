package practice.itemService.usingJsp.address;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class AddressManager {

    private static final String _ZIP_DIRECTORY = "C:\\addressTest\\zip";
    private static final String _ZIP_EXTRACT_DIRECTORY = "C:\\addressTest\\zip_extract";

    private final AddressService service;

    public void extractZip()  throws Exception {
        File dir = new File(_ZIP_DIRECTORY);

        log.info("download_path:" + dir.getAbsolutePath());
        log.info("2. 압축파일 해제 시작");

        File files[] = dir.listFiles();
        for (File file : files) {
            ZipUtil.unzip(_ZIP_DIRECTORY + "\\" + file.getName(), _ZIP_EXTRACT_DIRECTORY);
        }
        log.info("2. 압축파일 해제 종료");
    }

    public String fmt(String str, String str2) throws IOException {
        if ("".equals(str) || "0".equals(str))
        {
            return "";
        }

        return str2 + str;
    }

    public List<Integer> readFileAndDBProcess() throws Exception
    {
        log.info("3. 데이터 처리 시작");

        File dir = new File(_ZIP_EXTRACT_DIRECTORY);
        File files[] = dir.listFiles();
        String filePath;
        String str;
        String[] strArray;
        Map<String,Object> map;
        int doroCount = 0;
        int bubJungCount = 0;
        int deleteCount = 0;
        List<Integer> returnList = new ArrayList<Integer>();

        List<Map<String, Object>> list = new ArrayList<>();
        int BATCH_SIZE = 1000;



        try
        {
            for (File file : files) {
                filePath = file.getAbsolutePath();

                    try
                    {
                        FileInputStream fileInputStream = new FileInputStream(filePath);
                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "EUC-KR");
                        BufferedReader reader = new BufferedReader(inputStreamReader);

                        while ((str = reader.readLine()) != null) {
                            if (!"No Data".equals(str)) {

                                strArray = str.split("\\|");
                                map = new HashMap<String,Object>();

                                String statusCode = strArray[22]; // 31 insert, 34 update, 63 delete
                                String buildNo = strArray[15]; // 빌딩관리번호
                                map.put("건물관리번호", buildNo);

                                if ("63".equals(statusCode) || "72".equals(statusCode)) //63일때는 삭제이고, 삭제일때는 건물관리번호만 있으면 된다
                                {
                                    service.delete_addr(map); // delete한후 다음행을 진행한다.
                                    deleteCount++;
                                    System.out.println("도로명데이터 삭제 : 이동사유코드 " + statusCode +  "로 인해 " + deleteCount +  "건 삭제완료");
                                    continue;
                                }

                                String dong = strArray[3]; //읍면동
                                String ri = strArray[4];
                                String buildingName = strArray[25].trim();

                                map.put("법정동코드", strArray[0]);
                                map.put("시도", strArray[1]);
                                map.put("시군구", strArray[2]);
                                map.put("읍면", dong); //읍면동
                                map.put("리", ri);
                                map.put("산여부", strArray[5]);
                                map.put("지번본번", strArray[6]); // 번지
                                map.put("지번부번", strArray[7]); // 호
                                map.put("도로명코드", strArray[8]);
                                map.put("도로명", strArray[9]);
                                map.put("지하여부", strArray[10]);
                                map.put("건물본번", strArray[11]);
                                map.put("건물부번", strArray[12]);

                                map.put("건축물대장건물명", strArray[13]);
                                map.put("상세건물명", strArray[14]);

                                map.put("읍면동일련번호", strArray[16]);
                                map.put("행정동코드", strArray[17]);
                                map.put("행정동명", strArray[18]);
                                map.put("우편번호", strArray[19]);
                                map.put("우편일련번호", strArray[20]);
                                map.put("다량배달처명", strArray[21]);
                                map.put("이동사유코드", strArray[22]);
                                map.put("변동일자", strArray[23]);
                                map.put("변동전도로명주소", strArray[24]);
                                map.put("시군구용건물명", buildingName);
                                map.put("공동주택여부", strArray[26]);
                                map.put("구역번호", strArray[27]);
                                map.put("상세주소여부", strArray[28]);

                                String juso = strArray[1] + " " + strArray[2];

                                String juso1 = strArray[9] + " " + strArray[11] + fmt(strArray[12],"-"); //도로명
                                String juso2 = dong + fmt(ri," ") + " " +strArray[6] + fmt(strArray[7],"-") + fmt(buildingName," "); //지번

                                if ("".equals(ri)) // 리 주소가 없을때
                                {
                                    juso1 = juso1 + "(" + dong + fmt(buildingName,",") + ")";
                                }
                                else // 리 주소가 있을때
                                {

                                    juso1 = dong + " " + juso1;
                                    if(buildingName.length() > 0)
                                    {
                                        juso1 = juso1 + "(" + buildingName + ")";
                                    }
                                }

                                map.put("검색용_도로명주소", juso1);
                                map.put("검색용_지번주소", juso2);

                                map.put("도로명주소", juso + " " + juso1);
                                map.put("지번주소", juso + " " + juso2);

                                /* 31이 insert이고 34가 update이지만 31인데 데이터가 존재하는 경우 insert 오류가 발생할수 있다.
                                 * 따라서 31이든 34든 update를 해서 update count = 0일때 insert하도록 구현한다. */

//                                int cnt = service.update_addr(map);
//                                if (cnt == 0)
//                                {
//                                    service.insert_addr(map);
//                                }

                                list.add(map);

                                if (list.size() == BATCH_SIZE) {
                                    service.insert_addr(list);
                                    list.clear();
                                }

                                doroCount++; // 데이터처리건수1증가
                            }
                        }

                        if (!list.isEmpty()) {
                            service.insert_addr(list);
                        }

                        reader.close();
                        System.out.println("도로명 처리 데이터 건수 : " + doroCount + "건");
                        System.out.println("도로명 삭제 데이터 건수 : " + deleteCount + "건");
                        deleteCount = 0;
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        throw new Exception("Error! " + doroCount + "행까지 success." + (doroCount+1) + " 원인:" + e.getMessage());
                    }
                try
                {
                    FileInputStream fileInputStream = new FileInputStream(filePath);
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "EUC-KR");
                    BufferedReader reader = new BufferedReader(inputStreamReader);

                    String sido = "";
                    String sigungu = "";
                    String bubjungdong = "";
                    String bubjungcode = "";
                    String ri = "";

                    while ((str = reader.readLine()) != null) {
                        if (!"No Data".equals(str)) {

                            strArray = str.split("\\|");
                            map = new HashMap<String,Object>();

                            String statusCode = strArray[13]; // 31 insert, 34 update, 63 delete

                            if(bubjungcode.equals(strArray[0]) && sido.equals(strArray[1]) && sigungu.equals(strArray[2]) && bubjungdong.equals(strArray[3]) && ri.equals(strArray[4])) {
                                continue;
                            }

                            if ("63".equals(statusCode)) //63일때는 삭제이고, 삭제일때는 법정동코드만 있으면 된다
                            {
                                service.delete_bubJung(map); // delete한후 다음행을 진행한다.
                                deleteCount++;
                                System.out.println("법정동데이터 삭제 : 이동사유코드 63로 인해 " + deleteCount +  "건 삭제완료");
                                continue;
                            }

                            bubjungcode = strArray[0];
                            sido = strArray[1];
                            sigungu = strArray[2];
                            bubjungdong = strArray[3];
                            ri = strArray[4];

                            map.put("법정동코드", bubjungcode);
                            map.put("시도", sido);
                            map.put("시군구", sigungu);
                            map.put("법정동명", bubjungdong); // 법정동명
                            map.put("리", ri);

                            /* 31이 insert이고 34가 update이지만 31인데 데이터가 존재하는 경우 insert 오류가 발생할수 있다.
                             * 따라서 31이든 34든 update를 해서 update count = 0일때 insert하도록 구현한다. */

                            int cnt = service.update_bubJung(map);
                            if (cnt == 0)
                            {
                                service.insert_bubJung(map);
                            }

                            bubJungCount++; // 데이터처리건수1증가
                        }
                    }
                    reader.close();
                    System.out.println("법정동 처리 데이터 건수 : " + bubJungCount + "건");
                    System.out.println("법정동 삭제 데이터 건수 : " + deleteCount + "건");
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    throw new Exception("Error! " + bubJungCount + "행까지 success." + (bubJungCount+1) + " 원인:" + e.getMessage());
                }

            }
            returnList.add(0, doroCount);
            returnList.add(1, bubJungCount);
            log.info("3. 데이터 처리 종료");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return returnList;

    }

}
