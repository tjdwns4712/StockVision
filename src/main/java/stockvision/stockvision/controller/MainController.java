package stockvision.stockvision.controller;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import stockvision.stockvision.DTO.MainDTO;
import stockvision.stockvision.service.MainService;

import java.util.Base64;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;
    private final RestTemplate restTemplate = new RestTemplate();
    //http요청을 보내고 응답을 받기 위한 객체

    @GetMapping("/")
    public String mainPage(){
        return "main";
    }

    @PostMapping("/")
    public String setStock(@RequestParam("stock_name") String stockName, HttpSession session) {
        session.setAttribute("stockName", stockName);
        return "redirect:/stock";
    }

    @GetMapping("/stock")
    public String stockPage(@ModelAttribute MainDTO mainDTO, HttpSession session, Model model){
        String stockName = (String) session.getAttribute("stockName");

        mainDTO.setStock_name(stockName);
        MainDTO stockCode = mainService.searchStockCode(mainDTO);

        session.setAttribute("stockCode",stockCode.getStock_code());

        model.addAttribute("stockName", stockCode.getStock_name());
        model.addAttribute("stockCode", stockCode.getStock_code());

        return "/stock";
    }

    @PostMapping("/stock")
    public String sendStockCode(HttpSession session, Model model) {

        String stockName = (String) session.getAttribute("stockName");
        String stockCode = (String) session.getAttribute("stockCode");

        model.addAttribute("stockName", stockName);
        model.addAttribute("stockCode", stockCode);

        System.out.println("Flask로 요청 보내기 시작");
        System.out.println("보내는 값은 " + stockCode);

        if (stockCode != null && !stockCode.isEmpty()) {
            String flaskApiUrl = "http://localhost:5000/stock-prediction";

            //JSON타입을 사용한 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            //요청할 값에 stockCode설정
            MainDTO requestDto = new MainDTO();
            requestDto.setStock_code(stockCode);

            HttpEntity<MainDTO> requestEntity = new HttpEntity<>(requestDto, headers);

            // 첫 번째 요청: POST로 Flask에 데이터 전송
            ResponseEntity<String> response = restTemplate.exchange(
                    flaskApiUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("flaskResponse", response.getBody());
            } else {
                model.addAttribute("flaskError", "Failed to send stock code to Flask.");
            }

            // 두 번째 요청: POST로 Flask에 이미지 요청
            ResponseEntity<byte[]> imageResponse = restTemplate.postForEntity(
                    flaskApiUrl,
                    requestEntity,
                    byte[].class
            );

            if (imageResponse.getStatusCode() == HttpStatus.OK) {
                // 이미지를 Base64 인코딩하여 HTML에서 표시할 수 있도록 모델에 추가
                byte[] imageBytes = imageResponse.getBody();
                String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
                model.addAttribute("imageData", "data:image/png;base64," + imageBase64);
            } else {
                model.addAttribute("error", "Could not fetch image from Flask API.");
            }
        }
        System.out.println("Flask로 요청 보내기 끝");
        return "stock";  // 결과를 표시할 뷰 반환
    }


}