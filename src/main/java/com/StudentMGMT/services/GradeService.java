package com.StudentMGMT.services;

import java.util.List;
import java.util.UUID;

import com.StudentMGMT.dao.GradeDao;
import com.StudentMGMT.entities.Grade;

public class GradeService {
	 private GradeDao gradeDao = new GradeDao();

	public List<Grade> getGradesByUser(UUID userId) {
        try {
            return gradeDao.getGradesByUser(userId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
