package ait.cohort70.forum.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDto {
    private Long id;
    private String fileName;
    private String contentType;
    private byte[] content;
}
