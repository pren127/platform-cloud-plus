//package com.qhh.platform.file.api.fallback;
//
//import com.qhh.basic.base.R;
//import com.qhh.platform.file.api.AttachmentApi;
//import com.qhh.platform.file.dto.AttachmentDTO;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
///**
// * 熔断
// *
// * @author qhh
// * @date 2019/07/25
// */
//@Component
//public class AttachmentApiFallback implements AttachmentApi {
//    @Override
//    public R<AttachmentDTO> upload(MultipartFile file, Boolean isSingle, Long id, String bizId, String bizType) {
//        return R.timeout();
//    }
//}
