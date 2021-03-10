package data_access.dao.impl.route_dao_impl;

import data_access.DataSource;
import data_access.dao.route_dao.RouteDao;
import data_access.model.route.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {

    private DataSource dataSource;

    public RouteDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Route> findByAll() {
        Connection connection = null;
        List<Route> routes = new ArrayList<Route>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Route route = new Route();
                route.setId(resultSet.getLong(Route.ID_COLUMN));
                route.setFromTown(resultSet.getString(Route.FROM_TOWN_COLUMN));
                route.setToTown(resultSet.getString(Route.TO_TOWN_COLUMN));
                route.setPrice(resultSet.getInt(Route.PRICE_COLUMN));
                route.setTimeStart(resultSet.getString(Route.TIME_START_COLUMN));
                route.setTimeEnd(resultSet.getString(Route.TIME_END_COLUMN));
                route.setAllTickets(resultSet.getInt(Route.ALL_TICKETS_COLUMN));
                route.setSoldTickets(resultSet.getInt(Route.SOLD_TICKETS_COLUMN));
                routes.add(route);
            }
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            }catch (SQLException exc) {
                System.out.println(exc);
            }
        }
        return routes;
    }

    @Override
    public Route findById(Long id) {
        Connection connection = null;
        Route route = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                route = new Route();
                route.setId(resultSet.getLong(Route.ID_COLUMN));
                route.setFromTown(resultSet.getString(Route.FROM_TOWN_COLUMN));
                route.setToTown(resultSet.getString(Route.TO_TOWN_COLUMN));
                route.setPrice(resultSet.getInt(Route.PRICE_COLUMN));
                route.setTimeStart(resultSet.getString(Route.TIME_START_COLUMN));
                route.setTimeEnd(resultSet.getString(Route.TIME_END_COLUMN));
                route.setAllTickets(resultSet.getInt(Route.ALL_TICKETS_COLUMN));
                route.setSoldTickets(resultSet.getInt(Route.SOLD_TICKETS_COLUMN));
            }
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            }catch (SQLException exc) {
                System.out.println(exc);
            }
        }
        return route;
    }

    @Override
    public void delete(Route route) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            preparedStatement.setLong(1, route.getId());
            preparedStatement.execute();
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            }catch (SQLException exc) {
                System.out.println(exc);
            }
        }

    }

    @Override
    public void insert(Route route) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, route.getFromTown());
            preparedStatement.setString(2, route.getToTown());
            preparedStatement.setString(3, route.getTimeEnd());
            preparedStatement.setString(4, route.getTimeStart());
            preparedStatement.setInt(5, route.getPrice());
            preparedStatement.setInt(6, route.getAllTickets());
            preparedStatement.setInt(7, route.getSoldTickets());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                route.setId(resultSet.getLong(1));
            }
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            }catch (SQLException exc) {
                System.out.println(exc);
            }
        }

    }

    @Override
    public void update(Route route) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            preparedStatement.setString(1, route.getFromTown());
            preparedStatement.setString(2, route.getToTown());
            preparedStatement.setString(3, route.getTimeEnd());
            preparedStatement.setString(4, route.getTimeStart());
            preparedStatement.setInt(5, route.getPrice());
            preparedStatement.setInt(6, route.getAllTickets());
            preparedStatement.setInt(7, route.getSoldTickets());
            preparedStatement.setLong(8, route.getId());
            preparedStatement.execute();
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            }catch (SQLException exc) {
                System.out.println(exc);
            }
        }

    }
}
