package com.example.royalhouse.mapper;

import com.example.royalhouse.entity.Request;
import com.example.royalhouse.model.RequestDTOView;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferRequest {
    public static RequestDTOView toDTOView(Request request) {
        RequestDTOView requestDTOView = new RequestDTOView();
        requestDTOView.setId(request.getId());
        requestDTOView.setFullName(request.getFullName());
        requestDTOView.setPhone(request.getPhone());
        requestDTOView.setEmail(request.getEmail());
        requestDTOView.setComment(request.getComment());

        if (request.getDate() != null)
            requestDTOView.setDate(request.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        requestDTOView.setReported(request.isReported());
        return requestDTOView;
    }

    public static List<RequestDTOView> toListDTOView(List<Request> requests) {
        return requests.stream()
                .map(TransferRequest::toDTOView)
                .collect(Collectors.toList());
    }
}
