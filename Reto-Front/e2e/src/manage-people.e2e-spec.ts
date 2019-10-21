import { AppPage } from './app.po';
import { browser, logging, protractor } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
    var origFn = browser.driver.controlFlow().execute;
    browser.driver.controlFlow().execute = function () {
    var args = arguments;
    // queue 100ms wait
    origFn.call(browser.driver.controlFlow(), function () {
    return protractor.promise.delayed(200);   // here we can adjust the execution speed
    });
    return origFn.apply(browser.driver.controlFlow(), args);
    };
  });

  it('should display creation screen', () => {
    page.navigateTo();
    page.clickInOptionsMenu();
    page.clickInManagePeople();
    page.addAPerson();
    page.goBackToPersonList();
    expect(page.getPeopleOfTableBodyFromPersonList()).toEqual(["PETER ALBERTO", "PARKER", "1990-02-02","29","EditarEliminar"]);
  });

  afterEach(async () => {
    page.deletePersonCreated();
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    }));
  });
});
