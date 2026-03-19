package com.facet.api.point;

import com.facet.api.common.model.BaseResponse;
import com.facet.api.common.model.BaseResponseStatus;
import com.facet.api.point.model.PointDto;
import com.facet.api.user.model.AuthUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.facet.api.common.model.BaseResponseStatus.*;

@RestController
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;

    //[1단계] 프론트에서 결제 금액 선택하고 충전하기 눌렀을 때
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<PointDto.CreateRes>> createPointOrder(
            @AuthenticationPrincipal AuthUserDetails userDetails,
            @RequestBody PointDto.CreateReq dto) {

        // 1. 서비스 호출 : DB에 '결제 대기(false)' 상태로 주문서를 만들고 결과(주문번호 등)를 받아옴
        PointDto.CreateRes result = pointService.createPointOrder(userDetails.toEntity(), dto);

        // 2. 프론트에게 결제대기 상태 코드와 응답 데이터 보내주기
        return ResponseEntity.ok(BaseResponse.ready_point(result));
    }

    //[2단계] 결제 검증 및 충전
    @PostMapping("/verify")
    public ResponseEntity<BaseResponse> verifyAndChargePoint(
            @AuthenticationPrincipal AuthUserDetails userDetails,
            @RequestBody PointDto.VerifyReq dto) {


        try{
            pointService.VerifyAndChargePoint(userDetails.toEntity(), dto);

            // 성공 시
            return ResponseEntity.ok(BaseResponse.success(SUCCESS));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
