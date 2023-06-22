package com.infyrail.entity.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomIDGenerator implements IdentifierGenerator {
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Connection connection = session.connection();
        try {
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery("select count(route_id) as Id from route");
            if(rs.next())
            {
                int id=rs.getInt(1)+100;
                return id;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
	}
}