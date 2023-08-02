package vehicleShop.core;

import vehicleShop.models.shop.Shop;
import vehicleShop.models.shop.ShopImpl;
import vehicleShop.models.tool.Tool;
import vehicleShop.models.tool.ToolImpl;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.vehicle.VehicleImpl;
import vehicleShop.models.worker.FirstShift;
import vehicleShop.models.worker.SecondShift;
import vehicleShop.models.worker.Worker;
import vehicleShop.repositories.Repository;
import vehicleShop.repositories.VehicleRepository;
import vehicleShop.repositories.WorkerRepository;

import java.util.List;
import java.util.stream.Collectors;

import static vehicleShop.common.ConstantMessages.*;
import static vehicleShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private Repository<Vehicle> vehicleRepository;
    private Repository<Worker> workerRepository;
    private int countMadeVehicles;

    public ControllerImpl() {
        this.vehicleRepository = new VehicleRepository();
        this.workerRepository = new WorkerRepository();
        this.countMadeVehicles=0;
    }

    @Override
    public String addWorker(String type, String workerName) {
        Worker worker;
        if ("FirstShift".equals(type)) {
            worker = new FirstShift(workerName);
        } else if ("SecondShift".equals(type)) {
            worker = new SecondShift(workerName);
        } else {
            throw new IllegalArgumentException(WORKER_TYPE_DOESNT_EXIST);
        }
        workerRepository.add(worker);
        return String.format(ADDED_WORKER, type, workerName);
    }

    @Override
    public String addVehicle(String vehicleName, int strengthRequired) {
        Vehicle vehicle = new VehicleImpl(vehicleName, strengthRequired);
        vehicleRepository.add(vehicle);
        return String.format(SUCCESSFULLY_ADDED_VEHICLE, vehicleName);
    }

    @Override
    public String addToolToWorker(String workerName, int power) {
        Worker worker = workerRepository.findByName(workerName);
        if (worker == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }
        Tool tool = new ToolImpl(power);
        worker.addTool(tool);
        return String.format(SUCCESSFULLY_ADDED_TOOL_TO_WORKER, power, workerName);
    }

    @Override
    public String makingVehicle(String vehicleName) {
        List<Worker> readyWorkers = workerRepository
                .getWorkers()
                .stream().filter(w -> w.getStrength() > 70)
                .collect(Collectors.toList());
        if (readyWorkers.isEmpty()) {
            throw new IllegalArgumentException(NO_WORKER_READY);
        }
        Vehicle vehicle = vehicleRepository.findByName(vehicleName);
        Shop shop = new ShopImpl();
        int brokenTools=0;
        while (!readyWorkers.isEmpty() && !vehicle.reached()) {
            Worker worker=readyWorkers.get(0);
            shop.make(vehicle,worker);
            brokenTools+=worker.getTools().stream().filter(Tool::isUnfit).count();

            if(!worker.canWork() || worker.getTools().stream().noneMatch(t->!t.isUnfit())){
                readyWorkers.remove(worker);
            }
        }
        if(vehicle.reached()){
            countMadeVehicles++;
            return String.format(VEHICLE_DONE,vehicle.getName(), "done")+
                    String.format(COUNT_BROKEN_INSTRUMENTS,brokenTools);
        }else{
            return String.format(VEHICLE_DONE,vehicle, "not done")+
                    String.format(COUNT_BROKEN_INSTRUMENTS,brokenTools);
        }
    }

    @Override
    public String statistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d vehicles are ready!",countMadeVehicles)).append(System.lineSeparator());
        sb.append("Info for workers:").append(System.lineSeparator());
        workerRepository.getWorkers().forEach(worker -> {
            sb.append(worker.toString()).append(System.lineSeparator());
        });
        return sb.toString().trim();
    }
}
