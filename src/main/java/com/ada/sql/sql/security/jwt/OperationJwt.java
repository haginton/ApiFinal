package com.ada.sql.sql.security.jwt;

import com.ada.sql.sql.dto.user.UserGenericDto;
import com.ada.sql.sql.model.sql.User;

import java.util.Calendar;

public interface OperationJwt {

    String generateToken(UserGenericDto user, Calendar calendar);
}
