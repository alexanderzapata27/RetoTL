import { browser, by, element, protractor } from 'protractor';

export class AppPage {
  navigateTo() {
    return browser.get(browser.baseUrl) as Promise<any>;
  }

  clickInDescriptionMenu(){
    element(by.id('description-menu')).click();
  }
  
  getTitleDescriptionText() {
    return element(by.className('person-list-title')).getText() as Promise<string>;
  }
  
  clickInOptionsMenu(){
    element(by.id('options-menu')).click();
  }
  
  clickInManagePeople(){
    element(by.id('manage-people-menu')).click();
  }
  
  clickInResultsMenu(){
    element(by.id('results-menu')).click();
  }
  
  addAPerson(){
    element(by.id('add-person')).click();
    element(by.id('identification')).sendKeys('4321');
    element(by.id('name')).sendKeys('PETER ALBERTO');
    element(by.id('lastname')).sendKeys('PARKER');
    element(by.id('dateOfBirth')).sendKeys('2');
    element(by.id('dateOfBirth')).sendKeys(protractor.Key.TAB);
    element(by.id('dateOfBirth')).sendKeys('2');
    element(by.id('dateOfBirth')).sendKeys(protractor.Key.TAB);
    element(by.id('dateOfBirth')).sendKeys('1990');
    element(by.id('save-button')).click();
  }
  
  goBackToPersonList(){
    element(by.id('back-button')).click();
  }
  
  getPeopleOfTableBodyFromPersonList(){
    let row = element.all(by.tagName('tr')).last();
    let cells = row.all(by.tagName('td'));
    let cellTexts = cells.map(function (elm) {
        return elm.getText();
    });

    return cellTexts;
  }
  
  deletePersonCreated(){
    element(by.id('deleteButton_4321')).click();
  }
}
