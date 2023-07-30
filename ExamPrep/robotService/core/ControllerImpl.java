package robotService.core;

import robotService.entities.robot.FemaleRobot;
import robotService.entities.robot.MaleRobot;
import robotService.entities.robot.Robot;
import robotService.entities.services.MainService;
import robotService.entities.services.SecondaryService;
import robotService.entities.services.Service;
import robotService.entities.supplements.MetalArmor;
import robotService.entities.supplements.PlasticArmor;
import robotService.entities.supplements.Supplement;
import robotService.repositories.SupplementRepository;

import java.util.*;
import java.util.stream.Collectors;

import static robotService.common.ConstantMessages.*;
import static robotService.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private SupplementRepository supplements;
    private Map<String,Service> services;

    public ControllerImpl() {
        this.supplements = new SupplementRepository();
        this.services = new LinkedHashMap<>();
    }

    @Override
    public String addService(String type, String name) {
        Service service;
        if ("MainService".equals(type)) {
            service = new MainService(name);
        } else if ("SecondaryService".equals(type)) {
            service = new SecondaryService(name);
        } else {
            throw new NullPointerException(INVALID_SERVICE_TYPE);
        }
        services.put(name,service);
        return String.format(SUCCESSFULLY_ADDED_SERVICE_TYPE, type);
    }

    @Override
    public String addSupplement(String type) {
        Supplement supplement;
        if ("PlasticArmor".equals(type)) {
            supplement = new PlasticArmor();
        } else if ("MetalArmor".equals(type)) {
            supplement = new MetalArmor();
        } else {
            throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }
        supplements.addSupplement(supplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {


        Supplement currentSupplement=supplements.findFirst(supplementType);

        if(currentSupplement==null){
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND,supplementType));
        }
        services.get(serviceName).addSupplement(currentSupplement);
        supplements.removeSupplement(currentSupplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE,supplementType,serviceName);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {
        Robot robot;
        if("FemaleRobot".equals(robotType)) {
            robot=new FemaleRobot(robotName,robotKind,price);
        }else if("MaleRobot".equals(robotType)) {
            robot=new MaleRobot(robotName,robotKind,price);
        }else{
            return INVALID_ROBOT_TYPE;
        }
        Service service=services.get(serviceName);
        String serviceType=service.getClass().getSimpleName();

        boolean isFemaleRobotInSecondaryService="FemaleRobot".equals(robotType) && "SecondaryService".equals(serviceType);
        boolean isMaleRobotInMainService="MaleRobot".equals(robotType) && "MainService".equals(serviceType);

        if(isFemaleRobotInSecondaryService ||isMaleRobotInMainService) {
            service.addRobot(robot);
        }else{
            return "Unsuitable service.";
        }

        return String.format(SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE,robotType,serviceName);
    }

    @Override
    public String feedingRobot(String serviceName) {
        Service service=services.get(serviceName);
        service.feeding();
        return String.format(FEEDING_ROBOT,service.getRobots().size());
    }

    @Override
    public String sumOfAll(String serviceName) {
        Collection<Robot> service=services.get(serviceName).getRobots();
        double sum=service.stream().mapToDouble(Robot::getPrice).sum();
        return String.format(VALUE_SERVICE,serviceName,sum);
    }

    @Override
    public String getStatistics() {
        return services.values().stream()
                .map(Service::getStatistics)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
