package main.dao.impl;

import main.DataSource;
import main.dao.TrainDao;
import main.model.Train;

import java.util.List;

public class TrainDaoImpl implements TrainDao {

    private DataSource dataSource;

    public TrainDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Train> findAll() {
        return null;
    }

    @Override
    public Train findById(Long id) {
        return null;
    }

    @Override
    public void delete(Train train) {

    }

    @Override
    public void insert(Train train) {

    }

    @Override
    public void update(Train train) {

    }
}
