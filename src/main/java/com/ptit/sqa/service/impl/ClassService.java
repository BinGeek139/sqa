package com.ptit.sqa.service.impl;

import java.util.List;
import java.util.Map;

public interface ClassService {

    List<Map<String, String>> findAllActivatingClass(Long semId, Long UserId);

}
