package racingcar.view;

import camp.nextstep.edu.missionutils.Console;

public class UserInput {
    static String userInputCarName;
    static int gameCount;

    public UserInput() {
    }

    public void setGameCount(int gameCount) {
        this.gameCount = gameCount;
    }

    public void setUserInputCarName(String userInputCarName) {
        this.userInputCarName = userInputCarName;
    }

    public static String getUserInputCarName() {
        return userInputCarName;
    }

    public static int getGameCount() {
        return gameCount;
    }

    // -이름 중복될 경우
    static void validateDuplicate(String[] names) {
        //3) 이름이 중복될 경우 예외
        String tmp = "";
        for (String x : names) {
            if (tmp.contains(x)) {
                throw new IllegalArgumentException("경주할 자동차 이름이 중복되면 안됩니다.");
            }
            tmp += x; //생각해보니까. 포함되는 문자열이 생길 수 도 있으니 다시 작성하자. :TODO
        }
    }

    //validate 메소드 분리 - 길이
    static void validateLength(String[] names) {
        //1) 각 이름 길이 5초과 시 예외
        for (String x : names) {
            if (x.length() > 5) {
                throw new IllegalArgumentException("경주할 자동차 이름의 길이는 5이하여야 합니다.");
            }
        }
    }

    //- 공백 포함 시
    static void validateBlank(String[] names) {
        //2) 각 이름에 " " 공백 포함 시 예외
        for (String x : names) {
            if (x.contains(" ")) {
                throw new IllegalArgumentException("경주할 자동차 이름에 공백이 포함되면 안됩니다.");
            }
        }
    }

    //1) 사용자 입력 처리해서 객체에도 세팅
    public void askInputUser(UserInput userInput) {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String carName = Console.readLine();

        validateInput(carName);

        System.out.println("시도할 회수는 몇회인가요?");
        int gameCount = Integer.parseInt(Console.readLine());

        userInput.setUserInputCarName(carName);
        userInput.setGameCount(gameCount);
    }

    //2) 사용자 입력에 대한 예외처리
    public void validateInput(String carName) throws IllegalArgumentException {
        String[] names = carName.split(",");
        //1) 각 이름 길이 5초과 시 예외
        validateLength(names);
        //2) 각 이름에 " " 공백 포함 시 예외
        validateBlank(names);
        //3) 이름 중복도 안됨
        validateDuplicate(names);
    }
}