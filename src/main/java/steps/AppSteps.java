//package steps;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import lombok.extern.slf4j.Slf4j;
//import org.openqa.selenium.Point;
//import org.openqa.selenium.WebElement;
//
//@Slf4j
//public class AppSteps {
//
//    private final AppiumBasePage appiumBasePage = new AppiumBasePage();
//
//    @Given("launch the app")
//    public void launchTheApp() {
//        log.info("launched app");
//    }
//
//    /**
//     * do swipe action on screen, for example if you want to swipe the screen to find more info below the current page,
//     * you can swipe up from bottom of the screen like this: swipe UP from
//     *
//     * @param direction UP|DOWN|LEFT|RIGHT
//     * @param point x,y on screen, both x and y are digit number, for example 100,200
//     * @param duration a digit number of pixels
//     */
//    @And("swipe {duration} pixels {direction} from {point} on the screen")
//    public void swipe(int duration, Direction direction, Point point) {
//        appiumBasePage.swipe(point, org.swlxm.utaf.utils.app.Direction.valueOf(direction.getDirection()), duration);
//    }
//
//    /**
//     * do swipe whole screen to specific direction by tapping specific position
//     * for example, if you want to tap on left of the screen and swipe up to find more info, you can call
//     * 'swipe UP on LEFT of screen'
//     *
//     * @param direction UP|DOWN|LEFT|RIGHT
//     * @param position LEFT|RIGHT|MID|TOP|BOTTOM
//     */
//    @And("tap {position} of the screen and swipe {direction}")
//    public void swipeScreen(Position position, Direction direction) {
//        appiumBasePage.swipeScreen(org.swlxm.utaf.utils.app.Direction.valueOf(direction.getDirection()), org.swlxm.utaf.utils.app.Position.valueOf(position.getPosition()));
//    }
//
//    /**
//     * do swipe action several times until the specific element is visible on screen
//     * @param direction UP|DOWN|LEFT|RIGHT
//     * @param times a digit number
//     * @param element Page_Name::Element_Name
//     */
//    @And("swipe {direction} {times} times until {element} is displayed")
//    public void swipeFindElement(Direction direction, int times, WebElement element) {
//        appiumBasePage.swipeToFindElement(element, org.swlxm.utaf.utils.app.Direction.valueOf(direction.getDirection()), times, 1);
//    }
//
//}
