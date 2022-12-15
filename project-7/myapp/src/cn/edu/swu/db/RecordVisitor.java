package cn.edu.swu.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RecordVisitor<T> {
    public T visit(ResultSet rs) throws SQLException;
}
