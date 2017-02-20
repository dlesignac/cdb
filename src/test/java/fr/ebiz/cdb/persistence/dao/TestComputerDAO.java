package fr.ebiz.cdb.persistence.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.ebiz.cdb.model.Computer;

@RunWith(MockitoJUnitRunner.class)
public class TestComputerDAO {

    @Mock
    Connection mockConnection;

    @Mock
    PreparedStatement mockPreparedStatement;

    @Mock
    Statement mockStatement;

    @Mock
    ResultSet mockResultSet;

    @Before
    public void setUp() throws SQLException {
        doNothing().when(mockConnection).commit();

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockConnection.createStatement()).thenReturn(mockStatement);

        doNothing().when(mockPreparedStatement).setString(anyInt(), anyString());
        doNothing().when(mockPreparedStatement).setNull(anyInt(), anyInt());
        doNothing().when(mockPreparedStatement).setDate(anyInt(), any());
        doNothing().when(mockPreparedStatement).setInt(anyInt(), anyInt());

        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);

        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
        when(mockResultSet.first()).thenReturn(Boolean.TRUE);
        when(mockResultSet.getInt("computer_id")).thenReturn(0);
        when(mockResultSet.getString("computer_name")).thenReturn("computer");
        when(mockResultSet.getDate("introduced")).thenReturn(null);
        when(mockResultSet.getDate("discontinued")).thenReturn(null);
        when(mockResultSet.getInt("computer_id")).thenReturn(0);
    }

    @Test
    public void testCreate() throws SQLException {
        Computer computer = new Computer();
        computer.setId(0);
        computer.setName("name");

        DAO<Computer> dao = new ComputerDAO(mockConnection);
        Assert.assertThat(dao.create(computer), equalTo(true));

        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).setString(anyInt(), anyString());
        verify(mockPreparedStatement, times(3)).setNull(anyInt(), anyInt());
        verify(mockPreparedStatement, times(1)).executeUpdate();
        verify(mockConnection, times(1)).commit();
    }

    @Test
    public void testDelete() throws SQLException {
        Computer computer = new Computer();
        computer.setId(0);

        DAO<Computer> dao = new ComputerDAO(mockConnection);
        Assert.assertThat(dao.delete(computer), equalTo(true));

        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).setInt(anyInt(), anyInt());
        verify(mockPreparedStatement, times(1)).executeUpdate();
        verify(mockConnection, times(1)).commit();
    }

    @Test
    public void testUpdate() throws SQLException {
        Computer computer = new Computer();
        computer.setId(0);
        computer.setName("name");

        DAO<Computer> dao = new ComputerDAO(mockConnection);
        Assert.assertThat(dao.update(computer), equalTo(true));

        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).setString(anyInt(), anyString());
        verify(mockPreparedStatement, times(3)).setNull(anyInt(), anyInt());
        verify(mockPreparedStatement, times(1)).setInt(anyInt(), anyInt());
        verify(mockPreparedStatement, times(1)).executeUpdate();
        verify(mockConnection, times(1)).commit();
    }

    @Test
    public void testFind() throws SQLException {
        DAO<Computer> dao = new ComputerDAO(mockConnection);
        Assert.assertThat(dao.find(0), notNullValue());

        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).setInt(anyInt(), anyInt());
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).first();
        verify(mockResultSet, times(1)).getInt("computer_id");
        verify(mockResultSet, times(1)).getString("computer_name");
        verify(mockResultSet, times(1)).getDate("introduced");
        verify(mockResultSet, times(1)).getDate("discontinued");
        verify(mockResultSet, times(1)).getInt("company_id");
    }

    @Test
    public void testFetchAll() throws SQLException {
        DAO<Computer> dao = new ComputerDAO(mockConnection);
        Assert.assertThat(dao.fetchAll().getEntries().size(), equalTo(4));

        verify(mockConnection, times(1)).createStatement();
        verify(mockStatement, times(1)).executeQuery(anyString());
        verify(mockResultSet, times(5)).next();
        verify(mockResultSet, times(4)).getInt("computer_id");
        verify(mockResultSet, times(4)).getString("computer_name");
        verify(mockResultSet, times(4)).getDate("introduced");
        verify(mockResultSet, times(4)).getDate("discontinued");
        verify(mockResultSet, times(4)).getInt("company_id");
    }

}
