package olii.apps.workplacer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

    @GetMapping("/test")
    public int test() {
        return 1;
    }

}
