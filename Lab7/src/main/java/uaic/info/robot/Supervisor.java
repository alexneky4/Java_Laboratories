package uaic.info.robot;

import uaic.info.exploration.Exploration;

import java.util.Scanner;

public class Supervisor implements Runnable{
    private Exploration exploration;

    public Supervisor(Exploration exploration) {
        this.exploration = exploration;
    }

    public void setExploration(Exploration exploration) {
        this.exploration = exploration;
    }

    public void pauseRobot() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What robot do you want to pause?");
        int index = scanner.nextInt();
        if(index <= 0 || index > exploration.getRobots().size()) {
            System.out.println("The robot could not be paused");
            return;
        }
        Robot robot = exploration.getRobots().get(index - 1);
        if(robot.isSleeping) {
            System.out.println("The robot is already paused");
            return;
        }
        System.out.println("Give the time (in seconds) that you want the robot to be paused for: ");
        int timer = scanner.nextInt();
        synchronized (robot) {
            if(timer > 0) {
                robot.pauseWithTime(timer);
            } else {
                robot.pause();
            }
        }
    }

    public void pauseAllRobots()
    {
        for(Robot robot : exploration.getRobots())
            robot.pause();
    }

    public void resumeAllRobots()
    {
        for(Robot robot : exploration.getRobots())
        {
            robot.resume();
        }

        exploration.getRobots().notifyAll();
    }

    public void resumeRobot() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What robot do you want to resume?");
        int index = scanner.nextInt();
        if(index <= 0 || index > exploration.getRobots().size()) {
            System.out.println("The robot could not be resumed");
            return;
        }
        Robot robot = exploration.getRobots().get(index - 1);
        if(!robot.isSleeping) {
            System.out.println("The robot is not paused");
            return;
        }

        robot.resume();
        synchronized (robot) {
            robot.notify();
        }
    }

    @Override
    public void run() {
        while(true) {
            if(exploration.getMap().allVisited()) {
                System.out.println("The map was already explored");
                break;
            }
            System.out.println("Choose one of the commands");
            System.out.println("1. Pause a robot");
            System.out.println("2. Resume a robot");
            System.out.println("3. Pause all robots");
            System.out.println("4. Resume all robots");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch(option) {
                case 1:
                    pauseRobot();
                    break;
                case 2:
                    resumeRobot();
                    break;
                case 3:
                    pauseAllRobots();
                    break;
                case 4:
                    resumeAllRobots();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

}
