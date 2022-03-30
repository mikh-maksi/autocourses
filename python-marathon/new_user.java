import java.time.*;

def registrationDate = {
    def today = LocalDateTime.now();
        
    return today;
};

//Переменные для жизней пользователя
commonActions.setUserVariable(user, 'pythonCurrentLiveCount', 3); //Сколько сейчас фактически жизней
commonActions.setUserVariable(user, 'pythonOldLiveCount', 3); //Сколько жизней было раньше

//Переменные для баллов пользователя
commonActions.setUserVariable(user, 'pythonCurrentScore', 30); //Сколько сейчас фактически баллов
commonActions.setUserVariable(user, 'pythonOldScore', 30); //Сколько баллов было раньше

//Переменная brandNewUser. Если true - значит пользователь новый
commonActions.setUserVariable(user, 'pythonBrandNewUser', true);

//Создаем переменную, которая определяет какой день пользователь уже прошел


commonActions.setUserVariable(user, 'regDay', registrationDate());




//Устанавливаем дефолтный язык русский
telegramUser.setLanguageCode('ru');
commonActions.saveTelegramUser(telegramUser);




//Отправляем стартовое сообщение
def message =
'''
А чтобы я мог сохранить ваш прогресс, нажми на кнопку *"Зарегистрироваться"*.
''';
commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false, botName: 'goiteens_edu_bot'], 'bottomKeyboard\nЗарегистрироваться=>${requestContact}');


// СБРОС СТАТИСТИКИ
//Очищаем данные пользователя о решенных задачах по Python

def taskIds = [
   'python-ta-string-01',
    'python-ta-string-02',
    'python-ta-string-03',
    'python-ta-string-04',
    'python-ta-string-05',
    'python-ta-string-06',
    'python-ta-string-07'
];

//Сбрасываем статистику по решенным задачам
commonActions.deleteUserTaskStat(user, taskIds);

//Сбрасываем статистику по видеоподсказкам
commonActions.deleteUserVariables(user, taskIds);

//Сброс накопленных переменных
commonActions.deleteUserVariable(user, 'openedPythonMarathonSite');
commonActions.deleteUserVariable(user, 'finishedPythonMarathon');
commonActions.deleteUserVariable(user, 'finishPythonMarathonDate');
commonActions.deleteUserVariable(user, 'userRequestedPythonBonus');

commonActions.deleteUserVariable(user, 'dayNumberDone');
commonActions.deleteUserVariable(user, 'dayNumberSend');

commonActions.setUserVariable(user, 'dayNumberDone', 0);
commonActions.setUserVariable(user, 'dayNumberSend', 1);


