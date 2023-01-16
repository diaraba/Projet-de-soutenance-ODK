package com.diaraba.projetDeSoutenance.payload.response;


import com.diaraba.projetDeSoutenance.payload.request.AvisOffreRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvisOffreResponse {
    private List<AvisOffreRequest> content;
    private  int pageNo;
    private int pageSize;
    private long totalElments;
    private  int totalPages;
    private boolean last;
}
