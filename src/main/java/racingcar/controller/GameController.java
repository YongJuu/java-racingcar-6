package racingcar.controller;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.CarService;
import racingcar.view.Output;
import racingcar.view.UserInput;

public class GameController {
    private UserInput userInput = new UserInput();
    private CarService carService = new CarService();
    private Output output;

    private List<Car> carList;
    private int gameCount;

    public void gameStart() {
        askInputUser();
        doGame();
        output.printFinalWinner(carList);
    }

    private void askInputUser() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        carList = changeUserInputToCarList(userInput.readCarNames());

        System.out.println("시도할 회수는 몇회인가요?");
        gameCount = userInput.readGameCount();
    }

    private List<Car> changeUserInputToCarList(List<String> carNames) {
        return carNames.stream().map(Car::new).toList();
    }

    private void doGame() {
        for (int i = 0; i < gameCount; i++) {
            doStep();
            output = new Output(carService);
            //(1) 출력 : 현재의 게임 객체들 상태 출력
            output.printCurrentCar(carList);
        }
    }

    private void doStep() {
        for (int j = 0; j < carList.size(); j++) {
            //전진, 멈춤 판별
            boolean flag = carService.judgeRunOrStop();
            //판별된 상태에 따른 상태 세팅
            carService.setCurrentCar(flag, carList.get(j));
        }
    }
}