package com.ptit.sqa.service.impl;

import java.util.List;
import java.util.Map;

public interface ClassStudentService {
    List<Map<String, Object>> findAllStudentByClass(Long classId);
}
