
package com.handler;


import com.utils.MyMD5Utils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



@Component
public class PasswdTypeHandler extends BaseTypeHandler<String> {

    //加密工具，用于登录时密文比对
    @Autowired
    private MyMD5Utils Mymd5Utils;

    // 写入/更新/修改：明文 → MD5加密（自动触发）
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        // 避免二次加密（如更新时传入的已是密文）
        if (parameter.matches("[a-f0-9]{32}")) {
            ps.setString(i, parameter);
            return;
        }
        // 明文+盐值加密后写入
        String encryptedPwd = Mymd5Utils.md5WithSalt(parameter);
        ps.setString(i, encryptedPwd);
    }

    // 读取：直接返回数据库密文（无需解密，透传即可）
    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getString(columnIndex);
    }
}