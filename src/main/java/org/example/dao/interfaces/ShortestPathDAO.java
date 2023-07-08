package org.example.dao;

import org.example.model.Route;
import org.example.model.ShortestPath;

import java.util.List;

public interface ShortestPathDAO {
    void insertShortestPath(ShortestPath shortestPath);
    void updateShortestPath(ShortestPath shortestPath);
    ShortestPath getShortestPath(int id);
}
