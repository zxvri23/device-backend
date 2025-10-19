package bg.tuvarna.devicebackend.controllers;

import bg.tuvarna.devicebackend.models.dtos.RenovationCreateVO;
import bg.tuvarna.devicebackend.models.dtos.RenovationVO;
import bg.tuvarna.devicebackend.models.entities.Renovation;
import bg.tuvarna.devicebackend.services.RenovationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/renovations")
@AllArgsConstructor
public class RenovationController {
    private final RenovationService renovationService;

    @Operation(description = "Add renovation for device",
            summary = "Add renovation for device")
    @PostMapping()
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<RenovationVO> saveRenovation(@RequestBody @Valid RenovationCreateVO vo) {
        Renovation saved = renovationService.save(vo);

        return ResponseEntity.created(URI.create("/devices/" + saved.getId())).body(new RenovationVO(saved));
    }
}